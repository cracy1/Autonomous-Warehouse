package aw.robot;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;

public class Robot {
	public static void main(String[] args) {
		BTConnection connection = Bluetooth.waitForConnection();
		
		DataInputStream dis = new DataInputStream(connection.openDataInputStream());
		DataOutputStream dos = new DataOutputStream(connection.openDataOutputStream());
		
		RobotReceiver robotReceiver = new RobotReceiver(dis);
		robotReceiver.start();
	}
}
