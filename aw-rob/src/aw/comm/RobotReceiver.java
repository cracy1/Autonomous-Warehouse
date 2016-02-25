package aw.comm;

import java.io.BufferedReader;
import java.io.IOException;

import aw.motion.Move;
import aw.motion.MoveExecutor;
import aw.robot.Display;
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
				String action = br.readLine();

				switch (action) {
				case "f":
					moveExecutor.addMove(Move.FORWARD);
					break;
				case "l":
					moveExecutor.addMove(Move.LEFT_TURN);
					break;
				case "r":
					moveExecutor.addMove(Move.RIGHT_TURN);
					break;
				case "t":
					moveExecutor.addMove(Move.HALF_TURN);
					break;
				case "i":
					disp.requestItem("Cat", 5);
					break;

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
