package com.Vue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class LoginController implements Initializable {

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
    private Parent login;


    @FXML
    void RegisterButtonOnAction(ActionEvent event) throws Exception{
        Stage stage = (Stage) RegisterButton.getScene().getWindow();
        //loginMessageError.setText("You try to register");

        Parent registration = FXMLLoader.load(getClass().getResource("/resources/View/registration.fxml"));
        Scene login = new Scene(registration);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        appStage.setScene(login);
        appStage.show();
    }

    @FXML
    public void loginButtonOnAction(ActionEvent event) throws Exception{
        Stage stage = (Stage) loginButton.getScene().getWindow();
        //loginMessageError.setText("You try to login");

        System.out.println(UsernameLogin.getText());
        System.out.println(enterPasswordField.getText());

        if ((UsernameLogin.getText().compareTo("admin")==0) && (enterPasswordField.getText().compareTo("admin")==0)) {
            loginMessageError.setText("OK");
            Parent registration = FXMLLoader.load(getClass().getResource("/resources/View/test.fxml"));
            Scene login = new Scene(registration);
            Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

            appStage.setScene(login);
            appStage.show();

        }
        else if ((UsernameLogin.getText().compareTo("nono")==0) && (enterPasswordField.getText().compareTo("admin")==0)) {
            loginMessageError.setText("OK");
            Parent registration = FXMLLoader.load(getClass().getResource("/resources/View/test.fxml"));
            Scene login = new Scene(registration);
            Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

            appStage.setScene(login);
            appStage.show();

        }
        else if ((UsernameLogin.getText().compareTo("raph")==0) && (enterPasswordField.getText().compareTo("admin")==0)) {
            loginMessageError.setText("OK");
            Parent registration = FXMLLoader.load(getClass().getResource("/resources/View/test.fxml"));
            Scene login = new Scene(registration);
            Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

            appStage.setScene(login);
            appStage.show();

        }
        else if ((UsernameLogin.getText().compareTo("gaelle")==0) && (enterPasswordField.getText().compareTo("admin")==0)) {
            loginMessageError.setText("OK");
            Parent registration = FXMLLoader.load(getClass().getResource("/resources/View/test.fxml"));
            Scene login = new Scene(registration);
            Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

            appStage.setScene(login);
            appStage.show();

        }
        else if ((UsernameLogin.getText().compareTo("igor")==0) && (enterPasswordField.getText().compareTo("admin")==0)) {
            loginMessageError.setText("OK");
            Parent registration = FXMLLoader.load(getClass().getResource("/resources/View/test.fxml"));
            Scene login = new Scene(registration);
            Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

            appStage.setScene(login);
            appStage.show();

        }
        else{
            loginMessageError.setText("UserName or Password aren't correct");
        }



    }

    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }










    /*
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //loginButton.setOnAction(actionEvent -> login());
    }
*/

/*
    public void login(){
        PreparedStatement state = null;
        ResultSet result = null;
        Connection con = DatabaseConnection.getConnection();
        try {
            state = con.prepareStatement("SELECT * FROM users WHERE USERNAME =? AND PASSWORD =?");
            state.setString(1, UsernameLogin.getText());
            state.setString(2, enterPasswordField.getText());
            result = state.executeQuery();
            if(result.next()){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Login successfull", ButtonType.OK);
                alert.show();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING, "Login Error", ButtonType.OK);
                alert.show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
*/

}





