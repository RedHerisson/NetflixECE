package com.Vue;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import java.awt.event.KeyEvent;
//import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Slider;

public class AdminController implements Initializable {




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    
    @FXML
    private BarChart barChart1;

    @FXML
    private Scene scene;
    @FXML
    private Stage stage;
    @FXML
    private Parent root;


    @FXML
    private PieChart pieChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    @FXML
    private ComboBox comboBox;

    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;

    @FXML
    private LineChart lineChart;
    @FXML
    private void HandleShowStatisticsGenre(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/ressources/View/admin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Genres");
        xAxis.setTickLabelFill(Color.WHITE);
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Nombre de visionnages");
        yAxis.setTickLabelFill(Color.WHITE);
        BarChart barChart = new BarChart(xAxis, yAxis);
        HBox hbox = new HBox();
        pieChart = new PieChart();
        XYChart.Series data = new XYChart.Series();
        data.setName("Nombre de visionnages selon le genre");

        data.getData().add(new XYChart.Data("Horreur",32000));
        data.getData().add(new XYChart.Data("Drame",85600));
        data.getData().add(new XYChart.Data("Action",69230));
        data.getData().add(new XYChart.Data("Fantastique",24870));
        data.getData().add(new XYChart.Data("Aventure",56240));
        data.getData().add(new XYChart.Data("Romantique",12450));

        barChart1.getData().add(data);
//        hbox.getChildren().add(barChart);
//        hbox.setMargin(barChart, new Insets(10, 10, 10, 10));


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


        pieChart.setTitle("Films les plus regardés");
        pieChart.setClockwise(true);
        pieChart.setLabelLineLength(10);
        pieChart.setStartAngle(180);



        ObservableList<PieChart.Data> pieChartDataSexPercentage = FXCollections.observableArrayList(
                new PieChart.Data("Hommes", 12500),
                new PieChart.Data("Femmes", 10790),
                new PieChart.Data("Autre",1260)
        );

        PieChart pieChartSexPercentage = new PieChart(pieChartDataSexPercentage);
        pieChartSexPercentage.setTitle("Proportion M/F");
        pieChartSexPercentage.setClockwise(true);
        pieChartSexPercentage.setLabelLineLength(10);
        pieChartSexPercentage.setStartAngle(360);
        pieChartSexPercentage.setLabelsVisible(false);



        NumberAxis xAxis2 = new NumberAxis();
        xAxis2.setLabel("No of employees");
        xAxis2.setTickLabelFill(Color.WHITE);
        NumberAxis yAxis2 = new NumberAxis();
        yAxis2.setLabel("Revenue per employee");
        yAxis2.setTickLabelFill(Color.WHITE);
        LineChart lineChart = new LineChart(xAxis2, yAxis2);


        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("2014");

        dataSeries1.getData().add(new XYChart.Data( 1, 567));
        dataSeries1.getData().add(new XYChart.Data( 5, 612));
        dataSeries1.getData().add(new XYChart.Data(10, 800));
        dataSeries1.getData().add(new XYChart.Data(20, 780));
        dataSeries1.getData().add(new XYChart.Data(40, 810));
        dataSeries1.getData().add(new XYChart.Data(80, 850));

        lineChart.getData().add(dataSeries1);


    }


    @FXML
    private void HandleCatalogGestion(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/ressources/View/admincatalog.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        comboBox.setValue("Fantastique");
        comboBox.setValue("Horreur");
        comboBox.setValue("Action");
        comboBox.setValue("Drame");

        //Image image = new Image(getClass().getResourceAsStream("/ressources/images/addbutton.jpg"));
        //hbox.getChildren().add(new ImageView(image));


        /*File brandingFile = new File("images/addbutton.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);/*
        Image image = new Image(getClass().getResourceAsStream("images/addbutton.png"));
        ImageView imgview = new ImageView();
        imgview.setImage(image);
        buttonAdd.setGraphic(brandingImageView);*/

    }


    @FXML
    private void HandleUser(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/ressources/View/adminusergestion.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

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

    @FXML
    private void HandleClose(ActionEvent event){
    System.exit(0);
    }



}
