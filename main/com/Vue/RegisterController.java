package com.Vue;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXML;
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
    private TextField Password;

    @FXML
    private TextField PasswordConfirm;

    @FXML
    private Button Register;

    @FXML
    private TextField UserName;

    public void RegisterAction(ActionEvent event) throws Exception{

        Parent registration = FXMLLoader.load(getClass().getResource("/resources/View/test.fxml"));
        Scene login = new Scene(registration);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        appStage.setScene(login);
        appStage.show();

    }

    public void CloseAction(ActionEvent actionEvent) {
        Stage stage = (Stage) Close.getScene().getWindow();
        stage.close();
    }
}
