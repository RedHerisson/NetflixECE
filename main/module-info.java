module netflix {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.swing;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;

    exports com.Vue;
    opens com.Vue to javafx.fxml;
    exports com.Vue.VideoPlayer;
    opens com.Vue.VideoPlayer to javafx.fxml;
    exports com.Vue.Carousel;
    opens com.Vue.Carousel to javafx.fxml;
}
