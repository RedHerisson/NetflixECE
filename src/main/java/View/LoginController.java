package View;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.File;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController  implements Initializable{

    @FXML
    private Button cancelButton;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginMessageError;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private ImageView lockImageView;
    @FXML
    private TextField UsernameLogin;
    @FXML
    private PasswordField enterPasswordField;

    public void loginButtonOnAction(ActionEvent event){
        Stage stage = (Stage) loginButton.getScene().getWindow();
        loginMessageError.setText("You try to login");
        /*

        if(UsernameLogin.getText().isBlank() == false && enterPasswordField.getText().isBlank() == false){
            validateLogin();
        }
        else {
            loginMessageError.setText("Please enter username and password");
        }
        */

    }
    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("images/tortipouss.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

        File lockFile = new File("images/cadena.png");
        Image lockImage = new Image(lockFile.toURI().toString());
        lockImageView.setImage(lockImage);
    }
/*
    public void validateLogin(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "...username here..." + UsernameLogin.getText() + "...password here..." + enterPasswordField.getText() + "" ;
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if(queryResult.getInt(1)==1){
                    loginMessageError.setText("Congratulations!");
                }
                else{
                    loginMessageError.setText("Invalid login. Please try again");
                }
            }

        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }
    */

}
