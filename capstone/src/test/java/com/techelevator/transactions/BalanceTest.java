package com.techelevator.transactions;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.*;

public class BalanceTest {
    Balance balance;

    @Before
    public void test (){

    }

    @Test
    public void increase_balance_by_134_increases_starting_balance_100_by_134() {
        balance = new Balance();
        BigDecimal startingBalance = BigDecimal.valueOf( 100 );
        BigDecimal addToBalance = BigDecimal.valueOf( 134 );
        BigDecimal expectedBalance = startingBalance.add( addToBalance ).setScale( 2, RoundingMode.CEILING );

        balance.setBalance( startingBalance );
        balance.setBalance( addToBalance );

        assertEquals(expectedBalance + " not equal to " + balance.getBalance(), expectedBalance, balance.getBalance());
    }

    @Test
    public void dispenseMoney_balance_100_dispenses_only_dollars() {
        balance = new Balance();
        BigDecimal startingBalance = BigDecimal.valueOf( 100 );
        balance.setBalance( startingBalance );
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
}
