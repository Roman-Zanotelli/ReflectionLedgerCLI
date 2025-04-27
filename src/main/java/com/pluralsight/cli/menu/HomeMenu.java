package com.pluralsight.cli.menu;


import com.pluralsight.cli.annotation.display.StringFormatter;
import com.pluralsight.cli.annotation.display.menu.MenuHeader;
import com.pluralsight.cli.annotation.display.menu.MenuSelector;
import com.pluralsight.cli.annotation.display.option.MenuOption;
import com.pluralsight.cli.annotation.display.option.PrintResult;
import com.pluralsight.cli.annotation.display.option.PrintResultFormatter;
import com.pluralsight.cli.annotation.display.option.WhiteSpaceResult;
import com.pluralsight.util.FileManager;
import com.pluralsight.util.UserScanner;
import com.pluralsight.cli.annotation.*;
import com.pluralsight.cli.annotation.prompt.PromptFloat;
import com.pluralsight.cli.annotation.prompt.PromptString;


//....................
//HOME MENU
//..................
@Menu("home_menu")
@MenuHeader("Home Menu")
@StringFormatter("home_menu_header_format")
@MenuSelector("Enter Selection >")
public final class HomeMenu {


    //ADD DEPOSIT OPTION
    @RunLogic @PrintResult @WhiteSpaceResult(1)
    @MenuOption(order = 0, key = "D", description = "ADD DEPOSIT", formatter = "home_menu_option_format")
    public static String addDeposit(@PromptFloat("Enter Deposit Amount: ") float amount, @PromptString("Enter Description: ") String description, @PromptString("Enter Vendor/Depositee: ") String vendor){
        //ADD DEPOSIT LOGIC HERE
        return "Result Message";
    }



    //MAKE PAYMENT OPTION
    @RunLogic @PrintResult @WhiteSpaceResult(1)
    @MenuOption(order = 1, key = "P", description = "Make Payment (Debit)", formatter = "home_menu_option_format")
    public static String makePayment(@PromptFloat("Enter Payment Amount: ") float amount, @PromptString("Enter Description: ") String description, @PromptString("Enter Vendor/Depositee: ") String vendor){
        //ADD PAYMENT LOGIC HERE
        return "Result Message";
    }



    //LEDGER MENU OPTION
    @NextMenu("ledger_menu") @WhiteSpaceResult(1)
    @MenuOption(order = 2, key = "L", description = "Open Ledger", formatter = "home_menu_option_format")
    public static void ledger() {
        //DOES NOTHING
    }


    //EXIT OPTION
    @RunLogic @WhiteSpaceResult(1)
    @MenuOption(order = 3, key = "X", description = "Exit System", formatter = "home_menu_option_format")
    public static void exit(){
        //EXIT LOGIC
        UserScanner.close();
        FileManager.close();
        System.exit(0);
    }
}
