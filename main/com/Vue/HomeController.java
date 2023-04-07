package com.Vue;

import com.Model.map.Movie;
import com.Vue.Carousel.CarouselController;
import com.Vue.Carousel.MovieIntegrationController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private VBox PlaylistContainer;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void AddPlaylist(ArrayList<Movie> movies, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/View/Carousel/Carousel.fxml"));
        VBox mvContainer = fxmlLoader.load();
        CarouselController controller = fxmlLoader.<CarouselController>getController();
        controller.setTitle(title);
        controller.LoadMovies(movies);


        PlaylistContainer.getChildren().add(mvContainer);
        PlaylistContainer.setPrefHeight(PlaylistContainer.getPrefHeight() + 220 + PlaylistContainer.getSpacing());
    }
}
