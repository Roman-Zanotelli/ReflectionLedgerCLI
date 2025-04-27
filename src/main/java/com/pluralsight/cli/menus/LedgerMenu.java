package com.pluralsight.cli.menus;

import com.pluralsight.cli.annotations.*;
import com.pluralsight.cli.annotations.display.menu.MenuHeader;
import com.pluralsight.cli.annotations.display.option.MenuOption;
import com.pluralsight.cli.annotations.display.option.PrintResult;
import com.pluralsight.cli.annotations.display.WhiteSpaceAfter;

//..................
//LEDGER MENU
//...................
@Menu("ledger_menu")
@MenuHeader("Ledger Menu")
public final class LedgerMenu {


    //GET ALL OPTION
    @RunLogic @PrintResult @WhiteSpaceAfter
    @MenuOption(order = 0, key = "A", description = "Display All Entries")
    public static String getAll(){
        //GET ALL LOGIC HERE
        return "RESULT";
    }


    //GET DEPOSITS OPTION
    @RunLogic @PrintResult @WhiteSpaceAfter
    @MenuOption(order = 1, key = "D", description = "Display All Deposits")
    public static String getDeposits(){
        //GET DEPOSITS LOGIC HERE
        return "Result";
    }


    //GET PAYMENTS OPTION
    @RunLogic @PrintResult @WhiteSpaceAfter
    @MenuOption(order = 2, key = "P", description = "Display All Payments")
    public static String getPayments(){
        //GET PAYMENTS LOGIC HERE
        return "Result";
    }


    //REPORTS MENU OPTION
    @NextMenu("reports_menu") @WhiteSpaceAfter
    @MenuOption(order = 3, key = "R", description = "Open Reports Menu")
    public void reports(){
        //DOES NOTHING
    }


    //RETURN HOME OPTION
    @NextMenu("home_menu") @WhiteSpaceAfter
    @MenuOption(order = 4, key = "H", description = "Return to Home Menu")
    public void home(){
        //DOES NOTHING
    }


}
