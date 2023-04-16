package com.Model.map;

import com.Controller.BDD;
/**
 * Classe abstraite qui permet de définir les méthodes communes aux modèles
 */
public abstract class BdModel {

    protected int id;



    public BdModel(int id) {
        this.id = id;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; };

    public abstract String getTableName();


}
