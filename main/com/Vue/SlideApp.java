package com.Vue;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.util.Duration;

import java.io.File;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SlideApp implements Initializable{

    /*
    Buttons
     */
    @FXML
    private Button Back;
    @FXML
    private Button Next;
    @FXML
    private Button Back1;

    @FXML
    private Button Next1;

    /*
    Label
     */
    @FXML
    private Label Num;

    @FXML
    private AnchorPane Panel1;

    @FXML
    private AnchorPane Panel2;


    @FXML
    private AnchorPane Panel3;

    @FXML
    private Button exitButton;

    public void translateAnimation(double duration, Node node, double width){
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(duration), node);
        translateTransition.setByX(width);
        translateTransition.play();
    }


    @FXML
    void exitButtonAction(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        translateAnimation(0.5, Panel2, 829);
        translateAnimation(0.5, Panel3, 829);
    }

    int show = 0;
    @FXML
    void BackImage(ActionEvent event) {
        if(show ==1){
            translateAnimation(0.5, Panel2, 829);
            show--;
            Num.setText("1/3");
        }
        else if(show ==2){
            translateAnimation(0.5, Panel3, 829);
            show--;
            Num.setText("2/3");
        }
    }

    @FXML
    void NextImage(ActionEvent event) {
        if(show ==0){
            translateAnimation(0.5, Panel2, -829);
            show++;
            Num.setText("2/3");
        }
        else if(show ==1){
            translateAnimation(0.5, Panel3, -829);
            show++;
            Num.setText("3/3");
        }

    }

    @FXML
    void BackImage1(ActionEvent event) {
        if(show ==1){
            translateAnimation(0.5, Panel2, 829);
            show--;
            Num.setText("1/3");
        }
        else if(show ==2){
            translateAnimation(0.5, Panel3, 829);
            show--;
            Num.setText("2/3");
        }
    }

    @FXML
    void NextImage1(ActionEvent event) {
        if(show ==0){
            translateAnimation(0.5, Panel2, -829);
            show++;
            Num.setText("2/3");
        }
        else if(show ==1){
            translateAnimation(0.5, Panel3, -829);
            show++;
            Num.setText("3/3");
        }

    }

}
