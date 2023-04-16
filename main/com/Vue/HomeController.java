package com.Vue;

import com.Controller.AppController;
import com.Model.map.Movie;
import com.Vue.Carousel.CarouselController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeController extends Controller implements Initializable {

    @FXML
    private ScrollPane mainContainer;
    @FXML
    private AnchorPane ScrollableContainer;
    @FXML
    private VBox PlaylistContainer;

    private AppController appController;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void AddPlaylist(ArrayList<Movie> movies, String title) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/View/Carousel/Carousel.fxml"));
        VBox mvContainer = fxmlLoader.load();
        CarouselController controller = fxmlLoader.<CarouselController>getController();
        controller.setAppController(appController);
        controller.setTitle(title);
        controller.LoadMovies(movies);



        PlaylistContainer.getChildren().add(mvContainer);
        System.out.println(ScrollableContainer.getPrefHeight());
        ScrollableContainer.setPrefHeight(ScrollableContainer.getPrefHeight() + mvContainer.getPrefHeight() + PlaylistContainer.getSpacing());
        // print all value for debug
        System.out.println("mvContainer.getPrefHeight() = " + mvContainer.getPrefHeight());
        System.out.println("PlaylistContainer.getSpacing() = " + PlaylistContainer.getSpacing());
        System.out.println("ScrollableContainer.getPrefHeight() = " + ScrollableContainer.getPrefHeight());
        
    }

    public void AddPromotion(Movie movie) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/View/VideoPlayer/movieInfo.fxml"));
        VBox PromoContainer = fxmlLoader.load();
        MovieInfoController controller = fxmlLoader.getController();
        controller.setAppController(appController);
        controller.updateFromMovie(movie);


        PlaylistContainer.getChildren().add(PromoContainer);
        // update the height of the scroll pane to fit the new content
        ScrollableContainer.setPrefHeight(ScrollableContainer.getPrefHeight() + PromoContainer.getPrefHeight() + PlaylistContainer.getSpacing());

    }

    public void backToTop() {
        mainContainer.setVvalue(0);
    }
    public void setAppController(AppController appController) {
        this.appController = appController;
    }
}
