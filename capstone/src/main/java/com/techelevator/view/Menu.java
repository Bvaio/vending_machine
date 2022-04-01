package com.techelevator.view;

import com.techelevator.data.Sales;
import com.techelevator.inventory.Inventory;
import com.techelevator.transactions.Balance;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Scanner scan = new Scanner(System.in);
    private Inventory inventory = new Inventory();
    private Balance balance = new Balance();
    private DisplayMenu displayMenu;
    private PurchaseMenu purchaseMenu;
    private Sales sales;

    public void showMenu() {
        scan = new Scanner( System.in );

        System.out.println( "Welcome to the Terminal" );
        System.out.println( "Display Menu ( D )" );
        System.out.println( "Purchase Menu ( P )" );
        System.out.println( "Exit ( E )" );

        String menuChoice = scan.nextLine().toUpperCase();

        switch( menuChoice ) {
            case "D" :
                displayMenu = new DisplayMenu();
                displayMenu.showMenu();
                break;
            case "P" :
                purchaseMenu = new PurchaseMenu();
                purchaseMenu.showMenu();
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

    public Sales getSales() {
        return sales;
    }

    public void sortedInventoryDisplay() {// move to another Class
        List<String> keys = new ArrayList<>( inventory.getItemMap().keySet() ); // turn our map keys into a list
        Collections.sort( keys );

        for ( String key : keys  ) {
            System.out.println( key + " " + inventory.getItemMap().get( key ).getItemValues() );
        }
    }

    public void exit() { // exits program
    }
}








