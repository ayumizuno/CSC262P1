package edu.smith.cs.csc262.coopsh.apps;

import edu.smith.cs.csc262.coopsh.InputLine;
import edu.smith.cs.csc262.coopsh.ShellEnvironment;
import edu.smith.cs.csc262.coopsh.Task;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This Task mimics the UNIX Sort utility.
 *
 * @author amizuno
 *
 */
public class Sort extends Task {

    /**
     * This stores all of the input lines.
     */
    private ArrayList<String> lines;

    /**
     * This Task initializes the arraylist to store all of
     * the lines.
     *
     * @param args - command line arguments!
     */
    public Sort(ShellEnvironment env, String[] args) {
        super(env, args);
        lines = new ArrayList<String>();
    }

    /**
     * Adds each line to the arraylist and
     * prints out the arraylist after sorting.
     */
    @Override
    protected void update() {
        InputLine line = this.input.poll();
        if (line == null) {
            return;
        }

        if (line.isEndOfFile()) {
            Collections.sort(lines);
            for (String oneLine:lines){
                this.println(oneLine);
            }
            this.closeOutput();
            this.exit(0);
            return;
        }
        //add line to array
        lines.add(line.get());
    }
}
