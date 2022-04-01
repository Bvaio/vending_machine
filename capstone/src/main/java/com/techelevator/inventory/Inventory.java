package com.techelevator.inventory;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Inventory {
    private Map<String, Item> itemMap = new HashMap<>();
    private int inventoryCount = 7;
    public void createItemMap(String filePath) {
        File readFile = new File(filePath);
        try(Scanner scanReadFile = new Scanner(readFile)) {
            while(scanReadFile.hasNextLine()) {
                String readLine = scanReadFile.nextLine();
                String[] readLineToArray = readLine.split(",");
                Item makeItem = new Item(readLineToArray[0],readLineToArray[1],readLineToArray[2],BigDecimal.valueOf(Double.parseDouble(readLineToArray[3])), inventoryCount);
                itemMap.put(makeItem.getSlotIdentifier(), makeItem);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Sorry, file does not exist");
        }
    }


    public int getInventoryCount() {
        return inventoryCount;
    }
    //    public void setInventoryCount(int inventoryCount) {
//        this.inventoryCount = inventoryCount;
//    }
    public void removeOneFromInventory(){
        inventoryCount = inventoryCount > 0 ? inventoryCount - 1 : 0;
    }
    public Map<String, Item> getItemMap() {
        return itemMap;
    }
}
