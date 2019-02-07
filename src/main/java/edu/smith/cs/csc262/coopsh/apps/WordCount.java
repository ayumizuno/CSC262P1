package edu.smith.cs.csc262.coopsh.apps;

import edu.smith.cs.csc262.coopsh.InputLine;
import edu.smith.cs.csc262.coopsh.ShellEnvironment;
import edu.smith.cs.csc262.coopsh.Task;

import java.io.UnsupportedEncodingException;

/**
 * This Task mimics the UNIX wc utility and prints out
 * number of words, number of lines, and number of bytes.
 *
 * @author jfoley
 * edited by amizuno
 *
 */
public class WordCount extends Task {
	/**
	 * These store the number of words,
	 * number of lines,
	 * and number of bytes.
	 */
	int wordCount = 0;
	int numLines = 0;
	int numBytes = 0;

	/**
	 * This Task counts words, lines, and bytes.
	 *
	 * @param args - command line arguments
	 */
	public WordCount(ShellEnvironment env, String[] args) {
		super(env, args);
	}

	/**
	 * Takes each line and increments number of words, lines, and bytes
	 * until all lines have been polled. 
	 */
	@Override
	protected void update() {
		InputLine line = this.input.poll();
		if (line == null) {
			// still waiting for more...
			return;
		}
		
		// only output and print when we've seen the whole file!
		if (line.isEndOfFile()) {
			this.println(wordCount + " " + numLines + " " + numBytes);
			this.closeOutput();
			this.exit(0);
			return;
		}
		
		// Otherwise, increment this count!
		String oneLine = line.get();
		wordCount += oneLine.split("\\s+").length;
		numLines += 1;
		try {
			byte[] lineBytes = oneLine.getBytes("UTF-8");
			numBytes += lineBytes.length;
		} catch(UnsupportedEncodingException e){
			System.err.println(e.getMessage());
		}
	}
}
