package com.Vue;

import com.Controller.AppController;
import com.Model.map.Movie;
import com.Vue.Carousel.CarouselController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeController extends Controller implements Initializable {

    @FXML
    private ScrollPane mainContainer;// = new ScrollPane();
    @FXML
    private AnchorPane ScrollableContainer;// = new AnchorPane();
    @FXML
    private VBox PlaylistContainer;// = new VBox();
    @FXML
    private Pane headerContainer;

    private AppController appController;

    private boolean isSearched;


    @Override
    public void initialize(URL location, ResourceBundle resources) {



    }

    public void setSearchedFlag(){
        this.isSearched = true;

    }


    public void removeSearchedPlaylist(){
        if(isSearched){
            PlaylistContainer.getChildren().remove(0);
        }
    }

    public void AddPlaylist(ArrayList<Movie> movies, String title, int pos) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/View/Carousel/Carousel.fxml"));
        VBox mvContainer = fxmlLoader.load();
        CarouselController controller = fxmlLoader.<CarouselController>getController();
        controller.setAppController(appController);
        controller.setTitle(title);
        controller.LoadMovies(movies);

        if( pos == -1){
            PlaylistContainer.getChildren().add(mvContainer);
        }
        else{

            PlaylistContainer.getChildren().add(pos, mvContainer);
        }


        System.out.println(ScrollableContainer.getPrefHeight());
        ScrollableContainer.setPrefHeight(ScrollableContainer.getPrefHeight() + mvContainer.getPrefHeight() + PlaylistContainer.getSpacing());
        // print all value for debug
        System.out.println("mvContainer.getPrefHeight() = " + mvContainer.getPrefHeight());
        System.out.println("PlaylistContainer.getSpacing() = " + PlaylistContainer.getSpacing());
        System.out.println("ScrollableContainer.getPrefHeight() = " + ScrollableContainer.getPrefHeight());

    }

    public void AddPromotion(Movie movie, int index) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/View/VideoPlayer/movieInfo.fxml"));
        VBox PromoContainer = fxmlLoader.load();
        MovieInfoController controller = fxmlLoader.getController();
        controller.setAppController(appController);
        controller.updateFromMovie(movie);


        PlaylistContainer.getChildren().add(index, PromoContainer);
        // update the height of the scroll pane to fit the new content
        ScrollableContainer.setPrefHeight(ScrollableContainer.getPrefHeight() + PromoContainer.getPrefHeight() + PlaylistContainer.getSpacing());

    }

    public void backToTop() {
        mainContainer.setVvalue(0);
    }
    public void setAppController(AppController appController) {
        this.appController = appController;
        try {
            isSearched = false;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/View/headerBar.fxml"));
            BorderPane mvContainer = fxmlLoader.load();
            HeaderBarController controller = fxmlLoader.getController();
            controller.setAppController(appController);
            controller.setHomeController(this);
            headerContainer.getChildren().add(mvContainer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getNbrPlayList() {
        return PlaylistContainer.getChildren().size();
    }
}
