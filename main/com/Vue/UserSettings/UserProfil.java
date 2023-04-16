package com.Vue.UserSettings;

import com.Controller.AppController;
import com.Model.dao.PersonAccessor;
import com.Model.dao.UserAccessor;
import com.Model.map.User;
import com.Vue.Controller;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class UserProfil extends Controller implements Initializable {


    @FXML
    private TextField LastName;
    @FXML
    private TextField FirstName;
    @FXML
    private TextField UserName;
    @FXML
    private TextField MailUser;
    @FXML
    private PasswordField CurrPassword;
    @FXML
    private PasswordField Password;
    @FXML
    private PasswordField ConfirmPassword;

    @FXML
    private Label ErrorMessage;

    private User temporaryUser;

    private UserAccessor userAccessor = new UserAccessor();
    private PersonAccessor personAccessor = new UserAccessor();


    public UserProfil() throws SQLException, ClassNotFoundException {
    }

    public void loadUserInfo(AppController appController) {
        this.appController = appController;
        temporaryUser = appController.getCurrentuser();
        graphicUpdate();

    }

    public void graphicUpdate() {
        UserName.setText(temporaryUser.getPseudo());
        LastName.setText(temporaryUser.getSurname());
        FirstName.setText(temporaryUser.getName());
        MailUser.setText(temporaryUser.getEmail());
    }

    @FXML
    public void RegisterAction() {
        try {
            if (!CurrPassword.getText().equals(temporaryUser.getPwd())) {
                ErrorMessage.setText("Wrong password");
                return;
            }
            UserAccessor userAccessor = new UserAccessor();
            String enteredUserName = UserName.getText();
            String enteredEmail = MailUser.getText();
            User existingName = userAccessor.findByName(enteredUserName);
            User existingEmail = userAccessor.findByMail(enteredEmail);
            if (existingName != null) {
                ErrorMessage.setText("User name already exists");
            } else if (existingEmail != null) {
                ErrorMessage.setText("Email already exists");
            }
            if (Password.getText().equals(ConfirmPassword.getText())) {

                if(Password.getText() != "") temporaryUser.setPwd(Password.getText());
                temporaryUser.setName(FirstName.getText());
                temporaryUser.setPseudo(UserName.getText());
                temporaryUser.setSurname(LastName.getText());
                temporaryUser.setEmail(MailUser.getText());

                personAccessor.update(temporaryUser);
                userAccessor.updatePseudo(temporaryUser);
                userAccessor.updatePwd(temporaryUser);
                userAccessor.updateEmail(temporaryUser);
                graphicUpdate();
                appController.setLoginUser(temporaryUser);

            ErrorMessage.setText("User updated");
        } else{
            ErrorMessage.setText("Passwords do not match");
        }
        } catch(Exception e ){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}