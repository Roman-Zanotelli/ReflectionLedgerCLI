package com.pluralsight.menus;


import com.pluralsight.cli.annotations.display.ClearScreenBefore;
import com.pluralsight.cli.annotations.display.StringFormatter;
import com.pluralsight.cli.annotations.display.WhiteSpaceBefore;
import com.pluralsight.cli.annotations.display.menu.MenuHeader;
import com.pluralsight.cli.annotations.display.menu.MenuSelector;
import com.pluralsight.cli.annotations.display.option.MenuOption;
import com.pluralsight.cli.annotations.display.option.PrintResult;
import com.pluralsight.cli.annotations.*;
import com.pluralsight.cli.annotations.prompt.PromptValue;
import com.pluralsight.util.Ledger;


//....................
//HOME MENU
//..................
@Menu("home_menu")
@MenuHeader("Home Menu") @WhiteSpaceBefore @StringFormatter("menu_formatter") @MenuSelector()
public final class HomeMenu {

    @StringFormatter("menu_formatter")
    public static String header_formatter= "\033[4;47;30m%s\033[0m";

    //ADD DEPOSIT OPTION
    @RunLogic @ClearScreenBefore @PrintResult
    @MenuOption(order = 0, key = "D", description = "ADD DEPOSIT")
    public static String addDeposit(@PromptValue(prompt = "Enter Deposit Amount: ", targetClass = Float.class, parserMethod = "parseFloat") Float amount, @PromptValue(prompt = "Enter a Description: ") String description, @PromptValue(prompt = "Please Enter a Vendor/Depositee: ") String vendor){
        //ADD DEPOSIT LOGIC HERE
        return Ledger.addDeposit(amount, description, vendor);
    }


    //MAKE PAYMENT OPTION
    @RunLogic @ClearScreenBefore @PrintResult
    @MenuOption(order = 1, key = "P", description = "Make Payment (Debit)")
    public static String makePayment(@PromptValue(prompt = "Enter Payment Amount: ", targetClass = Float.class, parserMethod = "parseFloat") Float amount, @PromptValue(prompt = "Enter Description: ") String description, @PromptValue(prompt = "Enter Vendor: ") String vendor){
        return Ledger.makePayment(amount, description, vendor);
    }


    //LEDGER MENU OPTION
    @ClearScreenBefore @NextMenu("ledger_menu")
    @MenuOption(order = 2, key = "L", description = "Open Ledger")
    public static void ledger() {
        //DOES NOTHING
    }


    //EXIT OPTION
    @ClearScreenBefore @RunLogic
    @MenuOption(order = 3, key = "X", description = "Exit System")
    public static void exit(){
        System.exit(0);
    }

    //LOGOUT OPTION
    @RunLogic @ClearScreenBefore @PrintResult @NextMenu("start_menu")
    // REMOVED OPTION @MenuOption(order = 4, key = "<<", description = "Log Out")
    public static String logout(){
        return Ledger.logout();
    }

}
