package com.techelevator.transactions;

import com.techelevator.inventory.Inventory;
import com.techelevator.inventory.Item;
import com.techelevator.view.Menu;
import com.techelevator.view.PurchaseMenu;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

public class Balance {
    private static BigDecimal balance = BigDecimal.valueOf(0);
    private final BigDecimal ZERO = BigDecimal.valueOf(0);
    private final BigDecimal ONE = BigDecimal.valueOf(1);
    private final BigDecimal FIVE = BigDecimal.valueOf(5);
    private final BigDecimal TEN = BigDecimal.valueOf(10);
    private final BigDecimal TWENTY = BigDecimal.valueOf(20);
    private static PurchaseMenu purchaseMenu = new PurchaseMenu();


    public void feedMoney(Scanner scan) {
        System.out.println("Please enter one of the denominations below: ");
        System.out.println("$1\n$5\n$10\n$20");
        System.out.println("Current balance: $" + getBalance());
        BigDecimal money = BigDecimal.valueOf(Integer.parseInt(scan.nextLine()));
        if (money.compareTo(ZERO) > 0 && (money.compareTo(TWENTY) == 0) || (money.compareTo(TEN) == 0) || (money.compareTo(FIVE) == 0) || (money.compareTo(ONE) == 0)) {
            setBalance(money);
        } else {
            System.out.println("Invalid denomination selected. Please try again.");
            feedMoney(scan);
        }
        System.out.println("Continue adding funds?\nYes ( Y )\nNo ( N )");
        String choice = scan.nextLine().toUpperCase();
        if (choice.equals("Y")) {
            feedMoney(scan);
        }
//        else {
//            purchaseMenu.showMenu();
//        }
    }


    public void payForItem(Scanner scan, Inventory inventory) {
        System.out.println("Please select from the list below: ");
        String selection = scan.nextLine().toUpperCase(); // added to uppercase
        if (inventory.getItemMap().containsKey(selection) && inventory.getItemMap().get(selection).getItemPrice().compareTo(ZERO) == 0) {
            System.out.println("Item cannot be purchased at this time");
        } else if (balance.compareTo(inventory.getItemMap().get(selection).getItemPrice()) >= 0) {
            balance = balance.subtract(inventory.getItemMap().get(selection).getItemPrice());
            inventory.getItemMap().get(selection).removeOneFromInventory();
        } else {
            System.out.println("Insufficient Funds"); //modify for validation and looping back to menu
        }
//        purchaseMenu.showMenu();
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


    public BigDecimal getBalance() {
        return balance;
    }
    public static void setBalance (BigDecimal money) {
        balance = balance.add(money);
    }
}
