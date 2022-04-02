package com.techelevator.view;

import com.techelevator.data.Sales;
import com.techelevator.inventory.Inventory;
import com.techelevator.inventory.Item;
import com.techelevator.transactions.Balance;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Menu {
    private static Logger log = new Logger();
    private Scanner scan = new Scanner( System.in );
    private static Inventory inventory = new Inventory(); // changed to static made it work?
    private static Balance balance = new Balance();
    private static Map< String, Item > pulledInventory = pullInventory();


    private static DisplayMenu displayMenu = new DisplayMenu();
    private static PurchaseMenu purchaseMenu = new PurchaseMenu();
    private Sales sales;


    public void showMenu() {
//        inventory.createItemMap( "catering.csv" );
        System.out.println( "Welcome to the Terminal" );
        System.out.println( "Display Menu ( D )" );
        System.out.println( "Purchase Menu ( P )" );
        System.out.println( "Exit ( E )" );
        System.out.print( "- - >  " );
        String menuChoice = scan.nextLine().toUpperCase();
        selectMenu( menuChoice );
    }

    public Scanner getScan() {
        return scan;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Balance readBalance() {
        return balance;
    }

//    public BigDecimal printBalance() {
//        return balance.getBalance().setScale(2,RoundingMode.CEILING);
//    }

    public DisplayMenu getDisplayMenu() {
        return displayMenu;
    }

    public Sales getSales() {
        return sales;
    }

    public PurchaseMenu getPurchaseMenu() {
        return purchaseMenu;
    }

    public static Map<String, Item> getPulledInventory() {
        return pulledInventory;
    }

    public void showInventory() {
        System.out.println("\nOur currently inventory");
        sortedInventoryDisplay();
    }

    public void selectMenu( String choice ) {
        switch( choice ) {
            case "D" :
                displayMenu.showMenu();
                break;
            case "P" :
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

    public void sortedInventoryDisplay() {
        List<String> keys = new ArrayList<>( pulledInventory.keySet() ); // turn our map keys into a list
        Collections.sort( keys ); // sorts keys

        for ( String key : keys  ) {
            System.out.println( key + " | " + pulledInventory.get( key ).displayItem() );
        }
    }

    public static Map< String, Item> pullInventory() {
        inventory.createItemMap("catering.csv");
        return inventory.getItemMap();
    }

    public void exit() {// exits program
        readBalance().dispenseMoney();
        System.exit( 0 );
    }

    public Logger getLogger() {
        return log;
    }

}








