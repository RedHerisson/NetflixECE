module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    requires java.sql;
    //requires mysql.connector.java;

    opens View to javafx.fxml;
    exports View;



}