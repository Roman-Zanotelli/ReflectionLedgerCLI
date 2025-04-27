package com.pluralsight.cli.menu;

import com.pluralsight.cli.annotation.*;
import com.pluralsight.cli.annotation.display.StringFormatter;
import com.pluralsight.cli.annotation.display.menu.MenuHeader;
import com.pluralsight.cli.annotation.display.menu.MenuSelector;
import com.pluralsight.cli.annotation.display.option.MenuOption;
import com.pluralsight.cli.annotation.display.option.PrintResult;
import com.pluralsight.cli.annotation.display.option.WhiteSpaceResult;
import com.pluralsight.cli.annotation.prompt.PromptString;

@Menu("reports_menu")
@MenuHeader("Reports Menu")
@StringFormatter("reports_menu_header_format")
@MenuSelector("Enter Selection >")
public final class ReportsMenu {

//    @StringFormatter("reports_menu_header_format")
//    public static final String header_format = "%s";
//
//    @StringFormatter("reports_menu_option_format")
//    public static final String option_format = "\n\t%s - %s";

    @MenuOption(order = 0, key = "1", description = "Month To Date", formatter = "reports_menu_option_format")
    @RunLogic
    @PrintResult
    @WhiteSpaceResult(1)
    static String monthToDate(){
        return "Result";
    }

    @MenuOption(order = 1, key = "2", description = "Previous Month", formatter = "reports_menu_option_format")
    @RunLogic
    @PrintResult
    @WhiteSpaceResult(1)
    static String previousMonth(){
        return "Result";
    }

    @MenuOption(order = 2, key = "3", description = "Year To Date", formatter = "reports_menu_option_format")
    @RunLogic
    @PrintResult
    @WhiteSpaceResult(1)
    static String yearToDate(){
        return "Result";
    }

    @MenuOption(order = 3, key = "4", description = "Previous Year", formatter = "reports_menu_option_format")
    @RunLogic
    @PrintResult
    @WhiteSpaceResult(1)
    static String previousYear() {
        return "Result";
    }

    @MenuOption(order = 4, key = "5", description = "Search By Vendor", formatter = "reports_menu_option_format")
    @RunLogic
    @PrintResult
    @WhiteSpaceResult(1)
    static String searchByVendor(@PromptString("Enter Vendor Name: ") String vendor_name){
        return "Result";
    }

    @MenuOption(order = 5, key = "0", description = "Return To Ledger", formatter = "reports_menu_option_format")
    @NextMenu("ledger_menu")
    @WhiteSpaceResult(1)
    static void backToLedger(){}

}
