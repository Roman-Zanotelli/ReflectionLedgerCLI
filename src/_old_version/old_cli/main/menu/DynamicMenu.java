package com.pluralsight.old_cli.main.menu;

import com.pluralsight.old_cli.main.type.state.Menu;

import java.lang.reflect.Constructor;

public final class DynamicMenu extends Menu { //careful this menu should not be used as a menu option
    @SafeVarargs
    public DynamicMenu(String header, Constructor<? extends MenuOption>... constructors){
        this.init_display_builder.append(header);
        this.constructors = constructors;
    }
}
