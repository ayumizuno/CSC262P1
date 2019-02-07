package edu.smith.cs.csc262.coopsh.apps;

import edu.smith.cs.csc262.coopsh.InputLine;
import edu.smith.cs.csc262.coopsh.ShellEnvironment;
import edu.smith.cs.csc262.coopsh.Task;

/**
 * This Task mimics the UNIX grep utility without regex.
 *
 * @author amizuno
 *
 */
public class SimpleGrep extends Task {

    /**
     * This stores the pattern to be searched
     */
    String pattern;

    /**
     * This Task sets the pattern to be searched.
     *
     * @param args - command line arguments
     */
    public SimpleGrep(ShellEnvironment env, String[] args) {
        super(env, args);
        this.pattern = args[0];
    }

    /**
     * Go through each line and print the entire line
     * if the pattern matches in that line.
     */
    @Override
    protected void update() {
        InputLine line = this.input.poll();
        if (line == null) {
            return;
        }

        if (line.isEndOfFile()) {
            this.closeOutput();
            this.exit(0);
            return;
        }

        //print line if pattern found
        String oneLine = line.get();
        if (oneLine.contains(pattern)) {
            this.println(oneLine);
        }


    }
}
