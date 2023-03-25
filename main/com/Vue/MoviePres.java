package com.Vue;

import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MoviePres implements Initializable {

    @FXML
    private MediaView trailerIntegration;

    @FXML
    private AnchorPane TrailerConteneur;

    private MediaPlayer player;
    private Media movie;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File file = new File("star.mp4");
        movie = new Media(file.toURI().toString());
        player = new MediaPlayer(movie);
        trailerIntegration.setMediaPlayer(player);


        InvalidationListener resizeMediaView = observable -> {
            trailerIntegration.setFitWidth(TrailerConteneur.getWidth());
            trailerIntegration.setFitHeight(TrailerConteneur.getHeight());

            // After setting a big fit width and height, the layout bounds match the video size. Not sure why and this feels fragile.
            Bounds actualVideoSize = trailerIntegration.getLayoutBounds();
            trailerIntegration.setX((TrailerConteneur.getWidth() - actualVideoSize.getWidth()) / 2);
            trailerIntegration.setY((TrailerConteneur.getHeight() - actualVideoSize.getHeight()) / 2);
        };
        TrailerConteneur.heightProperty().addListener(resizeMediaView);
        TrailerConteneur.widthProperty().addListener(resizeMediaView);
        player.setVolume(0);
        player.play();
        player.setCycleCount(MediaPlayer.INDEFINITE);
    }
}
