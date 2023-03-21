package Model.dao;

import Controller.BDD;

import java.io.IOException;
import java.sql.SQLException;

public abstract class Accessor<T> {

    protected BDD dataBase;

    public abstract T find(int id) throws SQLException, ClassNotFoundException, IOException;

    public abstract int create(T obj) throws SQLException;

    public abstract int update(T obj) throws SQLException;

    public abstract void delete( int id ) throws SQLException;

    public Accessor() throws SQLException, ClassNotFoundException {
        dataBase = BDD.getInstance();
    }
}
