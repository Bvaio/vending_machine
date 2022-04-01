package com.techelevator.transactions;

import com.techelevator.inventory.Item;
import com.techelevator.view.PurchaseMenu;

import java.io.File;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Scanner;

public class Balance {
    private BigDecimal balance = BigDecimal.valueOf(0);
    private final BigDecimal ZERO = BigDecimal.valueOf(0);
    private final BigDecimal ONE = BigDecimal.valueOf(1);
    private final BigDecimal FIVE = BigDecimal.valueOf(5);
    private final BigDecimal TEN = BigDecimal.valueOf(10);
    private final BigDecimal TWENTY = BigDecimal.valueOf(20);
//    PurchaseMenu purchaseMenu = new PurchaseMenu();

    public BigDecimal getBalance() {
        return balance;
    }

    public void feedMoney(Scanner scan) {
        System.out.println("Please enter one of the denominations below: ");
        System.out.println("$1\n$5\n$10\n$20");
        BigDecimal money = BigDecimal.valueOf(Integer.parseInt(scan.nextLine()));
        if (money.compareTo(ZERO) > 0 && (money.compareTo(TWENTY) == 0) || (money.compareTo(TEN) == 0) || (money.compareTo(FIVE) == 0) || (money.compareTo(ONE) == 0)) {
            balance = balance.add(money);
        } else {
            System.out.println("Invalid denomination selected. Please try again.");
            feedMoney(scan);
        }
        System.out.println("Continue adding funds?\nYes ( Y )\nNo ( N )");
        String choice = scan.nextLine().toUpperCase();
        if (choice.equals("Y")) {
            feedMoney(scan);
        } else {
            System.out.println("test");
        }
    }



//        } else {
//            purchaseMenu.showMenu();
//        }
//    }

    public void payForItem(Item item) {
        if ( item.getItemPrice().compareTo( ZERO ) == 0 ) {
            System.out.println( "Item cannot be purchased at this time" );
        } else if ( balance.compareTo( item.getItemPrice() ) >= 0 ) {
            balance = balance.subtract(item.getItemPrice());
            item.removeOneFromInventory();
        }
        System.out.println("Insufficient Funds"); //modify for validation and looping back to menu
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
            } else if (balance.compareTo(BigDecimal.valueOf(0.25)) >= 1) {// increase quarters
                balance = balance.subtract(BigDecimal.valueOf(0.25));
                quarter++;
                continue;
            } else if (balance.compareTo(BigDecimal.valueOf(0.10)) >= 1) {// increases dimes
                balance = balance.subtract(BigDecimal.valueOf(0.10));
                dime++;
                continue;
            } else {
                nickel++;
                balance = balance.subtract(BigDecimal.valueOf(0.05)); // increase nickels
            }
        }   System.out.println("Dispensing " + dollars + " dollar(s), " + quarter + " quarter(s), " + dime + " dime(s), " + nickel + " nickel(s)");
    }
}
