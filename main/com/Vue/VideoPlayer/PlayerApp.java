package com.Vue.VideoPlayer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class PlayerApp extends Application {



    @Override
    public void start(Stage stage) throws IOException {

            Parent root = FXMLLoader.load(getClass().getResource("/ressources/VideoPlayer/player.fxml"));

            Scene scenes = new Scene(root, 500, 500, Color.BLACK);
            stage.setScene(scenes);
            stage.setTitle("Demo Player Vidéo");
            stage.setFullScreen(true);
            stage.show();


    }



    public static void main(String[] args) {
        Application.launch();
    }
}