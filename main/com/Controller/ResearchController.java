package com.Controller;


import com.Model.map.Movie;
import com.Model.map.Person;
import com.Model.dao.PersonAccessor;
import com.Model.dao.MovieAccessor;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Controller responsable de la recherche de films
 */
public class ResearchController {

    private ArrayList<Person> listPerson;
    private ArrayList<Movie> listMovies;


    private MovieAccessor movieAccessor;

    /**
     * Constructeur
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ResearchController() throws SQLException, ClassNotFoundException {
        listPerson = new ArrayList<Person>();
        listMovies = new ArrayList<Movie>();
        movieAccessor = new MovieAccessor();
    }

    /**
     * Création d'une liste de films
     * @param title
     * @return
     * @throws Exception
     */
    public ArrayList<Movie> movieSearched(String title) throws Exception {
        MovieAccessor movieAccessor = new MovieAccessor();
        ArrayList<Movie> listMovies = movieAccessor.search(title);
        return listMovies;
    }

}
