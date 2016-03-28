package aw.comms;

import java.io.PrintStream;

public class RobotSender {
	private PrintStream ps;
	
	public RobotSender(PrintStream ps) {
		this.ps = ps;
	}
	
	public void sendMessage(String string) {
		ps.println(string);
		ps.flush();
	}
}
