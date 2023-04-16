package com.Vue;

import com.Controller.AppController;
import com.Model.dao.UserAccessor;
import com.Model.map.Movie;
import com.Model.map.Playlist;
import com.Model.map.User;
import com.Model.map.UserData;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXML;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.scene.*;

public class RegisterController extends Controller {

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
    private TextField UserName;

    @FXML
    private TextField UserMail;

    @FXML
    private RadioButton UserSetFemale, UserSetMale;

    @FXML
    private DatePicker UserAge;
    @FXML
    private Label loginMessage;

    @FXML
    String sexe;


    public void RegisterAction(ActionEvent event) throws Exception{

        System.out.println(Password.getText());
        System.out.println(ConfirmPassword.getText());

        if(FirstName.getText().isBlank()==true || LastName.getText().isBlank()==true || UserName.getText().isBlank()==true ||
                Password.getText().isBlank()==true || ConfirmPassword.getText().isBlank()==true || (UserSetFemale.isSelected()==false && UserSetMale.isSelected()==false)){
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
            UserAccessor userAccessor = new UserAccessor();
            String enteredUserName = UserName.getText();
            String enteredEmail = UserMail.getText();
            User existingName = userAccessor.findByName(enteredUserName);
            User existingEmail = userAccessor.findByMail(enteredEmail);
            if( existingName != null) {
                loginMessage.setText("User already exists");
            }
            else if( existingEmail != null ) {
                loginMessage.setText("Email already exists");
            }
            else {
                LocalDate userBirth = UserAge.getValue();
                int age = userBirth.getYear() - LocalDate.now().getYear(); // TODO: check for month and day
                Playlist history = new Playlist(-1, -1, "History", new ArrayList<Movie>());
                Playlist WatchLater = new Playlist(-1, -1, "WatchLater", new ArrayList<Movie>());


                User user = new User(-1, UserName.getText(),Password.getText(), LastName.getText(), FirstName.getText(), UserMail.getText(),
                age, sexe, LocalDate.now(),WatchLater ,history, new ArrayList<String>(), new ArrayList<UserData>(),false );
                userAccessor.create(user);
                System.out.println("User created: " + user);
                appController.setHomePage();
            }
            loginMessage.setText("Password OK");
            //User user = new User(-1, UserName.getText(),Password.getText(), LastName.getText(), FirstName.getText(), );

        }
    }
    @FXML
    public void setMale() {
        sexe = "M";
        UserSetFemale.setSelected(false);
    }

    @FXML
    public void setFemale() {
        sexe = "F";
        UserSetMale.setSelected(false);
    }

    public void CloseAction(ActionEvent actionEvent) throws IOException {
        appController.setLoginPage();
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }
}
