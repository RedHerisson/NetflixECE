package com.Vue;

import com.Controller.AppController;
import com.Model.dao.MovieAccessor;
import com.Model.dao.UserDataAccessor;
import com.Model.map.Movie;
import com.Model.map.User;
import com.Model.map.UserData;
import com.Vue.Carousel.CarouselController;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.geometry.Insets;

import javafx.geometry.NodeOrientation;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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

    private int currentMark = 0;
    private UserData userData;
    private UserDataAccessor userDataAccessor;
    private Movie movie;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            userDataAccessor = new UserDataAccessor();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        rankStarList = new ArrayList<ImageView>();
    }


    public void loadMovie(Movie movie) throws Exception {
        this.movie = movie;
        userData = userDataAccessor.findByMovieAndUser(appController.getCurrentuser().getId(), this.movie.getId());
        if( userData == null ) {
            currentMark = 0;
        }
        else {
            currentMark = userData.getRate();
        }


        FXMLLoader movieInfoPagingData = new FXMLLoader(getClass().getResource("/resources/View/VideoPlayer/movieInfo.fxml"));
        VBox MovieInfoContainer = movieInfoPagingData.load();
        MovieInfoController InfoController = movieInfoPagingData.getController();
        InfoController.setAppController(appController);
        InfoController.updateFromMovie(movie);
        InfoController.setMediaHeightClip(500); // TODO: change for responsive

        mainContainer.getChildren().add(0, MovieInfoContainer);

        ImageView BackArrow = new ImageView();
        BackArrow.setFitHeight(50);
        BackArrow.setFitWidth(50);
        BackArrow.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                appController.setHomePage();
            }
        });
        BackArrow.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                BackArrow.getScene().setCursor(javafx.scene.Cursor.HAND);
            }
        });
        BackArrow.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                BackArrow.getScene().setCursor(Cursor.DEFAULT);
            }
        });
        BackArrow.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        BackArrow.setImage(SwingFXUtils.toFXImage(ImageIO.read(getClass().getResource("/resources/images/icon/arrow.png")), null));
        mainContainer.getChildren().add(0, BackArrow);

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
            suggestionContainer.setPrefHeight(playlist.getPrefHeight() + 1200);
            mainContainer.setPrefHeight(2000);

            // load rankStar from resources
            rankStar = ImageIO.read(getClass().getResource("/resources/images/icon/starFilled.png"));
            rankStarEmpty = ImageIO.read(getClass().getResource("/resources/images/icon/star.png"));

            for(int i= 0 ; i < 5; i++) {
                ImageView star = new ImageView();
                star.setFitHeight(50);
                star.setFitWidth(50);

                star.setImage(SwingFXUtils.toFXImage(rankStarEmpty, null));
                int finalI = i;
                star.hoverProperty().addListener((observable, oldValue, newValue) -> {
                    if(newValue) {
                        updateGraphicRanking(finalI +1);
                    }
                    else {
                        updateGraphicRanking(currentMark);
                    }
                });

                star.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        currentMark = finalI +1 ;
                        updateGraphicRanking(currentMark);
                        try {
                            updateMark(currentMark);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        event.consume();
                    }
                });
                rankStarList.add(star);
                optionContainer.getChildren().add(star);

            }
            updateGraphicRanking(currentMark);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateGraphicRanking(int mark ) {
        for(int i = 0; i < mark; i++) {
            rankStarList.get(i).setImage(SwingFXUtils.toFXImage(rankStar, null));
        }
        for(int i = mark; i < 5; i++) {
            rankStarList.get(i).setImage(SwingFXUtils.toFXImage(rankStarEmpty, null));
        }
    }

    public void updateMark(int mark) throws Exception {
        if( userData == null ) {
            try {
                userData = new UserData(-1,appController.getCurrentuser().getId(), movie.getId(),false, 0, "", mark);
                userData.setId(userDataAccessor.create(userData));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        userData.setRate(mark);
        userDataAccessor.updateRate(userData);
    }

    @Override
    public void setAppController(AppController appController) {
        this.appController = appController;
    }
}
