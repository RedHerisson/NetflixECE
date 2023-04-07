package com.Vue.Carousel;

import com.Model.map.Movie;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CarouselController implements Initializable {

    @FXML
    public ImageView nextArrow;
    @FXML
    public ImageView prevArrow;
    @FXML
    private HBox carouselContainer;

    @FXML
    public Label title;

    @FXML


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void nextPage() {

    }

    public void LoadMovies(ArrayList<Movie> movies) throws IOException {


        int nbrMovies = (int) (carouselContainer.getPrefWidth() / 200) + 1;
        System.out.println("Nombre de Films : " + nbrMovies);
        for (int i = 0; i < nbrMovies; i++) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/View/Carousel/MovieIntegration.fxml"));
            StackPane mvContainer = fxmlLoader.load();
            MovieIntegrationController controller = fxmlLoader.<MovieIntegrationController>getController();
            controller.LoadPoster(movies.get(i));

            carouselContainer.getChildren().add(mvContainer);
        }
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }
}
