package com.Vue;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.IOException;

public class AdminCatalog {

    ///Attributs
    AdminAppController adminAppController;
    @FXML
    private Button button1, button2 , button3, button4;

    ///Méthodes

    //Setter du controller de l'application
    public void setAppAdminController(AdminAppController adminAppController) {
        this.adminAppController= adminAppController;
    }

    //Chargement de la page de statistiques
    @FXML
    public void HandleShowStatisticsGenre(ActionEvent event) throws Exception {
        adminAppController.loadStatsPage();
    }

    //Chargement de la page de gestion des catalogues
    @FXML
    private void HandleCatalogGestion(ActionEvent event) throws Exception {
        adminAppController.loadCatalogPage();
    }

    //Chargement de la page de gestion des utilisateurs
    @FXML
    private void HandleUser(ActionEvent event) throws Exception {
        adminAppController.loadUserPage();
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
        System.exit(0);
    }

}
