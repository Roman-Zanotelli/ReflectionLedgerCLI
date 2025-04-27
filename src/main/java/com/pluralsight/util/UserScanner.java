package com.pluralsight.util;

import com.pluralsight.cli.annotation.display.menu.MenuSelector;
import com.pluralsight.cli.annotation.prompt.*;

import java.lang.annotation.Annotation;
import java.util.Scanner;
import java.util.Set;

public final class UserScanner {
    private static final Scanner scanner = new Scanner(System.in);

    public static String getWithin(Set<String> options, MenuSelector selector){
        //Final Res
        String res;
        do {
            //Gets the menu's MenuSelector using it if available
            System.out.printf("\n%s ", selector != null ? selector.value() : "Enter Selection >");
            //Todo: add a better way besides just uppercase
            res = scanner.nextLine().trim().toUpperCase();

            //Check input matches keyset
        }while (!options.contains(res));

        //Return Final Res
        return res;
    }

    public static Object getPromptAnnotation(Annotation annotation){
        //Res
        Object res = null;

        //Logic for PromptString
        //Java 17 doesn't support the switch case I wanted to use, but it is apparently available in later versions
        if (annotation instanceof PromptString){
            do {
                //Prompt
                System.out.print(((PromptString) annotation).value());
                //Get value
                res = scanner.nextLine();

                //Check the final value
            } while (res == null || ((String) res).isBlank());
        }

        //Logic for prompt float
        else if(annotation instanceof PromptFloat){
            do{
                //Prompt
                System.out.print(((PromptFloat) annotation).value());

                //Check valid Float
            } while (checkValueAndClear(scanner.hasNextFloat()));

            //Get value
            res = scanner.nextFloat();
            //Clear for next scan
            scanner.nextLine();
        }

        //Logic for NullablePromptString
        else if(annotation instanceof NullablePromptString){
            res = scanner.nextLine().trim();
        }

        //Logic for NullablePromptFloat
        else if(annotation instanceof NullablePromptFloat){
            //TODO: add a check for if there ARE values but they ARENT floats
            //TODO: accept completely empty inputs (reprompt in the case of spaces)
            //TODO: Return proper float if possible
        }

        //Logic for NullablePromptDate
        else if (annotation instanceof NullablePromptDate) {
            //TODO: add a check for if there ARE values but they ARENT dates
            //TODO: add parser logic
            //TODO: accept completely empty inputs (reprompt in the case of spaces)
            //TODO: Return proper date if possible
        }

        //Return Final Res
        return res;
    }

    //Convenience method for do-while loop
    private static boolean checkValueAndClear(boolean bool){
        //Break loop
        if(bool) return false;
        //Otherwise consume enter
        scanner.nextLine();
        //Continue loop
        return true;
    }

    //Cleanup
    public static void close(){
        scanner.close();
    }

}
