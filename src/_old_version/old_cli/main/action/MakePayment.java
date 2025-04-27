package com.pluralsight.old_cli.main.action;

import com.pluralsight.old_cli.main.type.state.LedgerAction;

public final class MakePayment extends LedgerAction {
    public MakePayment(){
        this.key = "P";
        this.desc = "Make A Payment";
        this.dynHeader = "Payment Complete, Would You like to Complete Another?";
    }
    @Override
    protected void runMode() throws NoSuchMethodException {
        System.out.println("YOU MADE A PAYMENT"); //TODO: REPLACE WITH ACTUAL PAYMENT LOGIC
        super.runMode();
    }

}
