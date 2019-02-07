package edu.smith.cs.csc262.coopsh.apps;

import java.io.File;

import edu.smith.cs.csc262.coopsh.ShellEnvironment;
import edu.smith.cs.csc262.coopsh.Task;

public class Echo extends Task {

    String[] args;

    public Echo(ShellEnvironment env, String[] args) {
        super(env, args);
        this.args = args;
    }

    @Override
    protected void update() {
        for(String arg: this.args){
            System.out.print(arg + " ");
        }
        System.out.println();
        this.closeOutput();
        this.exit(0);
    }

}