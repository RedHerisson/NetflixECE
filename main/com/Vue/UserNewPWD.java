package com.Vue;

import com.Controller.AppController;
import com.Model.dao.UserAccessor;
import com.Model.map.User;
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



public class UserNewPWD implements Initializable {

    @FXML
    private TextField ConfirmNewPDW;

    @FXML
    private TextField NewPWD;

    @FXML
    private Button NewPWDBtn;

    private UserAccessor userAccessor = new UserAccessor();

    @FXML
    void ChangePWDAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        if(event.getSource() == NewPWDBtn){

            String pwd = NewPWD.getText();
            String confirmPDW = ConfirmNewPDW.getText();
            User newUser = new User(userConnected); //clone


            if(NewPWD.getText().equals(ConfirmNewPDW.getText())){

                System.out.println("New password: " + pwd);
                userAccessor.updatePwd(newUser);
            }

        else if(NewPWD.getText().compareTo(ConfirmNewPDW.getText())!=0) {
            System.out.println("Les mots de passe ne correspondent pas");
        }
      }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
