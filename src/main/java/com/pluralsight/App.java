package com.pluralsight;

import com.pluralsight.cli.annotation.*;
import com.pluralsight.cli.annotation.prompt.PromptFloat;
import com.pluralsight.cli.annotation.prompt.PromptString;
import com.pluralsight.cli.menu.*;
import com.pluralsight.util.FileManager;
import com.pluralsight.util.UserScanner;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public class App {
    public static void main(String[] args) {
        //Registered Menu Classes
        Class<?>[] registered_menus = {HomeMenu.class, LedgerMenu.class, ReportsMenu.class};

        //initial cli_state
        String cli_state = "home_menu";

        boolean running_ok;

        try{
            do{
                //prevent context escape where the cli_state is not a valid registered menu
                running_ok = false; // (if menu is not found running_ok remains false, breaking main loop)


                for(Class<?> menu_class : registered_menus){ //scan all register menu classes
                    Menu menu_annotation = menu_class.getAnnotation(Menu.class); //gets the annotation for each class

                    if(menu_annotation != null && menu_annotation.value().equals(cli_state)){ //finds our desired menu
                        HashMap<String, Method> options = new HashMap<>(); //creates a map of input - method

                        ArrayList<MenuOption> menuOptions = new ArrayList<>(); //list of found option descriptions

                        Arrays.stream(menu_class.getDeclaredMethods()).forEach(method -> { //checks each method in menu class
                                MenuOption annotation = method.getAnnotation(MenuOption.class); //gets the MenuOption annotation if available

                                if (annotation != null) { //checks if it exists

                                    menuOptions.add(annotation); //add option information to list
                                    options.put(annotation.key(), method); // add method to map with corresponding input

                                }
                        });

                        MenuHeader header = menu_class.getAnnotation(MenuHeader.class); //gets the header for the menu
                        if(header != null) System.out.printf("%s", header.value()); //prints it out
                        menuOptions.stream().sorted(Comparator.comparing(MenuOption::order)) //sort the list info
                                .forEach(menuOption ->
                                        //prints each menu option
                                        System.out.printf("\n\t%s - %s", menuOption.key(), menuOption.description())
                                );

                        //this line does a lot it calls the scanner class with a range of the valid keyset
                        // and then gets the selected option from the map returning the reflected method to be used
                        Method selection = options.get(UserScanner.getWithin(options.keySet()));
                        if(selection.isAnnotationPresent(RunLogic.class)){ //checks if the method should run
                            Parameter[] params = selection.getParameters(); //gets all params
                            Object res; //result of method

                            if (params.length == 0) res = selection.invoke(null); //runs it directly if no params
                            else res = selection.invoke(null, Arrays.stream(params).map(parameter -> { //attempts to fill parameters if they exist
                                //! IF A FUNCTION DOESN'T PROPERLY ANNOTATE IT WILL PROBABLY CRASH

                                //handle String param
                                PromptString stringPrompt = parameter.getAnnotation(PromptString.class);
                                if(stringPrompt != null) return UserScanner.getPromptAnnotation(stringPrompt);

                                //handle float param
                                PromptFloat floatPrompt = parameter.getAnnotation(PromptFloat.class);
                                if(floatPrompt != null) return UserScanner.getPromptAnnotation(floatPrompt);

                                return null; //return null otherwise
                            }).toArray());

                            if(selection.isAnnotationPresent(PrintResult.class))System.out.println(res);
                        }

                        NextMenu next = selection.getDeclaredAnnotation(NextMenu.class); //gets the next menu if any
                        if(next != null) cli_state = next.value();



                        WhiteSpace whiteSpace = selection.getDeclaredAnnotation(WhiteSpace.class);
                        if (whiteSpace != null) System.out.print("\n".repeat(whiteSpace.value())); //prints the whitespace
                        running_ok = true; //signals to the main loop things are ok (not somehow escaped menu context)
                        break;
                    }
                }
            }while(running_ok); //this should never be false, proper execution ends through exit function not loop termination
        }catch(IllegalAccessException | InvocationTargetException e){
            System.out.println(e.getLocalizedMessage());
        }
        UserScanner.close();
        FileManager.close();
        System.exit(1);
    }

}
