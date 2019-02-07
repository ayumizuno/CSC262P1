package edu.smith.cs.csc262.coopsh.apps;

import java.io.File;

import edu.smith.cs.csc262.coopsh.ShellEnvironment;
import edu.smith.cs.csc262.coopsh.Task;

public class ListFiles extends Task {

    File workingDir;

    public ListFiles(ShellEnvironment env, String[] args) {
        super(env, args);
        this.workingDir = env.currentDirectory;
    }

    @Override
    protected void update() {
        File[] files = workingDir.listFiles();
        for(File file:files) {
            // print only files and not directories
            if (file.isFile()) {
                System.out.println(file.getName());
            }
        }
        this.closeOutput();
        this.exit(0);
    }

}
