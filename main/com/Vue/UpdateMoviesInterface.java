package com.Vue;

import com.Controller.AppController;
import com.Model.dao.MovieAccessor;
import com.Model.map.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateMoviesInterface extends Controller implements Initializable {


    @FXML
    private TextField newTitle, newType;

    @FXML
    private DatePicker newDate;

    @FXML
    private Button confirmUpdate;

    private Movie movie;

    private MovieAccessor movieAccessor = new MovieAccessor();

    public UpdateMoviesInterface() throws SQLException, ClassNotFoundException {
    }

    @Override
    public void initialize(java.net.URL location, ResourceBundle resources) {
        newTitle.setStyle("-fx-text-fill: #FFFFFF; -fx-background-color: transparent; -fx-background-radius: 30; -fx-border-radius: 30; -fx-border-color: #FFFFFF; -fx-border-width: 2;");
        newType.setStyle("-fx-text-fill: #FFFFFF; -fx-background-color: transparent; -fx-background-radius: 30; -fx-border-radius: 30; -fx-border-color: #FFFFFF; -fx-border-width: 2;");
    }

    @FXML
    public void ConfirmUpdate(ActionEvent event) throws SQLException, IOException {
        Movie newMovie = new Movie(movie);
        newMovie.setTitle(newTitle.getText());
        newMovie.setType(newType.getText());
        newMovie.setReleaseDate(newDate.getValue());
        movieAccessor.update(newMovie);

    }

    @Override
    public void setAppController(AppController appController) {
        this.appController = appController;
    }
}
