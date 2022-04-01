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
<<<<<<< HEAD
    private Scanner scan = new Scanner(System.in);
    private Inventory inventory = new Inventory();
    private Balance balance = new Balance();
=======
    private Scanner scan = new Scanner( System.in );
    private static Inventory inventory = new Inventory(); // changed to static made it work?
    private static Balance balance = new Balance();
>>>>>>> ede930527687c030f1cbecb7109bcb99ef5dea35
    private DisplayMenu displayMenu;
    private PurchaseMenu purchaseMenu;
    private Sales sales;

    public void showMenu() {
        inventory.createItemMap( "catering.csv" );

        System.out.println( "Welcome to the Terminal" );
        System.out.println( "Display Menu ( D )" );
        System.out.println( "Purchase Menu ( P )" );
        System.out.println( "Exit ( E )" );

        String menuChoice = scan.nextLine().toUpperCase();

        selectMenu( menuChoice );

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

    public void sortedInventoryDisplay() {
        List<String> keys = new ArrayList<>( inventory.getItemMap().keySet() ); // turn our map keys into a list
        Collections.sort( keys ); // sorts keys

        for ( String key : keys  ) {
            System.out.println( inventory.getItemMap().get( key ).displayItem() );
        }
    }

    public void selectMenu( String choice ) {
        switch( choice ) {
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

    public void exit() { // exits program
    }
}








