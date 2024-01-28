package beerspurchasingapp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.io.File;

public class HandlerActions {
    //String inventoryPath = Objects.requireNonNull(getClass().getResource("inventory.csv")).toString();

    File inventory = new File("inventory.csv");
    public InventoryItem item = new InventoryItem();

    public void inventoryParser(String currentLine){
        boolean itemFound = false;
        item.setItemID(currentLine.substring(0, currentLine.indexOf(',')));
        currentLine = currentLine.substring(currentLine.indexOf(',')+1);
        item.setItemDescription(currentLine.substring(1, currentLine.indexOf(',')));
        currentLine = currentLine.substring(currentLine.indexOf(',')+1);
        if(currentLine.substring(1, currentLine.indexOf(',')).equals("true")) {
            item.setInStock(true);
        }
        else{
            item.setInStock(false);
        }
        currentLine = currentLine.substring(currentLine.indexOf(',')+1);
        item.setQtyInStock(Integer.parseInt(currentLine.substring(1, currentLine.indexOf(','))));
        currentLine = currentLine.substring(currentLine.indexOf(',')+1);
        item.setPrice(Double.parseDouble(currentLine.substring(1)));
    }

    public InventoryItem getItem(String itemID){
        String currentLine;
        try {
            Scanner inventoryReader = new Scanner(inventory);
            boolean itemFound = false;
            while(!itemFound){
                currentLine = inventoryReader.nextLine();
                if(currentLine.substring(0, currentLine.indexOf(',')).equals(itemID)){
                    inventoryParser(currentLine);
                    item.setExists(true);
                    itemFound = true;
                }
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return item;
    }

    // for writing transactions.csv
    public void writeTransaction(){

    }
}