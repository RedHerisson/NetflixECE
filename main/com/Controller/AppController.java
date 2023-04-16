package com.Controller;

import com.Model.dao.MovieAccessor;
import com.Model.dao.PlaylistAccessor;
import com.Model.dao.UserAccessor;
import com.Model.dao.UserDataAccessor;
import com.Model.map.Movie;
import com.Model.map.Playlist;
import com.Model.map.User;
import com.Model.map.UserData;
import com.Vue.HomeController;
import com.Vue.LoginController;
import com.Vue.MoviePres;
import com.Vue.RegisterController;
import com.Vue.VideoPlayer.PlayerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;


public class AppController extends Application {

    private Stage mainStage;

    private Scene scene;

    private User loginUser;

    private boolean NasConnected = false;

    @Override
    public void start(Stage stage) throws Exception {

    mainStage = stage;
    TestConnection();
    setLoginPage();
    }

    private void TestConnection() {
        try {
            File file = new File("L:/test.txt");
            if (file.exists()) {
                NasConnected = true;
                System.out.println("NAS connected");
            }
            else {
                System.out.println("NAS NOT connected");
                NasConnected = false;
            }
        } catch (Exception e) {
            NasConnected = false;
        }

    }

    public void setLoginPage() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/View/login.fxml"));
        scene = new Scene(loader.load(), 600, 400.0, Color.BLACK);

        LoginController logCtrl = loader.getController();
        logCtrl.setAppController(this);

        mainStage.setScene(scene);
        mainStage.setTitle("Login");

        mainStage.setResizable(false);

        mainStage.show();
    }

    public void setHomePage() {
        try {
            FXMLLoader loaderHome = new FXMLLoader(getClass().getResource("/resources/View/home.fxml"));

            scene = new Scene( loaderHome.load(), 1275, 645, Color.BLACK );
            HomeController controller = loaderHome.getController();
            controller.setAppController(this);

            loadHomeFromUser(controller);

            mainStage.setTitle("NetflixECE");

            mainStage.setScene(scene);
            mainStage.setResizable(false);
            mainStage.show();
            controller.backToTop();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setMoviePresPage(Movie movie) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/View/moviePres.fxml"));
            scene = new Scene(loader.load(), 1275, 645, Color.BLACK);
            MoviePres controller = loader.getController();
            controller.setAppController(this);
            controller.loadMovie(movie);

            mainStage.setResizable(false);

            mainStage.setScene(scene);
            mainStage.setTitle(movie.getTitle());
            mainStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadHomeFromUser(HomeController controller) throws SQLException, ClassNotFoundException, IOException {
        MovieAccessor movieAccessor = new MovieAccessor();
        Movie movie = movieAccessor.findById(130);
        UserDataAccessor userDataAccessor = new UserDataAccessor();
        controller.setAppController(this);

        controller.AddPromotion(movie);


        ArrayList<Movie> movieStarted = loginUser.getMovieStarted();
        // reverse array
        Collections.reverse(movieStarted);
        if( movieStarted.size() != 0 ) controller.AddPlaylist(movieStarted, "Continue to watch", -1);

        ArrayList<Movie> movieFromContinue = loginUser.getWatchList().getMoviesList();
        if( movieFromContinue.size() != 0) controller.AddPlaylist(movieFromContinue, "In Watchlist", -1);

        ArrayList<String> TypeFromHistory = loginUser.getTypeFromHistory();
        ArrayList<Movie> movieFromHistory = new ArrayList<Movie>();
        for (String type : TypeFromHistory) {
            System.out.println(type);
            ArrayList<Movie> movies = movieAccessor.findByType(type, 1);
            // melange des films pour avoir un affichage aléatoire
            for (int i = 0; i < movies.size(); i++) {
                int rand = (int) (Math.random() * movies.size());
                Movie tmp = movies.get(i);
                movies.set(i, movies.get(rand));
                movies.set(rand, tmp);
            }
            movieFromHistory.addAll(movies);
        }
        controller.AddPlaylist(movieFromHistory, "Type From your history", -1);

        ArrayList<Movie> movieFromPopular = movieAccessor.findByPopular(20);
        controller.AddPlaylist(movieFromPopular, "Most Popular", -1);

        ArrayList<Movie> movieFromBestRank = movieAccessor.findByRank(20);
        controller.AddPlaylist(movieFromBestRank, "Best Ranking", -1);

        ArrayList<Movie> RecentMovies = movieAccessor.findByDate(20);
        controller.AddPlaylist(RecentMovies, "Recent releases", -1);

    }

    public void loginComplete(User user){

        loginUser = user;
        setHomePage();
    }

    public static void Main(String[] args){
        launch(args);
    }


    public void setRegisterPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/View/registration.fxml"));
            scene = new Scene(loader.load(), 600, 500, Color.BLACK);

            RegisterController logCtrl = loader.getController();
            logCtrl.setAppController(this);

            mainStage.setScene(scene);
            mainStage.setTitle("Register");

            mainStage.setResizable(false);

            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadPlayer(Movie movie) {
        try {
            UserAccessor userAccessor = new UserAccessor();
            PlaylistAccessor playlistAccessor = new PlaylistAccessor();
            MovieAccessor movieAccessor = new MovieAccessor();
            // userData Update
            loginUser.addMovieToHistory(movie);
            System.out.println(loginUser.getHistory().getId());
            playlistAccessor.addMovie(movie, loginUser.getHistory());

            movieAccessor.addView(movie.getId());

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/View/VideoPlayer/player.fxml"));
            scene = new Scene(loader.load(), 1275, 645, Color.BLACK);
            PlayerController controller = loader.getController();
            controller.setAppController(this);

            controller.loadMovie(movie);

            mainStage.setResizable(true);

            mainStage.setScene(scene);
            mainStage.setTitle(movie.getTitle());

            mainStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User getCurrentuser() {
        return loginUser;
    }

    public boolean isConnected() {
        return NasConnected;
    }
}

