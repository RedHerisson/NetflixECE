package com.Vue;

import com.Controller.AppController;
import com.Model.dao.MovieAccessor;
import com.Model.map.Movie;
import com.Vue.Carousel.CarouselController;
import com.Vue.Carousel.MovieIntegrationController;
import com.Vue.VideoPlayer.PlayerController;
import javafx.beans.InvalidationListener;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Rectangle;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MovieInfoController extends Controller implements Initializable {

    ///Attributs
    @FXML
    private Label title, synopsis, DirectorLabel, ActorsLabel ;

    @FXML
    private Button LaunchPlayerButton;

    @FXML
    private MediaView trailerIntegration;
    @FXML
    private AnchorPane TrailerContainer;

    @FXML
    private Label movieRating;
    @FXML
    private Label TypeLabel;

    private MediaPlayer player;
    private Media movie;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void updateFromMovie(Movie movie) throws IOException {

        //
        title.setText(movie.getTitle());
        synopsis.setText(movie.getSummary());
        DirectorLabel.setText(" " + movie.getDirector().getSurname() + " " + movie.getDirector().getName());
        String actors = "";
        for (int i = 0; i < movie.getActors().size(); i++) {
            actors += movie.getActors().get(i).getSurname() + " " + movie.getActors().get(i).getName() + ", ";
        }
        if(actors.length() > 2)
            actors = actors.substring(0, actors.length() - 2);
        ActorsLabel.setText(actors);

        File mediaFile;
        if( appController.isConnected() ) {
            mediaFile = new File("L:/" + movie.getTeaserPath() + ".mp4");
        } else {
            mediaFile = new File("demo.mp4");

        }
        Media movieFile = new Media(mediaFile.toURI().toString());
        player = new MediaPlayer(movieFile);
        trailerIntegration.setMediaPlayer(player);

        System.out.println("width : " + TrailerContainer.getWidth() + " height : " + TrailerContainer.getHeight());
        trailerIntegration.setFitWidth(TrailerContainer.getWidth());
        trailerIntegration.setFitHeight(TrailerContainer.getHeight());

        System.out.println("width : " + trailerIntegration.getFitWidth() + " height : " + trailerIntegration.getFitHeight());


        Bounds actualVideoSize = trailerIntegration.getLayoutBounds();
        trailerIntegration.setX(( (TrailerContainer.getWidth() - actualVideoSize.getWidth()) / 2) ) ;
        trailerIntegration.setY( ( (TrailerContainer.getHeight() - actualVideoSize.getHeight()) / 2) - 90);

        player.setVolume(0);
        player.play();
        player.setCycleCount(MediaPlayer.INDEFINITE);

        Rectangle clip = new Rectangle(
               TrailerContainer.getPrefWidth(), TrailerContainer.getPrefHeight()
        );
        TrailerContainer.setClip(clip);

        DecimalFormat df = new DecimalFormat("#.#");

        movieRating.setText(df.format(movie.getRating() )+ "/5");
        TypeLabel.setText(movie.getTypes());

        LaunchPlayerButton.onMouseClickedProperty().set(mouseEvent -> {
            Parent root = null;
            try {
                appController.loadPlayer(movie);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });




    }

    public void setMediaHeightClip(int height) {
        Rectangle clip = new Rectangle(
                TrailerContainer.getPrefWidth(), height
        );
        TrailerContainer.setClip(clip);
    }

    @Override
    public void setAppController(AppController appController) {
        this.appController = appController;
    }
}
