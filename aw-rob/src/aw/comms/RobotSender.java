package aw.comms;

import java.io.PrintStream;

/**
 * Used to send messages from the robot to the PC.
 */
public class RobotSender {
	private PrintStream ps;

	/**
	 * Creates an object for this class for a robot's PrintStream (data sent
	 * from the robot to PC).
	 * 
	 * @param ps
	 *            The robot's PrintStream
	 */
	public RobotSender(PrintStream ps) {
		this.ps = ps;
	}

	/**
	 * Sends a message from the robot to the PC.
	 * 
	 * @param string
	 *            The message to send
	 */
	public void sendMessage(String string) {
		ps.println(string);
		ps.flush();
	}
}
