package com.techelevator.data;

import com.techelevator.inventory.Inventory;
import com.techelevator.inventory.Item;
import com.techelevator.view.Menu;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Scanner;


public class Logger {
    private File logFile;
    private File salesLog;
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


    public void salesReport(){
        BigDecimal grossSales = BigDecimal.valueOf(0);
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm:ss a");
        LocalDateTime dateAndTime = LocalDateTime.now().withNano(0);
        formatterDate.format(dateAndTime);
        String date = dateAndTime.toString().replaceAll(":","-");
        date = date.replaceAll("T","_");
        String fileName ="/receipts/" + date + "_sales-report.txt";
        this.salesLog = new File(fileName);
        try {
            this.writer = new PrintWriter(new FileWriter(this.salesLog,true));
            for (String item : menu.getInventory().getItemMap().keySet()) {
                if (menu.getInventory().getItemMap().get(item).getInventoryCount() < 7) {
                    writer.println(menu.getInventory().getItemMap().get(item).getItemName() + "," + (7 - menu.getInventory().getItemMap().get(item).getInventoryCount()));
                    grossSales = grossSales.add(menu.getInventory().getItemMap().get(item).getItemPrice());
                }
            } writer.println("TOTAL SALES: $" + grossSales);
        } catch (IOException e) {
            e.printStackTrace();
        }

             }

    public void moneyFed(Scanner scan) {
        this.writer.format(convertDateTime() + " MONEY FED: ");
        this.writer.printf("%10s",menu.readBalance().getBalance().toString());
        menu.readBalance().feedMoney(scan);
        this.writer.printf("%10s",menu.readBalance().getBalance().toString());
        this.writer.print("\n");
        this.writer.flush();
    }
    //
//    public void writeMoneyFed(Scanner scan) {
//        this.writer.format(convertDateTime() + " MONEY FED: ");
//        this.writer.printf("%-2s",menu.readBalance().getBalance().toString());
//        menu.readBalance().feedMoney(scan);
//        this.writer.printf("%-2s",menu.readBalance().getBalance().toString());
//        this.writer.print("\n");
//        this.writer.flush();
//    }

//    public double convertBalanceToString(){
//        return menu.readBalance().getBalance().doubleValue();
//    }

    public void itemPurchase(String scan, Item item) {
        this.writer.printf(convertDateTime() + " " +  menu.getInventory().getItemMap().get(scan).getItemName() + " " + menu.getInventory().getItemMap().get(scan).getSlotIdentifier());
        this.writer.printf("%-2s",menu.readBalance().getBalance().toString());
        menu.readBalance().payForItem(item);
        this.writer.printf("%-2s",menu.readBalance().getBalance().toString());
        this.writer.print("\n");
        this.writer.flush();
    }
    public void moneyDispensed(){
        this.writer.printf(convertDateTime() + " " + "CHANGE GIVEN: ");
        this.writer.printf("%-2s",menu.readBalance().getBalance().toString());
        menu.readBalance().dispenseMoney();
        this.writer.printf("%-2s",menu.readBalance().getBalance().toString());
        this.writer.print("\n");
        this.writer.flush();
    }

    public void salesLog(){

    }
//    public void salesList(String scan){
//        BigDecimal grossSales = BigDecimal.valueOf(0);
//        this.salesWriter.printf(menu.getInventory().getItemMap().get(scan).getItemName());
//        grossSales = grossSales.add(menu.getInventory().getItemMap().get(scan).getItemPrice());
//    }
//    public PrintWriter formatStartingBalance(BigDecimal amount) {
//        return this.writer.printf("%15s",amount);
//    }

//    public void formatEndingBalance(BigDecimal balance){
//        this.writer.printf(menu.readBalance().getBalance().toString());
//        this.writer.print("\n");
//        this.writer.flush();
//    }

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
