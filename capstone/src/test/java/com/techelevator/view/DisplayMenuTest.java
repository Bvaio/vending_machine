package com.techelevator.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DisplayMenuTest {
    Menu menu;
    DisplayMenu displayMenu;

    @Before
    public void setUp() throws Exception {
        menu = new Menu();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void has_access_to_inventory_slot_keys() {
        displayMenu = new DisplayMenu();
        menu.getInventory().createItemMap( "test.csv" );

        String[] expectedKeys = new String[] {
                "A1", "A2", "A3", "A4",
                "B1", "B2", "B3", "B4",
                "C1", "C2", "C3", "C4",
                "D1", "D2", "D3", "D4"
        };

        for ( String key : expectedKeys ) {
            assertTrue( displayMenu.getInventory().getItemMap().containsKey( key ) );
        }
    }

    @Test
    public void has_access_to_inventory_item_names() {
        displayMenu = new DisplayMenu();

        String[] expectedKeys = new String[] {
                "A1", "A2", "A3", "A4",
                "B1", "B2", "B3", "B4",
                "C1", "C2", "C3", "C4",
                "D1", "D2", "D3", "D4"
        };

        for ( String key : expectedKeys ) {
            assertTrue( displayMenu.getInventory().getItemMap().containsKey( key ) );
        }
    }

}
