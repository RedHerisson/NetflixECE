package com.Vue.VideoPlayer;


import java.awt.*;

import com.Controller.AppController;
import com.Model.dao.UserDataAccessor;
import com.Model.map.Movie;
import com.Model.map.UserData;
import com.Vue.Controller;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import static java.lang.Math.abs;


public class PlayerController extends Controller implements Initializable {

    @FXML
    private StackPane FullFrame;
    @FXML
    private MediaView viewer;
    @FXML
    private Button playPauseButton, FsButton ;
    @FXML
    private Slider timeSlider;
    @FXML
    private Label timerLabel;
    @FXML
    private Label totalTimeLabel;
    @FXML
    private Slider volumeSlider;

    @FXML
    private HBox ControlItems;

    private boolean controlVisible;

    @FXML
    private AnchorPane bgControl;
    @FXML
    private ImageView playPauseIcon;

    private Media movieMedia;

    private Movie movie;
    private MediaPlayer player;

    private UserData userData;

    private UserDataAccessor userDataAccessor;

    @Override
    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    enum stateButton {
        PLAY, RESUME, REPLAY;

    }
    stateButton state;
    MouseListener mouse;

    @Override

    public void initialize(URL arg0, ResourceBundle arg1) {

    }

    /**
     * lancement du film suivant celui sélectionné
     * @param movie
     * @throws Exception
     */
    public void loadMovie(Movie movie) throws Exception {
        this.movie = movie;
        userDataAccessor = new UserDataAccessor();
        userData = userDataAccessor.findByMovieAndUser(appController.getCurrentuser().getId(), this.movie.getId());
        if( userData == null ) {
                userData = new UserData(-1, appController.getCurrentuser().getId(), movie.getId(), true, 0, "", 0);
                userData.setId(userDataAccessor.create(userData));
        }
        else {
            userData.setView(true);
            userDataAccessor.updateView(userData);
        }


        File mediaFile;
        if( appController.isConnected() ) {
            mediaFile = new File("L:/" + movie.getFilePath() + ".mp4");
        } else {
             mediaFile = new File("demo.mp4");
        }

        movieMedia = new Media(mediaFile.toURI().toString());
        player = new MediaPlayer(movieMedia);
        viewer.setMediaPlayer(player);

        DoubleProperty width = viewer.fitWidthProperty();
        DoubleProperty height = viewer.fitHeightProperty();
        width.bind(Bindings.selectDouble(viewer.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(viewer.sceneProperty(), "height"));


        viewer.setPreserveRatio(true);

        state = stateButton.PLAY;

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    int seek = (int) player.getCurrentTime().toSeconds();
                    userData.setLengthAlreadySeen(seek);
                    userDataAccessor.updateLengthAlreadySeen(userData);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }));

        // Set the cycle count of the timeline to indefinite, so it keeps running until stopped
        timeline.setCycleCount(Timeline.INDEFINITE);

        timeline.play();

        player.play();

        controlVisible = false;

        timerLabel.setText(computeTime(player.getCurrentTime()) + " /");
        totalTimeLabel.setText(computeTime(player.getTotalDuration()));



        player.totalDurationProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observableValue, Duration duration, Duration t1) {
                timeSlider.setMax(t1.toSeconds());
                totalTimeLabel.setText(computeTime(t1));
            }
        });

        timeSlider.valueProperty().addListener(new ChangeListener<Number>() {
           @Override
           public void changed(ObservableValue<? extends Number> observableValue, Number prevValue, Number currentValue) {
               double currentTime = player.getCurrentTime().toSeconds();
               if( Math.abs( currentTime - currentValue.doubleValue()) > 0.5 ) {
                   player.seek(Duration.seconds(currentValue.doubleValue()));
               }
           }
       });

        player.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observableValue, Duration prevTime, Duration currentTime) {
                if (!timeSlider.isValueChanging()) {
                    timeSlider.setValue(currentTime.toSeconds());
                }
                timerLabel.setText( computeTime(currentTime) + " /");
            }
        });

        player.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                state = stateButton.REPLAY;
                playPauseButton.setText("REPLAY");

            }
        });

        volumeSlider.setMax(100);
        volumeSlider.setValue(100);

        player.volumeProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                if( !volumeSlider.isValueChanging()) {
                    volumeSlider.setValue( (double ) t1 );
                }
            }
        });

        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number lastVolume, Number currVolume) {
                player.setVolume( (double) currVolume );
            }
        });


        FullFrame.setOnMouseClicked(event -> {
            setPlay();
        });

        FullFrame.setOnMouseMoved(event -> {

            if (!controlVisible) {

                fadeControl(0);
                controlVisible = true;

                new Timer().schedule(
                        new TimerTask() {
                            @Override
                            public void run() {
                                fadeControl(1);
                                controlVisible = false;
                            }
                        }, 3000
                );
            }
        });

    }

    /**
     * permet de compter le temps qu'il reste à regarder de la bande annonce du film
     * @param duration temps du film
     * @return le temps restant
     */
    public String computeTime(Duration duration ) {
        int hours = ( int ) duration.toHours() % 60;
        int minutes = ( int ) duration.toMinutes() % 60;
        int seconds = ( int ) duration.toSeconds() % 60;

        if(hours > 0 ) return String.format("%d:%02d:%02d", hours, minutes, seconds );
        else return String.format("%02d:%02d", minutes, seconds );
    }

    /**
     * différents boutons de la page de play movie
     */
    public void setPlay() {


        switch (state) {
            case RESUME:
                player.play();
                playPauseButton.setText("Pause");
                break;

            case PLAY:
                player.pause();
                playPauseButton.setText("Play");
                break;

            case REPLAY:
                player.seek(Duration.seconds(0.0));
                player.play();
                playPauseButton.setText("Pause");
                break;
        }

        state = (state == stateButton.PLAY ) ? stateButton.RESUME : stateButton.PLAY;

    }

    /**
     * permet de mettre le film sous format grand écran
     */
    public void setFullScreen() {
        Stage stage = (Stage) FsButton.getScene().getWindow();

        if (stage.isFullScreen() ) {
            stage.setFullScreen(false);
            FsButton.setText("set Full Screen");
        }
        else {
            stage.setFullScreen(true);
            FsButton.setText("remove Full Screen");

        }
    }

    public void fadeControl(int IN_OUT) {
        FadeTransition fadeCtrlItems = new FadeTransition(Duration.seconds(1), ControlItems);
        fadeCtrlItems.setFromValue(IN_OUT);
        fadeCtrlItems.setToValue(1 - IN_OUT);

        FadeTransition fadeTimeSlider = new FadeTransition(Duration.seconds(1), timeSlider);
        fadeTimeSlider.setFromValue(IN_OUT);
        fadeTimeSlider.setToValue(1 - IN_OUT);

        FadeTransition fadeBg = new FadeTransition(Duration.seconds(1), bgControl);
        fadeBg.setFromValue(IN_OUT);
        fadeBg.setToValue(1 - IN_OUT);

        fadeBg.play();
        fadeTimeSlider.play();
        fadeCtrlItems.play();
    }

    public void HandleBackButton(ActionEvent actionEvent) throws IOException {
        appController.setHomePage();

    }


}