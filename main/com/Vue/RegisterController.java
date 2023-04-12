package com.Vue;

import com.Model.map.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXML;

import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.*;

public class RegisterController {

    @FXML
    private Button Close;

    @FXML
    private TextField FirstName;

    @FXML
    private TextField LastName;

    @FXML
    private PasswordField Password;

    @FXML
    private PasswordField ConfirmPassword;

    @FXML
    private Button Register;

    @FXML
    private TextField UserName;
    @FXML
    private Label loginMessage;

    public void RegisterAction(ActionEvent event) throws Exception{

        System.out.println(Password.getText());
        System.out.println(ConfirmPassword.getText());

        if(FirstName.getText().isBlank()==true || LastName.getText().isBlank()==true || UserName.getText().isBlank()==true || Password.getText().isBlank()==true || ConfirmPassword.getText().isBlank()==true){
            Password.clear();
            ConfirmPassword.clear();
            UserName.clear();
            FirstName.clear();
            LastName.clear();
            loginMessage.setText("Not complete");
        }

        else if(Password.getText().compareTo(ConfirmPassword.getText())!=0){
            loginMessage.setText("Password not OK");
        }
        else if (Password.getText().compareTo(ConfirmPassword.getText())==0){
            loginMessage.setText("Password OK");

            Parent registration = FXMLLoader.load(getClass().getResource("/resources/View/test.fxml"));
            Scene login = new Scene(registration);
            Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

            appStage.setScene(login);
            appStage.show();
        }
    }

    public void CloseAction(ActionEvent actionEvent) {
        Stage stage = (Stage) Close.getScene().getWindow();
        stage.close();
    }
}
