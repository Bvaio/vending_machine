package com.techelevator.data;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Sales extends Logger{
    private PrintWriter salesWriter;
    private File salesFile;

    public Sales() {
        this.salesFile = new File(generateFileName());
        if (!salesFile.exists() && !salesFile.isFile()) {
            try {
                this.salesWriter= new PrintWriter(this.salesFile);
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try {
                this.salesWriter = new PrintWriter(new FileOutputStream(this.salesFile, true));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void generateSalesLog(){
        BigDecimal grossSalesFromItem = BigDecimal.valueOf(0);
        int counter = 0;
        for (String item : menu.getInventory().getItemMap().keySet()) {
            int numberSold = 7 - menu.getInventory().getItemMap().get(item).getInventoryCount();
            if (menu.getInventory().getItemMap().get(item).getInventoryCount() < 7) {
                this.salesWriter.println(menu.getInventory().getItemMap().get(item).getItemName() + "," + numberSold);
                this.salesWriter.flush();
                grossSalesFromItem = grossSalesFromItem.add(BigDecimal.valueOf(7 - menu.getInventory().getItemMap().get(item).getInventoryCount()).multiply(menu.getInventory().getItemMap().get(item).getItemPrice()));
            } counter++;
            if (counter == menu.getInventory().getItemMap().size()) {
                this.salesWriter.println("TOTAL SALES: $" + grossSalesFromItem);
                this.salesWriter.flush();
            }
        } closeSalesWriter();
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
