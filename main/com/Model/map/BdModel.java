package com.Model.map;

import com.Controller.BDD;

public abstract class BdModel {

    protected int id;

    public BdModel(int id) {
        this.id = id;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; };

    public abstract String getTableName();


}
