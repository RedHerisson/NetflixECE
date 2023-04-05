package com.Vue;

import com.Model.map.Movie;
import javafx.beans.InvalidationListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MoviePres implements Initializable {

    @FXML
    private Label title, synopsis, DirectorLabel, ActorsLabel ;

    @FXML
    private Button LaunchPlayerButton;
    
    @FXML
    private StackPane MainContainer;
    @FXML
    private MediaView trailerIntegration;
    @FXML
    private MediaView trailerBlur;


    @FXML
    private AnchorPane TrailerConteneur;

    @FXML
    private VBox InformationConteneur;

    private MediaPlayer player;
    private Media movie;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void updateFromMovie(Movie movie) {
        title.setText(movie.getTitle());
        synopsis.setText(movie.getSummary());
        DirectorLabel.setText(" " + movie.getDirector().getSurname() + " " + movie.getDirector().getName());
        String actors = "";
        for (int i = 0; i < movie.getActors().size(); i++) {
            actors += movie.getActors().get(i).getSurname() + " " + movie.getActors().get(i).getName() + ", ";
        }
        actors = actors.substring(0, actors.length() - 2);
        ActorsLabel.setText(actors);


        File file = new File(movie.getTeaserPath());
        Media movieFile = new Media(file.toURI().toString());
        player = new MediaPlayer(movieFile);
        trailerIntegration.setMediaPlayer(player);
        trailerBlur.setMediaPlayer(player);


        trailerBlur.setEffect(new BoxBlur(100, 100, 3));


        InvalidationListener resizeTrailer = observable -> {
            trailerIntegration.setFitWidth(TrailerConteneur.getWidth());
            trailerIntegration.setFitHeight(TrailerConteneur.getHeight());


            Bounds actualVideoSize = trailerIntegration.getLayoutBounds();
            trailerIntegration.setX((TrailerConteneur.getWidth() - actualVideoSize.getWidth()) / 2);
            trailerIntegration.setY((TrailerConteneur.getHeight() - actualVideoSize.getHeight()) / 2);

        };

        InvalidationListener resizeBlur = observable -> {
            trailerIntegration.setFitWidth(MainContainer.getWidth());
            trailerIntegration.setFitHeight(MainContainer.getHeight());


            Bounds actualVideoSize = trailerIntegration.getLayoutBounds();
            trailerIntegration.setX((MainContainer.getWidth() - actualVideoSize.getWidth()) / 2);
            trailerIntegration.setY((MainContainer.getHeight() - actualVideoSize.getHeight()) / 2);
        };

        TrailerConteneur.heightProperty().addListener(resizeTrailer);
        TrailerConteneur.widthProperty().addListener(resizeTrailer);


        MainContainer.heightProperty().addListener(resizeBlur);
        MainContainer.widthProperty().addListener(resizeBlur);

        player.setVolume(0);
        player.play();
        player.setCycleCount(MediaPlayer.INDEFINITE);

        LaunchPlayerButton.onMouseClickedProperty().set(mouseEvent -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/ressources/VideoPlayer/player.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            LaunchPlayerButton.getScene().setRoot(root);
        });



    }
}
