package aw.comms.commtest;

import java.io.DataInputStream;
import java.io.IOException;

import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;

public class CommsTestRobot {
	public static void main(String[] args) {
		System.out.println("Waiting for connection...");
		BTConnection connection = Bluetooth.waitForConnection();
		System.out.println("Connected!");
		
		DataInputStream dis = connection.openDataInputStream();
		
		boolean run = true;
		
		while (run) {
			try {
				int input = dis.readInt();
				System.out.println("Received: " + input);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
