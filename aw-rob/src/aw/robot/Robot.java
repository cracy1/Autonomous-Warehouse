package aw.robot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

import aw.comms.RobotReceiver;
import aw.comms.RobotSender;
import aw.config.RobotConfigs;
import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;
import rp.robotics.DifferentialDriveRobot;

/**
 * The main class of the robot.
 */
public class Robot {
	private static RobotSender robotSender;

	/**
	 * The main loop of the robot. It waits for a bluetooth connection, then
	 * initialises the connections and the appropriate classes.
	 * 
	 * @param args Command-line arguments
	 */
	public static void main(String[] args) {
		System.out.println("Waiting for connection...");
		BTConnection connection = Bluetooth.waitForConnection();
		System.out.println("Connected!");

		BufferedReader br = new BufferedReader(new InputStreamReader(connection.openInputStream()));
		PrintStream ps = new PrintStream(connection.openOutputStream());

		DifferentialDriveRobot robot = new DifferentialDriveRobot(RobotConfigs.CASTOR_BOT);

		RobotReceiver robotReceiver = new RobotReceiver(br, robot);
		robotReceiver.start();

		robotSender = new RobotSender(ps);
		robotSender.sendMessage("Robot->PC message");
	}

	/**
	 * Gets the RobotSender for the robot to send messages from the robot to the
	 * PC.
	 * 
	 * @return The robot sender object
	 */
	public static RobotSender getRobotSender() {
		return robotSender;
	}
}
