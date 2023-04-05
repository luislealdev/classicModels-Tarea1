module luisrrleal.com.classicmodelstarea1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens luisrrleal.com.classicmodelstarea1 to javafx.fxml;
    exports luisrrleal.com.classicmodelstarea1;
}