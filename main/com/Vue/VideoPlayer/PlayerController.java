package com.Vue.VideoPlayer;


import java.awt.*;

import javafx.animation.FadeTransition;
import javafx.scene.input.MouseEvent;

import java.awt.event.MouseListener;
import java.io.File;
import java.net.URL;
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


public class PlayerController implements Initializable{

    @FXML
    private StackPane FullFrame;
    @FXML
    private Rectangle bg;
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


    private Media movie;
    private MediaPlayer player;

    enum stateButton {
        PLAY, RESUME, REPLAY;

    }
    stateButton state;

    MouseListener mouse;

    @Override

    public void initialize(URL arg0, ResourceBundle arg1) {

        File file = new File("star.mp4");
        movie = new Media(file.toURI().toString());
        player = new MediaPlayer(movie);
        viewer.setMediaPlayer(player);

        DoubleProperty width = viewer.fitWidthProperty();
        DoubleProperty height = viewer.fitHeightProperty();
        width.bind(Bindings.selectDouble(viewer.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(viewer.sceneProperty(), "height"));


        viewer.setPreserveRatio(true);

        state = stateButton.RESUME;

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

            if (controlVisible == false ) {

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

    public String computeTime(Duration duration ) {
        int hours = ( int ) duration.toHours() % 60;
        int minutes = ( int ) duration.toMinutes() % 60;
        int seconds = ( int ) duration.toSeconds() % 60;

        if(hours > 0 ) return String.format("%d:%02d:%02d", hours, minutes, seconds );
        else return String.format("%02d:%02d", minutes, seconds );
    }

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


}