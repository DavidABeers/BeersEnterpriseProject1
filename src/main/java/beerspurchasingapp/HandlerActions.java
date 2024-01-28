package beerspurchasingapp;

import javafx.util.converter.DateTimeStringConverter;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
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

    public String timePrepend(LocalDateTime transactionTime){
        String time = "";
        int day = transactionTime.getDayOfMonth();
        int month = transactionTime.getMonthValue();
        int hour = transactionTime.getHour();
        int min = transactionTime.getMinute();
        int sec = transactionTime.getSecond();
        if(day<10){
            time = "0";
        }
        time = time + day;
        if(month<10){
            time = time + "0";
        }
        time = time + month + transactionTime.getYear();
        if(hour<10){
            time = time + "0";
        }
        time = time + hour;
        if(min<10){
            time = time + "0";
        }
        time = time + min;
        if(sec<10){
            time = time + "0";
        }
        time = time + sec;
        return time;
    }

    // for writing transactions.csv
    public void writeTransaction(String[] cartItems, int qty, LocalDateTime transactionTime){
        File transactions = new File("transactions.csv");

        if(!transactions.exists()){
            try {
                if(transactions.createNewFile()){

                    FileWriter transactionWriter = new FileWriter(transactions);
                    String amPM = "AM EST";
                    int hour = transactionTime.getHour();
                    if(hour>=12){
                        amPM = "PM EST";
                        if(hour>=13){
                            hour = hour%12;
                        }
                    }
                    String readableTime = transactionTime.getMonth().toString() + " " +
                            transactionTime.getDayOfMonth() + ", " + transactionTime.getYear() + ", " +
                            hour + ":" + transactionTime.getMinute() + ":" +
                            transactionTime.getSecond() + amPM;
                    for(String item: cartItems){
                        transactionWriter.write(timePrepend(transactionTime));
                        transactionWriter.write(item + ", " + readableTime + "\n");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        else{
            FileWriter transactionWriter = null;
            try {
                transactionWriter = new FileWriter(transactions);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String amPM = "AM EST";
            int hour = transactionTime.getHour();
            if(hour>=12){
                amPM = "PM EST";
                if(hour>=13){
                    hour = hour%12;
                }
            }
            String readableTime = transactionTime.getMonth().toString() + " " +
                    transactionTime.getDayOfMonth() + ", " + transactionTime.getYear() + ", " +
                    hour + ":" + transactionTime.getMinute() + ":" +
                    transactionTime.getSecond() + amPM;
            for(String item: cartItems){
                try {
                    transactionWriter.write(timePrepend(transactionTime));
                    transactionWriter.write(item + ", " + readableTime + "\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }
}