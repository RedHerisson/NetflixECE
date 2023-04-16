package com.Vue;

import com.Model.dao.UserAccessor;
import com.Model.map.Movie;
import com.Model.map.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminUser implements Initializable{

AdminAppController adminAppController;

    ///Attributs
    @FXML
    private Button button1, button2 , button3, button4;
    @FXML
    private TextField textField;
    @FXML
    private VBox vBox;
    @FXML
    private ScrollPane scrollPane;

    private String querrySearched;
    private ArrayList<User>userArrayList = new ArrayList<>();

    private UserAccessor userAccessor = new UserAccessor();

    public AdminUser() throws SQLException, ClassNotFoundException {
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
        userArrayList = userAccessor.searchPseudo(querrySearched);
        System.out.println(querrySearched);
        int idMovieDeleted = 0;

        //On load tous les films de la arraylist
        for (User user: userArrayList) {
            System.out.println(user.getPseudo());
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/View/UserGestion.fxml"));
            Pane root = fxmlLoader.load();
            UserGestion userGestion = fxmlLoader.getController();
            userGestion.setUser(user);

            vBox.getChildren().add(root);


        }
    }



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
    //On quitte le mode admin
    @FXML
    private void HandleClose(ActionEvent event){
        System.exit(0);
    }
}
