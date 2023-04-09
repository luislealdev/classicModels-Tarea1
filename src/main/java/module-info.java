module luisrrleal.com.classicmodelstarea {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens luisrrleal.com.classicmodelstarea to javafx.fxml;
    exports luisrrleal.com.classicmodelstarea;
    exports luisrrleal.com.classicmodelstarea.controllers;
    opens luisrrleal.com.classicmodelstarea.controllers to javafx.fxml;

    exports luisrrleal.com.classicmodelstarea.models;
    opens luisrrleal.com.classicmodelstarea.models to javafx.fxml;
    exports luisrrleal.com.classicmodelstarea.database;
    opens luisrrleal.com.classicmodelstarea.database to javafx.fxml;


}