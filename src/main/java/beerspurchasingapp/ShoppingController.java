/*
 * David Beers
 * Course: CNT 4714 - Spring 2024
 * Assignment title: Project 1 - An Event-driven Enterprise Simulation
 * Date: Sunday, January 28, 2024
 */

package beerspurchasingapp;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import beerspurchasingapp.ShoppingApp;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class ShoppingController{
    public Label subtotal;
    public Label labelItemID;
    public Label labelItemQuantity;
    public Label labelItemDetails;
    ArrayList<String> cart = new ArrayList<>();
    ArrayList<InventoryItem> cartItems = new ArrayList<>();
    String discount = "0%";
    @FXML
    Label cartLabel;

    @FXML
    public TextField subtotalText;
    Dialog<String> dialog = new Dialog<>();

    public void setDialog(String title, String content){
        dialog.setTitle(title);
        dialog.setContentText(content);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
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

    public double calculateDiscount(int qty){
        double priceMult = 1;
        if(qty>4 && qty<10){
            priceMult = 0.9;
            discount = "10%";
        }
        if(qty>9 && qty<15){
            priceMult = 0.85;
            discount = "15%";
        }
        if(qty>14){
            priceMult = 0.8;
            discount = "20%";
        }
        return priceMult;
    }

    @FXML
    private void actionFindItem(){
        InventoryItem item = ha.getItem(itemIDText.getText());
        if(!item.exists()){
            setDialog("Item Not Found", "The item you're looking for could not be found");
            dialog.showAndWait();
        }
        else{
            int qty = Integer.parseInt(quantityText.getText());
            double priceMult = calculateDiscount(qty);
            NumberFormat priceFormatter = new DecimalFormat("#0.00");
            String itemDetails = item.getItemID() + " " + item.getItemDescription() + " " + item.getPrice() + " " + qty + " " + discount + " " + priceFormatter.format((item.getPrice()*priceMult));
            detailsText.setText(itemDetails);
            cart.add(itemDetails);
            addToCart.setDisable(false);
            viewCart.setDisable(false);
            checkOut.setDisable(false);
        }
    }

    public void updateUINumbers(){
        int itemNum = cart.size()+1;
        labelItemID.setText("Item #" + itemNum + " ID");
        labelItemQuantity.setText("Item #" + itemNum + " Quantity");
        // labelItemDetails.setText("Item #" + itemNum + " Details"); only meant to update on finding a new item
        addToCart.setText("Add Item #" + itemNum + " to Cart");
        findItem.setText("Find Item #" + itemNum);
    }
    @FXML
    private void actionAddToCart(){
        cart.add(detailsText.getText());
        updateUINumbers();
        //cartItem1.setText("Item " + cart.size() + " - SKU: " + item.getItemID() + ", Desc: " + item.getItemDescription() + ", Price Ea. $" + item.getPrice() + ", Qty: " + quantityText.getText() + ", Total: $$" + calculateDiscount(Integer.parseInt(quantityText.getText())));
        //itemIDText.setText("");
        //quantityText.setText("");
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