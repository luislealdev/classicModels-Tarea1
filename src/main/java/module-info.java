module luisrrleal.com.classicmodelstarea {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens luisrrleal.com.classicmodelstarea to javafx.fxml;
    exports luisrrleal.com.classicmodelstarea;
}