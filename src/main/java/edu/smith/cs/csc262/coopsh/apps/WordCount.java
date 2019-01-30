package edu.smith.cs.csc262.coopsh.apps;

import edu.smith.cs.csc262.coopsh.InputLine;
import edu.smith.cs.csc262.coopsh.ShellEnvironment;
import edu.smith.cs.csc262.coopsh.Task;

import java.io.UnsupportedEncodingException;

public class WordCount extends Task {
	int wordCount = 0;
	int numLines = 0;
	int numBytes = 0;

	public WordCount(ShellEnvironment env, String[] args) {
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
			this.println(wordCount);
			this.println(numLines);
			this.println(numBytes);
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

		}


	}
	
}
