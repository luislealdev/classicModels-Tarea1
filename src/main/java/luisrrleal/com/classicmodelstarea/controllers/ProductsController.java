package luisrrleal.com.classicmodelstarea.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import luisrrleal.com.classicmodelstarea.database.dao.ProductsDao;
import luisrrleal.com.classicmodelstarea.models.Product;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductsController implements Initializable {
    ProductsDao productsDao = new ProductsDao();
    @FXML
    TableView<Product> tbl_Products;

    public void setProducts(){
        tbl_Products.setItems(FXCollections.observableArrayList(productsDao.findAll()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setProducts();
    }
}
