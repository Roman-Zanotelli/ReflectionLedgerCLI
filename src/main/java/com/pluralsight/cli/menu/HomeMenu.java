package com.pluralsight.cli.menu;


import com.pluralsight.cli.annotation.display.StringFormatter;
import com.pluralsight.cli.annotation.display.menu.MenuHeader;
import com.pluralsight.cli.annotation.display.menu.MenuSelector;
import com.pluralsight.cli.annotation.display.option.MenuOption;
import com.pluralsight.cli.annotation.display.option.PrintResult;
import com.pluralsight.cli.annotation.display.option.WhiteSpaceResult;
import com.pluralsight.util.FileManager;
import com.pluralsight.util.UserScanner;
import com.pluralsight.cli.annotation.*;
import com.pluralsight.cli.annotation.prompt.PromptFloat;
import com.pluralsight.cli.annotation.prompt.PromptString;

@Menu("home_menu")
@MenuHeader("Home Menu")
@StringFormatter("home_menu_header_format")
@MenuSelector("Enter Selection >")
public final class HomeMenu {

//    @StringFormatter("home_menu_header_format")
//    public static final String header_format = "%s";
//
//    @StringFormatter("home_menu_option_format")
//    public static final String option_format = "\n\t%s - %s";

    @MenuOption(order = 0, key = "D", description = "ADD DEPOSIT", formatter = "home_menu_option_format")
    @RunLogic
    @PrintResult
    @WhiteSpaceResult(1)
    public static String addDeposit(
            @PromptFloat("Enter Deposit Amount: ") float amount,
            @PromptString("Enter Description: ") String description,
            @PromptString("Enter Vendor/Depositee: ") String vendor
    ){
        //Add deposit Logic Here
        return "Result Message";
    }

    @MenuOption(order = 1, key = "P", description = "Make Payment (Debit)", formatter = "home_menu_option_format")
    @RunLogic
    @PrintResult
    @WhiteSpaceResult(1)
    public static String makePayment(
            @PromptFloat("Enter Payment Amount: ") float amount,
            @PromptString("Enter Description: ") String description,
            @PromptString("Enter Vendor/Depositee: ") String vendor
    ){
        return "Result Message";
    }

    @MenuOption(order = 2, key = "L", description = "Open Ledger", formatter = "home_menu_option_format")
    @NextMenu("ledger_menu")
    @WhiteSpaceResult(1)
    public static void ledger() {}

    @MenuOption(order = 3, key = "X", description = "Exit System", formatter = "home_menu_option_format")
    @RunLogic
    @WhiteSpaceResult(1)
    public static void exit(){
        UserScanner.close();
        FileManager.close();
        System.exit(0);
    }
}
