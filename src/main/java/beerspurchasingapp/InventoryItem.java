/*
 * David Beers
 * Course: CNT 4714 - Spring 2024
 * Assignment title: Project 1 - An Event-driven Enterprise Simulation
 * Date: Sunday, January 28, 2024
 */
package beerspurchasingapp;

public class InventoryItem {
    private boolean exists = false;
    private String itemID;
    private String itemDescription;
    private boolean inStock;
    private int qtyInStock;
    private double price;

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public boolean exists() {
        return exists;
    }

    public String getItemID(){
        return itemID;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public boolean isInStock() {
        return inStock;
    }

    public int getQtyInStock() {
        return qtyInStock;
    }

    public double getPrice() {
        return price;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQtyInStock(int qtyInStock) {
        this.qtyInStock = qtyInStock;
    }
}
