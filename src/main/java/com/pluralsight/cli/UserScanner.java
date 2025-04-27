package com.pluralsight.cli;

import com.pluralsight.cli.annotations.prompt.*;

import java.text.DateFormat;
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
        return null;
    }

    public static Object getPrompt(PromptValue promptValue){
        return switch (promptValue.type()){

            case FLOAT -> {
                while(true){
                    System.out.print(promptValue.prompt());
                    String in = scanner.nextLine().trim();
                    if(in.isBlank() && promptValue.nullable()) yield null;
                    try {
                        yield Float.parseFloat(in);
                    }catch (Exception ignored){}
                }
            }
            case STRING -> {
                while(true){
                    System.out.print(promptValue.prompt());
                    String in = scanner.nextLine().trim();
                    if(!in.isBlank()) yield in;
                    if(promptValue.nullable()) yield null;
                }
            }
            case INT -> {
                while(true){
                    System.out.print(promptValue.prompt());
                    String in = scanner.nextLine().trim();
                    if(in.isBlank() && promptValue.nullable()) yield null;
                    try {
                        yield Integer.parseInt(in);
                    }catch (Exception ignored){}
                }
            }
            default -> null;
        };
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
