package com.techelevator.inventory;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class InventoryTest {
    Inventory inventory = new Inventory();
    String filePath = null;
    Item item = null;
    String testDirectory = "src/test/java/com/techelevator/testFiles/";
    String testPath = null;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void inventory_check_slot_number_exists() {
        filePath = "test.csv";
        String[] expectedKeys = new String[] {
                "A1", "A2", "A3", "A4",
                "B1", "B2", "B3", "B4",
                "C1", "C2", "C3", "C4",
                "D1", "D2", "D3", "D4"
        };

        inventory.createItemMap( filePath );

        for ( String expectedKey : expectedKeys ) {
            assertTrue( expectedKey + " not found in created map", inventory.getItemMap().containsKey( expectedKey ) );
        }
    }

    @Test
    public void inventory_throws_and_catches_FileNotFoundException_if_path_not_valid() {
        filePath = "doesNotExists.csv";

        inventory.createItemMap( filePath );

        assertTrue( "Invalid File Path should not return true", inventory.invalidFilePathMapFailure() );
    }

    @Test
    public void inventory_throws_and_catches_file_lines_less_than_four() {
        testPath = "invalidFile_LessThanFourSplit.csv";
        filePath = testDirectory + testPath;

        inventory.createItemMap( filePath );

        assertTrue( "Invalid File inserted, csv file should only have four items", inventory.invalidFilePathMapFailure() );
    }

    @Test
    public void inventory_throws_and_catches_file_too_much_items() {
        testPath = "invalidFile_TooMuchItemData.csv";
        filePath = testDirectory + testPath;

        inventory.createItemMap( filePath );

        assertTrue( "Invalid File inserted, csv file should only have four items", inventory.invalidFilePathMapFailure() );
    }

    @Test
    public void inventory_throws_and_catches_file_with_no_prices() {
        testPath = "invalidFile_NoPrices.csv";
        filePath = testDirectory + testPath;

        inventory.createItemMap( filePath );

        assertTrue( "Invalid File inserted, no prices", inventory.invalidFilePathMapFailure() );
    }

}
