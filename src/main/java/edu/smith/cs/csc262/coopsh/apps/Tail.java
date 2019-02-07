package edu.smith.cs.csc262.coopsh.apps;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import edu.smith.cs.csc262.coopsh.ShellEnvironment;
import edu.smith.cs.csc262.coopsh.Task;

/**
 * This Task mimics the UNIX Cat utility, but is cooperative; i.e., it knows it
 * has to give up control to other programs manually, so it only does a
 * bite-size piece of work in update.
 *
 * @author jfoley
 *
 */
public class Tail extends Task {
    /**
     * This is the state of this program; a BufferedReader.
     */
    private BufferedReader current = null;
    private Integer numLines;
    private ArrayList<String> lines = new ArrayList<String>();;

    /**
     * This Task reads a file.
     *
     * @param args - command line arguments!
     */
    public Tail(ShellEnvironment env, String[] args) {
        super(env, args);
        if (args.length != 2) {
            System.err.println("our ``tail`` only supports 2 argument!");
        }
        File input = env.makeFile(args[0]);
        numLines = Integer.parseInt(args[1]);
        try {
            this.current = new BufferedReader(new FileReader(input));
        } catch (FileNotFoundException e) {
            caughtFatalException("Could not open file!", e);
        }

    }

    /**
     * Do a bite-size piece of work.
     */
    @Override
    public void update() {
        // Case 2: Maybe we're not done:
        String next;
        try {
            next = current.readLine();
            lines.add(next);
        } catch (IOException e) {
            caughtFatalException("Reading file error!", e);
            return;
        }

        // Case 2A: We're done.
        if (next == null) {
            Integer i = lines.size()-numLines-1;
            while (numLines > 0){
                System.out.println(lines.get(i));
                numLines--;
                i++;
            }
            this.closeOutput();
            this.exit(0);;
            try {
                current.close();
            } catch (IOException e) {
                caughtFatalException("Could not close file!", e);
                return;
            }
            current = null;
        }
    }
}
