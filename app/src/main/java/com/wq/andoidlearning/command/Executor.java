package com.wq.andoidlearning.command;

public class Executor {
    private LeftCommand leftCommand;

    public Executor() {
        leftCommand = new LeftCommand();

    }

    public void onLeft() {
        leftCommand.execute();
    }
}
