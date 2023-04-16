package com.Vue.UserSettings;

import com.Controller.AppController;
import com.Model.dao.MovieAccessor;
import com.Model.map.Movie;
import com.Vue.Controller;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;
/**
 * FXML Controller class
 *
 * @author Author
 */
public class HistoricMovie extends Controller implements Initializable {
    ///Attributs
    private Movie movie;
    private MovieAccessor posterGetter;
    @FXML
    private Pane darkLayer;

    @FXML
    private Label nameActors, titleMovie, nameGenre;

    @FXML
    private ImageView posterContainer;

    @FXML
    private Button addPromoButton, deleteButton, editButton;


    public HistoricMovie() throws SQLException, ClassNotFoundException {
    }


    @Override
    public void initialize(java.net.URL location, ResourceBundle resources) {
        try {
            posterGetter = new MovieAccessor();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    /**
     * @param bPoster
     */
    @FXML
    private void LoadPoster(BufferedImage bPoster) {

        if (bPoster != null) {
            Image poster = SwingFXUtils.toFXImage(bPoster, null);
            posterContainer.setImage(poster);
        }
        else {
            posterContainer.setImage(null);
        }
    }

    /**
     * @param movie
     * @throws SQLException
     * @throws IOException
     */
    public void setMovie(Movie movie) throws SQLException, IOException {

        this.movie = movie;
        if( movie != null) {
            LoadPoster( posterGetter.loadPoster(movie.getId()));
        }
        else {
            LoadPoster(null);
        }
        titleMovie.setText(movie.getTitle());
        String actors = "";
        for (int i = 0; i < movie.getActors().size(); i++) {
            actors += movie.getActors().get(i).getSurname() + " " + movie.getActors().get(i).getName() + ", ";
        }
        actors = actors.substring(0, actors.length() - 2);
        nameActors.setText(actors);
        nameGenre.setText(movie.getTypes());
    }

    @Override
    public void setAppController(AppController appController) {
        this.appController = appController;
    }
    @FXML
    public void deleteMovie(ActionEvent event) throws Exception {
        appController.getCurrentuser().removeMovieFromHistory(movie);

        System.out.println("Movie deleted");

    }
    public void decreaseOpacityOnHover3(MouseEvent event) {
        deleteButton.setOpacity(0.7);
    }

    //create a function that increase the opacity of the buttons when the mouse is over them
    public void increaseOpacityOnHover3(MouseEvent event) {
        deleteButton.setOpacity(1.0);

    }
}
