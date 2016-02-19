package aw.robot;

import java.io.DataInputStream;

import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;

public class Robot {
	public static void main(String[] args) {
		System.out.println("Waiting for connection...");
		BTConnection connection = Bluetooth.waitForConnection();
		System.out.println("Connected!");
		
		DataInputStream dis = new DataInputStream(connection.openDataInputStream());
		
		RobotReceiver robotReceiver = new RobotReceiver(dis);
		robotReceiver.start();
	}
}
