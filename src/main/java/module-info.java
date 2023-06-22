module com.example.affectero {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.affectero to javafx.fxml;
    exports com.example.affectero;
}