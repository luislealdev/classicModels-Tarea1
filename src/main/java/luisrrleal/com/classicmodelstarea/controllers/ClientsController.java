package luisrrleal.com.classicmodelstarea.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import luisrrleal.com.classicmodelstarea.Reports.ClientsReports;
import luisrrleal.com.classicmodelstarea.Reports.OfficesReports;
import luisrrleal.com.classicmodelstarea.database.dao.ClientsDao;
import luisrrleal.com.classicmodelstarea.database.dao.OfficesDao;
import luisrrleal.com.classicmodelstarea.models.Client;
import luisrrleal.com.classicmodelstarea.models.Product;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientsController implements Initializable {
    public static final String REPORTS_FOLDER = "pdfReports/";
    ClientsDao clientsDao = new ClientsDao();
    ClientsReports clientsReports = new ClientsReports();
    @FXML TableView<Client> tbl_clients;
    @FXML BarChart barChart;
    @FXML
    Button btn_best_clients_list;

    public void setClients(){tbl_clients.setItems(FXCollections.observableArrayList(clientsDao.findAll()));}

    private void generate_best_client_list_report() throws IOException {
        String destination = REPORTS_FOLDER+"Best_clients_report.pdf";
        File file = new File(destination);
        file.getParentFile().mkdirs();
        new ClientsReports().generate_best_client_list_pdf(destination);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setClients();
        clientsDao.findTwentyBestClients();
        btn_best_clients_list.setOnAction(event -> {
            try {
                generate_best_client_list_report();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
