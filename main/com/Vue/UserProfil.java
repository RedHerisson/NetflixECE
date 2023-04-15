package com.Vue;

import com.Controller.AppController;
import com.Model.dao.UserAccessor;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class UserProfil implements Initializable {

    @FXML
    private Button BttChangePassword;

    @FXML
    private Button BttSaveNewName;

    @FXML
    private Button BttSaveNewPseudo;

    @FXML
    private Button BttSaveNewSurname;

    @FXML
    private TextField NewName;

    @FXML
    private TextField NewPseudo;

    @FXML
    private TextField NewSurname;

    @FXML
    void HandleClick(ActionEvent event) throws SQLException, ClassNotFoundException {

        /// TODO: Voir s'il faut un seul bouton pour tout les changements

        if(event.getSource() == BttSaveNewName){
           //UserAccessor.updateName(AppController.getInstance().getUser().getId(), NewName.getText());
        }
        else if(event.getSource() == BttSaveNewSurname){

           // AppController.getInstance().getUser().setLastName(NewSurname.getText());
        }
        else if(event.getSource() == BttSaveNewPseudo){
            System.out.println(NewPseudo.getText());

           UserAccessor user = new UserAccessor();
           //user.updatePseudo(AppController.getId(), NewPseudo.getText());
        }
        else if(event.getSource() == BttChangePassword){
            try{
                Parent root = FXMLLoader.load(getClass().getResource("/resources/View/ChangePWD.fxml"));
                Stage PWDStage = new Stage();
                PWDStage.setScene(new Scene(root));
                PWDStage.show();
                Image icon = new Image(getClass().getResourceAsStream("/resources/images/ECE_LOGO.png"));
                PWDStage.getIcons().add(icon);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}