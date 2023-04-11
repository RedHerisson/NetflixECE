package com.Vue;

import com.Model.dao.MovieAccessor;
import com.Model.map.Movie;
import com.Vue.Carousel.CarouselController;
import com.Vue.Carousel.MovieIntegrationController;
import javafx.beans.InvalidationListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MovieInfoController extends Controller implements Initializable {

    @FXML
    private Label title, synopsis, DirectorLabel, ActorsLabel ;

    @FXML
    private Button LaunchPlayerButton;

    @FXML
    private MediaView trailerIntegration;


    @FXML
    private AnchorPane TrailerContainer;

    @FXML
    private VBox InformationContainer;

    private MediaPlayer player;
    private Media movie;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void updateFromMovie(Movie movie) throws IOException {

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

        trailerIntegration.setFitWidth(TrailerContainer.getWidth());
        trailerIntegration.setFitHeight(TrailerContainer.getHeight());


        Bounds actualVideoSize = trailerIntegration.getLayoutBounds();
        trailerIntegration.setX(( (TrailerContainer.getWidth() - actualVideoSize.getWidth()) / 2) ) ;
        trailerIntegration.setY( ( (TrailerContainer.getHeight() - actualVideoSize.getHeight()) / 2) - 130 );

        player.setVolume(0);
        player.play();
        player.setCycleCount(MediaPlayer.INDEFINITE);

        Rectangle clip = new Rectangle(
               TrailerContainer.getPrefWidth(), TrailerContainer.getPrefHeight()
        );
        TrailerContainer.setClip(clip);



        LaunchPlayerButton.onMouseClickedProperty().set(mouseEvent -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/resources/View/VideoPlayer/player.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            LaunchPlayerButton.getScene().setRoot(root);
        });
    }

    public void setMediaHeightClip(int height) {
        Rectangle clip = new Rectangle(
                TrailerContainer.getPrefWidth(), height
        );
        TrailerContainer.setClip(clip);
    }
}
