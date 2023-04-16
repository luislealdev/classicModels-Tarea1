package luisrrleal.com.classicmodelstarea;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.util.Objects;
public class HelloApplication extends Application {
    private BorderPane borderPane;
    @Override
    public void start(Stage primaryStage) throws IOException {

        primaryStage.setTitle("Classic Models");

        //CREATE THE NAVBAR
        //Create elements
        HBox navbar = new HBox();

        Text txt_app_name = new Text("ClassicModels");
        txt_app_name.getStyleClass().add("app-name");

        Button btn_products = new Button("Products");
        Button btn_employees = new Button("Employees");
        Button btn_clients = new Button("Clients");
        Button btn_offices = new Button("Offices");

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

        //Add icons to buttons
        FontIcon icon_products = new FontIcon();
        icon_products.setIconLiteral("fas-boxes");
        icon_products.setIconColor(Paint.valueOf("white"));
        btn_products.setGraphic(icon_products);

        FontIcon icon_employees = new FontIcon();
        icon_employees.setIconLiteral("fas-people-carry");
        icon_employees.setIconColor(Paint.valueOf("white"));
        btn_employees.setGraphic(icon_employees);

        FontIcon icon_clients = new FontIcon();
        icon_clients.setIconLiteral("fas-users");
        icon_clients.setIconColor(Paint.valueOf("white"));
        btn_clients.setGraphic(icon_clients);

        FontIcon icon_offices = new FontIcon();
        icon_offices.setIconLiteral("fas-store-alt");
        icon_offices.setIconColor(Paint.valueOf("white"));
        btn_offices.setGraphic(icon_offices);

        borderPane = new BorderPane();
        borderPane.setTop(navbar);

        borderPane.setCenter(products_section());

        //ADD ACTIONS TO BUTTONS
        btn_products.setOnAction(event -> {
            try {
                switchPanes(products_section());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        btn_offices.setOnAction(event -> {
            try {
                switchPanes(offices_section());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        btn_clients.setOnAction(event -> {
            try {
                switchPanes(clients_section());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        btn_employees.setOnAction(event -> {
            try {
                switchPanes(employees_section());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        Scene scene = new Scene(borderPane, 1200, 700);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/index.css")).toString());
        scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private Parent products_section() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("products-view.fxml"));
        return fxmlLoader.load();
    }

    private Parent employees_section() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("employees-view.fxml"));
        return fxmlLoader.load();
    }

    private Parent clients_section() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("clients-view.fxml"));
        return fxmlLoader.load();
    }

    private Parent offices_section() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("offices-view.fxml"));
        return fxmlLoader.load();
    }

    // Switch Layout Panes in Center of BorderPane
    public void switchPanes(Parent newView) {
        borderPane.setCenter(newView);
    }

    public static void main(String[] args) {
        launch();
    }
}