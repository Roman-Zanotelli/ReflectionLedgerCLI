package com.pluralsight;

import com.pluralsight.cli.annotations.*;
import com.pluralsight.cli.annotations.display.option.*;
import com.pluralsight.cli.annotations.display.*;
import com.pluralsight.cli.annotations.prompt.*;
import com.pluralsight.menus.HomeMenu;
import com.pluralsight.menus.LedgerMenu;
import com.pluralsight.menus.ReportsMenu;
import com.pluralsight.menus.StartMenu;
import com.pluralsight.util.*;
import com.pluralsight.cli.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

//
public class App {

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) {
        //TODO: Possibly Add a background timer to detect if the user has been inside a single loop for longer than expected
        //TODO: Possibly Add an encrypted file with login system

        //Shutdown and Cleanup Handler
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            //ADD ALL SHUTDOWN LOGIC HERE
            UserScanner.close();
            FileManager.close();
            System.out.println("\u001B[32mB\u001B[33my\u001B[34me\u001B[35m! \u001B[31m<3\u001B[0m_\u001B[31m<3");
        }));

        //Register Menu Classes
        {
            Class<?>[] _registeredMenus = {HomeMenu.class, LedgerMenu.class, ReportsMenu.class};
            Arrays.stream(_registeredMenus).forEach(MenuRegister::register);
        }
        Ledger.setPath();
        //initial cli_state
        String cliState = "home_menu";
        System.out.print("\033[3J\033[2J");
        System.out.flush();
        try{
            do{
                if(!MenuRegister.exists(cliState)) throw new RuntimeException("CLI Context Has Escaped Registered Bounds! Immediate Shutdown Required!");
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



                //Logic for WhiteSpaceBefore Mode
                {
                    WhiteSpaceBefore whiteSpaceBefore = selection.getAnnotation(WhiteSpaceBefore.class);
                    if (whiteSpaceBefore != null)System.out.print("\n".repeat(whiteSpaceBefore.value()));
                }

                //Grab the whiteSpaceAfterMenu before potential context switch
                WhiteSpaceAfter whiteSpaceAfterMenu = MenuRegister.getClass(cliState).getAnnotation(WhiteSpaceAfter.class);

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


                        PromptValue promptValue = parameter.getDeclaredAnnotation(PromptValue.class);
                        if(promptValue != null) {
                            try {
                                return UserScanner.getParsePrompt(promptValue);
                            } catch (Throwable e) {
                                //Throws an error only if the annotations are mis-used
                                throw new RuntimeException(e);
                            }
                        }else{
                            PromptDate promptDate = parameter.getAnnotation(PromptDate.class);
                            if(promptDate != null) return UserScanner.getDate(promptDate);
                        }


                        return null; //return null otherwise
                    }).toArray()); //collects then invokes the method

                    {
                        OnReturnNextMenu onReturn = selection.getAnnotation(OnReturnNextMenu.class);
                        if(onReturn != null ){

                            //Check fail on null
                            if(res == null && !onReturn.onNull()){

                                //Check fail print
                                if(!onReturn.onFailPrint().isEmpty()) System.out.println(onReturn.onFailPrint());

                            }else {
                                //Eval gotoResult
                                if (res != null && onReturn.gotoResult() && res instanceof String) cliState = (String) res;
                                //Get menu ID
                                else if (!onReturn.menuID().isEmpty()) cliState = onReturn.menuID();
                            }
                        }
                    }

                    //check for clear screen before print result
                    if(selection.isAnnotationPresent(ClearScreenBefore.class)) {
                        System.out.print("\033[3J\033[2J");
                        System.out.flush();
                    }

                    //Logic for PrintResult
                    if(selection.isAnnotationPresent(PrintResult.class) && res != null){

                        //Logic for ResultHeader
                        ResultHeader resultHeader = selection.getAnnotation(ResultHeader.class);
                        if(resultHeader != null)System.out.println(resultHeader.value());

                        //Logic for PrintResultFormatter
                        PrintResultFormatter formatter = selection.getAnnotation(PrintResultFormatter.class);
                        String format = formatter != null ? MenuRegister.getFormat(cliState, formatter.value()) : null;

                        //Actual Logic for PrintResult
                        System.out.printf((format != null ? format : "%s") + "\n", res);

                    }

                } else if(selection.isAnnotationPresent(ClearScreenBefore.class)) { //clear screen logic for when not running function logic
                            System.out.print("\033[3J\033[2J");
                            System.out.flush();
                        }


                //Logic for WhiteSpaceAfter Selection
                {
                    WhiteSpaceAfter whiteSpaceAfter = selection.getDeclaredAnnotation(WhiteSpaceAfter.class);
                    if (whiteSpaceAfter != null) System.out.print("\n".repeat(whiteSpaceAfter.value()));
                }

                //Logic for NextMenu (context switch)
                {

                    NextMenu next = selection.getDeclaredAnnotation(NextMenu.class);
                    if (next != null) {
                        NextMenuWhiteSpace nextWhiteSpace = selection.getDeclaredAnnotation(NextMenuWhiteSpace.class);
                        if (nextWhiteSpace != null) System.out.print("\n".repeat(nextWhiteSpace.value()));
                        cliState = next.value();
                    }

                    //Grab the whiteSpaceBefore for the nextMenu (Allows Initial menu to ignore this on startup)
                    WhiteSpaceBefore whiteSpaceBeforeNextMenu = MenuRegister.getClass(cliState).getAnnotation(WhiteSpaceBefore.class);

                    //Sum the whiteSpaceAfter and whiteSpaceBefore for the current and next menu and print it out
                    System.out.print("\n".repeat((whiteSpaceAfterMenu != null ? whiteSpaceAfterMenu.value() : 0) + (whiteSpaceBeforeNextMenu != null ? whiteSpaceBeforeNextMenu.value() : 0)));
                }
                if(selection.getDeclaredAnnotation(PressEnterToContinue.class) != null) UserScanner.pressEnter();
            }while(true); //While loop is not supposed to terminate this way; Instead always use System.exit(0)


        }catch(IllegalAccessException | InvocationTargetException e){

            //Annotations were used wrong/missing
            System.out.println(e.getLocalizedMessage());

        }catch(IllegalStateException e){

            //Scanner was closed
            System.out.println(e.getLocalizedMessage());

        }

        System.exit(1);
    }//*** END OF MAIN


    private void playground(Class<?> type){
    }

}
