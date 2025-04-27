package com.pluralsight.cli.menu;


import com.pluralsight.util.FileManager;
import com.pluralsight.util.UserScanner;
import com.pluralsight.cli.annotation.*;
import com.pluralsight.cli.annotation.prompt.PromptFloat;
import com.pluralsight.cli.annotation.prompt.PromptString;

@Menu("home_menu")
@MenuHeader("Home Menu")
public final class HomeMenu {

    @MenuOption(order = 0, key = "D", description = "ADD DEPOSIT")
    @RunLogic
    @PrintResult
    @WhiteSpace(1)
    public static String addDeposit(
            @PromptFloat("Enter Deposit Amount: ") float amount,
            @PromptString("Enter Description: ") String description,
            @PromptString("Enter Vendor/Depositee: ") String vendor
    ){
        //Add deposit Logic Here
        return "Result Message";
    }

    @MenuOption(order = 1, key = "P", description = "Make Payment (Debit)")
    @RunLogic
    @PrintResult
    @WhiteSpace(1)
    public static String makePayment(
            @PromptFloat("Enter Deposit Amount: ") float amount,
            @PromptString("Enter Description: ") String description,
            @PromptString("Enter Vendor/Depositee: ") String vendor
    ){
        return "Result Message";
    }

    @MenuOption(order = 2, key = "L", description = "Open Ledger")
    @NextMenu("ledger_menu")
    @WhiteSpace(1)
    public static void ledger() {}

    @MenuOption(order = 3, key = "X", description = "Exit System")
    @RunLogic
    @WhiteSpace(1)
    public static void exit(){
        UserScanner.close();
        FileManager.close();
        System.exit(0);
    }
}
