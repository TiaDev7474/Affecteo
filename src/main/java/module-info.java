module com.example.affectero {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;
    requires kernel;
    requires layout;
    requires java.mail;
    requires activation;

    opens www.ong.affectero to javafx.fxml;
    exports www.ong.affectero;
    exports www.ong.affectero.Controller;
    opens www.ong.affectero.Controller to javafx.fxml;
    opens  www.ong.affectero.Model to javafx.base;
}
