package www.ong.affectero.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import www.ong.affectero.Config.PdfGenerator;
import www.ong.affectero.MainApplication;
import www.ong.affectero.Model.Assignment;
import www.ong.affectero.Model.Employee;
import www.ong.affectero.Model.Location;

import java.io.IOException;
import java.sql.SQLException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TransferModalController {

    @FXML
    public DatePicker startDateField;
    @FXML
    public ComboBox transferLocationField;

    public static Stage modalStage;
    public Employee selectedEmployee;

    public void initialize() throws SQLException {
        List<String> locationList = Location.getAllLocation();
        System.out.println(locationList.get(0));
        ObservableList<String> provinceOptions = FXCollections.observableArrayList(locationList);
        transferLocationField.setItems(provinceOptions);

    }
    public void showtransferform(AssignmentController assignmentController, String title, Employee selectedEmployee) throws IOException {
        try{
            FXMLLoader transferLoader = new FXMLLoader(MainApplication.class.getResource("/www/ong/affectero/View/TransferModal.fxml"));
            Parent root = transferLoader.load();
            TransferModalController  transferController = transferLoader.getController();
            modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.setTitle(title);
            modalStage.setScene(new Scene(root));
            modalStage.setOnHidden(event ->{
                HomeController controller = new HomeController();
                AnchorPane anchorPane = controller.getContainer();
                EmployeeController employeeController = new EmployeeController();
                try {
                    employeeController.refreshTableViewData();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }


            });
            if(selectedEmployee != null){
                transferController.selectedEmployee = selectedEmployee;

            }
            modalStage.showAndWait();
        }catch (IOException e){
            e.printStackTrace();
        }


    }

    public String getTransferLocationFieldValue() {
        return (String) transferLocationField.getValue();
    }

    public LocalDate getStartDateField() {
        return startDateField.getValue();
    }

    public void handleConfirmTransferClick(ActionEvent actionEvent) throws SQLException {

         if(getStartDateField()!=null && !getTransferLocationFieldValue().isEmpty() && selectedEmployee!=null){
               LocalDate localDate = LocalDate.now();
               DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
               String  formatedDate = localDate.format(formatter);

               Assignment assignment = new Assignment(0, selectedEmployee.getNumEmployee(),selectedEmployee.getAddress(),getTransferLocationFieldValue(),formatedDate,String.valueOf(getStartDateField()));
               Boolean isEmpty = assignment.isEmpty();
               if(!isEmpty){
                   Integer result= assignment.createOne();
                   Integer affectedRow = Employee.updateLocation(getTransferLocationFieldValue(),selectedEmployee.getNumEmployee());
                   System.out.println(result);
                   if(!result.equals(0)){
                       String filepath = result+".pdf";
                       PdfGenerator  pdfGenerator = new PdfGenerator("src/main/resources/pdfs/"+filepath);
                       pdfGenerator.generatePdf(result);
                       modalStage.close();
                   }
               }
         }
    }


}
