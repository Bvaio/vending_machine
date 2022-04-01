package com.techelevator.view;

import com.techelevator.data.Sales;
import com.techelevator.inventory.Inventory;
import com.techelevator.transactions.Balance;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Menu {
    private Scanner scan;
    private Inventory inventory = new Inventory();
    private Balance balance;
    private DisplayMenu displayMenu = new DisplayMenu();
    private PurchaseMenu purchaseMenu = new PurchaseMenu();
    private Sales sales = new Sales();

    public void showMenu() {
        scan = new Scanner( System.in );

        System.out.println( "Welcome to the Terminal" );
        System.out.println( "Display Menu ( D )" );
        System.out.println( "Purchase Menu ( P )" );
        System.out.println( "Exit ( E )" );

        String menuChoice = scan.nextLine().toUpperCase();

        switch( menuChoice ) {
            case "D" :
                displayMenu.getDisplayMenu();
                break;
            case "P" :
                purchaseMenu.getPurchaseMenu();
                break;
//            case "S" :
//                sales.getSales();
            case "E" :
                exit();
                break;
            default:
                System.out.println( "None selected try again" );
        }

    }

// to-do -- build out
    public Scanner getScan() {
        return scan;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Balance getBalance() {
        return balance;
    }

    public DisplayMenu getDisplayMenu() {
        return displayMenu;
    }

    public PurchaseMenu getPurchaseMenu() {
        return purchaseMenu;
    }

    public Sales getSales() {
        return sales;
    }

    public void exit() { // exits program
    }
}








