package com.techelevator;

import java.util.PropertyResourceBundle;

public class TextFormatter {
    public final String BOLD_FONT = "\033[1m";
    public final String TEXT_GREEN = "\u001B[32m";
    public final String TEXT_RED_ERROR = "\033[1;31m";
    public final String TEXT_RESET_COLOR = "\u001B[0m";
    public final String TEXT_GREEN_AND_BOLD = "\033[1;32m";
    public final String TEXT_BLUE_AND_BOLD = "\033[1;34m";
    public final String RESET_FORMATTING = BOLD_FONT + TEXT_RESET_COLOR;
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

    public String getBlueString(String input){
        return TEXT_BLUE_AND_BOLD + input + RESET_FORMATTING;
    }

    public String getGreenString(String input){
        return TEXT_GREEN_AND_BOLD + input + RESET_FORMATTING;
    }

    public String getRedString(String input){
        return TEXT_RED_ERROR + input + RESET_FORMATTING;
    }

}



