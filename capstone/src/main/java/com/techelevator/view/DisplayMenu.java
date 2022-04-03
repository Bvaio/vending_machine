package com.techelevator.view;

public class DisplayMenu extends Menu {


    public void displayMenu() {

        showInventory();

        System.out.println( "\nWhat would you like to do?" );
        System.out.println( "Return to Main Menu ( D )" );
        System.out.println( "Purchase Menu ( P )" );
        System.out.println( "Exit ( E )" );
        System.out.print( "- - >  " );

        String menuChoice = getScan().nextLine().toUpperCase();

        switch (menuChoice) {
            case "D":
                super.showMenu();
                break;
            case "P":
                getPurchaseMenu().showMenu();
                break;
            case "E":
                exit();
                break;
            default:
                System.out.println("Invalid selection, please try again.");
                showMenu();
        }
    }
}
