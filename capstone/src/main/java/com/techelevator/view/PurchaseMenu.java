package com.techelevator.view;


import com.techelevator.inventory.Item;
import java.math.BigDecimal;
import java.util.*;

public class PurchaseMenu extends Menu {
    private Map< String , Item > pulledInventory = pullInventory();

    public void purchaseMenu() {

        System.out.println("\nPlease select from the following options:");
        System.out.println("Feed Money ( " + getFormatter().getBlueString("M") + " )");
        System.out.println("Select Item ( " + getFormatter().getBlueString("S") +  " )");
        System.out.println("Finish Transaction & Return to Main Menu ( "+ getFormatter().getBlueString("F")+ " )");
        System.out.println("Current balance: " + getFormatter().getGreenString("$" + readBalance().getBalance().toString()));
        System.out.print( "- - >  " );

        String menuChoice = getScan().nextLine().toUpperCase();

        switch (menuChoice) {

            case "M" :
                getLogger().moneyFed(getScan());
                purchaseMenu();
                break;
            case "S":

                showInventory();

                System.out.println("\nWhat would you like to purchase");

                String scan = getScan().nextLine().toUpperCase();
                final BigDecimal previousBalance = readBalance().getBalance();

                try {
                    getLogger().itemPurchase(scan, getPulledInventory().get(scan));
                }
                catch ( NullPointerException notInListItem ) {
                    System.out.println(getFormatter().getRedString("Item not in list") );
                    purchaseMenu();
                }

                if ( !previousBalance.equals( readBalance().getBalance() ) ) {
                    System.out.println( userEats(getPulledInventory().get( scan ) ) );
                }

                purchaseMenu();
                break;
            case "F":
                getLogger().moneyDispensed();
                System.out.println( "Current Balance: " + getFormatter().getGreenString("$" + readBalance().getBalance().toString()));
                System.out.println( getFormatter().getBlueString("Returning to Main Menu\n "));
                super.showMenu();
                break;
            default:
                System.out.println(getFormatter().getRedString("Invalid selection, please try again."));
                purchaseMenu();
        }
    }

    public String userEats( Item item ) {
        final String MUNCHY = "munchy";
        final String SANDWICH = "sandwich";
        final String DRINK = "drink";
        final String DESSERT = "dessert";

        if ( item.getItemType().equalsIgnoreCase( MUNCHY ) ) {
            return getFormatter().getBlueString("Munchy, Munchy, so Good");
        }
        else if ( item.getItemType().equalsIgnoreCase( SANDWICH ) ) {
            return getFormatter().getBlueString("Sandwich So Delicious, Yum!");
        }
        else if ( item.getItemType().equalsIgnoreCase( DRINK ) ) {
            return getFormatter().getBlueString("Drinky, Drinky, Slurp Slurp!");
        }
        else if ( item.getItemType().equalsIgnoreCase( DESSERT ) ) {
            return getFormatter().getBlueString("Sugar, Sugar, so Sweet!");
        }
        else {
            return getFormatter().getBlueString("That was a good ") + item.getItemType();
        }
    }
}
