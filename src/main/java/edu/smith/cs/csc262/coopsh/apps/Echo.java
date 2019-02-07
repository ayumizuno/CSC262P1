package edu.smith.cs.csc262.coopsh.apps;

import edu.smith.cs.csc262.coopsh.ShellEnvironment;
import edu.smith.cs.csc262.coopsh.Task;

/**
 * This Task mimics the UNIX Echo utility.
 *
 * @author amizuno
 *
 */
public class Echo extends Task {
    /**
     * Command line arguments to be echoed.
     */
    String[] args;

    /**
     * This Task stores the arguments.
     *
     * @param args - command line arguments!
     */
    public Echo(ShellEnvironment env, String[] args) {
        super(env, args);
        this.args = args;
    }

    /**
     * Print out the command line arguments.
     */
    @Override
    protected void update() {
        for(String arg: this.args){
            this.println(arg);
        }
        this.closeOutput();
        this.exit(0);
    }

}