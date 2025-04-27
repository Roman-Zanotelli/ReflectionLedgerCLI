package com.pluralsight.cli.menus;


import com.pluralsight.cli.annotations.display.menu.MenuHeader;
import com.pluralsight.cli.annotations.display.option.MenuOption;
import com.pluralsight.cli.annotations.display.option.PrintResult;
import com.pluralsight.cli.annotations.display.option.WhiteSpaceAfterResult;
import com.pluralsight.cli.annotations.*;
import com.pluralsight.cli.annotations.prompt.PromptFloat;
import com.pluralsight.cli.annotations.prompt.PromptString;


//....................
//HOME MENU
//..................
@Menu("home_menu")
@MenuHeader("Home Menu")
public final class HomeMenu {


    //ADD DEPOSIT OPTION
    @RunLogic @PrintResult @WhiteSpaceAfterResult(1)
    @MenuOption(order = 0, key = "D", description = "ADD DEPOSIT", formatter = "home_menu_option_format")
    public static String addDeposit(@PromptFloat("Enter Deposit Amount: ") float amount, @PromptString("Enter Description: ") String description, @PromptString("Enter Vendor/Depositee: ") String vendor){
        //ADD DEPOSIT LOGIC HERE
        return "Result Message";
    }


    //MAKE PAYMENT OPTION
    @RunLogic @PrintResult @WhiteSpaceAfterResult(1)
    @MenuOption(order = 1, key = "P", description = "Make Payment (Debit)", formatter = "home_menu_option_format")
    public static String makePayment(@PromptFloat("Enter Payment Amount: ") float amount, @PromptString("Enter Description: ") String description, @PromptString("Enter Vendor/Depositee: ") String vendor){
        //ADD PAYMENT LOGIC HERE
        return "Result Message";
    }


    //LEDGER MENU OPTION
    @NextMenu("ledger_menu") @WhiteSpaceAfterResult(1)
    @MenuOption(order = 2, key = "L", description = "Open Ledger", formatter = "home_menu_option_format")
    public static void ledger() {
        //DOES NOTHING
    }


    //EXIT OPTION
    @RunLogic @WhiteSpaceAfterResult(1)
    @MenuOption(order = 3, key = "X", description = "Exit System", formatter = "home_menu_option_format")
    public static void exit(){
        //EXIT LOGIC
        System.exit(0);
    }


}
