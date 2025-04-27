package com.pluralsight.cli.menus;

import com.pluralsight.cli.annotations.*;
import com.pluralsight.cli.annotations.display.menu.MenuHeader;
import com.pluralsight.cli.annotations.display.option.MenuOption;
import com.pluralsight.cli.annotations.display.option.PrintResult;
import com.pluralsight.cli.annotations.display.WhiteSpaceAfter;
import com.pluralsight.cli.annotations.prompt.PromptDate;
import com.pluralsight.cli.annotations.prompt.PromptType;
import com.pluralsight.cli.annotations.prompt.PromptValue;

import java.util.Date;

//....................
//REPORTS MENU
//....................
@Menu("reports_menu")
@MenuHeader("Reports Menu")
public final class ReportsMenu {


    //MONTH TO DATE OPTION
    @RunLogic @PrintResult @WhiteSpaceAfter
    @MenuOption(order = 0, key = "1", description = "Month To Date")
    public static String monthToDate(){
        //MONTH TO DATE LOGIC HERE
        return "Result";
    }


    //PREVIOUS MONTH OPTION
    @RunLogic @PrintResult @WhiteSpaceAfter
    @MenuOption(order = 1, key = "2", description = "Previous Month")
    public static String previousMonth(){
        //PREVIOUS MONTH LOGIC
        return "Result";
    }


    //YEAR TO DATE OPTION
    @RunLogic @PrintResult @WhiteSpaceAfter
    @MenuOption(order = 2, key = "3", description = "Year To Date")
    public static String yearToDate(){
        //YEAR TO DATE LOGIC HERE
        return "Result";
    }


    //PREVIOUS YEAR OPTION
    @RunLogic @PrintResult @WhiteSpaceAfter
    @MenuOption(order = 3, key = "4", description = "Previous Year")
    public static String previousYear() {
        //PREVIOUS YEAR LOGIC HERE
        return "Result";
    }


    //SEARCH BY VENDOR OPTION
    @RunLogic @PrintResult @WhiteSpaceAfter
    @MenuOption(order = 4, key = "5", description = "Search By Vendor")
    public static String searchByVendor(@PromptValue(prompt = "Enter Vendor: ") String vendorName){
        //SEARCH BY VENDOR LOGIC HERE
        return "Result";
    }
    //SEARCH BY VENDOR OPTION
    @RunLogic @PrintResult @WhiteSpaceAfter
    @MenuOption(order = 5, key = "6", description = "Search")
    public static String searchByAll(@PromptDate(prompt = "Enter Start Date: ") Date startDate, @PromptDate(prompt = "Enter End Date: ") Date endDate, @PromptValue(prompt = "Enter Description: ") String desc, @PromptValue(prompt = "Enter Vendor: ") String vendorName, @PromptValue(type = PromptType.FLOAT, prompt = "Enter Min Price: ") Float minPrice, @PromptValue(type = PromptType.FLOAT, prompt = "Enter Min Price: ") Float maxPrice){
        //SEARCH BY VENDOR LOGIC HERE
        return "Result";
    }


    //RETURN TO LEDGER OPTION
    @NextMenu("ledger_menu") @WhiteSpaceAfter
    @MenuOption(order = 6, key = "0", description = "Return To Ledger")
    public static void backToLedger(){
        //DOES NOTHING
    }


}
