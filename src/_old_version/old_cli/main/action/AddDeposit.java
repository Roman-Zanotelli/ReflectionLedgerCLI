package com.pluralsight.old_cli.main.action;

import com.pluralsight.old_cli.main.type.state.LedgerAction;

public final class AddDeposit extends LedgerAction {
    public AddDeposit(){
        this.key = "D";
        this.desc = "Make A Deposit";
        this.dynHeader = "Deposit Complete, Would You like to Complete Another?";
    }
    @Override
    protected void runMode() throws NoSuchMethodException {
        System.out.println("YOU MADE A DEPOSIT!"); //TODO: REPLACE WITH ACTUAL DEPOSIT LOGIC
        super.runMode();
    }

}
