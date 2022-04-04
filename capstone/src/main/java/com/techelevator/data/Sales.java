package com.techelevator.data;

import com.techelevator.inventory.Item;
import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Sales extends Logger{
    private File salesFile;

    public void generateSalesLog() {
        Map< String, Item> menuInventoryMap = getMenu().getInventory().getItemMap();
        this.salesFile = new File(generateFileName());
        try (PrintWriter salesWriter = new PrintWriter(salesFile)) {
            BigDecimal grossSalesFromItem = BigDecimal.valueOf(0);
            int counter = 0;
            for (String item : menuInventoryMap.keySet()) {
                int numberSold = 7 - menuInventoryMap.get(item).getInventoryCount();
                if (menuInventoryMap.get(item).getInventoryCount() < 7) {
                    salesWriter.println(menuInventoryMap.get(item).getItemName() + "," + numberSold);
                    grossSalesFromItem = grossSalesFromItem.add(
                            BigDecimal.valueOf( 7 - menuInventoryMap.get(item).getInventoryCount() ).multiply( menuInventoryMap.get(item).getItemPrice() ));
                }
                counter++;
                if (counter == menuInventoryMap.size()) {
                    salesWriter.println("TOTAL SALES: $" + grossSalesFromItem);
                }
                }
            } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String generateFileName(){
        String pathToReport = "C:\\Users\\Student\\workspace\\module-1-capstone-team-1\\capstone\\receipts\\";
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm:ss a");
        LocalDateTime dateAndTime = LocalDateTime.now().withNano(0);
        formatterDate.format(dateAndTime);
        String date = dateAndTime.toString().replaceAll(":","-");
        date = date.replaceAll("T","_");
        return pathToReport + date + "_sales-report.txt";
    }
}
