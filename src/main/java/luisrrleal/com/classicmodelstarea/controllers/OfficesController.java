package luisrrleal.com.classicmodelstarea.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import luisrrleal.com.classicmodelstarea.Reports.OfficesReports;
import luisrrleal.com.classicmodelstarea.database.dao.OfficesDao;
import luisrrleal.com.classicmodelstarea.models.Office;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OfficesController implements Initializable {

    public static final String REPORTS_FOLDER = "pdfReports/";

    OfficesDao officesDao = new OfficesDao();
    @FXML
    TableView<Office> tbl_offices;
    @FXML
    Button btn_offices_list, btn_offices_employees_list;
    @FXML PieChart pieChart;

    public void setOffices(){
        tbl_offices.setItems(FXCollections.observableArrayList(officesDao.findAll()));
    }
    private void generate_offices_list_report(){
        String destination = REPORTS_FOLDER+"Employees-Report.pdf";
        try{
            File file = new File(destination);
            file.getParentFile().mkdirs();
            new OfficesReports().create_offices_list_Pdf(destination);
            showMessage("Report", "Report was generated successfully");
            openPDFFile(destination);
        }catch (IOException e){
        }
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
        btn_offices_list.setOnAction(event -> {
            generate_offices_list_report();
        });
        setOffices();

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Grapefruit", 13),
                        new PieChart.Data("Oranges", 25),
                        new PieChart.Data("Plums", 10),
                        new PieChart.Data("Pears", 22),
                        new PieChart.Data("Apples", 30));

        pieChart.setData(pieChartData);
    }
}
