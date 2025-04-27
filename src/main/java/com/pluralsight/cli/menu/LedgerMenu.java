package com.pluralsight.cli.menu;

import com.pluralsight.cli.annotation.*;
import com.pluralsight.cli.annotation.display.StringFormatter;
import com.pluralsight.cli.annotation.display.menu.MenuHeader;
import com.pluralsight.cli.annotation.display.menu.MenuSelector;
import com.pluralsight.cli.annotation.display.option.MenuOption;
import com.pluralsight.cli.annotation.display.option.PrintResult;
import com.pluralsight.cli.annotation.display.option.WhiteSpaceResult;

@Menu("ledger_menu")
@MenuHeader("Ledger Menu")
@StringFormatter("ledger_menu_header_format")
@MenuSelector("Enter Selection >")
public final class LedgerMenu {

//    @StringFormatter("ledger_menu_header_format")
//    public static final String header_format = "%s";
//
//    @StringFormatter("ledger_menu_option_format")
//    public static final String option_format = "\n\t%s - %s";

    @MenuOption(order = 0, key = "A", description = "Display All Entries", formatter = "ledger_menu_option_format")
    @RunLogic
    @PrintResult
    @WhiteSpaceResult(1)
    public static String getAll(){
        return "RESULT";
    }

    @MenuOption(order = 1, key = "D", description = "Display All Deposits", formatter = "ledger_menu_option_format")
    @RunLogic
    @PrintResult
    @WhiteSpaceResult(1)
    static String getDeposits(){
        return "Result";

    }

    @MenuOption(order = 2, key = "P", description = "Display All Payments", formatter = "ledger_menu_option_format")
    @RunLogic
    @PrintResult
    @WhiteSpaceResult(1)
    static String getPayments(){
        return "Result";

    }

    @MenuOption(order = 3, key = "R", description = "Open Reports Menu", formatter = "ledger_menu_option_format")
    @NextMenu("reports_menu")
    @WhiteSpaceResult(1)
    void reports(){}

    @MenuOption(order = 4, key = "H", description = "Return to Home Menu", formatter = "ledger_menu_option_format")
    @NextMenu("home_menu")
    @WhiteSpaceResult(1)
    void home(){}
}
