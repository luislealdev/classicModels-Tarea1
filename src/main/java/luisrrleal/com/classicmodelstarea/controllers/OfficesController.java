package luisrrleal.com.classicmodelstarea.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import luisrrleal.com.classicmodelstarea.database.dao.OfficesDao;
import luisrrleal.com.classicmodelstarea.models.Office;

import java.net.URL;
import java.util.ResourceBundle;

public class OfficesController implements Initializable {

    OfficesDao officesDao = new OfficesDao();
    @FXML
    TableView<Office> tbl_offices;

    public void setOffices(){
        tbl_offices.setItems(FXCollections.observableArrayList(officesDao.findAll()));
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setOffices();
    }
}
