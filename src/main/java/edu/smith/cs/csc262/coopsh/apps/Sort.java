package edu.smith.cs.csc262.coopsh.apps;

import edu.smith.cs.csc262.coopsh.InputLine;
import edu.smith.cs.csc262.coopsh.ShellEnvironment;
import edu.smith.cs.csc262.coopsh.Task;

import java.util.ArrayList;
import java.util.Collections;

public class Sort extends Task {

    private ArrayList<String> lines = new ArrayList<String>();;

    public Sort(ShellEnvironment env, String[] args) {
        super(env, args);
    }

    @Override
    protected void update() {
        InputLine line = this.input.poll();
        if (line == null) {
            // still waiting for more...
            return;
        }

        // only output and print when we've seen the whole file!
        if (line.isEndOfFile()) {
            System.out.println(lines);
            Collections.sort(lines);
            for (String oneLine:lines){
                System.out.println(oneLine);
            }
            this.closeOutput();
            this.exit(0);
            return;
        }

        // Otherwise, increment this count!
        lines.add(line.get());
    }

}
