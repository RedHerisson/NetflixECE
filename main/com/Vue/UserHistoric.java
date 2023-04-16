package com.Vue;

import com.Model.dao.PlaylistAccessor;
import com.Model.map.Movie;
import com.Model.map.Playlist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class UserHistoric implements Initializable {

    private Playlist historic;



    @FXML
    private VBox vBox;
    @FXML
    public void ShowHistoric(ActionEvent event) {

        try {
            historic = playlistAccessor.findById(28);
            System.out.println("test");

            for (Movie movie : historic.getMoviesList()) {
                System.out.println(movie.getTitle());
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/View/HistoricMovie.fxml"));
                Pane root = fxmlLoader.load();
                HistoricMovie historicMovie= fxmlLoader.getController();
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