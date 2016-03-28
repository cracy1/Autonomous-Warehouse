package aw.comms.test;

import java.util.ArrayList;
import java.util.Scanner;

import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommException;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTInfo;

public class CommsTest {
	public static void main(String[] args) {
		// Our 2 robots
		NXTInfo[] nxts = { new NXTInfo(NXTCommFactory.BLUETOOTH, "Ricardo", "0016531B5974"),
				new NXTInfo(NXTCommFactory.BLUETOOTH, "NXT", "0016530C73B0") };

		// Store robot connections in an array list to access
		ArrayList<RobConnection> connections = new ArrayList<>();

		// Initialise the robot connection objects for each robot and add them to the array list
		for (NXTInfo nxt : nxts) {
			connections.add(new RobConnection(nxt));
		}

		System.out.println("Attemping to connect to " + nxts.length + " robots");

		// Attempt to connect to each robot
		for (RobConnection robConn : connections) {
			robConn.connect();
		}

		System.out.println("Robot connections complete");

		System.out.println("Enter a number to send to all robots");

		Scanner scanner = new Scanner(System.in);

		// Send the integer input in the console to each robot
		while (true) {
			int input = scanner.nextInt();

			for (RobConnection robConn : connections) {
				robConn.send(input);
				System.out.println(input + " sent to '" + robConn.getName() + "'");
			}
		}
	}
}
