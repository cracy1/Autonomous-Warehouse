package aw.comms;

import java.io.BufferedReader;
import java.io.IOException;

import aw.display.Display;
import aw.motion.Move;
import aw.motion.MoveExecutor;
import aw.robot.Robot;
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
				String[] actionSplit = StringUtils.splitBySpace(action);
				
				switch (actionSplit[0]) {
				case "f":
					moveExecutor.execute(Move.FORWARD);
					break;
				case "l":
					moveExecutor.execute(Move.LEFT_TURN);
					break;
				case "r":
					moveExecutor.execute(Move.RIGHT_TURN);
					break;
				case "t":
					moveExecutor.execute(Move.HALF_TURN);
					break;
				case "i":
					String item = actionSplit[1];
					int amount = Integer.parseInt(actionSplit[2]);
					moveExecutor.requestItem(item, amount);
					break;		
				}
				
				Robot.getRobotSender().sendMessage("c");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
