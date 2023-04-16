package com.Vue.Admin;

import com.Controller.AppController;
import com.Model.dao.MovieAccessor;
import com.Model.map.Movie;
import com.Vue.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Author
 */
public class UpdateMoviesInterface extends Controller implements Initializable {

    ///Attributs
    @FXML
    private TextField newTitle, newType;

    @FXML
    private DatePicker newDate;


    private Movie movie;

    private MovieAccessor movieAccessor = new MovieAccessor();

    public UpdateMoviesInterface() throws SQLException, ClassNotFoundException {
    }

    @Override
    public void initialize(java.net.URL location, ResourceBundle resources) {
        newTitle.setStyle("-fx-text-fill: #FFFFFF; -fx-background-color: transparent; -fx-background-radius: 30; -fx-border-radius: 30; -fx-border-color: #FFFFFF; -fx-border-width: 2;");
        newType.setStyle("-fx-text-fill: #FFFFFF; -fx-background-color: transparent; -fx-background-radius: 30; -fx-border-radius: 30; -fx-border-color: #FFFFFF; -fx-border-width: 2;");
    }

    /**
     * Méthode qui permet de charger tous les films dans la vBox
     */
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
