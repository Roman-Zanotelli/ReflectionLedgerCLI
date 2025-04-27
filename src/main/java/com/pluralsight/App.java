package com.pluralsight;

import com.pluralsight.cli.MenuRegister;
import com.pluralsight.cli.UserScanner;
import com.pluralsight.cli.annotations.*;
import com.pluralsight.cli.annotations.display.option.*;
import com.pluralsight.cli.annotations.prompt.PromptFloat;
import com.pluralsight.cli.annotations.prompt.PromptString;
import com.pluralsight.cli.menus.*;
import com.pluralsight.util.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public class App {
    public static void main(String[] args) {
        //TODO: Possibly Add a background timer to detect if the user has been inside a single loop for longer than expected
        //TODO: Possibly Add an encrypted file with login system

        //Shutdown and Cleanup Handler
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            //ADD ALL SHUTDOWN LOGIC HERE

            Ledger.close();
            UserScanner.close();
            FileManager.close();

        }));

        //Registered Menu Classes
        Class<?>[] _registeredMenus = {HomeMenu.class, LedgerMenu.class, ReportsMenu.class};
        Arrays.stream(_registeredMenus).forEach(MenuRegister::register);

        //initial cli_state
        String cliState = "home_menu";

        try{
            do{
                //Display Header
                {
                    String header = MenuRegister.getMenuHeader(cliState);
                    if (header != null) System.out.print(header);
                }

                //Display Menu Options
                {
                    ArrayList<String> options = MenuRegister.getOptionDisplay(cliState);
                    options.forEach(System.out::print);
                }

                //Gets the User selection and handles invalid inputs
                Method selection = MenuRegister.getMethod(
                        //current menu
                        cliState,
                        UserScanner.getWithin(
                                //Option set for current menu
                                MenuRegister.getOptionSet(cliState),
                                //Selector for current menu
                                MenuRegister.getMenuSelector(cliState)
                        )
                );


                //Logic for RunLogic
                if(selection.isAnnotationPresent(RunLogic.class)){

                    //All Parameters
                    Parameter[] params = selection.getParameters();

                    //Result of method
                    Object res;

                    //Invoke directly if parameterless
                    if (params.length == 0) res = selection.invoke(null);
                    //Otherwise Prompt User for parameter values
                    else res = selection.invoke(null, Arrays.stream(params).map(parameter -> {
                        //! IF A FUNCTION DOESN'T PROPERLY ANNOTATE IT WILL PROBABLY CRASH THE PROGRAM!

                        //Todo: consider using a value of the PromptType instead of different annotationsTypes
                        //Todo: letting getPromptAnnotation hande the rest

                        //Logic for PromptString
                        PromptString stringPrompt = parameter.getAnnotation(PromptString.class);
                        if(stringPrompt != null) return UserScanner.getPromptAnnotation(stringPrompt);

                        //Logic for PromptFloat
                        PromptFloat floatPrompt = parameter.getAnnotation(PromptFloat.class);
                        if(floatPrompt != null) return UserScanner.getPromptAnnotation(floatPrompt);

                        return null; //return null otherwise
                    }).toArray()); //collects then invokes the method

                    //Logic for PrintResult
                    if(selection.isAnnotationPresent(PrintResult.class)){

                        //Logic for WhiteSpaceBeforeResult
                        WhiteSpaceBeforeResult whiteSpace = selection.getAnnotation(WhiteSpaceBeforeResult.class);
                        if (whiteSpace != null)System.out.print("\n".repeat(whiteSpace.value()));

                        //Logic for ResultHeader
                        ResultHeader resultHeader = selection.getAnnotation(ResultHeader.class);
                        if(resultHeader != null)System.out.println(resultHeader.value());

                        //Logic for PrintResultFormatter
                        PrintResultFormatter formatter = selection.getAnnotation(PrintResultFormatter.class);
                        String format = formatter != null ? MenuRegister.getFormat(cliState, formatter.value()) : null;

                        //Actual Logic for PrintResult
                        System.out.printf((format != null ? format : "%s") + "\n", res);

                        //Logic for WhiteSpaceAfter Result
                        WhiteSpaceAfterResult whiteSpaceResult = selection.getDeclaredAnnotation(WhiteSpaceAfterResult.class);
                        if (whiteSpaceResult != null) System.out.print("\n".repeat(whiteSpaceResult.value()));

                    }
                }


                //Logic for NextMenu
                NextMenu next = selection.getDeclaredAnnotation(NextMenu.class);
                if(next != null) cliState = next.value();



            //While loop is not supposed to terminate this way
            //Should always use HomeMenu.exit()
            }while(true);


        }catch(IllegalAccessException | InvocationTargetException e){
            //Annotations were used wrong/missing
            System.out.println(e.getLocalizedMessage());
        }catch(IllegalStateException e){
            //Scanner was closed
            System.out.println(e.getLocalizedMessage());
        }

        System.exit(1);
    }

}
