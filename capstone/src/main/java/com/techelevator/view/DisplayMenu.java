package com.techelevator.view;

import java.util.Scanner;

public class DisplayMenu extends Menu {

    public String thing() {
        return "Hello";
    }
    @Override
    public void showMenu() {
        System.out.println("Our currently inventory");

        sortedInventoryDisplay();

        System.out.println( "What would you like to do?" );
        System.out.println( "Return to Main Menu ( D )" );
        System.out.println( "Purchase Menu ( P )" );
        System.out.println( "Exit ( E )" );
        String menuChoice = getScan().nextLine().toUpperCase();

        selectMenu( menuChoice );


//        String menuChoice = scan.nextLine().toUpperCase();
    }

    @Override
    public void selectMenu( String choice ) {
        switch( choice ) {
            case "D" :
                super.showMenu();
                break;
            case "P" :
                super.getPurchaseMenu().showMenu();
                break;
//            case "S" :
//                sales.getSales();
            case "E" :
                exit();
                break;
            default:
                System.out.println( "None selected try again" );
        }
    }


}
