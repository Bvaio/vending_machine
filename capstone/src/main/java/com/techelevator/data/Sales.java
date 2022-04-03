package com.techelevator.data;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Sales extends Logger{
    private PrintWriter salesWriter;
    private File salesFile;

//    public Sales() {
//        this.salesFile = new File(generateFileName());
//            try {
//                this.salesWriter = new PrintWriter(new FileOutputStream(this.salesFile, true));
//            } catch (IOException e) {
//                System.out.println(e.getMessage());
//            }
//        }


    public void generateSalesLog() {
//        String filePath = generateFileName();
        this.salesFile = new File(generateFileName());
        try (PrintWriter salesWriter = new PrintWriter(salesFile)) {
            BigDecimal grossSalesFromItem = BigDecimal.valueOf(0);
            int counter = 0;
            for (String item : menu.getInventory().getItemMap().keySet()) {
                int numberSold = 7 - menu.getInventory().getItemMap().get(item).getInventoryCount();
                if (menu.getInventory().getItemMap().get(item).getInventoryCount() < 7) {
                    salesWriter.println(menu.getInventory().getItemMap().get(item).getItemName() + "," + numberSold);
                    salesWriter.flush();
                    grossSalesFromItem = grossSalesFromItem.add(BigDecimal.valueOf(7 - menu.getInventory().getItemMap().get(item).getInventoryCount()).multiply(menu.getInventory().getItemMap().get(item).getItemPrice()));
                }
                counter++;
                if (counter == menu.getInventory().getItemMap().size()) {
                    salesWriter.println("TOTAL SALES: $" + grossSalesFromItem);
                    salesWriter.flush();
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

    public void closeSalesWriter(){
        this.salesWriter.close();
    }
}
