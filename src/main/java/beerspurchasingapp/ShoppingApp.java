package beerspurchasingapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Dialog;
import java.io.IOException;
import java.util.Objects;

public class ShoppingApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        //initDialogs();
        // setting the main scene
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("purchasing-app-main.fxml")));

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.setTitle("Purchases");
        ShoppingController controller = new ShoppingController();
        controller.setStage(stage);
        stage.show();
    }
}