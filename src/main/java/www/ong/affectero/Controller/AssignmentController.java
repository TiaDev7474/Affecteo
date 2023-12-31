package www.ong.affectero.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import www.ong.affectero.Model.Assignment;
import www.ong.affectero.Model.Location;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AssignmentController implements Initializable {
    public TableView affectationTable;
    public TableColumn numAffectation;
    public TableColumn numEmployee;
    public TableColumn previousLocationColumn;
    public TableColumn newLocationColumn;
    public TableColumn dateAffectationColumn;
    public TableColumn datePriseDeServiceColumn;
    public TableColumn  fetchHistoryColumn;

    private ObservableList<Assignment> assignmentList = FXCollections.observableArrayList();


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
         fetchHistoryColumn.setText("Action");
    }
    public void handleDeleteAffectionClick(ActionEvent actionEvent) throws SQLException {
        List<Assignment> selectedAssignment = affectationTable.getSelectionModel().getSelectedItems();
        for(Assignment assignment : selectedAssignment){
             Integer result = Assignment.deleteOne(assignment.getNumAffectation());
             System.out.println(result);
             if(result.equals(1)){

             }
        }
        refreshTableViewData();
    }
    public void handleUpdateAffectationClick(ActionEvent actionEvent) {
    }
    public void handleCreateAffectationClick(ActionEvent actionEvent) {
    }

    public void handleSearchByDateClick(MouseEvent mouseEvent) {
    }

    public void handleSearchByNameClick(MouseEvent mouseEvent) {
    }
}
