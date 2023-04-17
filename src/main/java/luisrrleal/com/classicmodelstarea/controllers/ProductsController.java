package luisrrleal.com.classicmodelstarea.controllers;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import luisrrleal.com.classicmodelstarea.database.dao.ProductsDao;
import luisrrleal.com.classicmodelstarea.models.Product;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProductsController implements Initializable {
    ProductsDao productsDao = new ProductsDao();
    Product selected_product;
    boolean is_adding_new = false;
    @FXML TableView<Product> tbl_Products;
    @FXML VBox edit_section;
    @FXML TextField tf_product_code, tf_product_name, tf_product_line,tf_product_scale,tf_product_vendor,tf_product_quantity, tf_product_price, tf_product_msrp;
    @FXML TextArea ta_product_description;
    @FXML Button btn_add_new, btn_save, btn_delete;

    public void setProducts(){
        tbl_Products.setItems(FXCollections.observableArrayList(productsDao.findAll()));
    }
    public void add_select_method(){
        tbl_Products.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            edit_section.setVisible(true);
            is_adding_new = false;
            selected_product = tbl_Products.getSelectionModel().getSelectedItem();

            //Set info to textfields
            tf_product_code.setText(selected_product.getProduct_code());
            tf_product_name.setText(selected_product.getProduct_name());
            tf_product_line.setText(selected_product.getProduct_line());
            tf_product_scale.setText(selected_product.getProduct_scale());
            tf_product_vendor.setText(selected_product.getProduct_vendor());
            tf_product_quantity.setText(Integer.toString(selected_product.getQuantity_in_stock()));
            tf_product_price.setText(Double.toString(selected_product.getBuy_price()));
            tf_product_msrp.setText(Double.toString(selected_product.getMsrp()));
            ta_product_description.setText(selected_product.getProduct_description());
        });
    }
    public void set_add_new_action(){
        btn_add_new.setOnAction(event->{
            edit_section.setVisible(true);
            is_adding_new=true;
            clear_table_view();
        });
    }

    public void add_save_action(){
        btn_save.setOnAction(event -> {
                if(is_adding_new){
                    try {
                        productsDao.save(tf_product_code.getText(), tf_product_name.getText(), tf_product_line.getText(), tf_product_scale.getText(), tf_product_vendor.getText(),ta_product_description.getText(),tf_product_quantity.getText(),tf_product_price.getText(),tf_product_msrp.getText());
                        tbl_Products.getItems().add(new Product(tf_product_code.getText(), tf_product_name.getText(), tf_product_line.getText(), tf_product_scale.getText(), tf_product_vendor.getText(),ta_product_description.getText(),Integer.parseInt(tf_product_quantity.getText()),Double.parseDouble(tf_product_price.getText()),Double.parseDouble(tf_product_msrp.getText())));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    showMessage("New product","Product added correctly");
                    setProducts();
                }
                else{
                    try {
                        productsDao.update(tf_product_code.getText(), tf_product_name.getText(), tf_product_line.getText(), tf_product_scale.getText(), tf_product_vendor.getText(),ta_product_description.getText(),tf_product_quantity.getText(),tf_product_price.getText(),tf_product_msrp.getText());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    showMessage("Updated","Product updated correctly");
                    setProducts();
                }
        });
    }

    private void showMessage(String title, String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }

    private void clear_table_view(){
        tf_product_code.setText("");
        tf_product_name.setText("");
        tf_product_line.setText("");
        tf_product_scale.setText("");
        tf_product_vendor.setText("");
        tf_product_quantity.setText("");
        tf_product_price.setText("");
        tf_product_msrp.setText("");
        ta_product_description.setText("");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setProducts();
        add_select_method();
        set_add_new_action();
        add_save_action();
        btn_delete.setOnAction(event -> {
            try {
                productsDao.delete(tf_product_code.getText());
                setProducts();
                showMessage("Removed", "Product removed correctly");
                clear_table_view();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
