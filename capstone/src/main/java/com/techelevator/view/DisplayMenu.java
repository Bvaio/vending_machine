package com.techelevator.view;

import java.util.Scanner;

public class DisplayMenu extends Menu {
    private PurchaseMenu purchaseMenu;

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

        String menuChoice = getScan().nextLine().toUpperCase();
        switch (menuChoice) {
            case "D":
                super.showMenu();
                break;
            case "P":
                PurchaseMenu purchaseMenu = new PurchaseMenu();
                purchaseMenu.showMenu();
                break;
            case "E":
                exit();
                break;
            default:
                System.out.println("Invalid selection, please try again.");
        }
    }



}
