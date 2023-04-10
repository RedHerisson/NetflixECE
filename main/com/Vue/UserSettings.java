package com.Vue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.File;

import java.net.URL;
import java.util.ResourceBundle;
public class UserSettings implements Initializable {

    @FXML
    private Button btnHistoric;

    @FXML
    private Button btnPlaylists;

    @FXML
    private Button btnProfil;

    @FXML
    private Label lblStatut;

    @FXML
    private Pane pnlStatut;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void handleClicks(ActionEvent event){

        if(event.getSource() == btnProfil){
            lblStatut.setText("Profil");
            pnlStatut.setBackground(new Background(new BackgroundFill(Color.rgb(30,	66,	99), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        else if(event.getSource() == btnPlaylists){
            lblStatut.setText("Playlists");
            pnlStatut.setBackground(new Background(new BackgroundFill(Color.rgb(24,	52,	79), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        else if(event.getSource() == btnHistoric){
            lblStatut.setText("Historic");
            pnlStatut.setBackground(new Background(new BackgroundFill(Color.rgb(47,	72,	96), CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

}

