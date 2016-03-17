package aw.comms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommException;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTInfo;

/**
 * Handles the connection between the PC and the NXT robot and creates the data
 * streams.
 */
public class RobotConnection {
	private NXTInfo nxt;

	private CommandSender cs;
	private CommandReceiver cr;

	/**
	 * Creates an instance of RobotConnection for a specific NXT robot.
	 * 
	 * @param nxt
	 *            An NXTInfo object defining a robot's bluetooth information
	 */
	public RobotConnection(NXTInfo nxt) {
		this.nxt = nxt;
	}

	/**
	 * Initialises the connection between the PC and the robot contained in this
	 * class.
	 */
	public void connect() {
		try {
			NXTComm nxtComm = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);

			if (nxtComm.open(nxt)) {
				BufferedReader br = new BufferedReader(new InputStreamReader(nxtComm.getInputStream()));
				PrintStream ps = new PrintStream(nxtComm.getOutputStream());

				cs = new CommandSender(nxt.name, ps);

				cr = new CommandReceiver(nxt.name, br);
				cr.start();

				System.out.println("Robot '" + nxt.name + "' connected");
			}
		} catch (NXTCommException e) {
			System.err.println("Failed to connect to robot '" + nxt.name + "'");
		}
	}

	/**
	 * Gets the command sender to send messages from the PC to the robot.
	 * 
	 * @return The command sender
	 */
	public CommandSender getCommandSender() {
		return cs;
	}

	/**
	 * Gets the command receiver to allow you to add a listener to receive
	 * information from the robot
	 * 
	 * @return The command receiver
	 */
	public CommandReceiver getCommandReceiver() {
		return cr;
	}
}
