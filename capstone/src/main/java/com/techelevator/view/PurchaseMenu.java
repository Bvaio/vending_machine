package com.techelevator.view;


import com.techelevator.inventory.Item;
import java.util.*;

public class PurchaseMenu extends Menu {
    private Map< String , Item > pulledInventory = pullInventory();

    @Override
    public void showMenu() {

        System.out.println("\nPlease select from the following options:");
        System.out.println("Feed Money ( M )");
        System.out.println("Select Item ( S )");
        System.out.println("Finish Transaction ( F )");
        System.out.println("Current balance: $" + readBalance().getBalance());
        System.out.print( "- - >  " );

        String menuChoice = getScan().nextLine().toUpperCase();

        switch (menuChoice) {

            case "M" :
                readBalance().feedMoney( getScan() );
                showMenu();
                break;
            case "S":
//                System.out.println("Our current inventory");
//                sortedInventoryDisplay();
//                readBalance().payForItem( getScan(), getInventory() );
//                showMenu();

//                System.out.println("\nOur current inventory");
//                sortedInventoryDisplay();
                showInventory();

                System.out.println("\nWhat would you like to purchase");
                String scan = getScan().nextLine().toUpperCase();

                readBalance().payForItem( getPulledInventory().get( scan ) );
                showMenu();
                break;
            case "F":
                readBalance().dispenseMoney();
                System.out.println( "Current Balance: " + readBalance().getBalance() );
                System.out.println( "Returning to Main Menu\n ");
                super.showMenu();
                break;
            default:
                System.out.println("Invalid selection, please try again.");
                showMenu();
        }
    }
}



