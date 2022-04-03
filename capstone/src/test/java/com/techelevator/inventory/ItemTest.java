package com.techelevator.inventory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class ItemTest {
    private Inventory inventory;
    private Item item;
    private String testFilePath = "src/test/java/com/techelevator/testFiles/validTestFile.csv";
    private Map< String, Item > referenceMap;

    @Before
    public void setUp() throws Exception {
        inventory = new Inventory();
        inventory.createItemMap( testFilePath );

        referenceMap = inventory.getItemMap();
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
    public void item_removes_one_if_valid_slot_number() {
        String validFileSlotToTest = "A1";
        int testStock = 6;

        inventory.getItemMap().get( validFileSlotToTest ).removeOneFromInventory();

        assertEquals( testStock, referenceMap.get( validFileSlotToTest ).getInventoryCount() );
    }

    @Test
    public void item_removes_none_if_invalid_slot_number() {
        String invalidFileSlotToTest = "Z1";
        int testStock = 0;

        try {
            referenceMap.get(invalidFileSlotToTest).removeOneFromInventory();
        } catch ( NullPointerException invalidFile ) {
            testStock = 7;
        }

        for ( String key : referenceMap.keySet() ) {
            assertEquals( testStock, referenceMap.get( key ).getInventoryCount() );
        }
    }

    @Test
    public void item_should_not_contain_out_of_stock_in_name_if_inventory_not_zero() {
        String validFileSlotToTest = "A1";
        String outOfStockReference = " : OUT OF STOCK";

        while ( referenceMap.get( validFileSlotToTest ).getInventoryCount() > 1 ) {
            referenceMap.get( validFileSlotToTest ).removeOneFromInventory();
        }

        int currentInventoryCount =  referenceMap.get( validFileSlotToTest ).getInventoryCount();

        assertFalse( "Item contains " + currentInventoryCount + " should NOT have \"OUT OF STOCK\" in name",
                referenceMap.get( validFileSlotToTest ).getItemName().contains( outOfStockReference ) );
    }

    @Test
    public void item_inventory_zero_stays_at_zero_if_try_to_remove_more() {
        String validFileSlotToTest = "A1";
        int outOfStockReference = 0;

        for ( int each = 0 ; each < 20 ; each++ ) {
            referenceMap.get( validFileSlotToTest ).removeOneFromInventory();
        }

        int currentInventoryCount =  referenceMap.get( validFileSlotToTest ).getInventoryCount();

        assertEquals( "Item contains " + currentInventoryCount + " should have not go below 0 when asked to remove beyond 0",
                currentInventoryCount, referenceMap.get( validFileSlotToTest ).getInventoryCount() );
    }


    @Test
    public void item_display_item_should_display_item_name_item_price_item_stock() {
        String slotNumber = "A2";
        String itemName = "Walking Tacos";
        String itemPrice = "5.25";
        String itemStock = "7";

        String[] itemDisplay = referenceMap.get( slotNumber ).displayItem().split( " \\| " );

        assertEquals( itemName, itemDisplay[ 0 ] );
        assertEquals( itemPrice, itemDisplay[ 1 ] );
        assertEquals( itemStock, String.valueOf( itemDisplay[ 2 ].charAt( itemDisplay[ 2 ].length() - 1 ) ) );
    }

    @Test
    public void item_display_item_should_display_an_updated_display_if_stock_changes() {
        String slotNumber = "A2";
        String itemName = "Walking Tacos";
        String itemPrice = "5.25";
        String itemStock = "5";

        referenceMap.get( slotNumber ).removeOneFromInventory();
        referenceMap.get( slotNumber ).removeOneFromInventory();

        String[] itemDisplay = referenceMap.get( slotNumber ).displayItem().split( " \\| " );

        assertEquals( itemName, itemDisplay[ 0 ] );
        assertEquals( itemPrice, itemDisplay[ 1 ] );
        assertEquals( itemStock, String.valueOf( itemDisplay[ 2 ].charAt( itemDisplay[ 2 ].length() - 1 ) ) );
    }
}
