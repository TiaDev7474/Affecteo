package www.ong.affectero;

import www.ong.affectero.Controller.SplashController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        NavigationManager navigationManager = new NavigationManager(stage);
        FXMLLoader SplashLoader = new FXMLLoader(MainApplication.class.getResource("View/SplashView.fxml"));
        Parent root = SplashLoader.load();
        SplashController controller = SplashLoader.getController();
        controller.setNavigationManager(navigationManager);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}