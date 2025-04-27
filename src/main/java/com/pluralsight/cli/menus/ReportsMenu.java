package com.pluralsight.cli.menus;

import com.pluralsight.cli.annotations.*;
import com.pluralsight.cli.annotations.display.menu.MenuHeader;
import com.pluralsight.cli.annotations.display.option.MenuOption;
import com.pluralsight.cli.annotations.display.option.PrintResult;
import com.pluralsight.cli.annotations.display.option.WhiteSpaceAfterResult;
import com.pluralsight.cli.annotations.prompt.PromptString;

//....................
//REPORTS MENU
//....................
@Menu("reports_menu")
@MenuHeader("Reports Menu")
public final class ReportsMenu {


    //MONTH TO DATE OPTION
    @RunLogic @PrintResult @WhiteSpaceAfterResult(1)
    @MenuOption(order = 0, key = "1", description = "Month To Date", formatter = "reports_menu_option_format")
    public static String monthToDate(){
        //MONTH TO DATE LOGIC HERE
        return "Result";
    }


    //PREVIOUS MONTH OPTION
    @RunLogic @PrintResult @WhiteSpaceAfterResult(1)
    @MenuOption(order = 1, key = "2", description = "Previous Month", formatter = "reports_menu_option_format")
    public static String previousMonth(){
        //PREVIOUS MONTH LOGIC
        return "Result";
    }


    //YEAR TO DATE OPTION
    @RunLogic @PrintResult @WhiteSpaceAfterResult(1)
    @MenuOption(order = 2, key = "3", description = "Year To Date", formatter = "reports_menu_option_format")
    public static String yearToDate(){
        //YEAR TO DATE LOGIC HERE
        return "Result";
    }


    //PREVIOUS YEAR OPTION
    @RunLogic @PrintResult @WhiteSpaceAfterResult(1)
    @MenuOption(order = 3, key = "4", description = "Previous Year", formatter = "reports_menu_option_format")
    public static String previousYear() {
        //PREVIOUS YEAR LOGIC HERE
        return "Result";
    }


    //SEARCH BY VENDOR OPTION
    @RunLogic @PrintResult @WhiteSpaceAfterResult(1)
    @MenuOption(order = 4, key = "5", description = "Search By Vendor", formatter = "reports_menu_option_format")
    public static String searchByVendor(@PromptString("Enter Vendor Name: ") String vendor_name){
        //SEARCH BY VENDOR LOGIC HERE
        return "Result";
    }


    //RETURN TO LEDGER OPTION
    @NextMenu("ledger_menu") @WhiteSpaceAfterResult(1)
    @MenuOption(order = 5, key = "0", description = "Return To Ledger", formatter = "reports_menu_option_format")
    public static void backToLedger(){
        //DOES NOTHING
    }


}
