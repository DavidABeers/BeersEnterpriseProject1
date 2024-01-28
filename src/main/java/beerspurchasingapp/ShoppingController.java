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
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ShoppingController{
    public Label subtotal;
    public Label labelItemID;
    public Label labelItemQuantity;
    public Label labelItemDetails;

    // the concatenated details text for all cart items
    public String[] cartDescription = new String[5];
    //public String currentItemDesc;
    InventoryItem currentItem;
    public int itemNum = 1;

    // for appending to details text
    String discount = "0%";

    // tracks the total price of the current cart.
    public float total = 0;
    @FXML
    Label cartLabel;

    @FXML
    public TextField subtotalText;
    Dialog<String> dialog = new Dialog<>();

    // edits the title and content of dialog, allowing only one to be declared.
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

    // sets the string discount and returns the equivalent multiplier as a double
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
    public NumberFormat priceFormatter = new DecimalFormat("#0.00");
    @FXML
    private void actionFindItem(){
        discount = "0%";
        InventoryItem item = ha.getItem(itemIDText.getText());
        if(!item.exists()){
            setDialog("Item Not Found", "The item you're looking for could not be found");
            dialog.showAndWait();
        }
        else if(!item.isInStock()){
            setDialog("Out of Stock", "We're Sorry, that item is currently out of stock");
            dialog.showAndWait();
        }
        else{
            currentItem = item;
            int qty = Integer.parseInt(quantityText.getText());
            double priceMult = calculateDiscount(qty);
            cartDescription[itemNum-1] = item.getItemID() + ", " + item.getItemDescription() + ", " + item.getPrice() + ", " + qty + ", " + discount + ", ";
            detailsText.setText(cartDescription[itemNum-1]+priceFormatter.format((item.getPrice()*priceMult*Integer.parseInt(quantityText.getText()))));
            addToCart.setDisable(false);
            viewCart.setDisable(false);
            checkOut.setDisable(false);
        }
    }

    public void updateUINumbers(){
        labelItemID.setText("Item #" + itemNum + " ID");
        labelItemQuantity.setText("Item #" + itemNum + " Quantity");
        // labelItemDetails.setText("Item #" + itemNum + " Details"); only meant to update on finding a new item
        addToCart.setText("Add Item #" + itemNum + " to Cart");
        findItem.setText("Find Item #" + itemNum);
    }
    @FXML
    private void actionAddToCart(){
        if(Integer.parseInt(quantityText.getText())> currentItem.getQtyInStock()){
            String dialogText = "we're sorry, we only have " + currentItem.getQtyInStock() + " of that item, please reduce order quantity";
            setDialog("Insufficient Stock", dialogText);
            dialog.showAndWait();
        }
        else{
            // I know there's a better way to do this, but I'm too lazy to implement it.
            switch(itemNum){
                case 2:
                    cartItem2.setText("Item " + itemNum + " - SKU: " + currentItem.getItemID() +
                            ", Desc: " + currentItem.getItemDescription() + ", Price Ea. $" + currentItem.getPrice() +
                            ", Qty: " + quantityText.getText() + ", Total: $$" + priceFormatter.format(Integer.parseInt(quantityText.getText())*currentItem.getPrice()*calculateDiscount(Integer.parseInt(quantityText.getText()))));
                    break;
                case 3:
                    cartItem3.setText("Item " + itemNum + " - SKU: " + currentItem.getItemID() +
                            ", Desc: " + currentItem.getItemDescription() + ", Price Ea. $" + currentItem.getPrice() +
                            ", Qty: " + quantityText.getText() + ", Total: $$" + priceFormatter.format(Integer.parseInt(quantityText.getText())*currentItem.getPrice()*calculateDiscount(Integer.parseInt(quantityText.getText()))));
                    break;
                case 4:
                    cartItem4.setText("Item " + itemNum + " - SKU: " + currentItem.getItemID() +
                            ", Desc: " + currentItem.getItemDescription() + ", Price Ea. $" + currentItem.getPrice() +
                            ", Qty: " + quantityText.getText() + ", Total: $$" + priceFormatter.format(Integer.parseInt(quantityText.getText())*currentItem.getPrice()*calculateDiscount(Integer.parseInt(quantityText.getText()))));
                    break;
                case 5:
                    cartItem5.setText("Item " + itemNum + " - SKU: " + currentItem.getItemID() +
                            ", Desc: " + currentItem.getItemDescription() + ", Price Ea. $" + currentItem.getPrice() +
                            ", Qty: " + quantityText.getText() + ", Total: $$" + priceFormatter.format(Integer.parseInt(quantityText.getText())*currentItem.getPrice()*calculateDiscount(Integer.parseInt(quantityText.getText()))));
                    findItem.setDisable(true);
                    break;
                default:
                    cartItem1.setText("Item " + itemNum + " - SKU: " + currentItem.getItemID() +
                            ", Desc: " + currentItem.getItemDescription() + ", Price Ea. $" + currentItem.getPrice() +
                            ", Qty: " + quantityText.getText() + ", Total: $$" + priceFormatter.format(Integer.parseInt(quantityText.getText())*currentItem.getPrice()*calculateDiscount(Integer.parseInt(quantityText.getText()))));
            }
            total+= currentItem.getPrice()*calculateDiscount(Integer.parseInt(quantityText.getText()))*Integer.parseInt(quantityText.getText());
            cartDescription[itemNum-1] = cartDescription[itemNum-1] + priceFormatter.format(Integer.parseInt(quantityText.getText())*currentItem.getPrice()*calculateDiscount(Integer.parseInt(quantityText.getText())));
            itemIDText.setText("");
            // default quantity text is 1 to avoid null, it's also the minimum someone could order.
            quantityText.setText("1");
            addToCart.setDisable(true);
            cartLabel.setText(itemNum + " item(s) in cart");
            itemNum++;
            subtotalText.setText(priceFormatter.format(total));
            updateUINumbers();
        }
    }

    public String generateTransactionItems(){
        StringBuilder cartItems = new StringBuilder("Your Cart is empty");
        if(itemNum>1){
            cartItems = new StringBuilder(cartDescription[0]);
            for(int i = 1;i<itemNum-1;i++){
                cartItems.append("\n").append(cartDescription[i]);
            }
        }
        return cartItems.toString();
    }

    @FXML
    private void actionViewCart(){
        setDialog("Your Cart", generateTransactionItems());
        dialog.showAndWait();
    }

    // resets the UI. Doesn't currently need to be separate from actionEmptyCart, but was formerly used elsewhere.
    public void clearUI(){
        cartItem1.setText("");
        cartItem2.setText("");
        cartItem3.setText("");
        cartItem4.setText("");
        cartItem5.setText("");
        detailsText.setText("");
        cartLabel.setText("Your Cart is Empty");
        subtotalText.setText("");
        quantityText.setText("1");
        itemNum = 1;
        updateUINumbers();
    }

    @FXML
    private void actionEmptyCart(){
        clearUI();
        findItem.setDisable(false);
    }

    @FXML
    private void actionCloseApp(){
        System.exit(0);
    }

    @FXML
    private void actionCheckout(){
        LocalDateTime transactionTime = LocalDateTime.now();
        ha.writeTransaction(cartDescription, itemNum-1, transactionTime);
        String cartItems = generateTransactionItems();
        String invoice = ha.readableTime(transactionTime) + "\n\n" + (itemNum-1) + "\n\n" + "ItemID# / Title / Price / Qty / Disc% / Your price\n\n" + cartItems +
                "\n\n\nOrder subtotal: $" + priceFormatter.format(total) + "\n\nTax Rate: \t 7%\n\nTax Amount:\t$" + priceFormatter.format((total*0.07)) + "\n\nORDER TOTAL: \t$" + priceFormatter.format((total*1.07)) +
                "\n\nThanks for shopping with us!";
        setDialog("Invoice", invoice);
        dialog.showAndWait();
        findItem.setDisable(true);
    }
}