package edu.smith.cs.csc262.coopsh.apps;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.*;

import edu.smith.cs.csc262.coopsh.ShellEnvironment;
import edu.smith.cs.csc262.coopsh.Task;

public class RegexGrep extends Task {

    File file;
    Pattern pattern;

    public RegexGrep(ShellEnvironment env, String[] args) {
        super(env, args);
        File workingDir = env.currentDirectory;
        String pathname = workingDir.getAbsolutePath()+ "/" +args[1];
        System.out.println(pathname);
        this.file = new File(pathname);
        this.pattern = Pattern.compile(args[0]);
    }

    @Override
    protected void update() {
        Scanner sc = null;
        Matcher matcher = null;
        try {
            sc = new Scanner(file);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                matcher = pattern.matcher(line);
                if (matcher.find()) {
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
