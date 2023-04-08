package com.Model.dao;

import com.Controller.BDD;

import java.io.IOException;
import java.sql.SQLException;

public abstract class Accessor<BdModel> {

    protected BDD dataBase;

    public abstract BdModel findById(int id) throws SQLException, ClassNotFoundException, IOException;

    public abstract BdModel findByName(String name) throws SQLException, ClassNotFoundException, IOException;

    public abstract int create(BdModel obj) throws SQLException, IOException;


    public abstract void delete( int id ) throws SQLException;

    public Accessor() throws SQLException, ClassNotFoundException {
        dataBase = BDD.getInstance();
    }
}
