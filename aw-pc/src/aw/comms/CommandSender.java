package aw.comms;

import java.io.PrintStream;

/**
 * Handles sending commands from the PC to the robot.
 */
public class CommandSender {
	private PrintStream ps;

	/**
	 * Creates an instance of CommandSender for a DataOutputStream, normally
	 * from a RobotConnection object.
	 * 
	 * @param dos
	 *            The DataOutputStream
	 */
	public CommandSender(PrintStream ps) {
		this.ps = ps;
	}

	/**
	 * Sends an action command to the robot (protocol 1).
	 * 
	 * @param action
	 *            The action to be sent to the robot: 'F' - Forwards 1 square,
	 *            'L' - Turn left at junction, 'R' - Turn right at junction
	 */
	public void sendCommand(String action) {
		ps.println(action);
		ps.flush();

		System.out.println("Sent action command: " + action);
	}
}
