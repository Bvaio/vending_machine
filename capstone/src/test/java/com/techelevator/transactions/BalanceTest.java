package com.techelevator.transactions;

import org.junit.Test;

import static org.junit.Assert.*;

public class BalanceTest {
    Balance balance = new Balance();
    @Test
    public void test (){
        balance.getBalance();
        balance.dispenseMoney();
    }
}
