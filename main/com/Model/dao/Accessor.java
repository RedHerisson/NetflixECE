package com.Model.dao;

import com.Controller.BDD;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Classe abstraite pour les accessors
 * @param <BdModel> Classe du modèle
 */
public abstract class Accessor<BdModel> {

    protected BDD dataBase;

    /**
     * Trouve un objet par son id
     * @param id
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public abstract BdModel findById(int id) throws SQLException, ClassNotFoundException, IOException;

    /**
     * Crée un objet en base de données à partir d'un modèle
     * @param obj
     * @return
     * @throws SQLException
     * @throws IOException
     */
    public abstract int create(BdModel obj) throws SQLException, IOException;

    /**
     * Supprime un objet en base de données
     * @param id ID de l'objet à supprimer
     * @throws SQLException
     */
    public abstract void delete( int id ) throws SQLException;

    /**
     * Constructeur
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Accessor() throws SQLException, ClassNotFoundException {
        dataBase = BDD.getInstance();
    }
}
