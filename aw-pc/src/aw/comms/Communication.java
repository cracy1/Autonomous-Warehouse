package aw.comms;

import java.util.HashMap;
import java.util.Map;

import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommException;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTInfo;

/**
 * Handles setting up usage of communication between the PC and the NXT robots.
 */
public class Communication {
	private static Map<String, RobotConnection> robotConnections = new HashMap<>();

	/**
	 * Initialises all of the warehouse robots connections.
	 */
	public static void addRobots() {
		for (NXTInfo nxt : BluetoothRobots.NXTs) {
			addRobotConnection(nxt);
		}

		System.out.println("All robots connected");
	}

	/**
	 * Initialises a robot connection and adds the CommandSender object of the
	 * connection's DataOutputStream to a HashMap for use to send commands to a
	 * robot.
	 * 
	 * @param nxtComm
	 *            The NXTComm object
	 * @param nxt
	 *            The NXTInfo describing the robot's information
	 * @throws NXTCommException
	 */
	public static void addRobotConnection(NXTInfo nxt) {
		RobotConnection robotConnection = new RobotConnection(nxt);
		robotConnection.connect();

		robotConnections.put(nxt.name, robotConnection);
	}

	/**
	 * Get the CommandSender object of a named robot
	 * 
	 * @param name
	 *            The robot's name
	 * @return The CommandSender object
	 */
	public static RobotConnection getRobotConnection(String name) {
		return robotConnections.get(name);
	}

	@Deprecated
	public static CommandSender getCommandSender(String name) {
		return getRobotConnection(name).getCommandSender();
	}
}
