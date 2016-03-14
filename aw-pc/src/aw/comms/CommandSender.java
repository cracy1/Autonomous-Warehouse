package aw.comms;

import java.io.PrintStream;

/**
 * Handles sending commands from the PC to the robot.
 */
public class CommandSender {
	private String name;
	private PrintStream ps;

	/**
	 * Creates an instance of the class for the PC output stream (PrintStream) to the robot.
	 * @param name The name of the robot
	 * @param ps The PC's PrintStream for a robot
	 */
	public CommandSender(String name, PrintStream ps) {
		this.name = name;
		this.ps = ps;
	}

	/**
	 * Send a command to the robot.
	 * @param action The command (action) you want to send
	 */
	public void sendCommand(String action) {
		ps.println(action);
		ps.flush();

		System.out.println("PC -> " + name + ": " + action);
	}
}
