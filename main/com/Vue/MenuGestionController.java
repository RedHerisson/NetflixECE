package com.Vue;

import com.Controller.AppController;
import com.Model.dao.MovieAccessor;
import com.Model.map.Movie;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MenuGestionController extends Controller implements Initializable {

    private Movie movie;
    private MovieAccessor posterGetter;

    private Stage mainStage;

    private Scene scene;

    @FXML
    private Pane darkLayer;

    @FXML
    private Label nameActors, titleMovie, nameGenre;

    @FXML
    private ImageView posterContainer;

    private AdminCatalog adminCatalog = new AdminCatalog();

    public MenuGestionController() throws SQLException, ClassNotFoundException {
    }


    @Override
    public void initialize(java.net.URL location, ResourceBundle resources) {
        try {
            posterGetter = new MovieAccessor();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

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
    public void updateMovie(ActionEvent event) throws SQLException, IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/resources/View/UpdateMoviesInterface.fxml"));
        /*
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("Update Movie");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void deleteMovie(ActionEvent event) throws Exception {
        posterGetter.delete(movie.getId());


        adminCatalog.HandleSearch(event);
    }
}
