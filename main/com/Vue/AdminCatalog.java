package com.Vue;

import com.Model.dao.MovieAccessor;
import com.Model.map.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminCatalog extends Controller implements Initializable {


    @FXML
    private Button button1, button2, button3, button4;
    @FXML
    private VBox vBox;
    @FXML
    private TextField textField;
    @FXML
    private ScrollPane scrollPane;

    private String querrySearched;
    private MovieAccessor movieAccessor = new MovieAccessor();

    private ArrayList<Movie> movieArrayList = new ArrayList<>();

    public AdminCatalog() throws SQLException, ClassNotFoundException {
    }

    ///Méthodes

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textField.setStyle("-fx-text-fill: #FFFFFF; -fx-background-color: transparent; -fx-background-radius: 30; -fx-border-radius: 30; -fx-border-color: #FFFFFF; -fx-border-width: 2;");

    }

    @FXML
    public void HandleSearch(ActionEvent event) throws Exception {
        //On supprime tous les films de la vBox
        vBox.getChildren().clear();
        //On remet le scrollPane en haut
        scrollPane.setVvalue(0);
        //On capture ce qui a été pris dans le textField
        querrySearched = textField.getText();
        movieArrayList = movieAccessor.search(querrySearched);
        System.out.println(querrySearched);
        int idMovieDeleted = 0;

        //On load tous les films de la arraylist
        for (Movie movie : movieArrayList) {
            System.out.println(movie.getTitle());
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/View/MovieGestion.fxml"));
            Pane root = fxmlLoader.load();
            MenuGestionController menuGestionController = fxmlLoader.getController();
            menuGestionController.setMovie(movie);

            vBox.getChildren().add(root);


        }
    }

    //Chargement de la page de statistiques
    @FXML
    public void HandleShowStatisticsGenre(ActionEvent event) throws Exception {
        appController.loadStatsPage();
    }

    //Chargement de la page de gestion des catalogues
    @FXML
    private void HandleCatalogGestion(ActionEvent event) throws Exception {
        appController.loadCatalogPage();
    }

    //Chargement de la page de gestion des utilisateurs
    @FXML
    private void HandleUser(ActionEvent event) throws Exception {
        appController.loadUserPage();
    }

    //Méthodes pour des effets graphiques sur les boutons
    @FXML
    private void setOnMouseEntered1(MouseEvent event) {
        button1.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 30; -fx-border-radius: 30;");
        button1.setTextFill(Color.DARKBLUE);
    }

    @FXML
    private void setOnMouseExited1(MouseEvent event) {
        button1.setStyle("-fx-background-color: #04194F");
        button1.setTextFill(Color.WHITE);
    }

    @FXML
    private void setOnMouseEntered2(MouseEvent event) {
        button2.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 30; -fx-border-radius: 30;");
        button2.setTextFill(Color.DARKBLUE);
    }

    @FXML
    private void setOnMouseExited2(MouseEvent event) {
        button2.setStyle("-fx-background-color: #04194F");
        button2.setTextFill(Color.WHITE);
    }

    @FXML
    private void setOnMouseEntered3(MouseEvent event) {
        button3.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 30; -fx-border-radius: 30;");
        button3.setTextFill(Color.DARKBLUE);
    }

    @FXML
    private void setOnMouseExited3(MouseEvent event) {
        button3.setStyle("-fx-background-color: #04194F");
        button3.setTextFill(Color.WHITE);
    }

    @FXML
    private void setOnMouseEntered4(MouseEvent event) {
        button4.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 30; -fx-border-radius: 30;");
        button4.setTextFill(Color.DARKBLUE);
    }

    @FXML
    private void setOnMouseExited4(MouseEvent event) {
        button4.setStyle("-fx-background-color: #04194F");
        button4.setTextFill(Color.WHITE);
    }

    //Méthode pour quitter le mode admin
    @FXML
    private void HandleClose(ActionEvent event){
        try {
            appController.setLoginPage();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

    }

}
