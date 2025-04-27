package com.pluralsight;

import com.pluralsight.old_cli.main.menu.home.HomeMenu;
import com.pluralsight.old_cli.main.type.state.UserState;

public final class AccountingLedgerApplication {
    //Suppress infinite loop warning, System will exit through ExitProgram mode via System.exit(0) (or in the event of an unexpected error)
    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) {
        UserState next_state = new HomeMenu();
        try {
            while(true){
                next_state = next_state.start();
            }
        }catch(Exception e){
            System.out.println("An Unexpected Error Occurred!");
            System.out.println(e); //todo: log this error instead of just printing
        }
        UserScanner.close(); //closes scanner in the edge case an unexpected error is thrown
        System.out.println("GoodBye! @_@");
        System.exit(1); //this isn't ever supposed to be reached unless there is a truly unexpected error
    }
}
