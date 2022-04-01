package com.techelevator.data;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private File logFile;
    private PrintWriter writer;

    public Logger() {
        this.logFile = new File("Audit.txt");
        if (!logFile.exists() && !logFile.isFile()) {
            try {
                this.writer = new PrintWriter(this.logFile);
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try {
                this.writer = new PrintWriter(new FileWriter(this.logFile, true));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void write(String logMessage) {
        this.writer.println(logMessage);
        this.writer.flush();
    }

    public void close(){
        this.writer.close();
    }

    public void convertDateTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/DD/yyyy hh:mm:ss a");
        LocalDateTime dt = LocalDateTime.now();
    }

    public void printFormat(){

    }

    //examples of what to log
//        TEMPLATE
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
//        LocalDateTime dt = LocalDateTime.now();
//        System.out.format(formatter.format(dt) + " MONEY FED:");
//        System.out.format("%12s","$" + trial);
//        System.out.format("%8s","$" + trialTwo);

}
