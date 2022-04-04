package com.techelevator.view;


import com.techelevator.TextFormatter;
import com.techelevator.data.Logger;
import com.techelevator.data.Sales;
import com.techelevator.inventory.Inventory;
import com.techelevator.inventory.Item;
import com.techelevator.transactions.Balance;

import java.util.*;

public class Menu {
    private TextFormatter formatter = new TextFormatter();
    private Scanner scan = new Scanner( System.in );
    private static Inventory inventory = new Inventory();
    private static DisplayMenu displayMenu = new DisplayMenu();
    private static PurchaseMenu purchaseMenu = new PurchaseMenu();
    private static Balance balance = new Balance();
    private static Logger log = new Logger();
    private static Sales sales = new Sales();
    private static Map< String, Item > pulledInventory = pullInventory();

    public void showMenu() {

        if ( inventory.isInvalidFile() ) {
            exit();
        }

        System.out.println( "Welcome to the Terminal" );
        System.out.println( "Display Menu ( " + formatter.getBlueString("D") +" )" );
        System.out.println( "Purchase Menu ( " + formatter.getBlueString("P") + " )" );
        System.out.println( "Exit ( " + formatter.getBlueString("E") + " )" );
        System.out.print( "- - >  " );
        String menuChoice = scan.nextLine().toUpperCase();
        selectMenu( menuChoice );
    }

    public void selectMenu( String choice ) {
        switch( choice ) {
            case "D" :
                displayMenu.displayMenu();
                break;
            case "P" :
                purchaseMenu.purchaseMenu();
                break;
            case "S" :
                //secret sales log
                sales.generateSalesLog();
                System.out.println("Sales report generated at " + formatter.getBlueString(getLogger().convertDateTime()));
                showMenu();
                break;
            case "E" :
                exit();
                break;
            default:
                System.out.println(formatter.getRedString( "None selected try again" ));
                showMenu();
                break;
        }
    }

    public void sortedInventoryDisplay() {
        List<String> keys = new ArrayList<>( pulledInventory.keySet() ); // turn our map keys into a list
        Collections.sort( keys ); // sorts keys

        for ( String key : keys  ) {
            System.out.println( getFormatter().getBlueString(key) + " | " + pulledInventory.get( key ).displayItem() );
        }
    }

    public static Map< String, Item> pullInventory() {
        inventory.createItemMap("catering.csv");
//        inventory.createItemMap("catering1.csv");
//        inventory.createItemMap( "src/test/java/com/techelevator/testFiles/validTestFile.csv" );
        if ( inventory.isInvalidFile() ) {
            exit();
        }
        return inventory.getItemMap();
    }

    public void showInventory() {
        System.out.println("\nOur current inventory");
        sortedInventoryDisplay();
    }

    public static void exit() { // exits program
        System.exit( 0 );
    }

    public Balance readBalance() {
        return balance;
    }

    public Scanner getScan() {
        return scan;
    }

    public Inventory getInventory() {
        return inventory;
    }

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

    public Logger getLogger() {
        return log;
    }

    public TextFormatter getFormatter(){
        return formatter;
    }

}
