package www.ong.affectero.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import www.ong.affectero.MainApplication;
import www.ong.affectero.Model.Assignment;
import www.ong.affectero.Model.Employee;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AffecationHistory implements Initializable {
    public TableView affectationTable;
    public TableColumn numAffectation;
    public TableColumn numEmployee;
    public TableColumn previousLocationColumn;
    public TableColumn newLocationColumn;
    public TableColumn dateAffectationColumn;
    public TableColumn datePriseDeServiceColumn;
    public TableColumn  fetchHistoryColumn;
    private  static  Stage modalStage;

    private ObservableList<Assignment> assignmentList = FXCollections.observableArrayList();
    private  Employee selectedEmployee;


    @Override
    public void initialize(URL url , ResourceBundle resource){
        affectationTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        List<Assignment> assignments = null;
        try {
            assignments = Assignment.getAll();
            assignmentList.addAll(assignments);
            setTableHeaderTitle();
            numAffectation.setCellValueFactory(new PropertyValueFactory<>("numAffectation"));
            numEmployee.setCellValueFactory(new PropertyValueFactory<>("numEmployee"));
            previousLocationColumn.setCellValueFactory(new PropertyValueFactory<>("prevLocation"));
            newLocationColumn.setCellValueFactory(new PropertyValueFactory<>("newLocation"));
            dateAffectationColumn.setCellValueFactory(new PropertyValueFactory<>("dayOfAffectation"));
            datePriseDeServiceColumn.setCellValueFactory(new PropertyValueFactory<>("dayOfService"));

            // Create a button cell factory for the action column

            affectationTable.setItems(assignmentList);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public void showtransferform(String title, Employee selectedEmployee) throws IOException {
        try{
            FXMLLoader historyLoader = new FXMLLoader(MainApplication.class.getResource("/www/ong/affectero/View/AffectationHistoryModal.fxml"));
            Parent root =  historyLoader .load();
            AffecationHistory historyController =  historyLoader.getController();
            modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.setTitle(title);
            modalStage.setScene(new Scene(root));
            if(selectedEmployee != null){
                historyController.selectedEmployee = selectedEmployee;

            }
            modalStage.showAndWait();
        }catch (IOException e){
            e.printStackTrace();
        }


    }
    public  void refreshTableViewData() throws SQLException {
        List<Assignment> updatedAssignments = Assignment.getAll();

        assignmentList.clear();
        assignmentList.addAll(updatedAssignments);

        affectationTable.refresh();
    }
    public  void setTableHeaderTitle(){
        numAffectation.setText("N° Affectation");
        numEmployee.setText("N° Employee");
        newLocationColumn.setText("New Location");
        previousLocationColumn.setText("Prev Location");
        dateAffectationColumn.setText("Date of Affectation");
        datePriseDeServiceColumn.setText("Start of Service Date ");
    }

}
