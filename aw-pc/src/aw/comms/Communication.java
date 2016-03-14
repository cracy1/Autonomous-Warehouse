package aw.comms;

import java.util.HashMap;
import java.util.Map;

import lejos.pc.comm.NXTInfo;

/**
 * Handles setting up usage of communication between the PC and the NXT robots.
 */
public class Communication {
	private static Map<String, RobotConnection> robotConnections = new HashMap<>();

	/**
	 * Initialises the connections for all robots.
	 */
	public static void addRobots() {
		for (NXTInfo nxt : BluetoothRobots.NXTs) {
			addRobotConnection(nxt);
		}

		System.out.println("All robots connected");
	}

	/**
	 * Adds a new robot connection for the NXT information passed in.
	 * 
	 * @param nxt
	 *            The NXT information representing the robot and it's bluetooth
	 *            information
	 */
	public static void addRobotConnection(NXTInfo nxt) {
		RobotConnection robotConnection = new RobotConnection(nxt);
		robotConnection.connect();

		robotConnections.put(nxt.name, robotConnection);
	}

	/**
	 * Gets the robot connection object for a named robot.
	 * 
	 * @param name
	 *            The robot's name
	 * @return The robot's connection object, giving access to the command
	 *         sender and receiver
	 */
	public static RobotConnection getRobotConnection(String name) {
		return robotConnections.get(name);
	}

	/**
	 * For compatibility, old method to get the command sender of a named robot.
	 * 
	 * @param name
	 *            The robot's name
	 * @return The command sender for the robot
	 */
	@Deprecated
	public static CommandSender getCommandSender(String name) {
		return getRobotConnection(name).getCommandSender();
	}
}
