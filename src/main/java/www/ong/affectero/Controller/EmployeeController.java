package www.ong.affectero.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import www.ong.affectero.Model.Employee;
import www.ong.affectero.Model.Location;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    @FXML
    public TableColumn<Employee, Integer> numEmployeColumn;
    @FXML
    public TableColumn<Employee, String> civilityColumn;
    @FXML
    public TableColumn<Employee, String>  firstnameColumn;
    @FXML
    public TableColumn<Employee, String>  lastnameColumn;
    @FXML
    public TableColumn<Employee, String>  emailColumn;
    @FXML
    public TableColumn<Employee, String>  posteColumn;
    @FXML
    public TableColumn<Employee, String>  addressColumn;
    @FXML
    private TableView<Employee> employeeTable;
    private ObservableList<Employee> employeeList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        employeeTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        try{
            List<Employee> employees = Employee.getALl();
            employeeList.addAll(employees);
            setTableHeaderTitle();
            setCellValueFactory();
            employeeTable.setItems(employeeList);

        }catch (SQLException e){

        }
    }
    public void setCellValueFactory(){
        numEmployeColumn.setCellValueFactory(new PropertyValueFactory<>("numEmployee"));
        civilityColumn.setCellValueFactory(new PropertyValueFactory<>("civility"));
        firstnameColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        posteColumn.setCellValueFactory(new PropertyValueFactory<>("poste"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
    }
    public  void setTableHeaderTitle(){
        numEmployeColumn.setText("N° Employee");
        civilityColumn.setText("Civility");
        firstnameColumn.setText(("Firstname"));
        lastnameColumn.setText("Lastname");
        emailColumn.setText("Email ");
        posteColumn.setText("Post");
        addressColumn.setText("Address");
    }

    public void handleCreateEmployeeClick(ActionEvent actionEvent) throws IOException {
         AddEmployeeController addEmployeeController = new AddEmployeeController();
         addEmployeeController.showAddLocationForm(this,"Add Employee",null);
    }

    public void handleUpdateEmployeeClick(ActionEvent actionEvent) throws IOException {
         Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
        AddEmployeeController addEmployeeController = new AddEmployeeController();
        addEmployeeController.showAddLocationForm(this,"Add Employee",selectedEmployee);

    }

    public void handleDeleteEmployeeClick(ActionEvent actionEvent) throws SQLException {
           ObservableList<Employee> selectedEmployee = employeeTable.getSelectionModel().getSelectedItems();
           if(!selectedEmployee.isEmpty()){
                for(Employee employee: selectedEmployee){
                      Integer result = Employee.deleteOne(employee.getNumEmployee());
                      System.out.println(result);
                }
                refreshTableViewData();
           }
    }
    public  void refreshTableViewData() throws SQLException {
        List<Employee> updatedEmployees = Employee.getALl();
        employeeList.clear();
        employeeList.addAll(updatedEmployees);
        employeeTable.refresh();
    }


    public void handleTransferClick(ActionEvent actionEvent) throws IOException {
        Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
        TransferModalController transferModalController = new TransferModalController();
        AssignmentController assignmentController = new AssignmentController();
        transferModalController.showtransferform(assignmentController , "Transfer Form", selectedEmployee);

    }

    public void handleViewHistoryClick(ActionEvent actionEvent) throws IOException {
        Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
        AffecationHistory historyController = new AffecationHistory();
        historyController.showtransferform("Transfer Form", selectedEmployee);
    }
}
