package com.pluralsight.cli.menus;

import com.pluralsight.cli.annotations.*;
import com.pluralsight.cli.annotations.display.WhiteSpaceBefore;
import com.pluralsight.cli.annotations.display.menu.MenuHeader;
import com.pluralsight.cli.annotations.display.option.MenuOption;
import com.pluralsight.cli.annotations.display.option.PrintResult;
import com.pluralsight.cli.annotations.display.WhiteSpaceAfter;
import com.pluralsight.util.Ledger;

//..................
//LEDGER MENU
//...................
@Menu("ledger_menu")
@MenuHeader("Ledger Menu") @WhiteSpaceBefore
public final class LedgerMenu {


    //GET ALL OPTION
    @RunLogic @WhiteSpaceBefore(2) @PrintResult
    @MenuOption(order = 0, key = "A", description = "Display All Entries")
    public static String getAll(){
        return Ledger.getAll();
    }


    //GET DEPOSITS OPTION
    @RunLogic @WhiteSpaceBefore(2) @PrintResult
    @MenuOption(order = 1, key = "D", description = "Display All Deposits")
    public static String getDeposits(){
        return Ledger.getDeposits();
    }


    //GET PAYMENTS OPTION
    @RunLogic @WhiteSpaceBefore(2) @PrintResult
    @MenuOption(order = 2, key = "P", description = "Display All Payments")
    public static String getPayments(){
        return Ledger.getPayments();
    }


    //REPORTS MENU OPTION
    @WhiteSpaceBefore(2) @NextMenu("reports_menu")
    @MenuOption(order = 3, key = "R", description = "Open Reports Menu")
    public void reports(){
        //DOES NOTHING
    }


    //RETURN HOME OPTION
    @WhiteSpaceBefore(2) @NextMenu("home_menu")
    @MenuOption(order = 4, key = "H", description = "Return to Home Menu")
    public void home(){
        //DOES NOTHING
    }


}
