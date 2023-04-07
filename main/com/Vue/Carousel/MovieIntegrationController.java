package com.Vue.Carousel;

import com.Model.map.Movie;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.*;
import javafx.scene.media.Media;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MovieIntegrationController implements Initializable {

    @FXML
    private ImageView posterContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    @FXML
    public void LoadPoster(Movie movie) {

        Image poster = SwingFXUtils.toFXImage(movie.getThumbnail(), null);
        posterContainer.setImage(poster);

    }



}
