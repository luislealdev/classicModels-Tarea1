package luisrrleal.com.classicmodelstarea;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.scene.control.Button;

import java.util.Objects;


public class HelloApplication extends Application {

    private BorderPane borderPane;

    @Override
    public void start(Stage primaryStage){

        primaryStage.setTitle("Classic Models");

        //CREATE THE NAVBAR
        //Create elements
        HBox navbar = new HBox();

        Text txt_app_name = new Text("ClassicModels");
        txt_app_name.getStyleClass().add("app-name");

        Button btn_products = new Button("Productos");
        Button btn_employees = new Button("Empleados");
        Button btn_clients = new Button("Clientes");
        Button btn_offices = new Button("Oficinas");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        spacer.setMinWidth(Region.USE_PREF_SIZE);

        HBox navbar_items = new HBox();
        navbar_items.getChildren().addAll(btn_products,btn_employees, btn_clients, btn_offices);

        navbar.getChildren().addAll(txt_app_name,spacer,navbar_items);

        //CREATE SECTIONS

        //Add styles to elements
        navbar.getStyleClass().add("navbar");

        btn_products.getStyleClass().setAll("navbar-button");
        btn_employees.getStyleClass().setAll("navbar-button");
        btn_clients.getStyleClass().setAll("navbar-button");
        btn_offices.getStyleClass().setAll("navbar-button");

        borderPane = new BorderPane();
        borderPane.setTop(navbar);

        borderPane.setCenter(products_section());

        //ADD ACTIONS TO BUTTONS
        btn_products.setOnAction(event -> borderPane.setCenter(products_section()));
        btn_offices.setOnAction(event -> switchPanes(offices_section()));
        btn_clients.setOnAction(event -> switchPanes(clients_section()));
        btn_employees.setOnAction(event -> switchPanes(employees_section()));

        Scene scene = new Scene(borderPane, 800, 500);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/index.css")).toString());


        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private Parent products_section(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("products-view.fxml"));
        return fxmlLoader.getRoot();
    }

    private Parent employees_section(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("employees-view.fxml"));
        return fxmlLoader.getRoot();
    }

    private Parent clients_section(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("clients-view.fxml"));
        return fxmlLoader.getRoot();
    }

    private Parent offices_section(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("offices-view.fxml"));
        return fxmlLoader.getRoot();
    }

    // Switch Layout Panes in Center of BorderPane
    public void switchPanes(Parent newView) {
        borderPane.setCenter(newView);
    }

    public static void main(String[] args) {
        launch();
    }
}