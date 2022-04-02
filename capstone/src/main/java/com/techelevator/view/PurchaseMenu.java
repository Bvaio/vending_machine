package com.techelevator.view;


import com.techelevator.inventory.Item;
import com.techelevator.view.Menu;

import java.math.BigDecimal;
import java.math.RoundingMode;
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


                getLogger().writeMoneyFed(readBalance().getBalance());
                readBalance().feedMoney( getScan() );
                getLogger().formatEndingBalance(readBalance().getBalance());
//                getLogger().formatEndingBalance(readBalance().getBalance());
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
                final BigDecimal previousBalance = readBalance().getBalance();

                try {
                    readBalance().payForItem( getPulledInventory().get( scan ) );
                } catch ( NullPointerException notInListItem ) {
                    System.out.println( "Item not in list");
                    showMenu();
                }

                if ( !previousBalance.equals( readBalance().getBalance() ) ) {
                    userEats(getPulledInventory().get( scan ) );
                }

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

    public void userEats( Item item ) {
        final String MUNCHY = "munchy";
        final String SANDWICH = "sandwich";
        final String DRINK = "drink";
        final String DESSERT = "dessert";

        if ( item.getItemType().equalsIgnoreCase( MUNCHY ) ) {
            System.out.println( "Munchy, Munchy, so Good!" );
        }
        else if ( item.getItemType().equalsIgnoreCase( SANDWICH ) ) {
            System.out.println( "Sandwich So Delicious, Yum!" );
        }
        else if ( item.getItemType().equalsIgnoreCase( DRINK ) ) {
            System.out.println( "Drinky, Drinky, Slurp Slurp!" );
        }
        else if ( item.getItemType().equalsIgnoreCase( DESSERT ) ) {
            System.out.println( "Sugar, Sugar, so Sweet!" );
        }
        else {
            System.out.println( "That was a good " + item.getItemType() );
        }
    }
}
