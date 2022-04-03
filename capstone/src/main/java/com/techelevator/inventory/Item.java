package com.techelevator.inventory;

import java.math.BigDecimal;

public class Item {
    private String slotIdentifier;
    private String itemName;
    private String itemType;
    private BigDecimal itemPrice;
    private int inventoryCount = 7;

    public Item(String slotIdentifier, String itemName, BigDecimal itemPrice,  String itemType ) {
        this.slotIdentifier = slotIdentifier;
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemPrice = itemPrice;
    }

    public int removeOneFromInventory(){
        this.inventoryCount = inventoryCount > 0 ? inventoryCount - 1 : 0;
//        if ( inventoryCount == 0 ) {
//            itemName = itemName + " : OUT OF STOCK";
//        }
        return this.inventoryCount;
    }

    public String displayItem() {
        return itemName + " | " + itemPrice + " | Current Stock: " + getInventoryCount();
    }

    public String getSlotIdentifier() {
        return slotIdentifier;
    }
    public String getItemName() {
        return itemName;
    }
    public String getItemType() {
        return itemType;
    }
    public BigDecimal getItemPrice() {
        return itemPrice;
    }
    public int getInventoryCount() {
        return inventoryCount;
    }

}
