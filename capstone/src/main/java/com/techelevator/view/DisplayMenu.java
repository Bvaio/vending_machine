package com.techelevator.view;

import java.util.Scanner;

public class DisplayMenu extends Menu {

    public String thing() {
        return "Hello";
    }
    @Override
    public void showMenu() {
        getInventory().createItemMap( "catering.csv" );
        sortedInventoryDisplay();

        System.out.println( "The Display" );
        System.out.println( "Display Menu ( D )" );
        System.out.println( "Purchase Menu ( P )" );
        System.out.println( "Exit ( E )" );

//        String menuChoice = scan.nextLine().toUpperCase();
    }



}
