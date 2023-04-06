package luisrrleal.com.classicmodelstarea;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    /*@Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("CLASSIC MODELS");
        stage.setScene(scene);
        stage.show();
    }*/

    private Stage stage;
    private Scene scene;
    private BorderPane borderPane;

    private VBox vbox1;
    private Button button1;

    private VBox vbox2;
    private Button button2;
    @Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;
        stage.setTitle("Classic Models");

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
        Parent products_section = products_section();

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

        /*
        borderPane.setLeft(new VBox(new Button("Left")));
        borderPane.setRight(new VBox(new Button("Right")));
        borderPane.setBottom(new VBox(new Button("Bottom")));
         */



        scene = new Scene(borderPane, 800, 500);
        scene.getStylesheets().add(getClass().getResource("/styles/index.css").toString());


        stage.setScene(scene);

        stage.show();
    }

    private Parent products_section(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("products-view.fxml"));
        Parent root = fxmlLoader.getRoot();
        return root;
    }

    private Parent employees_section(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("employees-view.fxml"));
        Parent root = fxmlLoader.getRoot();
        return root;
    }

    private Parent clients_section(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("clients-view.fxml"));
        Parent root = fxmlLoader.getRoot();
        return root;
    }

    private Parent offices_section(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("offices-view.fxml"));
        Parent root = fxmlLoader.getRoot();
        return root;
    }

    // Switch Layout Panes in Center of BorderPane
    public void switchPanes(Parent newView) {
        borderPane.setCenter(newView);
    }

    public static void main(String[] args) {
        launch();
    }
}