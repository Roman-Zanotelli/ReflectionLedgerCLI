package com.pluralsight.old_cli.main.menu.ledger;

import com.pluralsight.old_cli.main.menu.home.HomeMenu;
import com.pluralsight.old_cli.main.type.state.Menu;
import com.pluralsight.old_cli.main.mode.ledger.display.DisplayAll;
import com.pluralsight.old_cli.main.mode.ledger.display.DisplayDeposits;
import com.pluralsight.old_cli.main.mode.ledger.display.DisplayPayments;

import java.lang.reflect.Constructor;

public final class LedgerMenu extends Menu {
    public LedgerMenu(){
        this.key = "L";
        this.desc = "Open Ledger Menu";
    }
    @Override
    protected void init() throws NoSuchMethodException {
        this.init_display_builder.append("Ledger Menu");
        this.constructors = new Constructor[]{
                DisplayAll.class.getDeclaredConstructor(),
                DisplayDeposits.class.getDeclaredConstructor(),
                DisplayPayments.class.getDeclaredConstructor(),
                Reports.class.getDeclaredConstructor(),
                HomeMenu.class.getDeclaredConstructor()
        };
        super.init(); //actually process options
    }
}
