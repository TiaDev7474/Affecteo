package www.ong.affectero.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import www.ong.affectero.MainApplication;
import www.ong.affectero.Model.Location;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;



public class AddLocationController {
    public ComboBox provinceCombobBox;
    @FXML
    private TextField designField;
    private static Stage modalStage;


    public void initialize(){
        ObservableList<String> provinceOptions = FXCollections.observableArrayList("Antananarivo","Fianarantsoa","Toliara","Mahajanga","Toamasina","Antsiranana");
        provinceCombobBox.setItems(provinceOptions);
    }

    public String getSelectedProvince(){
         return (String) provinceCombobBox.getValue();
    }

    public String getDesignValue() {
        return designField.getText();
    }

    public static void showAddLocationForm() throws IOException {
        try{
            FXMLLoader addLocationLoader = new FXMLLoader(MainApplication.class.getResource("/www/ong/affectero/View/AddLocationModal.fxml"));
            Parent root = addLocationLoader.load();
            modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.setTitle("Add Location");
            modalStage.setScene(new Scene(root));
            modalStage.setOnHidden(event ->{
                  try {
                      LocationController controller = new LocationController();
                      controller.refreshTableViewData();

                  } catch (SQLException e) {
                      throw new RuntimeException(e);
                  }
            });
            modalStage.showAndWait();
        }catch (IOException e){
            e.printStackTrace();
        }


    }

    public void submitLocationClick(ActionEvent actionEvent) throws SQLException {
         if(!getDesignValue().isEmpty() && !getSelectedProvince().isEmpty()){
              int result = Location.create(getDesignValue(),getSelectedProvince());
              System.out.println();
              modalStage.close();
         }

    }
}
