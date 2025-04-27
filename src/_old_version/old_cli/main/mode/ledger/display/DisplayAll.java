package com.pluralsight.old_cli.main.mode.ledger.display;

import com.pluralsight.old_cli.main.menu.DynamicMenu;
import com.pluralsight.old_cli.main.menu.home.HomeMenu;
import com.pluralsight.old_cli.main.menu.ledger.LedgerMenu;
import com.pluralsight.old_cli.main.type.state.Mode;
import com.pluralsight.Ledger;

public final class DisplayAll extends Mode {
    public DisplayAll(){
        this.key = "A";
        this.desc = "Display All Transactions";
    }
    @Override
    protected void init(){
        this.init_display_builder.append("Displaying All Entries:\n")
                .append(Ledger.getInstance());

    }

    @Override
    protected void runMode() throws NoSuchMethodException {
        next_state = new DynamicMenu(
                "\nWould You Like To:",
                DisplayDeposits.class.getDeclaredConstructor(),
                DisplayPayments.class.getDeclaredConstructor(),
                LedgerMenu.class.getDeclaredConstructor(),
                HomeMenu.class.getDeclaredConstructor()
        );
    }
}
