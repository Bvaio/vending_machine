package com.techelevator.view;

import com.techelevator.inventory.Item;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class MenuTest {
    private Menu menu = new Menu();
    private final String filePath = "src/test/java/com/techelevator/testFiles/validTestFile.csv";
    private final String testDirectoryPath = "src/test/java/com/techelevator/testFiles/";
    private Map< String, Item> displayItemMap;
    private String[] expectedKeys;

    @Before
    public void setUp() throws Exception {
        menu.getInventory().createItemMap( filePath );

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

    @Test
    public void inventory_populates_map_from_valid_input() {
        displayItemMap = menu.getInventory().getItemMap();
        int countKeys = 0;
        final int expectedKeyCountTotal = 28;

        for ( String key : expectedKeys ) {
            if ( displayItemMap.containsKey( key ) ) {
                countKeys += 1;
            }
        }

        assertEquals( expectedKeyCountTotal, countKeys );
    }

    @Test
    public void menu_accepts_valid_csv_file_test_one() {
        final String testFilePath = "catering.csv";
        menu.getInventory().createItemMap( testFilePath );

        assertFalse(menu.getInventory().isInvalidFile());
    }

    @Test
    public void menu_accepts_valid_csv_file_test_two() {
        final String testFilePath = filePath;

        menu.getInventory().createItemMap( testFilePath );

        assertFalse( menu.getInventory().isInvalidFile() );
    }

    @Test
    public void menu_rejects_non_csv_file_txt() {
        final String testFilePath = "Audit.txt";
        menu.getInventory().createItemMap( testFilePath );

        assertTrue( menu.getInventory().isInvalidFile() );
    }

    @Test
    public void menu_rejects_non_csv_file_md() {
        final String testFilePath = "README.md";
        menu.getInventory().createItemMap( testFilePath );

        assertTrue( menu.getInventory().isInvalidFile() );
    }

    @Test
    public void menu_rejects_non_csv_file_xml() {
        final String testFilePath = "pom.xml";
        menu.getInventory().createItemMap( testFilePath );

        assertTrue( menu.getInventory().isInvalidFile() );
    }

    @Test
    public void menu_rejects_invalid_csv_file_does_not_contain_prices() {
        final String invalidFilePathName = "invalidFile_NoPrices.csv";
        final String invalidFilePath = testDirectoryPath + invalidFilePathName;
        menu.getInventory().createItemMap( invalidFilePath  );

        assertTrue( menu.getInventory().isInvalidFile() );
    }

    @Test
    public void menu_rejects_invalid_csv_file_lines_dont_contain_enough_information() {
        final String invalidFilePathName = "invalidFile_LessThanFour.csv";
        final String invalidFilePath = testDirectoryPath + invalidFilePathName;
        menu.getInventory().createItemMap( invalidFilePath  );

        assertTrue( menu.getInventory().isInvalidFile() );
    }

    @Test
    public void menu_rejects_invalid_csv_file_too_much_data_per_line() {
        final String invalidFilePathName = "invalidFile_TooMuchItemData.csv";
        final String invalidFilePath = testDirectoryPath + invalidFilePathName;
        menu.getInventory().createItemMap( invalidFilePath  );

        assertTrue( menu.getInventory().isInvalidFile() );
    }
}
