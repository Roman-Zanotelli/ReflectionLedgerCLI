package com.pluralsight.cli.menus;

import com.pluralsight.cli.annotations.*;
import com.pluralsight.cli.annotations.display.menu.MenuHeader;
import com.pluralsight.cli.annotations.display.option.MenuOption;
import com.pluralsight.cli.annotations.display.option.PrintResult;
import com.pluralsight.cli.annotations.display.option.WhiteSpaceAfterResult;

//..................
//LEDGER MENU
//...................
@Menu("ledger_menu")
@MenuHeader("Ledger Menu")
public final class LedgerMenu {


    //GET ALL OPTION
    @RunLogic @PrintResult @WhiteSpaceAfterResult(1)
    @MenuOption(order = 0, key = "A", description = "Display All Entries", formatter = "ledger_menu_option_format")
    public static String getAll(){
        //GET ALL LOGIC HERE
        return "RESULT";
    }


    //GET DEPOSITS OPTION
    @RunLogic @PrintResult @WhiteSpaceAfterResult(1)
    @MenuOption(order = 1, key = "D", description = "Display All Deposits", formatter = "ledger_menu_option_format")
    public static String getDeposits(){
        //GET DEPOSITS LOGIC HERE
        return "Result";
    }


    //GET PAYMENTS OPTION
    @RunLogic @PrintResult @WhiteSpaceAfterResult(1)
    @MenuOption(order = 2, key = "P", description = "Display All Payments", formatter = "ledger_menu_option_format")
    public static String getPayments(){
        //GETS PAYMENTS LOGIC HERE
        return "Result";
    }


    //REPORTS MENU OPTION
    @NextMenu("reports_menu") @WhiteSpaceAfterResult(1)
    @MenuOption(order = 3, key = "R", description = "Open Reports Menu", formatter = "ledger_menu_option_format")
    public void reports(){
        //DOES NOTHING
    }


    //RETURN HOME OPTION
    @NextMenu("home_menu") @WhiteSpaceAfterResult(1)
    @MenuOption(order = 4, key = "H", description = "Return to Home Menu", formatter = "ledger_menu_option_format")
    public void home(){
        //DOES NOTHING
    }


}
