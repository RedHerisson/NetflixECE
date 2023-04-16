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

public class MenuGestionController extends Controller implements Initializable {

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
    private Button updatePromoButton, deleteButton, editButton;

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

    //On set un movie qui va charger les affiches de chaque film
    public void setMovie(Movie movie) throws SQLException, IOException {
        //On set le movie et les attributs qu'il faut afficher
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

        updatePromoButtonGraphic();

    }

    public void updatePromoButtonGraphic() {
        if (movie.isPromoted()) {
            updatePromoButton.setStyle("-fx-background-color: blue");
        }
        else {
            updatePromoButton.setStyle("-fx-background-color: red");
        }
    }

    @Override
    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    @FXML
    public void updateMovie(ActionEvent event) throws SQLException, IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/resources/View/UpdateMoviesInterface.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("Update Movie");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void deleteMovie(ActionEvent event) throws Exception {
        posterGetter.delete(movie.getId());
        System.out.println("Movie deleted");

    }

    @FXML
    public void updatePromotion(ActionEvent event) throws Exception {
        movie.setPromoted(true);
        MovieAccessor movieAccessor = new MovieAccessor();
        movieAccessor.updatePromoted(movie);
        updatePromoButtonGraphic();

    }

    //create a function that decrease the opacity of the buttons when the mouse is over them
    public void decreaseOpacityOnHover1(MouseEvent event) {
        updatePromoButton.setOpacity(0.7);
    }

    //create a function that increase the opacity of the buttons when the mouse is over them
    public void increaseOpacityOnHover1(MouseEvent event) {
        updatePromoButton.setOpacity(1.0);

    }

    //create a function that decrease the opacity of the buttons when the mouse is over them
    public void decreaseOpacityOnHover2(MouseEvent event) {
        editButton.setOpacity(0.7);
    }

    //create a function that increase the opacity of the buttons when the mouse is over them
    public void increaseOpacityOnHover2(MouseEvent event) {
        editButton.setOpacity(1.0);

    }

    //create a function that decrease the opacity of the buttons when the mouse is over them
    public void decreaseOpacityOnHover3(MouseEvent event) {
        deleteButton.setOpacity(0.7);
    }

    //create a function that increase the opacity of the buttons when the mouse is over them
    public void increaseOpacityOnHover3(MouseEvent event) {
        deleteButton.setOpacity(1.0);

    }
}
