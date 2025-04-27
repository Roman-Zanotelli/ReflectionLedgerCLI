package com.pluralsight.old_cli.main.type.state;

import com.pluralsight.UserScanner;
import org.jetbrains.annotations.NotNull;

public abstract class UserState {
    protected boolean skip_init, skip_init_display; //indicates whether the state loop should skip init() and the initial display (default false)
    public final @NotNull UserState start() throws Exception { //final implementation for
        if (!skip_init) this.init();
        if (!skip_init_display && !init_display_builder.isEmpty()) System.out.println(this); //print out the UserState Startup Display if valid
        return this.run();
    }


    protected final @NotNull StringBuilder init_display_builder = new StringBuilder(); //handles initial display value


    protected void init() throws NoSuchMethodException {} //empty definition allowing optional override
    protected abstract @NotNull UserState run() throws NoSuchMethodException, Exception; //abstract definition requiring subclass implementation


    @Override
    public final @NotNull String toString(){ //final implementation used by all UserStates
        return init_display_builder.toString(); //gets the init_display_builders to string (checked by start)
    }


    //Wrapper used by All User States that can be accessed through a menu including menus themselves
    protected static abstract class MenuOption extends UserState {
        protected @NotNull String desc; //defines the description inside a menu

        protected @NotNull String key;

        //optional overrides for menu option display
        public @NotNull MenuOption overrideMenuOption(String key, String desc){ //overrides both desc and key
            if(desc != null) this.desc = desc;
            if(key != null) this.key = key;
            return this;
        }
        @NotNull
        public MenuOption overrideMenuOptionDesc(@NotNull String desc){
            this.desc = desc;
            return this;
        }
        public @NotNull MenuOption overrideMenuOptionKey(@NotNull String key){
            this.key = key;
            return this;
        }
    }

    //User State Logic for All Press Enter To Continue Components
    protected static final class PressEnterToContinue extends UserState { //final, no class should extend from this just wrapped by it
        @NotNull UserState next; //indicates the next state this
        protected PressEnterToContinue(@NotNull UserState next){
            this.next = next;
            this.init_display_builder.append("Press Enter to Continue");
        }

        @Override
        protected @NotNull UserState run() {
            UserScanner.getEnter(); //waits for enter
            return next; //return next state unwrapped
        }
    }
}


