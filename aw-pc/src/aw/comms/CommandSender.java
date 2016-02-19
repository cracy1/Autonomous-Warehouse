package aw.comms;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Handles sending commands from the PC to the robot.
 */
public class CommandSender {
	private DataOutputStream dos;

	/**
	 * Creates an instance of CommandSender for a DataOutputStream, normally
	 * from a RobotConnection object.
	 * 
	 * @param dos
	 *            The DataOutputStream
	 */
	public CommandSender(DataOutputStream dos) {
		this.dos = dos;
	}

	/**
	 * Sends an action command to the robot (protocol 1).
	 * 
	 * @param action
	 *            The action to be sent to the robot: 'F' - Forwards 1 square,
	 *            'L' - Turn left at junction, 'R' - Turn right at junction
	 */
	public void sendCommand(char action) {
		try {
			dos.writeInt(1);
			dos.writeChar(action);
			dos.flush();
			
			System.out.println("Sent action command: " + action);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
