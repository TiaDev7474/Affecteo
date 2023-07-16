package www.ong.affectero.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import javafx.scene.layout.AnchorPane;

import www.ong.affectero.NavigationManager;


import java.io.IOException;
import java.sql.SQLException;

public class HomeController {
    @FXML
    private AnchorPane container;
    private NavigationManager manager;
    public  void  setNavigationManager(NavigationManager manager){
        this.manager = manager;
    }

    public void goToLocationClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader locationLoader = new FXMLLoader(getClass().getResource("/www/ong/affectero/View/LocationView.fxml"));
        AnchorPane secondaryPane = locationLoader.load();
        container.getChildren().clear();
        container.getChildren().add(secondaryPane);
    }
    public void goToEmployeeClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader EmployeeLoader = new FXMLLoader(getClass().getResource("/www/ong/affectero/View/EmployeeView.fxml"));
        AnchorPane secondaryPane = EmployeeLoader.load();
        container.getChildren().clear();
        container.getChildren().add(secondaryPane);
    }
}
