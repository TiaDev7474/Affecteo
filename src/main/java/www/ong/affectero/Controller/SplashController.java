package www.ong.affectero.Controller;

import www.ong.affectero.NavigationManager;
import javafx.event.ActionEvent;
import java.io.IOException;

public class SplashController {

    private NavigationManager manager ;

    public  void  setNavigationManager(NavigationManager manager){
         this.manager = manager;
    }
    public void handleGoToLoginClick(ActionEvent actionEvent) throws IOException {
          manager.navigationToLogin();
    }
    public void handleRegisterClick(ActionEvent actionEvent) throws IOException{
         manager.navigationToRegister();
    }
}
