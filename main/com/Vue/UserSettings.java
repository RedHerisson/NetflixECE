package com.Vue;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
public class UserSettings implements Initializable {
    private AnchorPane rootUS;
    @FXML
    private Button btnHistoric;

    @FXML
    private Button btnPlaylists;

    @FXML
    private Button btnProfil;

    @FXML
    private Label lblStatut;

    @FXML
    private Pane pnlSettings;

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

            try{
                Parent root = FXMLLoader.load(getClass().getResource("/ressources/View/userProfil.fxml"));
                //rootUS.getChildren().removeAll();
               // rootUS.getChildren().setAll(root);
                Stage registerStage = new Stage();
                registerStage.setScene(new Scene(root));
                registerStage.show();

            }catch(Exception e){
                e.printStackTrace();
                e.getCause();
            }

        }
        else if(event.getSource() == btnPlaylists){
            lblStatut.setText("Playlists");
            pnlStatut.setBackground(new Background(new BackgroundFill(Color.rgb(24,	52,	79), CornerRadii.EMPTY, Insets.EMPTY)));

            try{
                Parent root = FXMLLoader.load(getClass().getResource("/ressources/View/UserPlaylists.fxml"));
                //rootUS.getChildren().removeAll();
                // rootUS.getChildren().setAll(root);
                Stage registerStage = new Stage();
                registerStage.setScene(new Scene(root));
                registerStage.show();

            }catch(Exception e){
                e.printStackTrace();
                e.getCause();
            }
        }
        else if(event.getSource() == btnHistoric){
            lblStatut.setText("Historic");
            pnlStatut.setBackground(new Background(new BackgroundFill(Color.rgb(47,	72,	96), CornerRadii.EMPTY, Insets.EMPTY)));

            try{
                Parent root = FXMLLoader.load(getClass().getResource("/ressources/View/UserHistoric.fxml"));
                //rootUS.getChildren().removeAll();
                // rootUS.getChildren().setAll(root);
                Stage registerStage = new Stage();
                registerStage.setScene(new Scene(root));
                registerStage.show();

            }catch(Exception e){
                e.printStackTrace();
                e.getCause();
            }
        }
    }

}

