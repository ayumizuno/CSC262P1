package edu.smith.cs.csc262.coopsh.apps;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.smith.cs.csc262.coopsh.ShellEnvironment;
import edu.smith.cs.csc262.coopsh.Task;

public class SimpleGrep extends Task {

    File file;
    String key;

    public SimpleGrep(ShellEnvironment env, String[] args) {
        super(env, args);
        File workingDir = env.currentDirectory;
        String pathname = workingDir.getAbsolutePath()+ "/" +args[1];
        System.out.println(pathname);
        this.file = new File(pathname);
        this.key = args[0];
    }

    @Override
    protected void update() {
        Scanner sc = null;
        try {
            sc = new Scanner(file);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                if (line.contains(key)) {
                    System.out.println(line);
                }
            }
        } catch (FileNotFoundException e){
            System.out.println("invalid file name");
        }
        this.closeOutput();
        this.exit(0);
    }

}
