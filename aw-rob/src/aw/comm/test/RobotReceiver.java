package aw.comm.test;

import java.io.BufferedReader;
import java.io.IOException;

import aw.motion.Move;
import aw.motion.MoveExecutor;
import aw.robot.Display;
import aw.utils.StringUtils;
import lejos.nxt.LCD;
import rp.robotics.DifferentialDriveRobot;

public class RobotReceiver extends Thread {
	private BufferedReader br;
	private MoveExecutor moveExecutor;
	private Display disp;

	public RobotReceiver(BufferedReader br, DifferentialDriveRobot robot) {
		this.br = br;
		this.moveExecutor = new MoveExecutor(robot);
		this.disp = new Display();
	}

	@Override
	public void run() {
		while (true) {
			try {
				LCD.clear();
				String action = br.readLine();
										
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
