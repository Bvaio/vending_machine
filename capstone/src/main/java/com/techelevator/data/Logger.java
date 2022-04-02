package com.techelevator.data;

import com.techelevator.inventory.Inventory;
import com.techelevator.view.Menu;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private File logFile;
    private PrintWriter writer;
    Menu menu = new Menu();

    public Logger() {
        this.logFile = new File("Audit.txt");
        if (!logFile.exists() && !logFile.isFile()) {
            try {
                this.writer = new PrintWriter(this.logFile);
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try {
                this.writer = new PrintWriter(new FileWriter(this.logFile, true));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void writeMoneyFed(BigDecimal balance) {
        this.writer.printf(convertDateTime() + " MONEY FED:");
        formatStartingBalance(balance);
        this.writer.flush();
    }

    public void writeItemPurchase(String scan, BigDecimal balance) {
        this.writer.printf(convertDateTime() + " " +  menu.getInventory().getItemMap().get(scan).getItemName() + " " + menu.getInventory().getItemMap().get(scan).getSlotIdentifier());
        this.formatStartingBalance(balance);
        this.writer.flush();
    }
//    public void salesList(String scan){
//        BigDecimal grossSales = BigDecimal.valueOf(0);
//        this.salesWriter.printf(menu.getInventory().getItemMap().get(scan).getItemName());
//        grossSales = grossSales.add(menu.getInventory().getItemMap().get(scan).getItemPrice());
//    }
    public void formatStartingBalance(BigDecimal amount) {
        this.writer.printf("%15s",amount);
        this.writer.flush();
    }
    public void formatEndingBalance(BigDecimal balance){
        this.writer.printf("%8s", balance + "\n");
        this.writer.flush();
    }

    public String convertDateTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
        LocalDateTime dt = LocalDateTime.now();
        return formatter.format(dt);
    }


    public void close(){
        this.writer.close();
    }

//    public void printFormat(){
//
//    }

    //examples of what to log
//        TEMPLATE
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
//        LocalDateTime dt = LocalDateTime.now();
//        System.out.format(formatter.format(dt) + " MONEY FED:");
//        System.out.format("%12s","$" + starting balance);
//        System.out.format("%8s","$" + end balance/current);

}
