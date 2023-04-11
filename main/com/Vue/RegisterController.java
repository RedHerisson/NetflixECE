package com.Vue;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class RegisterController extends Controller {

    public void createAccount(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("registration.fxml"));
            Stage registerStage = new Stage();
            //primaryStage.setTitle("Hello World");
            registerStage.setScene(new Scene(root));
            registerStage.show();

        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
