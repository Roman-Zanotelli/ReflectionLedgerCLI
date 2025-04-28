package com.pluralsight.cli.menus;


import com.pluralsight.cli.annotations.Menu;
import com.pluralsight.cli.annotations.NextMenu;
import com.pluralsight.cli.annotations.RunLogic;
import com.pluralsight.cli.annotations.display.OnReturnNextMenu;
import com.pluralsight.cli.annotations.display.WhiteSpaceBefore;
import com.pluralsight.cli.annotations.display.menu.MenuHeader;
import com.pluralsight.cli.annotations.display.option.MenuOption;
import com.pluralsight.cli.annotations.display.option.PrintResult;
import com.pluralsight.cli.annotations.prompt.PromptValue;
import com.pluralsight.cli.annotations.prompt.PressEnterToContinue;
import com.pluralsight.util.Ledger;

@Menu("start_menu")
@MenuHeader("Start Menu")
public final class StartMenu {


    @RunLogic @WhiteSpaceBefore(2) @PrintResult @NextMenu("home_menu")
    @PressEnterToContinue @MenuOption(order = 0, key = "D", description = "Demo Mode")
    public static String demo(){
        return "Demo Mode Selected!";
    }


    @RunLogic @WhiteSpaceBefore(2) @PrintResult @OnReturnNextMenu(menuID = "home_menu", onFailPrint = "Login Failed!")
    @PressEnterToContinue @MenuOption(order = 1, key = "L", description = "Login")
    public static String login(@PromptValue(prompt = "Username: ") String userName, @PromptValue(prompt = "Password: ") String pass, @PromptValue(prompt = "Pin: ", targetClass = Integer.class, parserMethod = "parseInt") Integer pin){
        return Ledger.login(userName, pass, pin);
    }

    @RunLogic
    @MenuOption(order = 2, key = "X", description = "Exit")
    public static void exit(){
        System.exit(0);
    }






}
