package com.Vue;

import com.Controller.AppController;

public abstract class Controller {
    //Classe abstraite qui permet de définir les méthodes communes aux controllers
    protected AppController appController;

    public void setAppController(AppController appController) {
        this.appController = appController;
    };


}
