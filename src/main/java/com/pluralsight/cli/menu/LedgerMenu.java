package com.pluralsight.cli.menu;

import com.pluralsight.cli.annotation.*;
import com.pluralsight.cli.annotation.display.StringFormatter;
import com.pluralsight.cli.annotation.display.menu.MenuHeader;
import com.pluralsight.cli.annotation.display.menu.MenuSelector;
import com.pluralsight.cli.annotation.display.option.MenuOption;
import com.pluralsight.cli.annotation.display.option.PrintResult;
import com.pluralsight.cli.annotation.display.option.WhiteSpaceResult;

//..................
//LEDGER MENU
//...................
@Menu("ledger_menu")
@MenuHeader("Ledger Menu") @MenuSelector("Enter Selection >") @StringFormatter("ledger_menu_header_format")
public final class LedgerMenu {

    //GET ALL OPTION
    @RunLogic @PrintResult @WhiteSpaceResult(1)
    @MenuOption(order = 0, key = "A", description = "Display All Entries", formatter = "ledger_menu_option_format")
    public static String getAll(){
        //GET ALL LOGIC HERE
        return "RESULT";
    }


    //GET DEPOSITS OPTION
    @RunLogic @PrintResult @WhiteSpaceResult(1)
    @MenuOption(order = 1, key = "D", description = "Display All Deposits", formatter = "ledger_menu_option_format")
    static String getDeposits(){
        //GET DEPOSITS LOGIC HERE
        return "Result";

    }

    //GET PAYMENTS OPTION
    @RunLogic @PrintResult @WhiteSpaceResult(1)
    @MenuOption(order = 2, key = "P", description = "Display All Payments", formatter = "ledger_menu_option_format")
    static String getPayments(){
        //GETS PAYMENTS LOGIC HERE
        return "Result";

    }


    //REPORTS MENU OPTION
    @NextMenu("reports_menu") @WhiteSpaceResult(1)
    @MenuOption(order = 3, key = "R", description = "Open Reports Menu", formatter = "ledger_menu_option_format")
    void reports(){
        //DOES NOTHING
    }

    //RETURN HOME OPTION
    @NextMenu("home_menu") @WhiteSpaceResult(1)
    @MenuOption(order = 4, key = "H", description = "Return to Home Menu", formatter = "ledger_menu_option_format")
    void home(){
        //DOES NOTHING
    }



}
