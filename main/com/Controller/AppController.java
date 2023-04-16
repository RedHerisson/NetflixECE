package com.Controller;

import com.Model.dao.MovieAccessor;
import com.Model.dao.PlaylistAccessor;
import com.Model.dao.UserAccessor;
import com.Model.dao.UserDataAccessor;
import com.Model.map.Movie;
import com.Model.map.Playlist;
import com.Model.map.User;
import com.Model.map.UserData;
import com.Vue.*;
import com.Vue.VideoPlayer.PlayerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
    public void setUserSettings() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/View/UserSettings.fxml"));
        scene = new Scene(loader.load(), 1080, 645, Color.BLACK);

        UserSettings logCtrl = loader.getController();
        logCtrl.setAppController(this);

        mainStage.setScene(scene);
        mainStage.setTitle("UserSettings");

        Image icon = new Image(getClass().getResourceAsStream("/resources/images/ECE_LOGO.png"));
        mainStage.getIcons().add(icon);

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
        UserDataAccessor userDataAccessor = new UserDataAccessor();
        controller.setAppController(this);

        ArrayList<Movie> movieStarted = loginUser.getMovieStarted();
        // reverse array
        Collections.reverse(movieStarted);
        if( movieStarted.size() != 0 ) controller.AddPlaylist(movieStarted, "Continue to watch", -1);

        ArrayList<Movie> movieFromContinue = loginUser.getWatchList().getMoviesList();
        if( movieFromContinue.size() != 0) controller.AddPlaylist(movieFromContinue, "In Watchlist", -1);

        ArrayList<String> TypeFromHistory = loginUser.getTypeFromHistory();
        Collections.shuffle(TypeFromHistory);
        TypeFromHistory = new ArrayList<String>(new java.util.HashSet<String>(TypeFromHistory));
        if( TypeFromHistory.size() > 4 ) TypeFromHistory = new ArrayList<String>(TypeFromHistory.subList(0, 4));

        ArrayList<Movie> movieFromPopular = movieAccessor.findByPopular(20);
        controller.AddPlaylist(movieFromPopular, "Most Popular", -1);

        ArrayList<Movie> movieFromBestRank = movieAccessor.findByRank(20);
        controller.AddPlaylist(movieFromBestRank, "Best Ranking", -1);

        ArrayList<Movie> RecentMovies = movieAccessor.findByDate(20);
        controller.AddPlaylist(RecentMovies, "Recent releases", -1);

        for (String type : TypeFromHistory) {
            System.out.println(type);
            ArrayList<Movie> movies = movieAccessor.findByType(type, 40);
            // melange des films pour avoir un affichage aléatoire
            for (int i = 0; i < movies.size(); i++) {
                int rand = (int) (Math.random() * movies.size());
                Movie tmp = movies.get(i);
                movies.set(i, movies.get(rand));
                movies.set(rand, tmp);
            }
            if( movies.size() != 0 )
                controller.AddPlaylist(movies, "You like " + type, -1);
        }

        int nbrPlayList = controller.getNbrPlayList();
        ArrayList<Movie> MovieToPromote = movieAccessor.getRandPromotedMovies(3);
        for (int i = 0; i < MovieToPromote.size(); i++) {
            if( i == 0 ) controller.AddPromotion(MovieToPromote.get(i), 0);
            if( i == 1 ) controller.AddPromotion(MovieToPromote.get(i), controller.getNbrPlayList()/2);
            if( i == 2 ) controller.AddPromotion(MovieToPromote.get(i), controller.getNbrPlayList());
        }

    }

    public void loginComplete(User user) throws Exception {

        loginUser = user;
        if( loginUser.isAdmin()) {
            launchAdmin();
        }
        else {
            setHomePage();
        }
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

    public void launchAdmin() throws Exception {

        FXMLLoader loaderHome = new FXMLLoader(getClass().getResource("/resources/View/adminStats.fxml"));
        this.scene = new Scene(loaderHome.load(), 1275, 645, Color.BLACK);
        AdminStats controller = loaderHome.getController();
        controller.setAppController(this);
        mainStage.setScene(scene);
        mainStage.setResizable(false);
        mainStage.show();
    }

    //Méthode pour load la page des statistiques
    public void loadStatsPage() throws Exception {
        AdminStats adminStats = new AdminStats();
        FXMLLoader loaderHome = new FXMLLoader(getClass().getResource("/resources/View/adminStats.fxml"));
        scene = new Scene(loaderHome.load(), 1275, 645, Color.BLACK);
        AdminStats controller = loaderHome.getController();
        controller.setAppController(this);
        mainStage.setScene(scene);
        mainStage.setResizable(false);
        mainStage.show();

    }

    //Méthode pour load la page du catalogue
    public void loadCatalogPage() throws Exception {
        AdminCatalog adminCatalog = new AdminCatalog();
        FXMLLoader loaderHome = new FXMLLoader(getClass().getResource("/resources/View/adminCatalog.fxml"));
        scene = new Scene(loaderHome.load(), 1275, 645, Color.BLACK);
        AdminCatalog controller = loaderHome.getController();
        controller.setAppController(this);
        mainStage.setScene(scene);
        mainStage.setResizable(false);
        mainStage.show();
    }

    //Méthode pour load la page des utilisateurs
    public void loadUserPage() throws Exception {
        AdminUser adminUser = new AdminUser();
        FXMLLoader loaderHome = new FXMLLoader(getClass().getResource("/resources/View/adminUserGestion.fxml"));
        scene = new Scene(loaderHome.load(), 1275, 645, Color.BLACK);
        AdminUser controller = loaderHome.getController();
        controller.setAppController(this);
        mainStage.setScene(scene);
        mainStage.setResizable(false);
        mainStage.show();
    }

    public User getCurrentuser() {
        return loginUser;
    }

    public boolean isConnected() {
        return NasConnected;
    }
}

