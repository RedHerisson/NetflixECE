package com.Vue;

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

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class searchBar implements Initializable {

    @FXML
    private TextField searchBar;

    @FXML
    private VBox searchSuggestionContainer;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.length() > 0){
                ArrayList<String> suggestionList = new ArrayList<>();
                suggestionList.add("test");
                suggestionList.add("test2");
                suggestionList.add("test3");
                updateSuggestions(suggestionList);
            }else{
                searchSuggestionContainer.getChildren().clear();
                searchSuggestionContainer.setPrefHeight(0);
                searchSuggestionContainer.setPadding(new Insets(0, 0, 0, 0));
            }
        });
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
            searchSuggestionContainer.getChildren().add(label);
            searchSuggestionContainer.setPrefHeight(searchSuggestionContainer.getPrefHeight() + 30);
            searchSuggestionContainer.setPadding(new Insets(25, 0, 20, 0));
        }

    }
}
