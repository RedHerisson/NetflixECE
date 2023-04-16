package com.Vue;

import com.Controller.AppController;
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
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
public class UserSettings extends Controller implements Initializable {
    private AnchorPane rootUS;
    @FXML
    private Label UserName;
    @FXML
    private Button btnHistoric;

    @FXML
    private Button btnPlaylists;

    @FXML
    private Button btnProfil;

    @FXML
    private Button btnSignOut;

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
    private void handleClicks(ActionEvent event) {

        if (event.getSource() == btnProfil) {
            lblStatut.setText("Profil");
            pnlStatut.setBackground(new Background(new BackgroundFill(Color.rgb(30, 66, 99), CornerRadii.EMPTY, Insets.EMPTY)));

            try {
                Parent root = FXMLLoader.load(getClass().getResource("/resources/View/UserProfil.fxml"));
                Stage ProfilStage = new Stage();
                ProfilStage.setScene(new Scene(root));
                ProfilStage.show();
                Image icon = new Image(getClass().getResourceAsStream("/resources/images/ECE_LOGO.png"));
                ProfilStage.setTitle("UserProfil");
                ProfilStage.getIcons().add(icon);

            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }

        } else if (event.getSource() == btnPlaylists) {
            lblStatut.setText("Playlists");
            pnlStatut.setBackground(new Background(new BackgroundFill(Color.rgb(24, 52, 79), CornerRadii.EMPTY, Insets.EMPTY)));

            try {
                Parent root = FXMLLoader.load(getClass().getResource("/resources/View/UserPlaylists.fxml"));
                Stage PlaylistsStage = new Stage();
                PlaylistsStage.setScene(new Scene(root));
                PlaylistsStage.show();
                Image icon = new Image(getClass().getResourceAsStream("/resources/images/ECE_LOGO.png"));
                PlaylistsStage.setTitle("UserPlaylists");
                PlaylistsStage.getIcons().add(icon);

            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        } else if (event.getSource() == btnHistoric) {
            lblStatut.setText("Historic");
            pnlStatut.setBackground(new Background(new BackgroundFill(Color.rgb(47, 72, 96), CornerRadii.EMPTY, Insets.EMPTY)));

            try {
                Parent root = FXMLLoader.load(getClass().getResource("/resources/View/UserHistoric.fxml"));
                Stage HistoricStage = new Stage();
                HistoricStage.setScene(new Scene(root));
                HistoricStage.show();
                Image icon = new Image(getClass().getResourceAsStream("/resources/images/ECE_LOGO.png"));
                HistoricStage.setTitle("UserHistoric");
                HistoricStage.getIcons().add(icon);

            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }

        else if (event.getSource() == btnSignOut) {

            }

    }

    @Override
    public void setAppController(AppController appController) {
        this.appController = appController;
    }
}

