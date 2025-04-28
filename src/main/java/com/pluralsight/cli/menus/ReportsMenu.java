package com.pluralsight.cli.menus;

import com.pluralsight.cli.annotations.*;
import com.pluralsight.cli.annotations.display.WhiteSpaceBefore;
import com.pluralsight.cli.annotations.display.menu.MenuHeader;
import com.pluralsight.cli.annotations.display.option.MenuOption;
import com.pluralsight.cli.annotations.display.option.PrintResult;
import com.pluralsight.cli.annotations.display.WhiteSpaceAfter;
import com.pluralsight.cli.annotations.prompt.PromptDate;
import com.pluralsight.cli.annotations.prompt.PromptValue;
import com.pluralsight.util.Ledger;

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
        return Ledger.monthToDate();
    }


    //PREVIOUS MONTH OPTION
    @RunLogic @PrintResult @WhiteSpaceAfter
    @MenuOption(order = 1, key = "2", description = "Previous Month")
    public static String previousMonth(){
        //PREVIOUS MONTH LOGIC
        return Ledger.previousMonth();
    }


    //YEAR TO DATE OPTION
    @RunLogic @PrintResult @WhiteSpaceAfter
    @MenuOption(order = 2, key = "3", description = "Year To Date")
    public static String yearToDate(){
        //YEAR TO DATE LOGIC HERE
        return Ledger.yearToDate();
    }


    //PREVIOUS YEAR OPTION
    @RunLogic @WhiteSpaceBefore(2) @PrintResult
    @MenuOption(order = 3, key = "4", description = "Previous Year")
    public static String previousYear() {
        //PREVIOUS YEAR LOGIC HERE
        return Ledger.previousYear();
    }


    //SEARCH BY VENDOR OPTION
    @RunLogic @WhiteSpaceBefore(2) @PrintResult
    @MenuOption(order = 4, key = "5", description = "Search By Vendor")
    public static String searchByVendor(@PromptValue(prompt = "Enter Vendor: ") String vendorName){
        //SEARCH BY VENDOR LOGIC HERE
        return Ledger.searchByVendor(vendorName);
    }
    //SEARCH BY ALL OPTION
    @RunLogic @WhiteSpaceBefore(2) @PrintResult
    @MenuOption(order = 5, key = "6", description = "Search")
    public static String searchByAll(@PromptDate(prompt = "Enter Start Date: ") Date startDate, @PromptDate(prompt = "Enter End Date: ") Date endDate, @PromptValue(prompt = "Enter Description: ") String desc, @PromptValue(prompt = "Enter Vendor: ") String vendorName, @PromptValue(prompt = "Enter Min Price: ", targetClass = Float.class, parserMethod = "parseFloat") Float minPrice, @PromptValue(prompt = "Enter Max Price: ", targetClass = Float.class, parserMethod = "parseFloat") Float maxPrice){
        //SEARCH BY ALL LOGIC HERE
        return Ledger.searchByAll(startDate, endDate, desc, vendorName, minPrice, maxPrice);
    }


    //RETURN TO LEDGER OPTION
    @WhiteSpaceBefore(2) @NextMenu("ledger_menu")
    @MenuOption(order = 6, key = "0", description = "Return To Ledger")
    public static void backToLedger(){
        //DOES NOTHING
    }

}
