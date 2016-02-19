package aw.comms;

import java.util.HashMap;

import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommException;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTInfo;

/**
 * Handles setting up usage of communication between the PC and the NXT robots.
 */
public class Communication {
	private static HashMap<String, CommandSender> commandSenders = new HashMap<>();

	/**
	 * Initialises all of the warehouse robots connections.
	 */
	public static void addRobots() {
		try {
			NXTComm nxtComm = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);

			NXTInfo[] nxts = { /*BluetoothRobots.NXT_RICARDO,*/ BluetoothRobots.NXT_I };

			for (NXTInfo nxt : nxts) {
				addRobotConnection(nxtComm, nxt);
			}
			
			System.out.println("All robots connected");
		} catch (NXTCommException e) {
			e.printStackTrace();
		}
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
	public static void addRobotConnection(NXTComm nxtComm, NXTInfo nxt) throws NXTCommException {
		RobotConnection robotConnection = new RobotConnection(nxt);
		robotConnection.connect(nxtComm);

		CommandSender commandSender = new CommandSender(robotConnection.getDataOutputStream());
		commandSenders.put(nxt.name, commandSender);
		
		System.out.println("Robot '" + nxt.name + "' has been initalised");
	}

	/**
	 * Get the CommandSender object of a named robot
	 * 
	 * @param name
	 *            The robot's name
	 * @return The CommandSender object
	 */
	public static CommandSender getCommandSender(String name) {
		return commandSenders.get(name);
	}
}
