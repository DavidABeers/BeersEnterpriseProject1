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
/*
    public Dialog<String> testDialog = new Dialog<>();

    public void setDialogContent(String title, String content){
        testDialog.setTitle(title);
        testDialog.setContentText(content);
    }
    */
 /*
        // setting dialog panes
    public static void initDialogs(){
        Dialog<String> outOfStock = new Dialog<String>();
        outOfStock.setTitle("Item Unavailable");
        outOfStock.setContentText("We're sorry, that item is out of stock, please try another item");

        Dialog<String> invalidItem = new Dialog<String>();
        invalidItem.setTitle("Item Not Found");
        invalidItem.setContentText("The item you requested does not exist");

        Dialog<String> viewCartDialog = new Dialog<String>();
        viewCartDialog.setTitle("Your Cart");
        viewCartDialog.setContentText("");

        Dialog<String> invoiceDialog = new Dialog<String>();
        invoiceDialog.setTitle("Thank you for shopping with us!");
        invoiceDialog.setContentText("");
    }*/

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