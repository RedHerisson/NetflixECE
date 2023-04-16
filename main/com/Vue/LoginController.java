package com.Vue;
import com.Controller.AppController;
import com.Model.dao.UserAccessor;
import com.Model.map.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.io.PushbackReader;
import java.net.URL;
import java.util.ResourceBundle;



public class LoginController  extends Controller implements Initializable {

    ///Attributs
    @FXML
    private Button cancelButton;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginMessageError;
    @FXML
    private TextField UsernameLogin;
    @FXML
    private PasswordField enterPasswordField;
    @FXML
    private Button RegisterButton;
    @FXML
    private Pane WarningMovieDB;


    @FXML
    void RegisterButtonOnAction(ActionEvent event) throws Exception{
        Stage stage = (Stage) RegisterButton.getScene().getWindow();
        loginMessageError.setText("You try to register");
        appController.setRegisterPage();
    }

    /**
     * Méthode qui permet de se connecter
     * @param event
     * @throws Exception
     */
    @FXML
    public void loginButtonOnAction(ActionEvent event) throws Exception{
        Stage stage = (Stage) loginButton.getScene().getWindow();


        System.out.println(UsernameLogin.getText());
        System.out.println(enterPasswordField.getText());

        UserAccessor userAccessor = new UserAccessor();
        //On cherche l'utilisateur
        User foundUser = userAccessor.findByName(UsernameLogin.getText());

        if( foundUser != null ) {
            System.out.println("User found: " + foundUser);
            if( userAccessor.checkPwd(foundUser.getId(), enterPasswordField.getText() ) ) {
                appController.loginComplete(foundUser);
            } else {
                loginMessageError.setText("Password is incorrect");
            }
        } else {
            loginMessageError.setText("User not found");
        }
    }

    //Setter d'appController
    public void setAppController(AppController appController) {
        this.appController = appController;
        if( appController.isConnected() ) {
            WarningMovieDB.setVisible(false);
        } else {
            WarningMovieDB.setVisible(true);
        }
    }

    //cancelButton
    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    //Initialisation
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}





