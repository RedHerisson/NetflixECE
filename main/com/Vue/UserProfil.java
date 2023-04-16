package com.Vue;

import com.Controller.AppController;
import com.Model.dao.PersonAccessor;
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

import java.io.IOException;
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
    private Label MailUser;
    @FXML
    private TextField NewSurname;
    private UserAccessor userAccessor = new UserAccessor();
    private PersonAccessor personAccessor = new UserAccessor();


    public UserProfil() throws SQLException, ClassNotFoundException {
    }

    /// TODO: Voir s'il faut un seul bouton pour tout les changements


    @FXML
    public void HandleClick(ActionEvent event) throws Exception {

        //obtenir l'ID de l'utilisateur connecté?????
        //User userConnected =

                if (event.getSource() == BttSaveNewName) {
                String name = NewName.getText();
                User newUser = new User(userConnected); //clone
                System.out.println("New name: " + name);
                personAccessor.updateName(newUser);

            } else if (event.getSource() == BttSaveNewSurname) {
                String surname = NewSurname.getText();
                User newUser = new User(userConnected); //clone
                System.out.println("New surname: " + surname);
                personAccessor.updateSurname(newUser);

            } else if (event.getSource() == BttSaveNewPseudo) {

                String pseudo = NewPseudo.getText();
                User newUser = new User(UserConnected);
                System.out.println("New pseudo: " + pseudo);
                userAccessor.updatePseudo(newUser);

            } else if (event.getSource() == BttChangePassword) {
                try {
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