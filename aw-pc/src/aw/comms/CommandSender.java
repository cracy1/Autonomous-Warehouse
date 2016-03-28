package aw.comms;

import java.io.PrintStream;

/**
 * Handles sending commands from the PC to the robot.
 */
public class CommandSender {
	private String name;
	private PrintStream ps;

	public CommandSender(String name, PrintStream ps) {
		this.name = name;
		this.ps = ps;
	}

	public void sendCommand(String action) {
		ps.println(action);
		ps.flush();

		System.out.println("PC -> " + name + ": " + action);
	}
}
