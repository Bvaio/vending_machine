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
    public void setInventoryCount(int inventoryCount) {
        this.inventoryCount = inventoryCount;
    }
}
