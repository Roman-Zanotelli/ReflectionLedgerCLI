package com.pluralsight.old_cli.main.type.state;

import org.jetbrains.annotations.NotNull;

public abstract class Mode extends UserState.MenuOption {

    protected @NotNull UserState next_state;


    protected abstract void runMode() throws NoSuchMethodException;
    @Override
    protected final @NotNull UserState run() throws NoSuchMethodException {
        runMode();
        return new PressEnterToContinue(next_state);
    }



}
