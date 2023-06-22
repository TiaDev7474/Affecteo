package com.example.affectero.Controller;

import com.example.affectero.NavigationManager;
import javafx.event.ActionEvent;

import java.io.IOException;

public class SplashController {

    private NavigationManager manager ;

    public  void  setNavigationManager(NavigationManager manager){
         this.manager = manager;
    }

    public void handleLoginClick(ActionEvent actionEvent) throws IOException {
          manager.navigationToLogin();
    }


    public void handleRegisterClick(ActionEvent actionEvent) throws IOException{
         manager.navigationToRegister();
    }
}
