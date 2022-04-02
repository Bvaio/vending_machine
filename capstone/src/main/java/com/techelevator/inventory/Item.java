package com.techelevator.inventory;

import java.math.BigDecimal;

public class Item {
    private String slotIdentifier;
    private String itemName;
    private String itemType;
    private BigDecimal itemPrice;
    private int inventoryCount = 7;

    public Item(String slotIdentifier, String itemName, String itemType, BigDecimal itemPrice) {
        this.slotIdentifier = slotIdentifier;
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemPrice = itemPrice;
    }

    public String getSlotIdentifier() {
        return slotIdentifier;
    }
    public String getItemName() { // make logger to report item is out of stock
        return itemName;
    }
    public String getItemType() {
        return itemType;
    }
    public BigDecimal getItemPrice() {
        return inventoryCount > 0 ? itemPrice : BigDecimal.valueOf( 0 );
    }
    public int getInventoryCount() {
        return inventoryCount;
    }
    public String displayItem() {
        return itemName + " | " + itemPrice + " | Current Stock: " + getInventoryCount();
    }
    public int removeOneFromInventory(){
        this.inventoryCount = inventoryCount > 0 ? inventoryCount - 1 : 0;
        if ( inventoryCount == 0 ) {
            itemName = itemName + " : OUT OF STOCK";
        }
        return this.inventoryCount;
    }
}
