package com.techelevator.view;

public class PurchaseMenu extends Menu {
    public String thing() {
        return "world";
    }

    @Override
    public void showMenu() {
        System.out.println("Our currenty inventory");

        super.sortedInventoryDisplay();

//        System.out.println( "What would you like to do?" );
//        System.out.println( "Return to Main Menu ( D )" );
//        System.out.println( "Purchase Menu ( P )" );
//        System.out.println( "Exit ( E )" );
//        String menuChoice = getScan().nextLine();

    }
}
