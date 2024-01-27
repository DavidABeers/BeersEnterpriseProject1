module beerspurchasingapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens beerspurchasingapp to javafx.fxml;
    exports beerspurchasingapp;
}