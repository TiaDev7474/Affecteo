package www.ong.affectero.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import www.ong.affectero.Model.Location;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;




public class LocationController implements Initializable {
     @FXML
     private  TableView<Location>  locationTable;
     @FXML
     private TableColumn<Location, String> designColumn;
    @FXML
    private TableColumn<Location, Integer> locationIdColumn;
     @FXML
     private TableColumn<Location, String> provinceColumn;
     public    Location selectedLocation;
     private  ObservableList<Location> locationList = FXCollections.observableArrayList();



     @Override
        public void initialize(URL url , ResourceBundle resource){
            locationTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
         try{

              List<Location> locations = Location.getALl();
              locationList.addAll(locations);
              setTableHeaderTitle();
              locationIdColumn.setCellValueFactory(new PropertyValueFactory<>("locationId"));
              designColumn.setCellValueFactory(new PropertyValueFactory<>("design"));
              provinceColumn.setCellValueFactory(new PropertyValueFactory<>("province"));
              locationTable.setItems(locationList);



         }catch ( SQLException e) {
             throw new RuntimeException(e);
         }


     }
     public  void setTableHeaderTitle(){
          locationIdColumn.setText("ID");
          designColumn.setText("Design");
          provinceColumn.setText(("Province"));
    }
    public  void refreshTableViewData() throws SQLException {
        List<Location> updatedlocations = Location.getALl();

        locationList.clear();
        locationList.addAll(updatedlocations);

        locationTable.refresh();
    }

    public void createLocationClick(ActionEvent actionEvent) throws SQLException {

    }



    public void updateLocationCLick(ActionEvent actionEvent) {
    }

    public void deleteLocationCLick(ActionEvent actionEvent) {
    }


    public  void handleUpdateLocationClick(ActionEvent actionEvent) throws IOException {
            selectedLocation =  locationTable.getSelectionModel().getSelectedItem();
            AddLocationController addLocationController = new AddLocationController();
            addLocationController.showAddLocationForm(this,"Update Location",selectedLocation);

    }

    public void handleDeleteLocationClick(ActionEvent actionEvent) throws SQLException {
        ObservableList<Location> selectedLocations = locationTable.getSelectionModel().getSelectedItems();
        if(!selectedLocations.isEmpty()){
            for(Location location: selectedLocations){
                System.out.println(location.getLocationId());
                int result = Location.deleteOne(location.getLocationId());
                System.out.println(result);
            }
            refreshTableViewData();
        }else {
             System.out.println("Aucune element selectionner");
        }
    }


    public void submitLocationClick(ActionEvent actionEvent) {
    }

    public void handleCreateLocationClick(ActionEvent actionEvent) throws IOException {
        AddLocationController locationController = new AddLocationController();
        locationController.showAddLocationForm(this,"Add Location", null);
    }
}
