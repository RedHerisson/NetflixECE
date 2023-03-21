package Model.dao;

import Model.map.MovieHistoric;

import java.sql.SQLException;

public class MovieHistoricAccessor extends Accessor<MovieHistoric> {

    public MovieHistoricAccessor() throws SQLException, ClassNotFoundException {
        super();
    }

    @Override
    public MovieHistoric find(int id) {
        return null;
    }

    @Override
    public int create(MovieHistoric obj) {
        return 0;
    }

    @Override
    public int update(MovieHistoric obj) {

        return 0;
    }

    @Override
    public void delete( int id ) {

    }
}
