package com.techelevator.view;

import com.techelevator.transactions.Balance;

public class PurchaseMenu extends Menu {
    Balance balance = new Balance();
    public String thing() {
        return "world";
    }

    @Override
    public void showMenu() {
<<<<<<< HEAD
        getInventory().createItemMap("catering.csv");
        sortedInventoryDisplay();
=======
        System.out.println("Our currenty inventory");

        super.sortedInventoryDisplay();

//        System.out.println( "What would you like to do?" );
//        System.out.println( "Return to Main Menu ( D )" );
//        System.out.println( "Purchase Menu ( P )" );
//        System.out.println( "Exit ( E )" );
//        String menuChoice = getScan().nextLine();
>>>>>>> ede930527687c030f1cbecb7109bcb99ef5dea35

        System.out.println("Please select from the following options:");
        System.out.println("Feed Money ( M )");
        System.out.println("Select Item ( S )");
        System.out.println("Finish Transaction ( F )");
        System.out.println("Current balance: $" + balance.getBalance());
        String menuChoice = getScan().nextLine().toUpperCase();
        switch (menuChoice) {
            case "M":
                Balance balance = new Balance();
                balance.feedMoney(getScan());
                break;
            case "S":
                PurchaseMenu purchaseMenu = new PurchaseMenu();
                purchaseMenu.showMenu();
                break;
            case "F":
                exit();
                break;
            default:
                System.out.println("Invalid selection, please try again.");
        }
    }
}



