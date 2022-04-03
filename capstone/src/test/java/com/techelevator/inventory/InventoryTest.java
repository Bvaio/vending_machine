package com.techelevator.inventory;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InventoryTest {
    private Inventory inventory = new Inventory();
    private String filePath = null;
    private Item item = null;
    private String testDirectory = "src/test/java/com/techelevator/testFiles/";
    private String testPath = null;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void inventory_check_slot_number_exists() {
        filePath = "catering.csv";
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
    public void inventory_check_slot_number_exists_from_test_file() {
        testPath = "validTestFile.csv";
        filePath = testDirectory + testPath;

        String[] expectedKeys = new String[] {
                "A1", "A2", "A3", "A4",
                "B1", "B2", "B3", "B4",
                "C1", "C2", "C3", "C4",
                "D1", "D2", "D3", "D4",
                "E1", "E2", "E3", "E4",
                "F1", "F2", "F3", "F4",
                "G1", "G2", "G3", "G4"
        };

        inventory.createItemMap( filePath );

        for ( String expectedKey : expectedKeys ) {
            assertTrue( expectedKey + " not found in created map", inventory.getItemMap().containsKey( expectedKey ) );
        }
    }

    @Test
    public void inventory_file_is_valid() {
        testPath = "validTestFile.csv";
        filePath = testDirectory + testPath;

        inventory.createItemMap( filePath );

        assertFalse( filePath + " is a valid File to use", inventory.isInvalidFile() );
    }


    @Test
    public void inventory_throws_and_catches_FileNotFoundException_if_path_not_valid() {
        filePath = "doesNotExists.csv";

        inventory.createItemMap( filePath );

        assertTrue( "Invalid File Path should not return true", inventory.isInvalidFile() );
    }

    @Test
    public void inventory_throws_and_catches_file_lines_less_than_four() {
        testPath = "invalidFile_LessThanFourSplit.csv";
        filePath = testDirectory + testPath;

        inventory.createItemMap( filePath );

        assertTrue( "Invalid File inserted, csv file should only have four items", inventory.isInvalidFile() );
    }

    @Test
    public void inventory_throws_and_catches_file_too_much_items() {
        testPath = "invalidFile_TooMuchItemData.csv";
        filePath = testDirectory + testPath;

        inventory.createItemMap( filePath );

        assertTrue( "Invalid File inserted, csv file should only have four items", inventory.isInvalidFile() );
    }

    @Test
    public void inventory_throws_and_catches_file_with_no_prices() {
        testPath = "invalidFile_NoPrices.csv";
        filePath = testDirectory + testPath;

        inventory.createItemMap( filePath );

        assertTrue( "Invalid File inserted, no prices", inventory.isInvalidFile() );
    }

    @Test
    public void inventory_throws_and_catches_file_contains_too_much_commas() {
        testPath = "invalidFile_OneTooManyCommas.csv";
        filePath = testDirectory + testPath;

        inventory.createItemMap( filePath );

        assertTrue( "Invalid File inserted, should not accept trailing or double inner commas", inventory.isInvalidFile() );
    }



}
