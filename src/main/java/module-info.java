module com.example.beersenterpriseproject1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.beersenterpriseproject1 to javafx.fxml;
    exports com.example.beersenterpriseproject1;
}