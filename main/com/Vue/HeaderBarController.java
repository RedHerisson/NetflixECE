package com.Vue;

import com.Model.dao.MovieAccessor;
import com.Model.map.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HeaderBarController extends Controller implements Initializable {

    @FXML
    private TextField searchBar;

    private HomeController homeController;
    private MovieAccessor movieAccessor = new MovieAccessor();
    private ArrayList<Movie> moviesList = new ArrayList<>(10);
    @FXML
    private VBox searchSuggestionContainer;
    private ArrayList<String> suggestionsList = new ArrayList<>(5);
    private String querrySearched;

    public HeaderBarController() throws SQLException, ClassNotFoundException {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //searchBar.setStyle("-fx-text-fill: #ffffff;");
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.length() > 0){
                try {
                    suggestionsList = movieAccessor.getTitleByQuerrt(newValue);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                System.out.println(suggestionsList.get(0));
                System.out.println(suggestionsList.get(1));
                System.out.println(suggestionsList.get(2));
                System.out.println(suggestionsList.get(3));
                System.out.println(suggestionsList.get(4));
                updateSuggestions(suggestionsList);
            }else{
                searchSuggestionContainer.getChildren().clear();
                searchSuggestionContainer.setPrefHeight(0);
                searchSuggestionContainer.setPadding(new Insets(0, 0, 0, 0));
            }
        });
    }

    @FXML
    public void HandleSearch(ActionEvent event) throws Exception {
        //On capture ce qui a été pris dans le textField
        querrySearched = searchBar.getText();
        //On récupère les films correspondant à la recherche
        moviesList = movieAccessor.search(querrySearched);
        System.out.println(querrySearched);
        //On affiche les films correspondant à la recherche
        for(int i = 0; i < moviesList.size(); i++){
            System.out.println(moviesList.get(i).getTitle());
        }
        homeController.removeSearchedPlaylist();
        homeController.AddPlaylist(moviesList, "Search results for " + querrySearched,0);
        homeController.setSearchedFlag();
        homeController.backToTop();

    }


    public void updateSuggestions(ArrayList<String> suggestionList){
        searchSuggestionContainer.getChildren().clear();
        searchSuggestionContainer.setPrefHeight(0);
        searchSuggestionContainer.setPadding(new Insets(0, 0, 0, 0));
        if(suggestionList.isEmpty()) searchSuggestionContainer.setVisible(false);
        else searchSuggestionContainer.setVisible(true);
        for(String suggestion : suggestionList){
            Label label = new Label(suggestion);
            //label.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            label.setPadding(new Insets(7, 0, 0, 8));
            // change text color
            label.setTextFill(Color.WHITE);
            // change font size
            label.setStyle("-fx-font-size: 14px;");
            label.setPrefWidth(searchBar.getPrefWidth());
                label.setOnMouseEntered(event -> {
                    label.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000;");
                });
                label.setOnMouseExited(event -> {
                    label.setStyle("-fx-background-color: #545454; -fx-text-fill: #ffffff;");
                });
                label.setOnMouseClicked(event -> {
                                    //searchBar.setText(suggestion);
                    try {
                        appController.setMoviePresPage(movieAccessor.search(suggestion).get(0));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            searchSuggestionContainer.getChildren().add(label);
            searchSuggestionContainer.setPrefHeight(searchSuggestionContainer.getPrefHeight() + 30);
            searchSuggestionContainer.setPadding(new Insets(25, 0, 20, 0));
        }
    }


    public void setHomeController(HomeController homeController){
        this.homeController = homeController;
    }
}
