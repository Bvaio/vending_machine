package com.techelevator.inventory;

import java.math.BigDecimal;

public class Item {
    private String slotIdentifier;
    private String itemName;
    private String itemType;
    private BigDecimal itemPrice;
//    private int inventoryCount = 7;

    public Item(String slotIdentifier, String itemName, String itemType, BigDecimal itemPrice, int inventoryCount) {
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
        Inventory inventory = new Inventory();
        return inventory.getInventoryCount() > 0 ? itemPrice : BigDecimal.valueOf( 0 );
    }
    public void getInventoryCount(Inventory inventory) {

    }
//    public BigDecimal getItemPrice() {
//        return BigDecimal.valueOf(0);
//    }
    public String displayItem() {
        Inventory inventory = new Inventory();
        return itemName + " | " + itemPrice + " | Current Stock: " + inventory.getInventoryCount();
    }
//    public void removeOneFromInventory(){
//        inventoryCount = inventoryCount > 0 ? inventoryCount - 1 : 0;
//    }
}
