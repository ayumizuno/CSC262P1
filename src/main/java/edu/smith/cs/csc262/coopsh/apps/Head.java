package edu.smith.cs.csc262.coopsh.apps;

import edu.smith.cs.csc262.coopsh.InputLine;
import edu.smith.cs.csc262.coopsh.ShellEnvironment;
import edu.smith.cs.csc262.coopsh.Task;

/**
 * This Task mimics the UNIX Head utility.
 *
 * @author amizuno
 *
 */
public class Head extends Task {
    /**
     * This number stores number of lines to print.
     */
    private Integer numLines;

    /**
     * This Task stores the number of lines to print.
     *
     * @param args - command line arguments
     */
    public Head(ShellEnvironment env, String[] args) {
        super(env, args);
        numLines = Integer.parseInt(args[0]);
    }

    /**
     * Print out lines while keeping track of
     * number of lines printed.
     */
    @Override
    public void update() {
        InputLine line = this.input.poll();
        if (line == null) {
            return;
        }
        if (numLines == 0 || line.isEndOfFile()) {
            this.closeOutput();
            this.exit(0);
            return;
        }
        this.println(line.get());
        numLines--;
    }
}
