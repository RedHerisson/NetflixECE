package com.Vue.UserSettings;

import com.Controller.AppController;
import com.Vue.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class UserSettings extends Controller implements Initializable {
    @FXML
    private AnchorPane rootUS;
    @FXML
    private Label UserName;
    @FXML
    private Button btnHistoric;

    @FXML
    private Button btnProfil;

    @FXML
    private Button btnSignOut;

    @FXML
    private Label lblStatut;

    @FXML
    private Pane pnlStatut;

    @FXML
    private ImageView BackArrow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BackArrow.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                appController.setHomePage();
            }
        });
    }

    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
        setGraphicUserName(appController.getCurrentuser().getPseudo());
        if (event.getSource() == btnProfil) {
            lblStatut.setText("Profil");
            pnlStatut.setBackground(new Background(new BackgroundFill(Color.rgb(30, 66, 99), CornerRadii.EMPTY, Insets.EMPTY)));

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/View/UserSettings/UserProfil.fxml"));
                AnchorPane Profil = loader.load();
                UserProfil userProfil = loader.getController();
                userProfil.loadUserInfo(appController);
                rootUS.getChildren().setAll(Profil);
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }

        } else if (event.getSource() == btnHistoric) {
            lblStatut.setText("Historic");
            pnlStatut.setBackground(new Background(new BackgroundFill(Color.rgb(47, 72, 96), CornerRadii.EMPTY, Insets.EMPTY)));

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/View/UserSettings/UserHistoric.fxml"));
                AnchorPane Historic = loader.load();
                UserHistoric userHistoric = loader.getController();
                userHistoric.ShowHistoric(appController);
                rootUS.getChildren().setAll(Historic);
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }

        else if (event.getSource() == btnSignOut) {
                appController.setLoginPage();
            }

    }

    public void setAppController(AppController appController) {
        this.appController = appController;
        setGraphicUserName(appController.getCurrentuser().getPseudo());
    }

    public void setGraphicUserName(String name) {
        UserName.setText(name);
    }
}

