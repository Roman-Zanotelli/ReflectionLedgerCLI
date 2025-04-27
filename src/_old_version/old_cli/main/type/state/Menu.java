package com.pluralsight.old_cli.main.type.state;

import com.pluralsight.UserScanner;
import com.pluralsight.old_cli.debug.MenuPanic;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Menu extends UserState.MenuOption {
    protected @NotNull Constructor<? extends MenuOption>[] constructors;
    private @NotNull List<MenuOption> options; //menu options

    static private final int retries_max = 3; //max menu retries before panic


    @Override
    protected void init() throws NoSuchMethodException {
        options = Arrays.stream(constructors).map(constructor -> { //this is actual magic
           try{
               return constructor.newInstance(); //basically we are creating an instance of the class's reflection
           } catch (Exception ignored) {
               return null; //shouldn't happen but if it does return null
           }
        }).collect(Collectors.toList());
        constructors = null; //clears constructors array
        options.forEach(option -> init_display_builder.append(String.format("\n\t%s - %s", option.key, option.desc)));
    }

    @Override
    final public @NotNull UserState run() throws NoSuchMethodException { //handles logic for all menus
        for(int retries = -1; retries < retries_max; retries++){ //attempts selections with 3 retries
            System.out.print(retries == -1? "Please Enter Selection: " : String.format("Invalid Selection, Retries Left: %d\nPlease Enter Selection: ", retries_max-retries)); //prompts normally first then alerts users of retries
            String selection = UserScanner.getStringNoSpace();
            for (MenuOption option : options){
                if(option.key.equalsIgnoreCase(selection)) return option;
            }
        }
        return new MenuPanic(this.getClass().getDeclaredConstructor()); //panics if max retries are reached (just in-case)
    }
}

