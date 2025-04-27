package com.pluralsight.old_cli.debug;

import com.pluralsight.UserScanner;
import com.pluralsight.old_cli.main.menu.home.HomeMenu;
import com.pluralsight.old_cli.main.mode.ExitProgram;
import com.pluralsight.old_cli.main.type.state.Menu;
import com.pluralsight.old_cli.main.type.state.UserState;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

public final class MenuPanic extends UserState { //handles user options for when a menu panic occurs (mostly from retries_max being reached)
    private Constructor<? extends Menu> panickedMenu;
    public MenuPanic(Constructor<? extends Menu> panickedMenu){
        this.panickedMenu = panickedMenu;
    }
    @Override
    public @NotNull UserState run() throws Exception {
        System.out.print("Menu Panicked! Would you like to:\nR - Return to Last Menu\nH - Return to Home Menu\nOTHER - Exit Program\nEnter Selection: ");
        return (switch (UserScanner.getStringNoSpace().toLowerCase()) {
                    case "r" -> panickedMenu.newInstance(); //attempts a new instance of class
                    case "h" -> HomeMenu.class.getDeclaredConstructor().newInstance();
                    default -> ExitProgram.class.getDeclaredConstructor().newInstance();
                });
    }
}
