package www.ong.affectero.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.layout.AnchorPane;

import www.ong.affectero.NavigationManager;


import java.io.IOException;

public class HomeController {
    @FXML
    private AnchorPane container;
    private static NavigationManager manager;
    public  void  setNavigationManager(NavigationManager manager){
        this.manager = manager;
    }

    public AnchorPane getContainer() {
        return container;
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
    public void goToAssignmentClick() throws IOException {
        FXMLLoader AssignmentLoader = new FXMLLoader(getClass().getResource("/www/ong/affectero/View/AssaignmentView.fxml"));
        AnchorPane secondaryPane = AssignmentLoader.load();
        container.getChildren().clear();
        container.getChildren().add(secondaryPane);
    }
    /*public  void updateSection(AnchorPane container) throws IOException {
        FXMLLoader AssignmentLoader = new FXMLLoader(getClass().getResource("/www/ong/affectero/View/AssaignmentView.fxml"));

        AnchorPane secondaryPane = AssignmentLoader.load();
        manager.navigationToHome();
        container.getChildren().clear();
        container.getChildren().add(secondaryPane);
    }*/

}
