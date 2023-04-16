package com.Vue;

import com.Controller.AppController;
import com.Model.dao.MovieAccessor;
import com.Model.dao.UserAccessor;
import com.Model.map.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class UserGestion extends Controller implements Initializable {

    private User user;
    private UserAccessor userAccessor;
    @FXML
    private Label pseudoUser;
    @FXML
    private Button adminButton,banButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            userAccessor = new UserAccessor();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setUser(User user) {
        this.user = user;
        pseudoUser.setText(user.getPseudo());
    }

    public void setAdmin(ActionEvent event) throws Exception {
        User newAdmin = user;
        newAdmin.setAdmin(true);
        userAccessor.setAdmin(newAdmin);
        if(newAdmin.isAdmin()){
        System.out.println("Nouvel admin !");}
    }

    public void setBan(ActionEvent event) throws Exception {
        userAccessor.delete(user.getId());
        System.out.println("Utilisateur banni !");
    }

    //create a function that decrease the opacity of the buttons when the mouse is over them
    public void decreaseOpacityOnHover1(MouseEvent event) {
        adminButton.setOpacity(0.7);
    }

    //create a function that increase the opacity of the buttons when the mouse is over them
    public void increaseOpacityOnHover1(MouseEvent event) {
        adminButton.setOpacity(1.0);

    }

    public void decreaseOpacityOnHover2(MouseEvent event) {
        banButton.setOpacity(0.7);
    }

    //create a function that increase the opacity of the buttons when the mouse is over them
    public void increaseOpacityOnHover2(MouseEvent event) {
        banButton.setOpacity(1.0);

    }
    @Override
    public void setAppController(AppController appController) {
        this.appController = appController;
    }
}
