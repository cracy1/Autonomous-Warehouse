package aw.robot;

import java.io.DataInputStream;

import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;
import aw.config.RobotConfigs;
import rp.robotics.DifferentialDriveRobot;

public class Robot {
	public static void main(String[] args) {
		System.out.println("Waiting for connection...");
		BTConnection connection = Bluetooth.waitForConnection();
		System.out.println("Connected!");
		
		DataInputStream dis = new DataInputStream(connection.openDataInputStream());
		DifferentialDriveRobot robot = new DifferentialDriveRobot(RobotConfigs.CASTOR_BOT);
		
		RobotReceiver robotReceiver = new RobotReceiver(dis, robot);
		robotReceiver.start();
	}
}
