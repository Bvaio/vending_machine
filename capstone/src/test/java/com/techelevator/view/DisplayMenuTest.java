package com.techelevator.view;

import com.techelevator.inventory.Item;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.Assert.*;

public class DisplayMenuTest {
    private Menu menu;
    private DisplayMenu displayMenu;
    private String filePath = "src/test/java/com/techelevator/testFiles/validTestFile.csv";
    private Map< String, Item > displayItemMap;
    private String[] expectedKeys;

    @Before
    public void setUp() throws Exception {
        menu = new Menu();
        menu.getInventory().createItemMap( filePath );

        displayMenu = new DisplayMenu();
        displayItemMap = displayMenu.getInventory().getItemMap();

        expectedKeys = new String[] {
                "A1", "A2", "A3", "A4",
                "B1", "B2", "B3", "B4",
                "C1", "C2", "C3", "C4",
                "D1", "D2", "D3", "D4",
                "E1", "E2", "E3", "E4",
                "F1", "F2", "F3", "F4",
                "G1", "G2", "G3", "G4"
        };
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void displayMenu_has_access_to_inventory_slot_keys() {

        for ( String key : expectedKeys ) {
            assertTrue( displayItemMap.containsKey( key ) );
        }

    }

    @Test
    public void displayMenu_has_access_to_inventory_item_names() {
        String A1Name = "Nachos";
        String B1Name = "Chippos";
        String C1Name = "Popcorn";
        String D1Name = "Preengles";

        assertEquals( A1Name, displayItemMap.get( "A1" ).getItemName() );
        assertEquals( B1Name, displayItemMap.get( "B1" ).getItemName() );
        assertEquals( C1Name, displayItemMap.get( "C1" ).getItemName() );
        assertEquals( D1Name, displayItemMap.get( "D1" ).getItemName() );
    }

    @Test
    public void displayMenu_has_access_to_inventory_item_types() {
        String A1Type = "Munchy";
        String A2Type = "Sandwich";
        String A3Type = "Drink";
        String A4Type = "Dessert";

        assertEquals( A1Type, displayItemMap.get( "A1" ).getItemType() );
        assertEquals( A2Type, displayItemMap.get( "A2" ).getItemType() );
        assertEquals( A3Type, displayItemMap.get( "A3" ).getItemType() );
        assertEquals( A4Type, displayItemMap.get( "A4" ).getItemType() );
    }

    @Test
    public void displayMenu_has_access_to_inventory_item_prices() {
        BigDecimal A1Prices = BigDecimal.valueOf( 3.85 );
        BigDecimal A2Prices = BigDecimal.valueOf( 5.25 );
        BigDecimal A3Prices = BigDecimal.valueOf( 2.35 );
        BigDecimal A4Prices = BigDecimal.valueOf( 1.75 );

        assertEquals( A1Prices, displayItemMap.get( "A1" ).getItemPrice() );
        assertEquals( A2Prices, displayItemMap.get( "A2" ).getItemPrice() );
        assertEquals( A3Prices, displayItemMap.get( "A3" ).getItemPrice() );
        assertEquals( A4Prices, displayItemMap.get( "A4" ).getItemPrice() );
    }

}
