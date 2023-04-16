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
    private ScrollPane mainContainer;
    @FXML
    private AnchorPane ScrollableContainer;
    @FXML
    private VBox PlaylistContainer;
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
        //Ouvre le fichier fxml du carousel
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/View/Carousel/Carousel.fxml"));
        //On la rajoute a la VBox
        VBox mvContainer = fxmlLoader.load();
        CarouselController controller = fxmlLoader.<CarouselController>getController();
        //On load les films dans le carousel
        controller.setAppController(appController);
        controller.setTitle(title);
        controller.LoadMovies(movies);

        //On positionne le carousel a la position voulu
        if( pos == -1){
            PlaylistContainer.getChildren().add(mvContainer);
        }
        else{

            PlaylistContainer.getChildren().add(pos, mvContainer);
        }


        System.out.println(ScrollableContainer.getPrefHeight());
        ScrollableContainer.setPrefHeight(ScrollableContainer.getPrefHeight() + mvContainer.getPrefHeight() + PlaylistContainer.getSpacing());
        // print les values pour debug
        System.out.println("mvContainer.getPrefHeight() = " + mvContainer.getPrefHeight());
        System.out.println("PlaylistContainer.getSpacing() = " + PlaylistContainer.getSpacing());
        System.out.println("ScrollableContainer.getPrefHeight() = " + ScrollableContainer.getPrefHeight());

    }

    public void AddPromotion(Movie movie, int index) throws IOException, SQLException {
        //Ouvre le fichier fxml du carousel
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/View/VideoPlayer/movieInfo.fxml"));
        //On la rajoute a la VBox
        VBox PromoContainer = fxmlLoader.load();
        MovieInfoController controller = fxmlLoader.getController();
        controller.setAppController(appController);
        controller.updateFromMovie(movie);

        //On positionne le carousel a la position voulu
        PlaylistContainer.getChildren().add(index, PromoContainer);
        // update the height of the scroll pane to fit the new content
        ScrollableContainer.setPrefHeight(ScrollableContainer.getPrefHeight() + PromoContainer.getPrefHeight() + PlaylistContainer.getSpacing());

    }
    //Fonction qui permet de revenir en haut de la page
    public void backToTop() {
        mainContainer.setVvalue(0);
    }

    //Fonction qui permet de mettre a jour le controller de la page
    public void setAppController(AppController appController) {
        this.appController = appController;
        try {
            //On initialise le flag a false pour dire que la recherche a été faite ou non
            isSearched = false;
            //On charge le header
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
