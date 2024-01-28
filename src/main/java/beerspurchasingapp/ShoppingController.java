/*
 * David Beers
 * Course: CNT 4714 - Spring 2024
 * Assignment title: Project 1 - An Event-driven Enterprise Simulation
 * Date: Sunday, January 28, 2024
 */

package beerspurchasingapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import beerspurchasingapp.ShoppingApp;

public class ShoppingController{

    @FXML
    Label cartLabel;

    @FXML
    public TextField subtotalText;
    Dialog<String> dialog = new Dialog<>();

    public void setDialog(String title, String content){
        dialog.setTitle(title);
        dialog.setContentText(content);
    }
    HandlerActions ha = new HandlerActions();
    private Stage primaryStage;
    public void setStage(Stage stage) {
        primaryStage = stage;
    }

    @FXML
    private TextField itemIDText;

    @FXML
    private TextField quantityText;

    @FXML
    private TextField detailsText;

    @FXML
    private TextField cartItem1;

    @FXML
    private TextField cartItem2;

    @FXML
    private TextField cartItem3;

    @FXML
    private TextField cartItem4;

    @FXML
    private TextField cartItem5;

    @FXML
    private Button findItem;

    @FXML
    private Button checkOut;

    @FXML
    private Button addToCart;

    @FXML
    private Button viewCart;

    @FXML
    private Button emptyCart;

    @FXML
    private Button closeApp;

    @FXML
    private void actionFindItem(){
        InventoryItem item = ha.getItem(itemIDText.getText());
        if(!item.exists()){
            setDialog("Item Not Found", "The item you're looking for could not be found");
            dialog.showAndWait();
        }
        else{
            detailsText.setText(item.getItemDescription());
            addToCart.setDisable(false);
            viewCart.setDisable(false);
            checkOut.setDisable(false);
        }
    }

    @FXML
    private void actionAddToCart(){
        
    }

    @FXML
    private void actionViewCart(){}

    @FXML
    private void actionEmptyCart(){}

    @FXML
    private void actionCloseApp(){
        System.exit(0);
    }

    @FXML
    private void actionCheckout(){}
}