package com.Vue;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminAppController extends Application {

    ///Attributs
    private Stage mainStage;
    private Scene scene;

    //Constructeur
    public AdminAppController() {
    }

    //Affichage de la page des statistiques
    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;
        AdminStats adminStats = new AdminStats();
        FXMLLoader loaderHome = new FXMLLoader(getClass().getResource("/resources/View/adminStats.fxml"));
        scene = new Scene(loaderHome.load(), 1275, 645, Color.BLACK);
        AdminStats controller = loaderHome.getController();
        controller.setAppAdminController(this);
        mainStage.setScene(scene);
        mainStage.setResizable(false);
        mainStage.show();
    }

    public static void Main(String[] args) {
        launch(args);
    }

    //Méthode pour load la page des statistiques
    public void loadStatsPage() throws Exception {
        AdminStats adminStats = new AdminStats();
        FXMLLoader loaderHome = new FXMLLoader(getClass().getResource("/resources/View/adminStats.fxml"));
        scene = new Scene(loaderHome.load(), 1275, 645, Color.BLACK);
        AdminStats controller = loaderHome.getController();
        controller.setAppAdminController(this);
        mainStage.setScene(scene);
        mainStage.setResizable(false);
        mainStage.show();

    }

    //Méthode pour load la page du catalogue
    public void loadCatalogPage() throws Exception {
        AdminCatalog adminCatalog = new AdminCatalog();
        FXMLLoader loaderHome = new FXMLLoader(getClass().getResource("/resources/View/adminCatalog.fxml"));
        scene = new Scene(loaderHome.load(), 1275, 645, Color.BLACK);
        AdminCatalog controller = loaderHome.getController();
        controller.setAppAdminController(this);
        mainStage.setScene(scene);
        mainStage.setResizable(false);
        mainStage.show();
    }

    //Méthode pour load la page des utilisateurs
    public void loadUserPage() throws Exception {
        AdminUser adminUser = new AdminUser();
        FXMLLoader loaderHome = new FXMLLoader(getClass().getResource("/resources/View/adminUserGestion.fxml"));
        scene = new Scene(loaderHome.load(), 1275, 645, Color.BLACK);
        AdminUser controller = loaderHome.getController();
        controller.setAppAdminController(this);
        mainStage.setScene(scene);
        mainStage.setResizable(false);
        mainStage.show();

    }
}
