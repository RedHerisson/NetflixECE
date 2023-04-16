package com.Vue.UserSettings;

import com.Controller.AppController;
import com.Model.dao.PlaylistAccessor;
import com.Model.map.Movie;
import com.Model.map.Playlist;
import com.Vue.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class UserHistoric extends Controller implements Initializable {

    private Playlist historic;
    @FXML
    private VBox vBox;

    @FXML
    public void ShowHistoric(AppController appController) {
        this.appController = appController;
        try {
            historic = appController.getCurrentuser().getHistory();
            System.out.println("test");

            for (Movie movie : historic.getMoviesList()) {
                System.out.println(movie.getTitle());
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/View/UserSettings/HistoricMovie.fxml"));
                Pane root = fxmlLoader.load();
                HistoricMovie historicMovie= fxmlLoader.getController();
                historicMovie.setAppController(appController);
                historicMovie.setMovie(movie);

                vBox.getChildren().add(root);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
    private  PlaylistAccessor playlistAccessor = new PlaylistAccessor();

    public UserHistoric() throws SQLException, ClassNotFoundException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {





        }

}