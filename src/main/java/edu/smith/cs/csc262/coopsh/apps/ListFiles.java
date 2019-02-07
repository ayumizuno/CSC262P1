package edu.smith.cs.csc262.coopsh.apps;

import java.io.File;

import edu.smith.cs.csc262.coopsh.ShellEnvironment;
import edu.smith.cs.csc262.coopsh.Task;

/**
 * This Task mimics the UNIX ls utility.
 *
 * @author amizuno
 *
 */
public class ListFiles extends Task {
    /**
     * This stores the current directory..
     */
    File workingDir;

    /**
     * This Task sets the current directory.
     *
     * @param args - command line arguments
     */
    public ListFiles(ShellEnvironment env, String[] args) {
        super(env, args);
        this.workingDir = env.currentDirectory;
    }

    /**
     * Store all files in directory in an array and
     * loop over them to print their names.
     */
    @Override
    protected void update() {
        File[] files = workingDir.listFiles();
        for(File file:files) {
            // print only files and not directories
            if (file.isFile()) {
                this.println(file.getName());
            }
        }
        this.closeOutput();
        this.exit(0);
    }

}
