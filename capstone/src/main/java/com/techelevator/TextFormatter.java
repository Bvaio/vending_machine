package com.techelevator;

import java.util.PropertyResourceBundle;

public class TextFormatter {
    private final String BOLD_FONT = "\033[1m";
    private final String TEXT_GREEN = "\u001B[32m";
    private final String TEXT_BLUE = "\u001B[34m";
    private final String TEXT_RESET_COLOR = "\u001B[0m";
    private final String TEXT_GREEN_AND_BOLD = BOLD_FONT + TEXT_GREEN;
    private final String TEXT_BLUE_AND_BOLD = BOLD_FONT + TEXT_BLUE;
    private final String RESET_FORMATTING = BOLD_FONT + TEXT_RESET_COLOR;
    public String getBOLD_FONT() {
        return BOLD_FONT;
    }

    public String getTEXT_GREEN_AND_BOLD() {
        return TEXT_GREEN_AND_BOLD;
    }

    public String getTEXT_BLUE_AND_BOLD() {
        return TEXT_BLUE_AND_BOLD;
    }

    public String getRESET_FORMATTING() {
        return RESET_FORMATTING;
    }

    public String getTEXT_GREEN() {
        return TEXT_GREEN;
    }

    public String getTEXT_RESET_COLOR() {
        return TEXT_RESET_COLOR;
    }

    public String getTEXT_BLUE(String input){
        return TEXT_BLUE;
    }

    public String getBlueString(String input){
        return TEXT_BLUE_AND_BOLD + input + RESET_FORMATTING;
    }

    public String getGreenString(String input){
        return TEXT_GREEN_AND_BOLD + input + RESET_FORMATTING;
    }

}



