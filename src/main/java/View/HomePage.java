package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.image.Image;


import java.io.FileInputStream;

public class HomePage extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("homePage.fxml"));
        //Stage stage = new Stage();
        Group root = new Group();
        Scene scene = new Scene(root, Color.BLACK);
        //Scene scene = new Scene(fxmlLoader.load());
        Image icon = new Image(new FileInputStream("src\\main\\resources\\Image\\ECE_LOGO.png"));
        stage.getIcons().add(icon);
        //stage.setScene(scene);
        stage.setTitle("homePage");
        stage.setFullScreen(true);

        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


}

