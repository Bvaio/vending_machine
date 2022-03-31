package com.techelevator.transactions;

import com.techelevator.inventory.Item;

import java.math.BigDecimal;

public class Balance {
    private BigDecimal balance = BigDecimal.valueOf(0);
    private final BigDecimal ZERO = BigDecimal.valueOf(0);

    public BigDecimal getBalance() {
        return balance;
    }

    public BigDecimal feedMoney(BigDecimal money) {
        if (money.compareTo(ZERO) > 0) {
            balance = balance.add(money);
            return balance;
        }
        System.out.println("Money not added");
        return balance;
    }

    public void payForItem(Item item) {
        if (balance.compareTo(item.getItemPrice()) >= 0) {
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
