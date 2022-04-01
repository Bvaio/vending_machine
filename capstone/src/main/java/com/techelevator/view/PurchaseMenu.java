package com.techelevator.view;

import com.techelevator.inventory.Inventory;
import com.techelevator.inventory.Item;
import com.techelevator.transactions.Balance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PurchaseMenu extends Menu {
//    Inventory inventory = new Inventory(); // moved to menuClass
    private Map< String , Item > pulledInventory = pullInventory();

    @Override
    public void showMenu() {
//        System.out.println("Our current inventory");
//
//        sortedInventoryDisplay();

//        Balance balance = new Balance(); // moved to menuClass

        System.out.println("Please select from the following options:");
//        System.out.println("Display Current Inventory ( U )");
        System.out.println("Feed Money ( M )");
        System.out.println("Select Item ( S )");
        System.out.println("Finish Transaction ( F )");
        System.out.println("Current balance: $" + readBalance().getBalance());

        String menuChoice = getScan().nextLine().toUpperCase();

        switch (menuChoice) {
//            case "U" :
//                System.out.println("Our current inventory");
//                sortedInventoryDisplay();
//                System.out.println();
//                showMenu();
//                break;
            case "M":
                readBalance().feedMoney( getScan() );
                showMenu();
                break;
            case "S":
//                System.out.println("Our current inventory");
//                sortedInventoryDisplay();
//                readBalance().payForItem( getScan(), getInventory() );
//                showMenu();
                System.out.println("Our current inventory");
                sortedInventoryDisplay();
                System.out.println("What would you like to purchase");
                String scan = getScan().nextLine().toUpperCase();
                readBalance().payForItem( getPulledInventory().get( scan ) );
                showMenu();
                break;
            case "F":
                readBalance().dispenseMoney();
                System.out.println( "Current Balance: " + readBalance().getBalance() );
                System.out.println( "Returning to Main Menu\n ");
                super.showMenu();
//                exit(); // doesn't do anything currently
                break;
            default:
                System.out.println("Invalid selection, please try again.");
                showMenu();
        }
    }

//    @Override
//    public void sortedInventoryDisplay() {
////        List<String> keys = new ArrayList<>( getInventory().getItemMap().keySet() ); // turn our map keys into a list
////        Collections.sort( keys ); // sorts keys
////
////        for ( String key : keys  ) {
////            System.out.println( key + " | " + getInventory().getItemMap().get( key ).displayItem() );
////        }
//        List< String > keys = new ArrayList<>( pulledInventory.keySet() );
//        Collections.sort( keys );
//
//        for ( String key : keys ) {
//            System.out.println( key + " | " + pulledInventory.get( key ).displayItem() );
//        }
//
//    }

//    public Map< String, Item > pullInventory() {
//        getInventory().createItemMap("catering.csv");
//        return getInventory().getItemMap();
//    }

//    public void sortedInventoryDisplayForPurchase(Inventory inventory) {
//        List<String> keys = new ArrayList<>( inventory.getItemMap().keySet() ); // turn our map keys into a list
//        Collections.sort( keys ); // sorts keys
//        for ( String key : keys  ) {
//            System.out.println( key + " " + inventory.getItemMap().get(key).displayForPurchase() );
//        }
//    }
}



