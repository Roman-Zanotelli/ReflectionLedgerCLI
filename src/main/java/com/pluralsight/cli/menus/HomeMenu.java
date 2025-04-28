package com.pluralsight.cli.menus;


import com.pluralsight.cli.annotations.display.WhiteSpaceBefore;
import com.pluralsight.cli.annotations.display.menu.MenuHeader;
import com.pluralsight.cli.annotations.display.option.MenuOption;
import com.pluralsight.cli.annotations.display.option.PrintResult;
import com.pluralsight.cli.annotations.display.WhiteSpaceAfter;
import com.pluralsight.cli.annotations.*;
import com.pluralsight.cli.annotations.prompt.PromptValue;
import com.pluralsight.util.Ledger;


//....................
//HOME MENU
//..................
@Menu("home_menu")
@MenuHeader("Home Menu")
public final class HomeMenu {


    //ADD DEPOSIT OPTION
    @RunLogic @WhiteSpaceBefore(2) @PrintResult @WhiteSpaceAfter
    @MenuOption(order = 0, key = "D", description = "ADD DEPOSIT")
    public static String addDeposit(@PromptValue(prompt = "Enter Deposit Amount: ", targetClass = Float.class, parserMethod = "parseFloat") Float amount, @PromptValue(prompt = "Enter a Description: ") String description, @PromptValue(prompt = "Please Enter a Vendor/Depositee: ") String vendor){
        //ADD DEPOSIT LOGIC HERE
        return Ledger.addDeposit(amount, description, vendor);
    }


    //MAKE PAYMENT OPTION
    @RunLogic @WhiteSpaceBefore(2) @PrintResult
    @MenuOption(order = 1, key = "P", description = "Make Payment (Debit)")
    public static String makePayment(@PromptValue(prompt = "Enter Payment Amount: ", targetClass = Float.class, parserMethod = "parseFloat") Float amount, @PromptValue(prompt = "Enter Description: ") String description, @PromptValue(prompt = "Enter Vendor: ") String vendor){
        return Ledger.makePayment(amount, description, vendor);
    }


    //LEDGER MENU OPTION
    @WhiteSpaceBefore(2) @NextMenu("ledger_menu")
    @MenuOption(order = 2, key = "L", description = "Open Ledger")
    public static void ledger() {
        //DOES NOTHING
    }


    //EXIT OPTION
    @RunLogic
    @MenuOption(order = 3, key = "X", description = "Exit System")
    public static void exit(){
        System.exit(0);
    }

    //LOGOUT OPTION
    @RunLogic @WhiteSpaceBefore(2) @PrintResult @NextMenu("start_menu")
    @MenuOption(order = 4, key = "<<", description = "Log Out")
    public static String logout(){
        return Ledger.logout();
    }

}
