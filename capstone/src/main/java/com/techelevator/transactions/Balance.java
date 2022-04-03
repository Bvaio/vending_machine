package com.techelevator.transactions;

import com.techelevator.inventory.Item;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Balance {
    private static BigDecimal balance = BigDecimal.valueOf(0);
    private final BigDecimal ZERO = BigDecimal.valueOf(0);
    private final BigDecimal ONE = BigDecimal.valueOf(1);
    private final BigDecimal FIVE = BigDecimal.valueOf(5);
    private final BigDecimal TEN = BigDecimal.valueOf(10);
    private final BigDecimal TWENTY = BigDecimal.valueOf(20);
    private Map< String, Integer > returnedChange = new HashMap<>();

    public void feedMoney( Scanner scan ) {
        System.out.println("Please enter one of the denominations below: ");
        System.out.println("$0\n$1\n$5\n$10\n$20");

        System.out.println("Current balance: $" + getBalance());
        System.out.print("- - >  ");

        String selection = scan.nextLine();

        BigDecimal money = null;
        try {
            money = BigDecimal.valueOf(Integer.parseInt(selection));
        } catch ( NumberFormatException cannotParseIntoBigDecimal ) {
            money = ZERO;
        }

        if (money.compareTo(ZERO) == 0) {
            System.out.println( "No money added" );
            raiseBalance( money );
        } else if ( (money.compareTo(TWENTY) == 0) || (money.compareTo(TEN) == 0) || (money.compareTo(FIVE) == 0) || (money.compareTo(ONE) == 0) ) {
            raiseBalance(money);
        } else {
            System.out.println("Invalid denomination selected. Please try again.");
            feedMoney(scan);
        }

        System.out.println( "\nNew Balance: $" + balance );
        System.out.println("Continue adding funds?\nYes ( Y )\nNo ( N )");
        System.out.print( "- - >  " );
        String choice = scan.nextLine().toUpperCase();

        while (!choice.equals("Y") && !choice.equals("N")) {
            System.out.println("Invalid selection, please try again.");
            choice = scan.nextLine().toUpperCase();
        }

        if (choice.equalsIgnoreCase("Y")) {
            feedMoney( scan );
        }
        else if (choice.equalsIgnoreCase("N")) {
            System.out.println("Returning to Purchase Menu");
        }
    }

    public void payForItem( Item item ) {
        if ( item.getInventoryCount() > 0 ) {
            if ( balance.compareTo( item.getItemPrice() ) >= 0 ) {
                balance = balance.subtract(item.getItemPrice());
                item.removeOneFromInventory();
            } else {
                System.out.println( "Insufficient funds" );
            }
        } else {
            System.out.println("Item out of stock");
        }
    }

    public void dispenseMoney() {
        int dollars = 0;
        int quarter = 0;
        int dime = 0;
        int nickel = 0;

        while (balance.compareTo(ZERO) > 0) {
            if (balance.compareTo(BigDecimal.valueOf(1)) >= 0) {
                balance = balance.subtract(BigDecimal.valueOf(1)); // increase dollars
                dollars++;
                continue;
            } else if (balance.compareTo(BigDecimal.valueOf(0.25)) >= 0) {// increase quarters
                balance = balance.subtract(BigDecimal.valueOf(0.25));
                quarter++;
                continue;
            } else if (balance.compareTo(BigDecimal.valueOf(0.10)) >= 0) {// increases dimes
                balance = balance.subtract(BigDecimal.valueOf(0.10));
                dime++;
                continue;
            } else {
                nickel++;
                balance = balance.subtract(BigDecimal.valueOf(0.05)); // increase nickels
            }
        }
        returnedChange.put( "Dollar", dollars );
        returnedChange.put( "Quarter", quarter );
        returnedChange.put( "Dime", dime );
        returnedChange.put( "Nickel", nickel );
    }

    public void dispenseChange() {
        dispenseMoney();
        System.out.println(
                "Dispensing " + returnedChange.get( "Dollar" ) + " dollar(s), " +
                        returnedChange.get( "Quarter" ) + " quarter(s), " +
                        returnedChange.get( "Dime" ) + " dime(s), " +
                        returnedChange.get( "Nickel" ) + " nickel(s)" );
    }

    public void raiseBalance(BigDecimal money) {
        balance = balance.add(money);
    }

    public BigDecimal getBalance() {
        return balance.setScale(2, RoundingMode.CEILING);
    }

    public void setBalance( BigDecimal money ) {
        balance = money;
    }

    public Map<String, Integer> getReturnedChange() {
        return returnedChange;
    }

}
