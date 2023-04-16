package com.Vue.Carousel;

import com.Controller.AppController;
import com.Model.dao.MovieAccessor;
import com.Model.map.Movie;
import com.Vue.Controller;
import com.Vue.MoviePres;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MovieIntegrationController extends Controller implements Initializable {

    public ImageView playIcon;
    public Pane darkLayer;
    @FXML
    private ImageView posterContainer;

    private static final int WIDTH = 135;
    private static final int HEIGHT = 202;

    private static final int SELECTED_WIDTH = 165;
    private static final int SELECTED_HEIGH = 247;

    private Movie movie;
    private MovieAccessor posterGetter;

    /**
     * initialisation de la page des films du carousel
     * @param url
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param resourceBundle
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        playIcon.setVisible(false);
        darkLayer.opacityProperty().setValue(0);
        movie = null;
        try {
            posterGetter = new MovieAccessor();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        posterContainer.setFitWidth(WIDTH);
        posterContainer.setFitHeight(HEIGHT);

        darkLayer.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue) {
                onMouseEnter();
            }
            else {
                if (movie != null) {
                    onMouseExited();
                }

            }
        });
    }


    private void onMouseEnter() {
        if( movie != null) {
            playIcon.setVisible(true);
            darkLayer.opacityProperty().setValue(0.4);
            posterContainer.setFitWidth(SELECTED_WIDTH);
            posterContainer.setFitHeight(SELECTED_HEIGH);
        }
    }
    @FXML
    private void onMouseExited() {
        if( movie != null) {
            playIcon.setVisible(false);
            darkLayer.opacityProperty().setValue(0);
            posterContainer.setFitWidth(WIDTH);
            posterContainer.setFitHeight(HEIGHT);
        }
    }

    /**
     * permet d'afficher les poster des films
     * @param bPoster
     */
    @FXML
    private void LoadPoster(BufferedImage bPoster) {

        if (bPoster != null) {
            Image poster = SwingFXUtils.toFXImage(bPoster, null);
            posterContainer.setImage(poster);
        }
        else {
            posterContainer.setImage(null);
        }
    }

    public void setMovie(Movie movie) throws SQLException, IOException {

        this.movie = movie;
        if( movie != null) {
             LoadPoster( posterGetter.loadPoster(movie.getId()));
        }
        else {
            LoadPoster(null);
        }

    }

    /**
     * une fois que l'on clique sur un film, sa page perso s'affiche
     */
    @FXML
    private void onMouseClicked() {

        try {
            if (movie != null) {
                appController.setMoviePresPage(movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
