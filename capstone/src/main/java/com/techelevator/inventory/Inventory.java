package com.techelevator.inventory;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Inventory {
    private Map<String, Item> itemMap = new HashMap<>();
    private boolean invalidFile;

    public void createItemMap(String filePath) {
        boolean isValidFileType = filePath.substring( filePath.length() - 4 ).equals( ".csv" );

        invalidFile = false;

        if ( isValidFileType ) {
            File readFile = new File(filePath);
            try (Scanner scanReadFile = new Scanner(readFile)) {
                while (scanReadFile.hasNextLine()) {
                    String readLine = scanReadFile.nextLine();
                    String[] readLineToArray = readLine.split(",");

                    if (readLineToArray.length > 4) {
                        invalidFileError();
                        break;
                    }

                    try {
                        Item makeItem = new Item(
                                readLineToArray[0],
                                readLineToArray[1],
                                BigDecimal.valueOf(Double.parseDouble(readLineToArray[3])),
                                readLineToArray[2]
                        );
                        itemMap.put( makeItem.getSlotIdentifier(), makeItem );
                    } catch ( ArrayIndexOutOfBoundsException outOfBoundsException ) {
                        invalidFileError();
                        break;
                    } catch ( NumberFormatException numberFormatException ) {
                        invalidFileError();
                        break;
                    }

                }
            } catch (FileNotFoundException e) {
                invalidFileError();
            }
        } else {
            invalidFileError();
        }
    }

    private void invalidFileError() {
        String invalidFileError = "System cannot collect items at this time";

        invalidFile = true;
        System.out.println( invalidFileError );
    }

    public boolean isInvalidFile() {
        return invalidFile;
    }

    public Map<String, Item> getItemMap() {
        return itemMap;
    }
}
