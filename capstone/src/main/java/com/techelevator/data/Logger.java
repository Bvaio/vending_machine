package com.techelevator.data;

import com.techelevator.inventory.Item;
import com.techelevator.view.Menu;

import java.io.*;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Logger {
    private File logFile;
    private PrintWriter writer;
    private Menu menu = new Menu();

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
        String moneyFed = "MONEY FED: ";
        this.writer.printf("%-23s %-22s %6s",convertDateTime(), moneyFed ,showCurrencyValue());
        menu.readBalance().feedMoney(scan);
        this.writer.printf("%8s",showCurrencyValue());
        this.writer.print("\n");
        this.writer.flush();
    }

    public void itemPurchase(String scan, Item item) {
        this.writer.printf("%-23s %-19s %-2s %6s",convertDateTime(),  menu.getInventory().getItemMap().get(scan).getItemName(), menu.getInventory().getItemMap().get(scan).getSlotIdentifier(),showCurrencyValue());
        menu.readBalance().payForItem(item);
        this.writer.printf("%8s",showCurrencyValue());
        this.writer.print("\n");
        this.writer.flush();
    }
    public void moneyDispensed(){
        String changeGiven = "CHANGE GIVEN: ";
        this.writer.printf("%-23s %-22s %6s",convertDateTime(),changeGiven,showCurrencyValue());
        menu.readBalance().dispenseChange();
        this.writer.printf("%8s",showCurrencyValue());
        this.writer.print("\n");
        this.writer.flush();
    }

    public String convertDateTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
        LocalDateTime dt = LocalDateTime.now();
        return formatter.format(dt);
    }

    public String showCurrencyValue() {
        Locale currentLocale = Locale.getDefault();
        NumberFormat currencyConversion = NumberFormat.getCurrencyInstance(currentLocale);
        return currencyConversion.format(menu.readBalance().getBalance());
    }

    public void close(){
        this.writer.close();
    }
    public Menu getMenu(){
        return menu;
    }
}
