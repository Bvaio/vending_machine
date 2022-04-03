package com.techelevator.view;

public class DisplayMenu extends Menu {

    public void displayMenu() {

        showInventory();

        System.out.println( "\nWhat would you like to do?" );
        System.out.println( "Return to Main Menu ( " + getFormatter().getBlueString("D") + " )" );
        System.out.println( "Purchase Menu ( " + getFormatter().getBlueString("P") + " )" );
        System.out.println( "Exit ( " + getFormatter().getBlueString("E") + " )" );
        System.out.print( "- - >  " );

        String menuChoice = getScan().nextLine().toUpperCase();

        switch (menuChoice) {
            case "D":
                super.showMenu();
                break;
            case "P":
                getPurchaseMenu().purchaseMenu();
                break;
            case "E":
                exit();
                break;
            default:
                System.out.println("Invalid selection, please try again.");
                displayMenu();
        }
    }
}
