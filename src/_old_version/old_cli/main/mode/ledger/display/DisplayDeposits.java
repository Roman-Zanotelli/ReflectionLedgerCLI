package com.pluralsight.old_cli.main.mode.ledger.display;

import com.pluralsight.old_cli.main.menu.DynamicMenu;
import com.pluralsight.old_cli.main.menu.home.HomeMenu;
import com.pluralsight.old_cli.main.menu.ledger.LedgerMenu;
import com.pluralsight.old_cli.main.type.state.Mode;
import com.pluralsight.Ledger;

public final class DisplayDeposits extends Mode {
    public DisplayDeposits(){
        this.key = "D";
        this.desc = "Display Deposits";
    }
    @Override
    protected void init(){
        this.init_display_builder.append("Displaying All Deposits:\n")
                .append(Ledger.getDeposits());

    }

    @Override
    protected void runMode() throws NoSuchMethodException {
        next_state = new DynamicMenu(
                "\nWould You Like To:",
                DisplayPayments.class.getDeclaredConstructor(),
                DisplayAll.class.getDeclaredConstructor(),
                LedgerMenu.class.getDeclaredConstructor(),
                HomeMenu.class.getDeclaredConstructor()
        );
    }
}
