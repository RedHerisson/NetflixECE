package com.Vue;

import com.Model.dao.MovieAccessor;
import com.Model.dao.PersonAccessor;
import com.Model.dao.UserAccessor;
import com.Model.map.Movie;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.chart.*;
import javafx.util.Duration;

public class AdminStats extends Controller implements Initializable {

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
    private Label totalFilms, totalUsers, totalUserConnected, nbAdmins;

    private PersonAccessor personAccessor = new PersonAccessor();
    private UserAccessor userAccessor = new UserAccessor();
    private MovieAccessor movieAccessor = new MovieAccessor();
    private ArrayList<Movie> movieArrayList = new ArrayList<>();
    int totalMen = 0,totalWomen = 0;
    int[] nbAbonnesParDate = new int[5];
    int[] nbAbonnesParAge = new int[6];
    int[] nbFilmsParGenre = new int[6];

    public AdminStats() throws SQLException, ClassNotFoundException {
    }

    ///Méthodes


    //Méthode d'initialisation des données pour les graphiques
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //On initialise le timer qui va s'exécuter toutes les secondes
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


                try {
                    //On met à jour les données
                    nbAdmins.setText(String.valueOf(userAccessor.countAdmin()));
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

        //On initialise les données
        try {
            totalMen = personAccessor.countUsersBySexe("M");
            totalWomen = personAccessor.countUsersBySexe("F");
            nbAbonnesParDate[0] = userAccessor.countUsersFromDate("2023-04-07", "2023-04-09");
            nbAbonnesParDate[1] = userAccessor.countUsersFromDate("2023-04-09", "2023-03-11");
            nbAbonnesParDate[2] = userAccessor.countUsersFromDate("2023-04-11", "2023-03-12");
            nbAbonnesParDate[3] = userAccessor.countUsersFromDate("2023-04-12", "2023-04-14");
            nbAbonnesParDate[4] = userAccessor.countUsersFromDate("2023-04-14", "2023-04-16");
            nbAbonnesParAge[0] = personAccessor.countUsersByAge(0, 18);
            nbAbonnesParAge[1] = personAccessor.countUsersByAge(19, 20);
            nbAbonnesParAge[2] = personAccessor.countUsersByAge(21, 23);
            nbAbonnesParAge[3] = personAccessor.countUsersByAge(24, 26);
            nbAbonnesParAge[4] = personAccessor.countUsersByAge(27, 29);
            nbAbonnesParAge[5] = personAccessor.countUsersByAge(30, 100);
            nbFilmsParGenre[0] = movieAccessor.countMoviesByGenre("Horror");
            nbFilmsParGenre[1] = movieAccessor.countMoviesByGenre("Drama");
            nbFilmsParGenre[2] = movieAccessor.countMoviesByGenre("Action");
            nbFilmsParGenre[3] = movieAccessor.countMoviesByGenre("Fantasy");
            nbFilmsParGenre[4] = movieAccessor.countMoviesByGenre("Adventure");
            nbFilmsParGenre[5] = movieAccessor.countMoviesByGenre("Sci-Fi");
            movieArrayList= movieAccessor.findByPopular(5);
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
        data.getData().add(new XYChart.Data("Horreur",nbFilmsParGenre[0]));
        data.getData().add(new XYChart.Data("Drame",nbFilmsParGenre[1]));
        data.getData().add(new XYChart.Data("Action",nbFilmsParGenre[2]));
        data.getData().add(new XYChart.Data("Fantastique",nbFilmsParGenre[3]));
        data.getData().add(new XYChart.Data("Aventure",nbFilmsParGenre[4]));
        data.getData().add(new XYChart.Data("Science-Fiction",nbFilmsParGenre[5]));

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
                new PieChart.Data(movieArrayList.get(0).getTitle(), movieArrayList.get(0).getViewCount()),
                new PieChart.Data(movieArrayList.get(1).getTitle(), movieArrayList.get(1).getViewCount()),
                new PieChart.Data(movieArrayList.get(2).getTitle(), movieArrayList.get(2).getViewCount()),
                new PieChart.Data(movieArrayList.get(3).getTitle(), movieArrayList.get(3).getViewCount()),
                new PieChart.Data(movieArrayList.get(4).getTitle(), movieArrayList.get(4).getViewCount())
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
        dataSeries1.getData().add(new XYChart.Data("07/04", nbAbonnesParDate[0]));
        dataSeries1.getData().add(new XYChart.Data("09/04", nbAbonnesParDate[1]));
        dataSeries1.getData().add(new XYChart.Data("11/04", nbAbonnesParDate[2]));
        dataSeries1.getData().add(new XYChart.Data("13/04", nbAbonnesParDate[3]));
        dataSeries1.getData().add(new XYChart.Data("15/04", nbAbonnesParDate[4]));

        //Le graphique prend la forme d'une courbe
        lineChart.getData().add(dataSeries1);
        lineChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent");
        lineChart.getYAxis().setStyle("-fx-border-color: transparent #ffffff transparent transparent;  -fx-border-width:3");
        lineChart.getXAxis().setStyle("-fx-border-color: #ffffff transparent transparent transparent;  -fx-border-width:3");

        ////////////////////////////////      BARCHART AGE      ///////////////////////////////////////////////

        //On ajoute les données au graphique
        XYChart.Series dataAge = new XYChart.Series();
        dataAge.setName("Nombre d'abonnés selon leur tranche d'âge");

        dataAge.getData().add(new XYChart.Data("0-18",nbAbonnesParAge[0]));
        dataAge.getData().add(new XYChart.Data("18-20",nbAbonnesParAge[1]));
        dataAge.getData().add(new XYChart.Data("20-23",nbAbonnesParAge[2]));
        dataAge.getData().add(new XYChart.Data("23-26",nbAbonnesParAge[3]));
        dataAge.getData().add(new XYChart.Data("26-29",nbAbonnesParAge[4]));
        dataAge.getData().add(new XYChart.Data("30 et +",nbAbonnesParAge[5]));


        //Le graphique prend la forme d'un histogramme
        barChart2.getData().add(dataAge);

        //On modifie le style du graphique
        barChart2.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent");
        barChart2.getXAxis().setStyle("-fx-border-color: #ffffff transparent transparent transparent;  -fx-border-width:3");
        barChart2.getYAxis().setStyle("-fx-border-color: transparent #ffffff transparent transparent;  -fx-border-width:3");

    };

    //On appelle la fonction qui permet de charger la page des statistiques
    @FXML
    public void HandleShowStatisticsGenre(ActionEvent event) throws Exception {
        appController.loadStatsPage();
    }

    //On appelle la fonction qui permet de charger la page du gestionnaire de catalogue
    @FXML
    private void HandleCatalogGestion(ActionEvent event) throws Exception {
        appController.loadCatalogPage();
    }

    //On appelle la fonction qui permet de charger la page du gestionnaire d'utilisateurs
    @FXML
    private void HandleUser(ActionEvent event) throws Exception {
        appController.loadUserPage();
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
        try {
            appController.setLoginPage();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

    }



}
