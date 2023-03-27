package com.Vue;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;

public class PresApp extends Application {



    @Override
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/resources/VideoPlayer/moviePres.fxml"));

        Scene scenes = new Scene(root, 500, 500, Color.BLACK);
        stage.setScene(scenes);
        stage.setTitle("Demo Pres Vidéo");
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.show();


    }



    public static void main(String[] args) {
        Application.launch();
    }
}