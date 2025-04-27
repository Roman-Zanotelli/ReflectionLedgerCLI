package com.pluralsight.old_cli.main.type.state;

import com.pluralsight.old_cli.main.menu.DynamicMenu;
import com.pluralsight.old_cli.main.menu.home.HomeMenu;


public abstract class LedgerAction extends Mode {
    protected String dynHeader;
    @Override
    protected void runMode() throws NoSuchMethodException {
        this.next_state = new DynamicMenu(dynHeader, this.getClass().getDeclaredConstructor(), HomeMenu.class.getDeclaredConstructor());
    }
}
