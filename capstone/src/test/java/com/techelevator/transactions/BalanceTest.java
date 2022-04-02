package com.techelevator.transactions;

import com.techelevator.inventory.Item;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.*;

public class BalanceTest {
    Balance balance;
    Item item;

    @Before
    public void test (){
        balance = new Balance();
        item = new Item( "A1", "Butter Mountain", BigDecimal.valueOf( 1.40 ), "Dessert" );
    }

    @After
    public void afterTests() {
        balance.setBalance( BigDecimal.valueOf( 0 ) );
    }

    @Test
    public void increase_balance_by_134_increases_starting_balance_100_by_134() {
        BigDecimal startingBalance = BigDecimal.valueOf( 100 );
        BigDecimal addToBalance = BigDecimal.valueOf( 134 );
        BigDecimal expectedBalance = startingBalance.add( addToBalance ).setScale( 2, RoundingMode.CEILING );

        balance.setBalance( startingBalance );
        balance.raiseBalance( addToBalance );

        assertEquals(expectedBalance + " not equal to " + balance.getBalance(), expectedBalance, balance.getBalance());
    }

    @Test
    public void dispenseMoney_balance_100_dispenses_only_dollars() {
        BigDecimal startingBalance = BigDecimal.valueOf( 100 );
        balance.raiseBalance( startingBalance );
        balance.dispenseMoney();

        int ExpectedDollarAmount = 100;
        int ExpectedQuarterAmount = 0;
        int ExpectedDimeAmount = 0;
        int ExpectedNickelAmount = 0;

        assertEquals( ExpectedDollarAmount, balance.getReturnedChange().get( "Dollar" ).intValue() );
        assertEquals( ExpectedQuarterAmount, balance.getReturnedChange().get( "Quarter" ).intValue() );
        assertEquals( ExpectedDimeAmount, balance.getReturnedChange().get( "Dime" ).intValue() );
        assertEquals( ExpectedNickelAmount, balance.getReturnedChange().get( "Nickel" ).intValue() );
    }

    @Test
    public void dispenseMoney_dispenses_1_40_one_dollar_one_quarter_two_dimes() {
        BigDecimal startingBalance = BigDecimal.valueOf( 1.40 );
        balance.raiseBalance( startingBalance );
        balance.dispenseMoney();

        int expectedDollarAmount = 1;
        int expectedQuarterAmount = 1;
        int expectedDimeAmount = 1;
        int expectedNickelAmount = 1;

        assertEquals( "Expected " + expectedDollarAmount+ " dollar(s) but got " + balance.getReturnedChange().get("Dollar"),
                expectedDollarAmount, balance.getReturnedChange().get( "Dollar" ).intValue() );

        assertEquals( "Expected " + expectedQuarterAmount+ " quarter(s) but got " + balance.getReturnedChange().get("Quarter"),
                expectedQuarterAmount, balance.getReturnedChange().get( "Quarter" ).intValue() );

        assertEquals( "Expected " + expectedDimeAmount+ " dime(s) but got " + balance.getReturnedChange().get("Dime"),
                expectedDimeAmount, balance.getReturnedChange().get( "Dime" ).intValue() );

        assertEquals( "Expected " + expectedNickelAmount+ " nickel(s) but got " + balance.getReturnedChange().get("Nickel"),
                expectedNickelAmount, balance.getReturnedChange().get( "Nickel" ).intValue() );
    }

    @Test
    public void dispenseMoney_dispenses_0_75_three_quarters() {
        BigDecimal startingBalance = BigDecimal.valueOf( 0.75 );
        balance.raiseBalance( startingBalance );
        balance.dispenseMoney();

        int expectedDollarAmount = 0;
        int expectedQuarterAmount = 3;
        int expectedDimeAmount = 0;
        int expectedNickelAmount = 0;

        assertEquals( "Expected " + expectedDollarAmount+ " dollar(s) but got " + balance.getReturnedChange().get("Dollar"),
                expectedDollarAmount, balance.getReturnedChange().get( "Dollar" ).intValue() );

        assertEquals( "Expected " + expectedQuarterAmount+ " quarter(s) but got " + balance.getReturnedChange().get("Quarter"),
                expectedQuarterAmount, balance.getReturnedChange().get( "Quarter" ).intValue() );

        assertEquals( "Expected " + expectedDimeAmount+ " dime(s) but got " + balance.getReturnedChange().get("Dime"),
                expectedDimeAmount, balance.getReturnedChange().get( "Dime" ).intValue() );

        assertEquals( "Expected " + expectedNickelAmount+ " nickel(s) but got " + balance.getReturnedChange().get("Nickel"),
                expectedNickelAmount, balance.getReturnedChange().get( "Nickel" ).intValue() );
    }

    @Test
    public void dispenseMoney_dispenses_0_20_two_dimes() {
        BigDecimal startingBalance = BigDecimal.valueOf( 0.20 );
        balance.raiseBalance( startingBalance );
        balance.dispenseMoney();

        int expectedDollarAmount = 0;
        int expectedQuarterAmount = 0;
        int expectedDimeAmount = 2;
        int expectedNickelAmount = 0;

        assertEquals( "Expected " + expectedDollarAmount+ " dollar(s) but got " + balance.getReturnedChange().get("Dollar"),
                expectedDollarAmount, balance.getReturnedChange().get( "Dollar" ).intValue() );

        assertEquals( "Expected " + expectedQuarterAmount+ " quarter(s) but got " + balance.getReturnedChange().get("Quarter"),
                expectedQuarterAmount, balance.getReturnedChange().get( "Quarter" ).intValue() );

        assertEquals( "Expected " + expectedDimeAmount+ " dime(s) but got " + balance.getReturnedChange().get("Dime"),
                expectedDimeAmount, balance.getReturnedChange().get( "Dime" ).intValue() );

        assertEquals( "Expected " + expectedNickelAmount+ " nickel(s) but got " + balance.getReturnedChange().get("Nickel"),
                expectedNickelAmount, balance.getReturnedChange().get( "Nickel" ).intValue() );
    }

    @Test
    public void dispenseMoney_dispenses_0_05_one_nickel() {
        BigDecimal startingBalance = BigDecimal.valueOf( 0.05 );
        balance.raiseBalance( startingBalance );
        balance.dispenseMoney();

        int expectedDollarAmount = 0;
        int expectedQuarterAmount = 0;
        int expectedDimeAmount = 0;
        int expectedNickelAmount = 1;

        assertEquals( "Expected " + expectedDollarAmount+ " dollar(s) but got " + balance.getReturnedChange().get("Dollar"),
                expectedDollarAmount, balance.getReturnedChange().get( "Dollar" ).intValue() );

        assertEquals( "Expected " + expectedQuarterAmount+ " quarter(s) but got " + balance.getReturnedChange().get("Quarter"),
                expectedQuarterAmount, balance.getReturnedChange().get( "Quarter" ).intValue() );

        assertEquals( "Expected " + expectedDimeAmount+ " dime(s) but got " + balance.getReturnedChange().get("Dime"),
                expectedDimeAmount, balance.getReturnedChange().get( "Dime" ).intValue() );

        assertEquals( "Expected " + expectedNickelAmount+ " nickel(s) but got " + balance.getReturnedChange().get("Nickel"),
                expectedNickelAmount, balance.getReturnedChange().get( "Nickel" ).intValue() );
    }

    @Test
    public void pay_for_item() {
        balance.setBalance( BigDecimal.valueOf( 2.00 ) );

        balance.payForItem( item );

        BigDecimal expectedBalanceAfterPurchase = BigDecimal.valueOf( 0.60 ).setScale( 2, RoundingMode.CEILING );

        assertEquals( "Valid purchase should decrease funds", expectedBalanceAfterPurchase, balance.getBalance() );
    }

    @Test
    public void insufficient_funds() {
        balance.setBalance( BigDecimal.valueOf( 1.00 ) );

        balance.payForItem( item );

        BigDecimal expectedBalanceAfterPurchase = BigDecimal.valueOf( 1.00 ).setScale( 2, RoundingMode.CEILING );

        assertEquals( "Should System.out \"Insufficient Funds\"", expectedBalanceAfterPurchase, balance.getBalance() );
    }

    @Test
    public void balance_remains_same_if_item_out_of_stock() {
        balance.setBalance( BigDecimal.valueOf( 1.40 ) );

        while ( item.getInventoryCount() > 0 ) {
            item.removeOneFromInventory();
        }

        balance.payForItem( item );

        BigDecimal expectedBalanceAfterPurchase = BigDecimal.valueOf( 1.40 ).setScale( 2, RoundingMode.CEILING );

        assertEquals( "Should System.out \"Item out of stock\"", expectedBalanceAfterPurchase, balance.getBalance() );
    }






}
