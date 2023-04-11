package com.Vue.Carousel;

import com.Model.dao.MovieAccessor;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CarouselController implements Initializable {

    @FXML
    private ImageView nextArrow;
    @FXML
    private ImageView prevArrow;
    @FXML
    private HBox carouselContainer;

    @FXML
    public Label title;


    private ArrayList<MovieIntegrationController> controllerList;
    private ArrayList<Movie> fullMovieArray;

    private int currentPage, maxPage, nbrMoviesPerPage ;

    private MovieAccessor posterGetter;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fullMovieArray = new ArrayList<Movie>();
        controllerList = new ArrayList<MovieIntegrationController>();
        currentPage = 1;
        maxPage = 1;
        nbrMoviesPerPage = 0;
        try {
            posterGetter = new MovieAccessor();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        prevArrow.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue) {
                prevArrow.setFitWidth(prevArrow.getFitWidth() + 10);
                prevArrow.setFitHeight(prevArrow.getFitHeight() + 10);
            } else {
                prevArrow.setFitWidth(prevArrow.getFitWidth() - 10);
                prevArrow.setFitHeight(prevArrow.getFitHeight() - 10);
            }
        });

        nextArrow.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue) {
                nextArrow.setFitWidth(nextArrow.getFitWidth() + 10);
                nextArrow.setFitHeight(nextArrow.getFitHeight() + 10);
            } else {
                nextArrow.setFitWidth(nextArrow.getFitWidth() - 10);
                nextArrow.setFitHeight(nextArrow.getFitHeight() - 10);
            }
        });
    }
    @FXML
    public void nextPage() {
        if(currentPage < maxPage) {
            currentPage++;

            try {
                updatePage();
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }

    }
    @FXML
    private void prevPage() {
        if(currentPage > 1) {
            currentPage--;
            try {
                updatePage();
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void updatePage() throws IOException, SQLException {

        int showMax = 7;
        int showMin = (currentPage - 1) * nbrMoviesPerPage;


        if( ( showMin + showMax ) > fullMovieArray.size()  ) {
            showMax = fullMovieArray.size() - showMin;

        }
        System.out.println("Page : " + currentPage);
        System.out.println("Min : " + showMin);
        System.out.println("Max : " + showMax);

        for(int i = 0; i < nbrMoviesPerPage; i++ ) {
            controllerList.get(i).setMovie(null);
        }

        for (int i = showMin; i <showMin + showMax; i++) {
            // get the controller of the movieIntegration from the carousel
            controllerList.get(i - showMin).setMovie(fullMovieArray.get(i));
        }
    }

    private void updateSize() throws IOException {
        nbrMoviesPerPage = (int) (carouselContainer.getPrefWidth() / 200) + 1;
        maxPage = (int) (fullMovieArray.size() / nbrMoviesPerPage) + 1;
        for(int i = 0; i < nbrMoviesPerPage; i++ ) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/View/Carousel/MovieIntegration.fxml"));
            StackPane mvContainer = fxmlLoader.load();
             controllerList.add(fxmlLoader.<MovieIntegrationController>getController());
            carouselContainer.getChildren().add(mvContainer);
        }
        System.out.println("Nombre de Films : " + nbrMoviesPerPage);
        System.out.println("Nombre de page : " + maxPage);

    }

    public void LoadMovies(ArrayList<Movie> movies) throws IOException, SQLException {

        fullMovieArray = movies;
        updateSize();
        updatePage();

    }

    public void setTitle(String title) {

        this.title.setText(title);
    }
}
