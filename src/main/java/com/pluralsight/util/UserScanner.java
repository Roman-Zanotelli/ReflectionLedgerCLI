package com.pluralsight.util;

import com.pluralsight.cli.annotation.display.menu.MenuSelector;
import com.pluralsight.cli.annotation.prompt.PromptFloat;
import com.pluralsight.cli.annotation.prompt.PromptString;

import java.lang.annotation.Annotation;
import java.util.Scanner;
import java.util.Set;

public final class UserScanner {
    private static final Scanner scanner = new Scanner(System.in);

    public static String getWithin(Set<String> options, MenuSelector selector){
        String res;

        do {
            System.out.printf("\n%s ", selector != null ? selector.value() : "Enter Selection >");
            res = scanner.nextLine().trim().toUpperCase();
        }while (!options.contains(res)); //check if the res is within keyset

        return res;
    }

    public static Object getPromptAnnotation(Annotation annotation){
        Object res = null; //result

        //logic for prompt string
        if (annotation instanceof PromptString){
            do {
                System.out.print(((PromptString) annotation).value()); //get the prompt
                res = scanner.nextLine(); //get value
            } while (res == null || ((String) res).isBlank()); //check the final value
        }

        //logic for prompt float
        else if(annotation instanceof PromptFloat){
            do{
                System.out.print(((PromptFloat) annotation).value()); //get prompt
            } while (checkValueAndClear(scanner.hasNextFloat()));
            res = scanner.nextFloat(); //get value
            scanner.nextLine(); //clear for next scan
        }

        return res;
    }

    private static boolean checkValueAndClear(boolean bool){ //used during do-while to check hasNextFloat, hasNextBool, or any other failable
        if(bool) return false; //if proper type break loop
        scanner.nextLine(); //if false clear line
        return true; //tell loop to retry
    }

    public static void close(){
        scanner.close();
    }

}
