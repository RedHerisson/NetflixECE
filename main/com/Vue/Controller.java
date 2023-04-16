package com.Vue;

import com.Controller.AppController;

public abstract class Controller {

    protected AppController appController;

    public void setAppController(AppController appController) {
        this.appController = appController;
    };


}
