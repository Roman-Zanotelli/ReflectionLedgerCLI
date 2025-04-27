package com.pluralsight.cli.menu;

import com.pluralsight.cli.annotation.*;
import com.pluralsight.cli.annotation.display.StringFormatter;
import com.pluralsight.cli.annotation.display.menu.MenuHeader;
import com.pluralsight.cli.annotation.display.menu.MenuSelector;
import com.pluralsight.cli.annotation.display.option.MenuOption;
import com.pluralsight.cli.annotation.display.option.PrintResult;
import com.pluralsight.cli.annotation.display.option.WhiteSpaceResult;
import com.pluralsight.cli.annotation.prompt.PromptString;

//....................
//REPORTS MENU
//....................
@Menu("reports_menu")
@MenuHeader("Reports Menu") @MenuSelector("Enter Selection >") @StringFormatter("reports_menu_header_format")
public final class ReportsMenu {


    //MONTH TO DATE OPTION
    @RunLogic @PrintResult @WhiteSpaceResult(1)
    @MenuOption(order = 0, key = "1", description = "Month To Date", formatter = "reports_menu_option_format")
    static String monthToDate(){
        //MONTH TO DATE LOGIC HERE
        return "Result";
    }


    //PREVIOUS MONTH OPTION
    @RunLogic @PrintResult @WhiteSpaceResult(1)
    @MenuOption(order = 1, key = "2", description = "Previous Month", formatter = "reports_menu_option_format")
    static String previousMonth(){
        //PREVIOUS MONTH LOGIC
        return "Result";
    }


    //YEAR TO DATE OPTION
    @RunLogic @PrintResult @WhiteSpaceResult(1)
    @MenuOption(order = 2, key = "3", description = "Year To Date", formatter = "reports_menu_option_format")
    static String yearToDate(){
        //YEAR TO DATE LOGIC HERE
        return "Result";
    }


    //PREVIOUS YEAR OPTION
    @RunLogic @PrintResult @WhiteSpaceResult(1)
    @MenuOption(order = 3, key = "4", description = "Previous Year", formatter = "reports_menu_option_format")
    static String previousYear() {
        //PREVIOUS YEAR LOGIC HERE
        return "Result";
    }


    //SEARCH BY VENDOR OPTION
    @RunLogic @PrintResult @WhiteSpaceResult(1)
    @MenuOption(order = 4, key = "5", description = "Search By Vendor", formatter = "reports_menu_option_format")
    static String searchByVendor(@PromptString("Enter Vendor Name: ") String vendor_name){
        //SEARCH BY VENDOR LOGIC HERE
        return "Result";
    }


    //RETURN TO LEDGER OPTION
    @NextMenu("ledger_menu") @WhiteSpaceResult(1)
    @MenuOption(order = 5, key = "0", description = "Return To Ledger", formatter = "reports_menu_option_format")
    static void backToLedger(){
        //DOES NOTHING
    }

}
