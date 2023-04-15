package com.Vue;
//package com.Model.dao;

import com.Model.dao.MovieAccessor;
import com.Model.dao.PersonAccessor;
import com.Model.dao.UserAccessor;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
//import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.util.Duration;

public class AdminStats implements Initializable {

    ///Attributs
    AdminAppController adminAppController;
    @FXML
    private BarChart barChart1, barChart2;
    @FXML
    private PieChart pieChartMoviesPercentage;
    @FXML
    private PieChart pieChartSexPercentage;
    @FXML
    private CategoryAxis xAxis, xAxis2, xAxis3;
    @FXML
    private NumberAxis yAxis, yAxis2, yAxis3;
    @FXML
    private Button button1, button2 , button3, button4;
    @FXML
    private LineChart lineChart;

    @FXML
    private Label totalFilms, totalUsers, totalUserConnected;

    private PersonAccessor personAccessor = new PersonAccessor();

    private UserAccessor userAccessor = new UserAccessor();
    private MovieAccessor movieAccessor = new MovieAccessor();
    int totalMen = 0,totalWomen = 0;

    public AdminStats() throws SQLException, ClassNotFoundException {
    }

    ///Méthodes






    //Méthode d'initialisation des données pour les graphiques
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Put your code here that should be executed every 10 seconds
                System.out.println("This code is executed every 10 seconds");

                try {
                    totalFilms.setText(String.valueOf(movieAccessor.countMovies()));
                    totalUsers.setText(String.valueOf(userAccessor.countUsers()));
                    totalUserConnected.setText(String.valueOf(userAccessor.countUsersConnected()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }));

        // On set le cycle du timer à l'infini, donc il continuera de tourner jusqu'à ce qu'on le stop
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();


        try {
            totalMen = personAccessor.countUsersBySexe("M");
            totalWomen = personAccessor.countUsersBySexe("F");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        //On modifie le style des axes
        Font font = Font.font("Symbol", FontWeight.NORMAL, 10);
        xAxis.setStyle("-fx-text-fill: white");
        xAxis.setTickLabelFill(Color.WHITE);
        xAxis.setTickLabelFont(font);
        yAxis.setStyle("-fx-text-fill: white");
        yAxis.setTickLabelFill(Color.WHITE);
        yAxis.setTickLabelFont(font);
        XYChart.Series<String, Number> data = new XYChart.Series();
        data.setName("Nombre de visionnages selon le genre");

        //On ajoute les données au graphique
        data.getData().add(new XYChart.Data("Horreur",32000));
        data.getData().add(new XYChart.Data("Drame",85600));
        data.getData().add(new XYChart.Data("Action",69230));
        data.getData().add(new XYChart.Data("Fantastique",24870));
        data.getData().add(new XYChart.Data("Aventure",56240));
        data.getData().add(new XYChart.Data("Romantique",12450));

        //Le graphique prend la forme d'un histogramme
        barChart1.getData().add(data);

        //On modifie le style du graphique
        barChart1.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent");
        barChart1.setStyle("-fx-text-fill: white;");
        barChart1.getYAxis().setStyle("-fx-border-color: transparent #ffffff transparent transparent;  -fx-border-width:3");
        barChart1.getXAxis().setStyle("-fx-border-color: #ffffff transparent transparent transparent;  -fx-border-width:3");


        ////////////////////////////////      PIECHART FILMS      ///////////////////////////////////////////////

        //On ajoute les données au graphique
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Pulp Fiction", 1253000),
                new PieChart.Data("Interstellar", 1138790),
                new PieChart.Data("Avatar", 983000),
                new PieChart.Data("You should not kill", 982563),
                new PieChart.Data("Titanic", 962563),
                new PieChart.Data("Harry Potter", 952563),
                new PieChart.Data("Hunger Games", 902563)
        );

        //Le graphique prend la forme d'un camembert
        pieChartMoviesPercentage.setData(pieChartData);
        pieChartMoviesPercentage.setTitle("Films les plus regardés");
        pieChartMoviesPercentage.setClockwise(true);
        pieChartMoviesPercentage.setLabelLineLength(10);
        pieChartMoviesPercentage.setStartAngle(90);
        pieChartMoviesPercentage.setLabelsVisible(false);
        pieChartMoviesPercentage.lookup(".chart-title").setStyle("-fx-text-fill: white");


////////////////////////////////      PIECHART SEXE      ///////////////////////////////////////////////

        //On ajoute les données au graphique
        ObservableList<PieChart.Data> pieChartDataSexPercentage = FXCollections.observableArrayList(
                new PieChart.Data("Hommes", totalMen),
                new PieChart.Data("Femmes", totalWomen)
        );

        //Le graphique prend la forme d'un camembert
        pieChartSexPercentage.setData(pieChartDataSexPercentage);
        pieChartSexPercentage.setTitle("Proportion M/F");
        pieChartSexPercentage.setClockwise(true);
        pieChartSexPercentage.setLabelLineLength(10);
        pieChartSexPercentage.setStartAngle(90);
        pieChartSexPercentage.setLabelsVisible(false);
        pieChartSexPercentage.lookup(".chart-title").setStyle("-fx-text-fill: white");

        ////////////////////////////////      LINECHART      ///////////////////////////////////////////////

        //On ajoute les données au graphique
        yAxis2.setTickLabelFill(Color.WHITE);
        xAxis2.setTickLabelFill(Color.WHITE);
        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("Nombre d'abonnés au cours du temps");
        dataSeries1.getData().add(new XYChart.Data("17/03", 1));
        dataSeries1.getData().add(new XYChart.Data("24/03", 2));
        dataSeries1.getData().add(new XYChart.Data("31/03", 8));
        dataSeries1.getData().add(new XYChart.Data("07/03", 10));
        dataSeries1.getData().add(new XYChart.Data("14/03", 14));

        //Le graphique prend la forme d'une courbe
        lineChart.getData().add(dataSeries1);
        lineChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent");
        lineChart.getYAxis().setStyle("-fx-border-color: transparent #ffffff transparent transparent;  -fx-border-width:3");
        lineChart.getXAxis().setStyle("-fx-border-color: #ffffff transparent transparent transparent;  -fx-border-width:3");

        ////////////////////////////////      BARCHART AGE      ///////////////////////////////////////////////

        //On ajoute les données au graphique
        XYChart.Series dataAge = new XYChart.Series();
        dataAge.setName("Nombre d'abonnés selon leur tranche d'âge");

        dataAge.getData().add(new XYChart.Data("18-25",4));
        dataAge.getData().add(new XYChart.Data("25-35",2));
        dataAge.getData().add(new XYChart.Data("35-45",1));
        dataAge.getData().add(new XYChart.Data("45-55",3));
        dataAge.getData().add(new XYChart.Data("55-65",3));
        dataAge.getData().add(new XYChart.Data("65 et +",1));


        //Le graphique prend la forme d'un histogramme
        barChart2.getData().add(dataAge);

        //On modifie le style du graphique
        barChart2.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent");
        barChart2.getXAxis().setStyle("-fx-border-color: #ffffff transparent transparent transparent;  -fx-border-width:3");
        barChart2.getYAxis().setStyle("-fx-border-color: transparent #ffffff transparent transparent;  -fx-border-width:3");

        ///////////////////////////////////////////////////////////////////////////////////Catalog

//        comboBox.setValue("Fantastique");
//        comboBox.setValue("Horreur");
//        comboBox.setValue("Action");
//        comboBox.setValue("Drame");


    };

    ///////////////////////////////////////////////////////////////////////////////////

    //On set le controller de l'application
    public void setAppAdminController(AdminAppController adminAppController) {
        this.adminAppController= adminAppController;
    }
    //On appelle la fonction qui permet de charger la page des statistiques
    @FXML
    public void HandleShowStatisticsGenre(ActionEvent event) throws Exception {
        adminAppController.loadStatsPage();
    }

    //On appelle la fonction qui permet de charger la page du gestionnaire de catalogue
    @FXML
    private void HandleCatalogGestion(ActionEvent event) throws Exception {
        adminAppController.loadCatalogPage();
    }

    //On appelle la fonction qui permet de charger la page du gestionnaire d'utilisateurs
    @FXML
    private void HandleUser(ActionEvent event) throws Exception {
        adminAppController.loadUserPage();
    }

    //Méthodes pour des effets visuels sur les boutons
    @FXML
    private void setOnMouseEntered1(MouseEvent event) {
        button1.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 30; -fx-border-radius: 30;");
        button1.setTextFill(Color.DARKBLUE);
    }

    @FXML
    private void setOnMouseExited1(MouseEvent event) {
        button1.setStyle("-fx-background-color: #04194F");
        button1.setTextFill(Color.WHITE);
    }

    @FXML
    private void setOnMouseEntered2(MouseEvent event) {
        button2.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 30; -fx-border-radius: 30;");
        button2.setTextFill(Color.DARKBLUE);
    }

    @FXML
    private void setOnMouseExited2(MouseEvent event) {
        button2.setStyle("-fx-background-color: #04194F");
        button2.setTextFill(Color.WHITE);
    }

    @FXML
    private void setOnMouseEntered3(MouseEvent event) {
        button3.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 30; -fx-border-radius: 30;");
        button3.setTextFill(Color.DARKBLUE);
    }

    @FXML
    private void setOnMouseExited3(MouseEvent event) {
        button3.setStyle("-fx-background-color: #04194F");
        button3.setTextFill(Color.WHITE);
    }

    @FXML
    private void setOnMouseEntered4(MouseEvent event) {
        button4.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 30; -fx-border-radius: 30;");
        button4.setTextFill(Color.DARKBLUE);
    }

    @FXML
    private void setOnMouseExited4(MouseEvent event) {
        button4.setStyle("-fx-background-color: #04194F");
        button4.setTextFill(Color.WHITE);
    }

    //Méthode pour quitter le mode admin
    @FXML
    private void HandleClose(ActionEvent event){
    System.exit(0);
    }



}
