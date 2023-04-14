package com.Vue;

import com.Controller.AppController;
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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MoviePres  extends Controller implements  Initializable {

    @FXML
    private VBox mainContainer;
    @FXML
    private Pane suggestionContainer;
    private VBox MovieInfoContainer;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void loadMovie(Movie movie) throws IOException {

        FXMLLoader movieInfoPagingData = new FXMLLoader(getClass().getResource("/resources/View/VideoPlayer/movieInfo.fxml"));
        VBox MovieInfoContainer = movieInfoPagingData.load();
        MovieInfoController InfoController = movieInfoPagingData.getController();
        InfoController.updateFromMovie(movie);
        InfoController.setAppController(appController);
        InfoController.setMediaHeightClip(800); // TODO: change for responsive

        mainContainer.getChildren().add(0, MovieInfoContainer);

        ArrayList<Movie> movies = new ArrayList<Movie>();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/View/Carousel/Carousel.fxml"));
            VBox playlist = fxmlLoader.load();
            // change playlist margin
            playlist.setMargin(playlist, new javafx.geometry.Insets(30, 0, 0, 0));
            CarouselController controller = fxmlLoader.<CarouselController>getController();
            controller.setTitle("Dans le même genre");

            MovieAccessor movieAccessor = new MovieAccessor();
            movies.add( movieAccessor.findById( movie.getId() ) );
            controller.setAppController(appController);
            controller.LoadMovies(movies);
            suggestionContainer.getChildren().add(playlist);
            suggestionContainer.setPrefHeight(playlist.getPrefHeight() + 1000);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setAppController(AppController appController) {
        this.appController = appController;
    }
}
