package com.pluralsight;

import com.pluralsight.cli.annotation.*;
import com.pluralsight.cli.annotation.display.StringFormatter;
import com.pluralsight.cli.annotation.display.menu.MenuHeader;
import com.pluralsight.cli.annotation.display.menu.MenuSelector;
import com.pluralsight.cli.annotation.display.option.*;
import com.pluralsight.cli.annotation.prompt.PromptFloat;
import com.pluralsight.cli.annotation.prompt.PromptString;
import com.pluralsight.cli.menu.*;
import com.pluralsight.util.FileManager;
import com.pluralsight.util.UserScanner;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public class App {
    public static void main(String[] args) {
        //TODO: Possibly Add a background timer to detect if the user has been inside a single loop for longer than expected
        //TODO: Possibly Add an encrypted file with login system
        //TODO: Possibly Add a formatter Annotation to easier format the menus


        //Registered Menu Classes
        Class<?>[] _registeredMenus = {HomeMenu.class, LedgerMenu.class, ReportsMenu.class};

        //initial cli_state
        String _cliState = "home_menu";

        boolean runningOk;

        try{
            do{
                //prevent context escape where the cli_state is not a valid registered menu
                runningOk = false; //(if menu is not found runningOk remains false, breaking main loop)


                //Scan All Registered Menus
                //The Menus class is only really needed for a few things
                //I prefer naming lesser used variable with an _underscore
                for(Class<?> _menuClass : _registeredMenus){

                    //Gets the annotation for the class
                    Menu _menuAnnotation = _menuClass.getAnnotation(Menu.class);

                    //Checks if the menu is correct
                    //After this point the upper level menu logic isn't very important
                    if(_menuAnnotation != null && _menuAnnotation.value().equals(_cliState)){


                        //Logic For MenuOption
                        //Create a table of the menu methods, Keyed by their Option's key
                        HashMap<String, Method> options = new HashMap<>();
                        //Create a List of option Annotations
                        ArrayList<MenuOption> menuOptions = new ArrayList<>();

                        //Scan All methods
                        Arrays.stream(_menuClass.getDeclaredMethods()).forEach(method -> {
                            //Attempts to get MenuOption
                            MenuOption option = method.getAnnotation(MenuOption.class); //gets the MenuOption annotation if available

                            //Check if valid menu option
                            if (option != null) {
                                //Add option info to list
                                menuOptions.add(option);

                                //Add to method to map keyed by menu key
                                options.put(option.key(), method);
                            }
                        });


                        //Logic for StringFormatter
                        //Preload Values into map for later use
                        HashMap<String, String> formatters = new HashMap<>();
                        //Logic for loading values
                        for (Field field : _menuClass.getDeclaredFields()){
                            //Make sure it's a string
                            if(field.getType() != String.class) continue;

                            //Make Sure it's a formater
                            StringFormatter formatter = field.getDeclaredAnnotation(StringFormatter.class);
                            if(formatter == null) continue;

                            //If Valid add its value to table
                            formatters.put(formatter.value(), (String) field.get(null));

                        }

                        //Logic for MenuHeader
                        MenuHeader header = _menuClass.getAnnotation(MenuHeader.class);
                        if(header != null) {
                            StringFormatter formatter = _menuClass.getAnnotation(StringFormatter.class);
                            String format = formatter != null ? formatters.get(formatter.value()) : null;
                            System.out.printf( format != null ? format : "%s", header.value());
                        }


                        //Logic for Printing MenuOption
                        //Sorts it by order
                        menuOptions.stream().sorted(Comparator.comparing(MenuOption::order))
                                .forEach(menuOption -> {
                                    //Gets format
                                    String format = formatters.get(menuOption.formatter());
                                    //Prints each option
                                    System.out.printf(format != null ? format :"\n\t%s - %s", menuOption.key(), menuOption.description());
                                });


                        //Gets the User selection and handles invalid inputs
                        Method selection = options.get(UserScanner.getWithin(options.keySet(), _menuClass.getAnnotation(MenuSelector.class)));


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

                                //Logic fo ResultHeader
                                ResultHeader resultHeader = selection.getAnnotation(ResultHeader.class);
                                if(resultHeader != null)System.out.println(resultHeader.value());

                                //Actual Logic for PrintResult
                                System.out.println(res);
                            }
                        }


                        //Logic for NextMenu
                        NextMenu next = selection.getDeclaredAnnotation(NextMenu.class);
                        if(next != null) _cliState = next.value();


                        //Logic for WhiteSpace
                        WhiteSpaceResult whiteSpaceResult = selection.getDeclaredAnnotation(WhiteSpaceResult.class);
                        if (whiteSpaceResult != null) System.out.print("\n".repeat(whiteSpaceResult.value()));

                        //signals to the main loop things are ok (not escaped context yet)
                        runningOk = true;

                        //Cancel the menu scan (continues main loop)
                        break;
                    }
                }

                //While loop is not supposed to terminate this way
                //Should always use HomeMenu.exit()
            }while(runningOk);

            //This will only occur if the Annotations are used wrong, or a missing Annotation is needed
        }catch(IllegalAccessException | InvocationTargetException e){
            System.out.println(e.getLocalizedMessage());
        }

        //Even though this shouldn't be reached attempt a graceful close
        //todo: add other cleanup methods as needed
        UserScanner.close();
        FileManager.close();
        System.exit(1);
    }

}
