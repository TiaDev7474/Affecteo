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
import java.sql.SQLException;



public class AddLocationController {
    @FXML
    private    ComboBox provinceComboBox;
    @FXML
    private  TextField designField;
    private Integer locationId;
    private static Stage modalStage;

    private  Location selectedLocation;


    public void initialize() throws SQLException {
        ObservableList<String> provinceOptions = FXCollections.observableArrayList("Antananarivo","Fianarantsoa","Toliara","Mahajanga","Toamasina","Antsiranana");
         provinceComboBox.setItems(provinceOptions);
         if(selectedLocation!=null){
             System.out.println();

         }

    }

    public String getSelectedProvince(){
         return (String) provinceComboBox.getValue();
    }

    public String getDesignValue() {
        return designField.getText();
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setDesignField(String value) {
        this.designField.setText(value);
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public void setProvinceComboBox(String value) {
        this.provinceComboBox.setValue(value);
    }

    public void showAddLocationForm(LocationController locationController, String title, Location selectedLocation ) throws IOException {
        try{
            FXMLLoader addLocationLoader = new FXMLLoader(MainApplication.class.getResource("/www/ong/affectero/View/AddLocationModal.fxml"));
            Parent root = addLocationLoader.load();
            AddLocationController addLocationController = addLocationLoader.getController();
            modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.setTitle(title);
            modalStage.setScene(new Scene(root));
            modalStage.setOnHidden(event ->{
                  try {
                      locationController.refreshTableViewData();

                  } catch (SQLException e) {
                      throw new RuntimeException(e);
                  }
            });
            if(selectedLocation != null){
                addLocationController.selectedLocation = selectedLocation;
                Location location = Location.getOne(selectedLocation.getLocationId());
                addLocationController.setDesignField(location.getDesign());
                addLocationController.setProvinceComboBox(location.getProvince());
                addLocationController.setLocationId(location.getLocationId());
            }
            modalStage.showAndWait();
        }catch (IOException e){
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void submitLocationClick(ActionEvent actionEvent) throws SQLException {
         Boolean isUpdate = selectedLocation != null;

         if(!getDesignValue().isEmpty() && !getSelectedProvince().isEmpty() && !isUpdate){
              int result = Location.create(getDesignValue(),getSelectedProvince());
              System.out.println(result);
              modalStage.close();
         }else if(!getDesignValue().isEmpty() && !getSelectedProvince().isEmpty() && isUpdate) {
             int result = Location.updateOne(selectedLocation.getLocationId(), getDesignValue() ,getSelectedProvince());
             System.out.println(result);
             modalStage.close();
         }

    }
}
