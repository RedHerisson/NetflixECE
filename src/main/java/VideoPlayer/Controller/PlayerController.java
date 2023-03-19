package VideoPlayer.Controller;


import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import static java.lang.Math.abs;


public class PlayerController implements Initializable{

    @FXML
    private MediaView viewer;
    @FXML
    private Button playPauseButton, FsButton ;
    @FXML
    private Slider timeSlider;

    private Media movie;
    private MediaPlayer player;

    private boolean play;



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

        play = false;

        player.totalDurationProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observableValue, Duration duration, Duration t1) {
                timeSlider.setMax(t1.toSeconds());
            }
        });

        timeSlider.valueProperty().addListener(new ChangeListener<Number>() {
           @Override
           public void changed(ObservableValue<? extends Number> observableValue, Number prevValue, Number currentValue) {
               double currentTime = player.getCurrentTime().toSeconds();
               if( abs( currentTime - currentValue.doubleValue()) > 0.5 ) {
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
            }
        });

    }

    public void setPlay() {
        play = !play;

        if( play == true ) {
            player.play();
            playPauseButton.setText("Pause");
        }
        else {
            player.pause();
            playPauseButton.setText("Play");
        }
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

}