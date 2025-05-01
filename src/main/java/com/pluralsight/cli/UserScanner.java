package com.pluralsight.cli;

import com.pluralsight.cli.annotations.prompt.*;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Set;

public final class UserScanner {
    private static final Scanner scanner = new Scanner(System.in);

    public static String getWithin(Set<String> options, String selector){
        //Final Res
        String res;
        do {

            //Gets the menu's MenuSelector using it if available
            System.out.printf("\n%s ", selector != null ? selector : "Enter Selection >");
            //Todo: add a better way besides just uppercase
            res = scanner.nextLine().trim().toUpperCase();

            //Check input matches keyset
        }while (!options.contains(res));

        //Return Final Res
        return res;
    }

    public static Object getDate(PromptDate promptDate){
        while (true){
            System.out.print(promptDate.prompt());
            String in = scanner.nextLine();
            try{
                return LocalDateTime.parse(in, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }catch (Exception ignore){
                if(in.isBlank() && promptDate.nullable()) return null;
            }
        }
    }

    public static Object getParsePrompt(PromptValue promptValue) throws Throwable {
        while(true){
            System.out.print(promptValue.prompt());
            String in = scanner.nextLine().trim();
            if(in.isBlank()){
                if(promptValue.nullable()) return null;
                continue;
            }
            if (promptValue.targetClass() == String.class) return in;
            try{
                //This is magic (it basically calls the class you wish to parse to gets function with parserName(String parseValue) and invokes a static reference passing in user string input)
                return promptValue.targetClass().getDeclaredMethod(promptValue.parserMethod(), String.class).invoke(null, in);
                //
            }catch(NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | NullPointerException | ExceptionInInitializerError e){
                throw new RuntimeException(String.format("ParsePromptValue Invocation Failed!\nParser Class: %s\nParser Method: %s\nPlease ensure targetClass and parserName are properly set, the parser method is public, static and only accepts a string", promptValue.targetClass(), promptValue.parserMethod())).initCause(e);
            } catch (InvocationTargetException e) {
                //This occurs when the underlying method being invoked throws an error
                //This error should be related to some form of parsing errors and is not indicative of a true issue
                continue;
            }
        }
    }
    public static void pressEnter(){
        System.out.println("Press Enter to Continue");
        scanner.nextLine();
    }
    //Cleanup
    public static void close(){
        scanner.close();
    }

}
