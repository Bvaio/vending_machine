package com.techelevator.inventory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemTest {
    Inventory inventory;
    Item item;
    String testFilePath = "src/test/java/com/techelevator/testFiles/validTestFile.csv";

    @Before
    public void setUp() throws Exception {
        inventory = new Inventory();
        inventory.createItemMap( testFilePath );
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void items_all_stocked_7_each_on_creation() {
        Inventory testStartUpStock = new Inventory();
        testStartUpStock.createItemMap( testFilePath );

        int testMaxStock = 7;

        for ( String key : testStartUpStock.getItemMap().keySet() ) {
            assertEquals( testMaxStock, testStartUpStock.getItemMap().get( key ).getInventoryCount() );
        }
    }

    @Test
    public void item_removes_one_if_valid() {
        String validFileSlotToTest = "A1";
        int testStock = 6;

        inventory.getItemMap().get( validFileSlotToTest ).removeOneFromInventory();

        assertEquals( testStock, inventory.getItemMap().get( validFileSlotToTest ).getInventoryCount() );
    }

    @Test
    public void item_removes_none_if_invalid_input() {
        String invalidFileSlotToTest = "Z1";
        int testStock = 0;

        try {
            inventory.getItemMap().get(invalidFileSlotToTest).removeOneFromInventory();
        } catch ( NullPointerException invalidFile ) {
            testStock = 7;
        }

        for ( String key : inventory.getItemMap().keySet() ) {
            assertEquals( testStock, inventory.getItemMap().get( key ).getInventoryCount() );
        }
    }
}
