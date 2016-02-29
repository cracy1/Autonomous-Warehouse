package aw.robot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

import aw.comm.RobotReceiver;
import aw.comm.RobotSender;
import aw.config.RobotConfigs;
import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;
import rp.robotics.DifferentialDriveRobot;

public class Robot {
	private static RobotSender robotSender;
	
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
	
	public static RobotSender getRobotSender() {
		return robotSender;
	}
}
