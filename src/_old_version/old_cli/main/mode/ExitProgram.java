package com.pluralsight.old_cli.main.mode;

import com.pluralsight.old_cli.main.type.state.Mode;

public final class ExitProgram extends Mode {
    public ExitProgram(){
        this.key = "X";
        this.desc = "Exit Program";
    }
    @Override
    protected void runMode() {
        System.out.println("\nGoodbye! <3_<3");
        System.exit(0);
    }
}
