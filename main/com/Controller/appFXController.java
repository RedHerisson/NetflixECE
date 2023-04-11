
/*package com.Controller;

import com.Vue.Controller;
import com.Vue.HomeController;
import com.Vue.LoginController;
import com.Vue.RegisterController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class appFXController extends Application {

    private HomeController homePage;

    private LoginController loginPage;

    private RegisterController registerPage;

    private Controller lastPage;

    private Stage mainStage;

    private FXMLLoader loader;

    public appFXController(){
        homePage = new HomeController();
        loginPage = new LoginController();
        registerPage = new RegisterController();
        Scene scene = new Scene(null, 1275, 645, Color.BLACK);

    }

    public void setLoginPage() throws IOException {
        loader = new FXMLLoader(getClass().getResource("/resources/View/login.fxml"));
        Scene scene = new Scene(null, 1275, 645, Color.BLACK);

        scene.setRoot(loader.load());
        mainStage.setScene(scene);

    }

    public void setRegisterPage() throws IOException {
        loader = new FXMLLoader(getClass().getResource("/resources/View/registration.fxml"));
        Scene scene = new Scene(null, 1275, 645, Color.BLACK);

        scene.setRoot(loader.load());
        mainStage.setScene(scene);

    }

    public void setHomePage() throws IOException {
        loader = new FXMLLoader(getClass().getResource("/resources/View/home.fxml"));
        Scene scene = new Scene(null, 1275, 645, Color.BLACK);

        scene.setRoot(loader.load());
        mainStage.setScene(scene);
    }


    @Override
    public void start(Stage mainStage) throws Exception {
        this.mainStage = mainStage;
        this.mainStage.setResizable(false);
        this.mainStage.show();
        setLoginPage();

    }
}*/
