package edu.smith.cs.csc262.coopsh.apps;

import java.util.regex.*;
import edu.smith.cs.csc262.coopsh.InputLine;
import edu.smith.cs.csc262.coopsh.ShellEnvironment;
import edu.smith.cs.csc262.coopsh.Task;

/**
 * This Task mimics the UNIX grep utility with regex.
 *
 * @author amizuno
 *
 */
public class RegexGrep extends Task {
    /**
     * This stores the regex pattern to be searched.
     */
    Pattern pattern;

    /**
     * This Task sets the regex pattern to be searched.
     *
     * @param args - command line arguments
     */
    public RegexGrep(ShellEnvironment env, String[] args) {
        super(env, args);
        this.pattern = Pattern.compile(args[0]);
    }

    /**
     * Go through each line and print the entire line
     * if the pattern matches in that line.
     */
    @Override
    protected void update() {
        InputLine line = this.input.poll();
        Matcher matcher = null;

        if (line == null) {
            return;
        }

        if (line.isEndOfFile()) {
            this.closeOutput();
            this.exit(0);
            return;
        }

        //search for match in line
        String oneLine = line.get();
        matcher = pattern.matcher(oneLine);
        if (matcher.find()) {
            this.println(oneLine);
        }
    }
}
