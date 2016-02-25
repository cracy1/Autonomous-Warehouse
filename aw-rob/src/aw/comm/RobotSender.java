package aw.comm;

import java.io.PrintStream;

public class RobotSender {
	private PrintStream ps;
	
	public RobotSender(PrintStream ps) {
		this.ps = ps;
	}
	
	public void TESTsendString(String string) {
		ps.println(string);
	}
}
