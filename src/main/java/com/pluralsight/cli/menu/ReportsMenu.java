package com.pluralsight.cli.menu;

import com.pluralsight.cli.annotation.*;
import com.pluralsight.cli.annotation.prompt.PromptString;

@Menu("reports_menu")
@MenuHeader("Reports Menu")
public final class ReportsMenu {

    @MenuOption(order = 0, key = "1", description = "Month To Date")
    @RunLogic
    @PrintResult
    @WhiteSpace(1)
    static String monthToDate(){
        return "Result";
    }

    @MenuOption(order = 1, key = "2", description = "Previous Month")
    @RunLogic
    @PrintResult
    @WhiteSpace(1)
    static String previousMonth(){
        return "Result";
    }

    @MenuOption(order = 2, key = "3", description = "Year To Date")
    @RunLogic
    @PrintResult
    @WhiteSpace(1)
    static String yearToDate(){
        return "Result";
    }

    @MenuOption(order = 3, key = "4", description = "Previous Year")
    @RunLogic
    @PrintResult
    @WhiteSpace(1)
    static String previousYear() {
        return "Result";
    }

    @MenuOption(order = 4, key = "5", description = "Search By Vendor")
    @RunLogic
    @PrintResult
    @WhiteSpace(1)
    static String searchByVendor(@PromptString("Enter Vendor Name: ") String vendor_name){
        return "Result";
    }

    @MenuOption(order = 5, key = "0", description = "Return To Ledger")
    @NextMenu("ledger_menu")
    @WhiteSpace(1)
    static void backToLedger(){}

}
