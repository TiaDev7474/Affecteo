package www.ong.affectero.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import www.ong.affectero.NavigationManager;
import java.io.IOException;

public class LoginController {
    private NavigationManager manager ;
    @FXML
    private Label username;

    @FXML
    private Label userpassword;
    public  void  setNavigationManager(NavigationManager manager){
        this.manager = manager;
    }
    public void handleLogin(ActionEvent actionEvent) throws IOException {
          manager.navigationToHome();
    }

    public void handleInputFocus(MouseEvent mouseEvent) {
    }

    public void handleforgetPassword(MouseEvent mouseEvent) {
    }
}
