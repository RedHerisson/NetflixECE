package Model.dao;

import Controller.BDD;

import java.sql.SQLException;

public abstract class Accessor<T> {

    protected BDD dataBase;

    public abstract T find(int id) throws SQLException;

    public abstract T create(T obj);

    public abstract T update(T obj) throws SQLException;

    public abstract void delete(T obj);

    public Accessor() throws SQLException, ClassNotFoundException {
        dataBase = BDD.getInstance();
    }
}
