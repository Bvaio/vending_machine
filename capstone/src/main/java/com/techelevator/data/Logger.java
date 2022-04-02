package com.techelevator.data;

import com.techelevator.inventory.Item;
import com.techelevator.view.Menu;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

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

    public void moneyFed(Scanner scan) {
        String moneyFed = " MONEY FED: ";
        this.writer.format("%22s %20s %8.2f",convertDateTime(), moneyFed ,menu.readBalance().getBalance());
//        this.writer.printf("%10s",menu.readBalance().getBalance().toString());
        menu.readBalance().feedMoney(scan);
        this.writer.printf("%7s",menu.readBalance().getBalance().toString());
        this.writer.print("\n");
        this.writer.flush();
    }

    public void itemPurchase(String scan, Item item) {
        this.writer.printf("%10s %18s %2s %8.2f",convertDateTime(),  menu.getInventory().getItemMap().get(scan).getItemName(), menu.getInventory().getItemMap().get(scan).getSlotIdentifier(),menu.readBalance().getBalance());
//        this.writer.printf("%-2s",menu.readBalance().getBalance().toString());
        menu.readBalance().payForItem(item);
        this.writer.printf("%2s",menu.readBalance().getBalance().toString());
        this.writer.print("\n");
        this.writer.flush();
    }

    public void moneyDispensed(){
        this.writer.printf(convertDateTime() + " " + "CHANGE GIVEN: ");
        this.writer.printf("%-2s",menu.readBalance().getBalance().toString());
        menu.readBalance().dispenseChange(); // dispenseMoney to dispenseChange
        this.writer.printf("%-2s",menu.readBalance().getBalance().toString());
        this.writer.print("\n");
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
    public Menu getMenu(){
        return menu;
    }
}
