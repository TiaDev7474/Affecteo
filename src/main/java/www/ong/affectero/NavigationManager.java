package www.ong.affectero;

import www.ong.affectero.Controller.HomeController;
import www.ong.affectero.Controller.LoginController;
import www.ong.affectero.Controller.RegisterController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
public class NavigationManager {
    private Stage stage;
    public  NavigationManager(Stage stage){
        this.stage = stage;
    }

    public void navigationToLogin() throws IOException{
        FXMLLoader LoginLoader = new FXMLLoader(getClass().getResource("View/LoginView.fxml"));
        Parent root = LoginLoader .load();
        LoginController  controller = LoginLoader.getController();
        controller.setNavigationManager(this);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void navigationToRegister() throws IOException{
        FXMLLoader RegisterLoader = new FXMLLoader(getClass().getResource("View/RegisterView.fxml"));
        Parent root = RegisterLoader.load();
        RegisterController  controller = RegisterLoader.getController();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void navigationToHome() throws IOException{
        FXMLLoader HomeLoader = new FXMLLoader(getClass().getResource("View/HomeView.fxml"));
        Parent root = HomeLoader.load();
        HomeController  controller = HomeLoader.getController();
        controller.setNavigationManager(this);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public  void  navigationToSplash() throws  IOException{
        FXMLLoader SplashLoader = new FXMLLoader(getClass().getResource("View/SplashView.fxml"));
        Parent root = SplashLoader.load();
        HomeController  controller = SplashLoader.getController();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
