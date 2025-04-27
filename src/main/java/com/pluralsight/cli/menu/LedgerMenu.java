package com.pluralsight.cli.menu;

import com.pluralsight.cli.annotation.*;

@Menu("ledger_menu")
@MenuHeader("Ledger Menu")
public final class LedgerMenu {

    @MenuOption(order = 0, key = "A", description = "Display All Entries")
    @RunLogic
    @PrintResult
    @WhiteSpace(1)
    public static String getAll(){
        return "RESULT";
    }

    @MenuOption(order = 1, key = "D", description = "Display All Deposits")
    @RunLogic
    @PrintResult
    @WhiteSpace(1)
    static String getDeposits(){
        return "Result";

    }

    @MenuOption(order = 2, key = "P", description = "Display All Payments")
    @RunLogic
    @PrintResult
    @WhiteSpace(1)
    static String getPayments(){
        return "Result";

    }

    @MenuOption(order = 3, key = "R", description = "Open Reports Menu")
    @NextMenu("reports_menu")
    @WhiteSpace(1)
    void reports(){}

    @MenuOption(order = 4, key = "H", description = "Return to Home Menu")
    @NextMenu("home_menu")
    @WhiteSpace(1)
    void home(){}
}
