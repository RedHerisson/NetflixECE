package com.Vue;

import com.Controller.AppController;
import com.Model.dao.MovieAccessor;
import com.Model.map.Movie;
import com.Vue.Carousel.CarouselController;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.geometry.Insets;

import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

// TODO : margin to the movie suggestion carousel
public class MoviePres  extends Controller implements  Initializable {

    @FXML
    private VBox mainContainer;
    @FXML
    private Pane suggestionContainer;
    private VBox MovieInfoContainer;
    @FXML
    private HBox optionContainer;

    private BufferedImage rankStar, rankStarEmpty;

    private ArrayList<ImageView> rankStarList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void loadMovie(Movie movie) throws IOException {

        FXMLLoader movieInfoPagingData = new FXMLLoader(getClass().getResource("/resources/View/VideoPlayer/movieInfo.fxml"));
        VBox MovieInfoContainer = movieInfoPagingData.load();
        MovieInfoController InfoController = movieInfoPagingData.getController();
        InfoController.setAppController(appController);
        InfoController.updateFromMovie(movie);
        InfoController.setMediaHeightClip(800); // TODO: change for responsive

        mainContainer.getChildren().add(0, MovieInfoContainer);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/View/Carousel/Carousel.fxml"));
            VBox playlist = fxmlLoader.load();
            // change playlist margin
            playlist.setMargin(playlist, new Insets(30, 0, 0, 0));
            CarouselController controller = fxmlLoader.<CarouselController>getController();
            controller.setAppController(appController);

            String mainType = "Action";
            ArrayList<Movie> movies = new ArrayList<Movie>();
            MovieAccessor movieAccessor = new MovieAccessor();
            try {
                 mainType = movie.getTypeArray().get(0);
                 movies = movieAccessor.findByType(mainType, 100);
            } catch( IndexOutOfBoundsException e ) {
                System.out.println("No type found for movie " + movie.getTitle());
            }

            controller.setTitle("Other " + mainType + " movies");

            controller.LoadMovies(movies);

            suggestionContainer.getChildren().add(playlist);
            suggestionContainer.setPrefHeight(playlist.getPrefHeight() + 1000);

            // load rankStar from resources
            rankStar = ImageIO.read(getClass().getResource("/resources/images/icon/starFilled.png"));
            rankStarEmpty = ImageIO.read(getClass().getResource("/resources/images/icon/star.png"));

            for(int i= 0 ; i < 5; i++) {
                ImageView star = new ImageView();
                star.setFitHeight(50);
                star.setFitWidth(50);

                star.setImage(SwingFXUtils.toFXImage(rankStarEmpty, null));
                star.hoverProperty().addListener((observable, oldValue, newValue) -> {
                    if(newValue) {
                        star.setImage(SwingFXUtils.toFXImage(rankStar, null));
                        optionContainer.getChildren().get(0);

                    }
                    else {
                        star.setImage(SwingFXUtils.toFXImage(rankStarEmpty, null));
                    }
                });

                rankStarList.add(star);
                optionContainer.getChildren().add(star);



            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void setUserRanking(int rank) {
        //optionContainer
    }


    @Override
    public void setAppController(AppController appController) {
        this.appController = appController;
    }
}
