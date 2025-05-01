package com.pluralsight.cli;

import com.pluralsight.cli.annotations.Menu;
import com.pluralsight.cli.annotations.display.StringFormatter;
import com.pluralsight.cli.annotations.display.menu.MenuHeader;
import com.pluralsight.cli.annotations.display.menu.MenuSelector;
import com.pluralsight.cli.annotations.display.option.MenuOption;

import java.lang.reflect.Method;
import java.util.*;

//Pre-Process and Cache Menu Data
public class MenuRegister {
//====================================================================================================================\\
//=========================================>  START OF INTERNAL REGISTRIES  <=========================================\\
//====================================================================================================================\\




    //Map of all registered modes by menuID -> modeKey
    private static final HashMap<String, HashMap<String, Method>> internalModeRegister = new HashMap<>();

    //Map of all registered mode displays by menuID
    private static final HashMap<String, ArrayList<String>> internalModeDisplayRegister = new HashMap<>();

    //Map of all registered menu classes by menuID
    private static final HashMap<String, Class<?>> internalClassRegister = new HashMap<>();

    //Map of all registered pre-formatted menu headers by menuID
    private static final HashMap<String, String> internalMenuHeaderRegister = new HashMap<>();

    //Map of all registered String formats by menuID -> formatterID
    private static final HashMap<String, HashMap<String, String>> internalFormatMap = new HashMap<>();

    //Map of all registered menu selectors by menuID
    private static final HashMap<String, String> internalMenuSelectorRegister = new HashMap<>();




//====================================================================================================================\\
//========================================>  END OF INTERNAL REGISTRIES  <============================================\\
//====================================================================================================================\\
//====================================================================================================================\\
//========================================>   START OF HELPER METHODS    <============================================\\
//====================================================================================================================\\
// Todo: reorganize source code to better group methods by functionality




    //Get Class Reflection by menuID
    public static Class<?> getClass(String menu){
        return internalClassRegister.get(menu);
    }


    //Get the display header string by menuID
    public static String getMenuHeader(String menu){
        return internalMenuHeaderRegister.get(menu);
    }


    //Get the menu selector string by menuID
    public static String getMenuSelector(String menu){
        return internalMenuSelectorRegister.get(menu);
    }


    //Get Method by menuID -> modeKey
    public static Method getMethod(String menu, String key){
        return internalModeRegister.get(menu).get(key);
    }


    //Get option display String list by menuID
    public static ArrayList<String> getOptionDisplay(String menu){
        return internalModeDisplayRegister.get(menu);
    }


    //Get the option set by MenuID
    public static Set<String> getOptionSet(String menu){
        return internalModeRegister.get(menu).keySet();
    }


    //Check if a menuID exists
    public static boolean exists(String menu){
        return internalClassRegister.containsKey(menu);
    }


    //Gets the format string by menuID -> formatterID
    public static String getFormat(String menu, String formatter){
        return internalFormatMap.get(menu).get(formatter);
    }




//====================================================================================================================\\
//========================================>   END OF HELPER METHODS    <==============================================\\
//====================================================================================================================\\
//====================================================================================================================\\
//========================================>  START OF REGISTER METHOD  <==============================================\\
//====================================================================================================================\\


    //Registers a new Menu Class
    public static boolean register(Class<?> menuClass){

        //Get MenuAnnotation
        Menu menuAnnotation = menuClass.getAnnotation(Menu.class);

        //Exit early if needed
        if (menuAnnotation == null
                || internalClassRegister.containsKey(menuAnnotation.value())
        ) return false;

        //Add class to internal Class Register
        internalClassRegister.put(menuAnnotation.value(), menuClass);

        //Map for this specific menu's formats (Temp)
        HashMap<String, String> formatMap = new HashMap<>();

        //List of this specific menu's options
        ArrayList<MenuOption> optionsFound = new ArrayList<>();




        //___________________________________________________________________
        //______ Load optionsFound and internalModeRegister _________________
        //___________________________________________________________________
        {


            //Map for this menu's methods
            HashMap<String, Method> methodMap = new HashMap<>();

            //+++ Stream all methods in class +++
            Arrays.stream(menuClass.getDeclaredMethods())

                    //Filter MenuOption methods
                    .filter(method -> method.isAnnotationPresent(MenuOption.class))

                    //Process each MenuOption method
                    .forEach(method -> {

                        //Get the annotation
                        MenuOption option = method.getDeclaredAnnotation(MenuOption.class);

                        //Put the method in the map with respect to the option key
                        methodMap.put(option.key(), method);

                        //Add MenuOption to options found for later
                        optionsFound.add(option);

                    });

            //+++++  END Method Stream  +++++


            //Once methodMap is loaded, add it to the register keyed by menuID
            internalModeRegister.put(menuAnnotation.value(), methodMap);

        }




        //___________________________________________________________________
        //_________ Load formatMap & internalFormatMap ______________________
        //___________________________________________________________________
        {


            //++++  Stream all available fields  ++++
            Arrays.stream(menuClass.getDeclaredFields())

                    //Filter only String fields with StringFormatter Annotation
                    .filter(field -> field.isAnnotationPresent(StringFormatter.class) && field.getType() == String.class && !formatMap.containsKey(field.getDeclaredAnnotation(StringFormatter.class).value()))

                    //Process each valid field
                    .forEach(field -> {

                        //Get the attached StringFormatter
                        StringFormatter formatter = field.getAnnotation(StringFormatter.class);

                        //Try to get the String value attached to the Annotated field
                        try {

                            //Attempt value grab
                            String format = (String) field.get(null);

                            //Puts value into format map under its formatter key
                            formatMap.put(formatter.value(), format);

                        } catch (IllegalAccessException ignored) {

                            //Caused by Mis-use of annotations on non-static fields
                        }
                    });

            //++++ End of Field Stream ++++

            //Once formatMap is loaded save it to register by MenuID
            internalFormatMap.put(menuAnnotation.value(), formatMap);

        }



        //___________________________________________________________________
        //____________ Load internalModeDisplayRegister _____________________
        //___________________________________________________________________
        {

            ArrayList<String> menuOptionDisplays = new ArrayList<>();

            //Sort optionsFound by Order
            optionsFound.sort(Comparator.comparing(MenuOption::order));

            //Process each option in order
            optionsFound.forEach(menuOption -> {

                //Get the format associated with the menu option
                String format = formatMap.get(menuOption.formatter());

                //Formats the menu options by specified formatter if any, then adds it to temp optionDisplay
                menuOptionDisplays.add(String.format(format != null ? format : "\n\t%s - %s", menuOption.key(), menuOption.description()));
            });

            //Once all values have been loaded save menuOptionDisplays to register by menuID
            internalModeDisplayRegister.put(menuAnnotation.value(), menuOptionDisplays);

        }



        //___________________________________________________________________
        //______________ Load internalMenuSelector __________________________
        //___________________________________________________________________
        {

            //Get the selector for the menu
            MenuSelector selector = menuClass.getDeclaredAnnotation(MenuSelector.class);

            //If valid save the selector in register by menuID
            if (selector != null) internalMenuSelectorRegister.put(menuAnnotation.value(), selector.value());

        }



        //___________________________________________________________________
        //_____________ Load internalMenuHeaderRegister _____________________
        //___________________________________________________________________
        {

            //Get the header
            MenuHeader header = menuClass.getAnnotation(MenuHeader.class);

            //exit early if there is no header for the class (REMOVE THIS IF YOU ADD ANY STEPS AFTER)
            if(header == null) return true;

            //Gets the formatter if any
            StringFormatter formatter = menuClass.getAnnotation(StringFormatter.class);

            //Gets the format if any
            String format = formatter != null? formatMap.get(formatter.value()): null;

            //Applies format if any or defaults, putting the result in the internalMenuHeaderRegister
            internalMenuHeaderRegister.put(menuAnnotation.value(), String.format(format != null? format : "%s", header.value()));

        }

        //Return Success
        return true;

    } //**** END of Method *****

//====================================================================================================================\\
//=========================================>    END OF REGISTER METHOD    <===========================================\\
//====================================================================================================================\\
//====================================================================================================================\\
//=========================================>  END OF MENU REGISTER CLASS  <===========================================\\
//====================================================================================================================\\
}
