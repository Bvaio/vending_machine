package com.techelevator.view;

import com.techelevator.inventory.Inventory;
import com.techelevator.inventory.Item;
import com.techelevator.transactions.Balance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PurchaseMenu extends Menu {
    Balance balance = new Balance();
    Inventory inventory = new Inventory();
    public String thing() {
        return "world";
    }

    @Override
    public void showMenu() {

        inventory.createItemMap("catering.csv");
        sortedInventoryDisplay();

        System.out.println("Our current inventory");

        sortedInventoryDisplay();

//        System.out.println( "What would you like to do?" );
//        System.out.println( "Return to Main Menu ( D )" );
//        System.out.println( "Purchase Menu ( P )" );
//        System.out.println( "Exit ( E )" );
//        String menuChoice = getScan().nextLine();

        Balance balance = new Balance();
        System.out.println("Please select from the following options:");
        System.out.println("Feed Money ( M )");
        System.out.println("Select Item ( S )");
        System.out.println("Finish Transaction ( F )");
        System.out.println("Current balance: $" + balance.getBalance());
        String menuChoice = getScan().nextLine().toUpperCase();
        switch (menuChoice) {

            case "M":
                balance.feedMoney(getScan());
                break;
            case "S":
                sortedInventoryDisplay();
                balance.payForItem(getScan(),inventory);
                sortedInventoryDisplay();
                break;
            case "F":
                exit();
                break;
            default:
                System.out.println("Invalid selection, please try again.");
        }
    }
    @Override
    public void sortedInventoryDisplay() {
        List<String> keys = new ArrayList<>( inventory.getItemMap().keySet() ); // turn our map keys into a list
        Collections.sort( keys ); // sorts keys

        for ( String key : keys  ) {
            System.out.println( key + " | " + inventory.getItemMap().get( key ).displayItem() );
        }
    }

//    public void sortedInventoryDisplayForPurchase(Inventory inventory) {
//        List<String> keys = new ArrayList<>( inventory.getItemMap().keySet() ); // turn our map keys into a list
//        Collections.sort( keys ); // sorts keys
//        for ( String key : keys  ) {
//            System.out.println( key + " " + inventory.getItemMap().get(key).displayForPurchase() );
//        }
//    }
}



