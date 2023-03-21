package Model.dao;

import Model.map.Movie;

import java.sql.SQLException;

public class MovieAccessor extends Accessor<Movie> {


    public MovieAccessor() throws SQLException, ClassNotFoundException {
        super();
    }

    @Override
    public Movie find(int id) {
        return null;
    }

    @Override
    public Movie create(Movie obj) {
        return null;
    }

    @Override
    public Movie update(Movie obj) {
        return null;
    }

    @Override
    public void delete(Movie obj) {

    }
}
