package com.techelevator.transactions;


import com.techelevator.TextFormatter;
import com.techelevator.inventory.Item;
import com.techelevator.view.PurchaseMenu;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Balance {
    private static PurchaseMenu purchaseMenu = new PurchaseMenu();
    private static BigDecimal balance = BigDecimal.valueOf(0);
    private final BigDecimal ZERO = BigDecimal.valueOf(0);
    private final BigDecimal ONE = BigDecimal.valueOf(1);
    private final BigDecimal FIVE = BigDecimal.valueOf(5);
    private final BigDecimal TEN = BigDecimal.valueOf(10);
    private final BigDecimal TWENTY = BigDecimal.valueOf(20);
    private TextFormatter formatter = new TextFormatter();
    private Map<String, Integer> returnedChange = new HashMap<>();

    public void feedMoney(Scanner scan) {
        System.out.println("Please enter one of the denominations below: ");
        System.out.println(formatter.getGreenString( "$0\n$1\n$5\n$10\n$20"));

        System.out.println("Current balance: " + formatter.getGreenString("$" + balance));
        System.out.print("- - >  ");

        String selection = scan.nextLine();

        BigDecimal money = null;
        try {
            money = BigDecimal.valueOf(Integer.parseInt(selection));
        } catch (NumberFormatException cannotParseIntoBigDecimal) {
            money = ZERO;
        }
        while (!(money.compareTo(TWENTY) == 0) && !(money.compareTo(TEN) == 0) && !(money.compareTo(FIVE) == 0) && !(money.compareTo(ONE) == 0) && !(money.compareTo(ZERO) == 0)){
            System.out.println(formatter.getRedString("Invalid denomination selected. Please try again."));
            System.out.println(formatter.getGreenString( "$0\n$1\n$5\n$10\n$20"));
            money = BigDecimal.valueOf(Integer.parseInt(scan.nextLine()));
        }
        if (money.compareTo(ZERO) == 0) {
            System.out.println("No money added");
            raiseBalance(money);
        } else if ((money.compareTo(TWENTY) == 0) || (money.compareTo(TEN) == 0) || (money.compareTo(FIVE) == 0) || (money.compareTo(ONE) == 0)) {
            raiseBalance(money);
        } else {

            feedMoney(scan);
        }

        System.out.println("\nNew Balance: " + formatter.getGreenString("$"+ balance));
        System.out.println("Continue adding funds?\nYes ( " + formatter.getBlueString("Y") + " )\nNo ( " + formatter.getBlueString("N") + " )");
        System.out.print("- - >  ");
        String choice = scan.nextLine().toUpperCase();
        while (!choice.equals("Y") && !choice.equals("N")) {
            System.out.println(formatter.getRedString("Invalid selection, please try again."));
            choice = scan.nextLine().toUpperCase();
        }

        if (choice.equalsIgnoreCase("Y")) {
            feedMoney(scan);
        } else if (choice.equalsIgnoreCase("N")) {
            System.out.println("\n"+formatter.getBlueString("Returning to Purchase Menu"));
        }
    }

    public void payForItem(Item item) {
        if (item.getInventoryCount() > 0) {
            if (balance.compareTo(item.getItemPrice()) >= 0) {
                balance = balance.subtract(item.getItemPrice());
                item.removeOneFromInventory();
                System.out.print(formatter.getTEXT_GREEN_AND_BOLD() + "Dispensing item");
                bufferingTransaction();
            } else {
                System.out.println("Insufficient funds");
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
        returnedChange.put("Dollar", dollars);
        returnedChange.put("Quarter", quarter);
        returnedChange.put("Dime", dime);
        returnedChange.put("Nickel", nickel);
    }

    public void dispenseChange() {
        dispenseMoney();
        System.out.print(formatter.getTEXT_GREEN() + formatter.getBOLD_FONT() + "Dispensing");
        bufferingTransaction();
        System.out.println(
                        formatter.getGreenString("Change given: ") +
                        formatter.getGreenString(returnedChange.get("Dollar").toString()) +  " dollar(s), " +
                        formatter.getGreenString(returnedChange.get("Quarter").toString()) + " quarter(s), " +
                        formatter.getGreenString(returnedChange.get("Dime").toString())  + " dime(s), " +
                        formatter.getGreenString(returnedChange.get("Nickel").toString()) + " nickel(s)");
    }

    public void raiseBalance(BigDecimal money) {
        balance = balance.add(money);
    }

    public BigDecimal getBalance() {
        return balance.setScale(2, RoundingMode.CEILING);
    }

    public void setBalance(BigDecimal money) {
        balance = money;
    }

    public Map<String, Integer> getReturnedChange() {
        return returnedChange;
    }

    //just added in to have some fun and interactivity, would not place in a large asynchronous function.
    public void bufferingTransaction() {
        int count = 0;
        while (count < 1) {
            try {
                Thread.sleep(500);
                System.out.print(".");
                Thread.sleep(500);
                System.out.print(".");
                Thread.sleep(500);
                System.out.print(".");
                Thread.sleep(500);
                System.out.print("\b\b\b");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } count++;
        }
        System.out.println(formatter.getTEXT_RESET_COLOR());
    }
}
