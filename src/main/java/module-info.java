module VideoPlayer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    exports VideoPlayer.Controller;
    opens VideoPlayer.Controller to javafx.fxml;
    exports VideoPlayer.Vue;
    opens VideoPlayer.Vue to javafx.fxml;
}