package aw.comms;

import java.io.PrintStream;

/**
 * Handles sending commands from the PC to the robot.
 */
public class CommandSender {
	private PrintStream ps;

	public CommandSender(PrintStream ps) {
		this.ps = ps;
	}

	public void sendCommand(String action) {
		ps.println(action);
		ps.flush();

		System.out.println("Sent action command: " + action);
	}
}
