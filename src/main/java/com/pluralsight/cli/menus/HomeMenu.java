package com.pluralsight.cli.menus;


import com.pluralsight.cli.annotations.display.menu.MenuHeader;
import com.pluralsight.cli.annotations.display.option.MenuOption;
import com.pluralsight.cli.annotations.display.option.PrintResult;
import com.pluralsight.cli.annotations.display.WhiteSpaceAfter;
import com.pluralsight.cli.annotations.*;
import com.pluralsight.cli.annotations.prompt.PromptType;
import com.pluralsight.cli.annotations.prompt.PromptValue;


//....................
//HOME MENU
//..................
@Menu("home_menu")
@MenuHeader("Home Menu")
public final class HomeMenu {


    //ADD DEPOSIT OPTION
    @RunLogic @PrintResult @WhiteSpaceAfter
    @MenuOption(order = 0, key = "D", description = "ADD DEPOSIT")
    public static String addDeposit(@PromptValue(type = PromptType.FLOAT, prompt = "Enter Deposit Amount: ") Float amount, @PromptValue(prompt = "Enter Description: ") String description, @PromptValue(prompt = "Enter Vendor/Depositee: ") String vendor){
        //ADD DEPOSIT LOGIC HERE
        return "Result Message";
    }


    //MAKE PAYMENT OPTION
    @RunLogic @PrintResult @WhiteSpaceAfter
    @MenuOption(order = 1, key = "P", description = "Make Payment (Debit)")
    public static String makePayment(@PromptValue(type = PromptType.FLOAT, prompt = "Enter Payment Amount: ") Float amount, @PromptValue(prompt = "Enter Description: ") String description, @PromptValue(prompt = "Enter Vendor: ") String vendor){
        //ADD PAYMENT LOGIC HERE
        return "Result Message";
    }


    //LEDGER MENU OPTION
    @NextMenu("ledger_menu") @WhiteSpaceAfter
    @MenuOption(order = 2, key = "L", description = "Open Ledger")
    public static void ledger() {
        //DOES NOTHING
    }


    //EXIT OPTION
    @RunLogic @WhiteSpaceAfter
    @MenuOption(order = 3, key = "X", description = "Exit System")
    public static void exit(){
        //EXIT LOGIC
        System.exit(0);
    }


}
