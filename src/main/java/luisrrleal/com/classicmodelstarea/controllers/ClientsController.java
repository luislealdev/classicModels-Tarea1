package luisrrleal.com.classicmodelstarea.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import luisrrleal.com.classicmodelstarea.Reports.ClientsReports;
import luisrrleal.com.classicmodelstarea.database.dao.ClientsDao;
import luisrrleal.com.classicmodelstarea.models.Client;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ClientsController implements Initializable {
    public static final String REPORTS_FOLDER = "pdfReports/";
    boolean is_adding_new=false;
    ClientsDao clientsDao = new ClientsDao();
    ClientsReports clientsReports = new ClientsReports();
    @FXML TableView<Client> tbl_clients;
    @FXML BarChart barChart;
    @FXML
    Button btn_best_clients_list, btn_add_client, btn_report_orders_selected_client, btn_save, btn_delete;
    @FXML
    GridPane gp_edit_section;
    @FXML
    TextField tf_customer_number,  tf_customer_name, tf_contact_last_name, tf_contact_first_name, tf_phone, tf_address_line_1, tf_address_line_2, tf_city, tf_state, tf_postal_code, tf_country, tf_sales_rep_employee_number, tf_credit_limit;

    public void setClients(){tbl_clients.setItems(FXCollections.observableArrayList(clientsDao.findAll()));}

    private void generate_best_client_list_report() throws IOException {
        String destination = REPORTS_FOLDER+"Best_clients_report.pdf";
        File file = new File(destination);
        file.getParentFile().mkdirs();
        new ClientsReports().generate_best_client_list_pdf(destination);
        showMessage("Report", "Report was generated successfully");
        openPDFFile(destination);
    }

    private void generate_client_orders_report() throws IOException{
        String destination = REPORTS_FOLDER+tf_customer_name.getText()+"-orders.pdf";
        File file = new File(destination);
        file.getParentFile().mkdirs();
        new ClientsReports().generate_client_orders_report_pdf(destination, tf_customer_name.getText());
        showMessage("Report", "Report was generated successfully");
        openPDFFile(destination);
    }

    private void showMessage(String title, String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }

    private void openPDFFile(String route) {
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File(route);
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
// no application registered for PDFs
            }
        }
    }

    public void add_select_method(){
        tbl_clients.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            gp_edit_section.setVisible(true);
            is_adding_new = false;
            Client selected_client = tbl_clients.getSelectionModel().getSelectedItem();

            //Set info to textfields
            tf_customer_number.setText(Integer.toString(selected_client.getCustomer_number()));
            tf_customer_name.setText(selected_client.getCustomer_name());
            tf_contact_last_name.setText(selected_client.getContact_last_name());
            tf_contact_first_name.setText(selected_client.getContact_first_name());
            tf_phone.setText(selected_client.getPhone());
            tf_address_line_1.setText(selected_client.getAddress_line_1());
            tf_address_line_2.setText(selected_client.getAddress_line_2());
            tf_city.setText(selected_client.getCity());
            tf_state.setText(selected_client.getState());
            tf_postal_code.setText(selected_client.getPostal_code());
            tf_country.setText(selected_client.getCountry());
            tf_sales_rep_employee_number.setText(Integer.toString(selected_client.getSales_rep_employee_number()));
            tf_credit_limit.setText(Double.toString(selected_client.getCredit_limit()));
        });
    }

    private void clear_gp_edit_section(){
        tf_customer_number.setText("");
        tf_customer_name.setText("");
        tf_contact_last_name.setText("");
        tf_contact_first_name.setText("");
        tf_phone.setText("");
        tf_address_line_1.setText("");
        tf_address_line_2.setText("");
        tf_city.setText("");
        tf_state.setText("");
        tf_postal_code.setText("");
        tf_country.setText("");
        tf_sales_rep_employee_number.setText("");
        tf_credit_limit.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gp_edit_section.setVisible(false);
        add_select_method();
        setClients();
        clientsDao.findTwentyBestClients();
        btn_best_clients_list.setOnAction(event -> {
            try {
                generate_best_client_list_report();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        btn_add_client.setOnAction(event -> {
            gp_edit_section.setVisible(true);
            is_adding_new = true;
            clear_gp_edit_section();
        });
        btn_report_orders_selected_client.setOnAction(event -> {
            try {
                generate_client_orders_report();
            }catch (IOException e){

            }
        });
        btn_save.setOnAction(event -> {
            try {
                clientsDao.save(tf_customer_number.getText(), tf_customer_name.getText(),tf_contact_last_name.getText(), tf_contact_first_name.getText(), tf_phone.getText(), tf_address_line_1.getText(),tf_address_line_2.getText(),tf_city.getText(), tf_state.getText(), tf_postal_code.getText(), tf_country.getText(), tf_sales_rep_employee_number.getText(), tf_credit_limit.getText());
                setClients();
                showMessage("New client", "Client added correctly");
                clear_gp_edit_section();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        btn_delete.setOnAction(event -> {
            try {
                clientsDao.delete(tf_customer_number.getText());
                setClients();
                showMessage("Removed", "Client removed correctly");
                clear_gp_edit_section();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
