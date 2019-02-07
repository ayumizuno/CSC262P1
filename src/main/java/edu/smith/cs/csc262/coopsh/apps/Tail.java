package edu.smith.cs.csc262.coopsh.apps;

import java.util.ArrayList;

import edu.smith.cs.csc262.coopsh.InputLine;
import edu.smith.cs.csc262.coopsh.ShellEnvironment;
import edu.smith.cs.csc262.coopsh.Task;

/**
 * This Task mimics the UNIX Tail utility.
 *
 * @author amizuno
 *
 */
public class Tail extends Task {
    /**
     * This stores the number of lines to print out.
     */
    private Integer numLines;
    /**
     * This arraylist stores all of the lines.
     */
    private ArrayList<String> lines;

    /**
     * This Task sets the number of lines to be printed
     * and initializes the arraylist.
     *
     * @param args - command line arguments.
     */
    public Tail(ShellEnvironment env, String[] args) {
        super(env, args);
        numLines = Integer.parseInt(args[0]);
        lines = new ArrayList<String>();
    }

    /**
     * Add each line to the arraylist and print out
     * the last numLines of the arraylist at the end.
     */
    @Override
    public void update() {
        InputLine line = this.input.poll();
        if (line == null){
            return;
        }

        if (line.isEndOfFile()) {
            for (int i = lines.size()-numLines; i < lines.size(); i++){
                this.println(lines.get(i));
            }
            this.closeOutput();
            this.exit(0);
            return;
        }

        lines.add(line.get());

    }
}
