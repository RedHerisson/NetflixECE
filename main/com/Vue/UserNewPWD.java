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



public class UserNewPWD implements Initializable {

    @FXML
    private TextField ConfirmNewPDW;

    @FXML
    private TextField NewPWD;

    @FXML
    private Button NewPWDBtn;

    @FXML
    void ChangePWDAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        if(event.getSource() == NewPWDBtn){
            if(NewPWD.getText().equals(ConfirmNewPDW.getText())){
                UserAccessor user = new UserAccessor();
                //user.updatePwd(AppController.getUser().getId())
                NewPWD.getText();
            }
        }
        else if(NewPWD.getText().compareTo(ConfirmNewPDW.getText())!=0) {
            //Message.setText("Password not OK");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
