package www.ong.affectero.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;
import www.ong.affectero.MainApplication;
import www.ong.affectero.Model.Employee;
import www.ong.affectero.Model.Location;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AddEmployeeController {

    @FXML
    public RadioButton mrsRadio;
    @FXML
    public ToggleGroup group;
    @FXML
    public RadioButton mrRadio;
    @FXML
    public RadioButton missRadio;
    @FXML
    public TextField firstnameField;
    @FXML
    public TextField lastnameField;
    @FXML
    public TextField emailField;
    @FXML
    public TextField posteField;
    @FXML
    public ComboBox addressField;
    @FXML
    private static  Stage modalStage;
    private  static  Employee selectedEmployee;
    public void initialize() throws SQLException {
        List<String> locationList = Location.getAllLocation();
        System.out.println(locationList.get(0));
        ObservableList<String> provinceOptions = FXCollections.observableArrayList(locationList);
        addressField.setItems(provinceOptions);

    }
    public void showAddLocationForm(EmployeeController employeeController, String title, Employee selectedEmployee ) throws IOException {
        try{
            FXMLLoader addEmployeeLoader = new FXMLLoader(MainApplication.class.getResource("/www/ong/affectero/View/AddEmployeeModal.fxml"));
            Parent root = addEmployeeLoader.load();
            AddEmployeeController addEmployeeController = addEmployeeLoader.getController();
            modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.setTitle(title);
            modalStage.setScene(new Scene(root));
            modalStage.setOnHidden(event ->{
                try {
                    employeeController.refreshTableViewData();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            if(selectedEmployee != null){
                addEmployeeController.selectedEmployee = selectedEmployee;
                addEmployeeController.setAddressField(selectedEmployee.getAddress());
                addEmployeeController.setEmailField(selectedEmployee.getEmail());
                addEmployeeController.setFirstnameField(selectedEmployee.getFirstname());
                addEmployeeController.setLastnameField(selectedEmployee.getLastname());
                addEmployeeController.setPosteField(selectedEmployee.getPoste());
                addEmployeeController.setSelectedRadioValue(selectedEmployee.getCivility());

            }
            modalStage.showAndWait();
        }catch (IOException e){
            e.printStackTrace();
        }


    }
    public  Boolean isEmpty(){
         return getAddressFieldValue().isEmpty() && getEmailFieldValue().isEmpty() && getFirstnameFieldValue().isEmpty() && getLastnameFieldValue().isEmpty() && getPosteFieldValue().isEmpty() && getSelectedRadioValue() == null ;
    }
    public String getSelectedRadioValue(){
            RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
            if(selectedRadioButton != null ){
                return selectedRadioButton.getText();
            }else{
                return null;
            }
    }
    public  void setSelectedRadioValue(String value){
         if(value.equals("Mr")){
              mrRadio.setSelected(true);
         }else if(value.equals("Mrs")){
             mrsRadio.setSelected(true);
         }else if(value.equals("Miss")){
             missRadio.setSelected(true);
         }
    }

    public String getAddressFieldValue() {
        return (String) addressField.getValue();
    }

    public void setAddressField(String value) {
         addressField.setValue(value);
    }

    public String getEmailFieldValue() {
        return emailField.getText();
    }
    public void setEmailField(String value){
        emailField.setText(value);
    }
    public String getFirstnameFieldValue() {
        return firstnameField.getText();
    }
    public void setFirstnameField(String value){
        firstnameField.setText(value);
    }


    public String  getLastnameFieldValue() {
        return lastnameField.getText();
    }


    public void setLastnameField(String value) {
        lastnameField.setText(value);
    }

    public  String getPosteFieldValue(){
        return  posteField.getText();
    }

    public void setPosteField(String value) {
        posteField.setText(value);
    }

    public void handleSubmitClick(ActionEvent actionEvent) throws SQLException {
         Boolean isUpdate = selectedEmployee != null;
         System.out.println(isUpdate);
         if(!isEmpty() && !isUpdate){
              Integer result = Employee.createOne(getSelectedRadioValue(),getFirstnameFieldValue(),getLastnameFieldValue(),getEmailFieldValue(),getPosteFieldValue(),getAddressFieldValue());
              System.out.println(result);
              modalStage.close();
         }
         if(!isEmpty() && isUpdate){
              Integer result = Employee.updateOne(getSelectedRadioValue(), getFirstnameFieldValue(), getLastnameFieldValue(),getEmailFieldValue(),getPosteFieldValue(),getAddressFieldValue(),selectedEmployee.getNumEmployee());
              System.out.println(result);
              modalStage.close();
         }
    }
}
