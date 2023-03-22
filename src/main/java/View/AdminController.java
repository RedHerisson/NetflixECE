package View;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.layout.BorderPane;

public class AdminController implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    @FXML
    private BorderPane borderPane;

    @FXML
    private void HandleShowStatisticsGenre(ActionEvent event){
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Genres");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Nombre de visionnages");

        BarChart barChart = new BarChart(xAxis, yAxis);

        XYChart.Series data = new XYChart.Series();
        data.setName("Nombre de visionnages selon le genre");

        data.getData().add(new XYChart.Data("Horreur",32000));
        data.getData().add(new XYChart.Data("Drame",85600));
        data.getData().add(new XYChart.Data("Action",69230));
        data.getData().add(new XYChart.Data("Fantastique",24870));
        data.getData().add(new XYChart.Data("Aventure",56240));
        data.getData().add(new XYChart.Data("Romantique",12450));

        barChart.getData().add(data);

        borderPane.setCenter(barChart);
    }


    @FXML
    private void HandleShowStatisticsVisionnages(ActionEvent event){
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Pulp Fiction", 1253000),
                new PieChart.Data("Interstellar", 1138790),
                new PieChart.Data("Twilight", 1084560),
                new PieChart.Data("Avatar", 983000),
                new PieChart.Data("You should not kill", 982563),
                new PieChart.Data("Joker", 972563),
                new PieChart.Data("Titanic", 962563),
                new PieChart.Data("Harry Potter", 952563),
                new PieChart.Data("Hunger Games", 902563),
                new PieChart.Data("Memento", 852563)
        );

        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Films les plus regardés");
        pieChart.setClockwise(true);
        pieChart.setLabelLineLength(50);
        pieChart.setLabelsVisible(true);
        pieChart.setStartAngle(180);

        borderPane.setLeft(pieChart);


    }

    @FXML
    private void HandleClose(ActionEvent event){
    System.exit(0);
    }



}
