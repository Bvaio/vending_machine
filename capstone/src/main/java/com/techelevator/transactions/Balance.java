package com.techelevator.transactions;

import com.techelevator.inventory.Item;

import java.math.BigDecimal;

public class Balance {
    private BigDecimal balance = new BigDecimal( 0 );
    private final BigDecimal ZERO = new BigDecimal( 0 );

    public BigDecimal getBalance() {
        return balance;
    }

    public BigDecimal feedMoney( BigDecimal money ) {
        if ( money.compareTo( ZERO ) > 0 ) {
            balance = balance.add( money );
            return balance;
        }
        System.out.println( "Money not added" );
        return balance;
    }

    public BigDecimal payForItem( Item itemPrice ) {
//        BigDecimal itemTruePrice = new BigDecimal( itemPrice.getItemPrice() );
        if ( balance.compareTo( itemPrice.getItemPrice() ) >= 0 ) {
            balance = balance.subtract( itemPrice.getItemPrice() );
            return balance;
        }
        System.out.println( "Insufficient Funds" );
        return balance;
    }

    public void dispenseMoney() {
        double dollars = 0.0;
        int quarter = 0;
        int dime = 0;
        int nickel = 0;

        while( balance.compareTo( ZERO ) > 0 ) {
            if( balance.compareTo( new BigDecimal( 1 ) ) >= 0 ) {
                balance = balance.subtract( new BigDecimal( 1 ) ); // increase dollars
                dollars++;
                continue;
            } else if ( balance.compareTo( new BigDecimal( 0.25 ) ) >= 1 ) {// increase quarters
                balance = balance.subtract( new BigDecimal( 0.25 ) );
                quarter++;
                continue;
            } else if ( balance.compareTo( new BigDecimal( 0.10 ) ) >= 1 ) {// increases dimes
                balance = balance.subtract( new BigDecimal( 0.10 ) );
                dime++;
                continue;
            } else {
                nickel++;
                balance = balance.subtract( new BigDecimal( 0.05 ) ); // increase nickels
            }
        }

        System.out.println( "" );
    }


}
