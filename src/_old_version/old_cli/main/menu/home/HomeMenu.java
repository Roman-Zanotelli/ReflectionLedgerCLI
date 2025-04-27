package com.pluralsight.old_cli.main.menu.home;


import com.pluralsight.old_cli.main.menu.ledger.LedgerMenu;
import com.pluralsight.old_cli.main.type.state.Menu;
import com.pluralsight.old_cli.main.action.AddDeposit;
import com.pluralsight.old_cli.main.action.MakePayment;
import com.pluralsight.old_cli.main.mode.ExitProgram;

import java.lang.reflect.Constructor;

public final class HomeMenu extends Menu {
    public HomeMenu(){
        this.key = "H";
        this.desc = "Return to Home Menu";
    }

    @Override
    protected void init() throws NoSuchMethodException {
        this.init_display_builder.append("Home Menu");
        this.constructors = new Constructor[]{
                AddDeposit.class.getDeclaredConstructor(),
                MakePayment.class.getDeclaredConstructor(),
                LedgerMenu.class.getDeclaredConstructor(),
                ExitProgram.class.getDeclaredConstructor()
        };
        super.init(); //actually process options
    }
}
