package aw.comms;

/**
 * Used to add a listener which receives information from the robot.
 */
public interface BluetoothCommandListener {
	/**
	 * Called when the robot sends a command to the PC.
	 * 
	 * @param name
	 *            The name of the robot
	 * @param command
	 *            The command sent
	 */
	void commandReceived(String name, String command);
}
