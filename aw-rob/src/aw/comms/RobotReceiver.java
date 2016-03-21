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

/**
 * Receives commands sent from the PC to the robot.
 */
public class RobotReceiver extends Thread {
	private BufferedReader br;
	private MoveExecutor moveExecutor;
	private Display disp;

	/**
	 * Creates a new RobotReceiver object for a robot's BufferedReader (data
	 * received from PC).
	 * 
	 * @param br
	 *            The robot's BufferedReader
	 * @param robot
	 *            The robot object
	 */
	public RobotReceiver(BufferedReader br, DifferentialDriveRobot robot) {
		this.br = br;
		this.moveExecutor = new MoveExecutor(robot);
		this.disp = new Display();
	}

	/**
	 * The main loop of this class. When a command is received, it updates the
	 * display and executes the command on thet appropriate object. Once it is
	 * done, it sends a command back to the PC to signal it has completed its
	 * move and is ready for the next move.
	 */
	@Override
	public void run() {
		while (true) {
			try {
				//LCD.clear();
				String action = br.readLine();
				String[] actionSplit = StringUtils.splitBySpace(action);

				switch (actionSplit[0]) {
				case "f":
					moveExecutor.execute(Move.FORWARD);
					Robot.getRobotSender().sendMessage("f");
					break;
				case "l":
					moveExecutor.execute(Move.LEFT_TURN);
					Robot.getRobotSender().sendMessage("t");
					break;
				case "r":
					moveExecutor.execute(Move.RIGHT_TURN);
					Robot.getRobotSender().sendMessage("t");
					break;
				case "t":
					moveExecutor.execute(Move.HALF_TURN);
					Robot.getRobotSender().sendMessage("t");
					break;
				case "i":
					String item = actionSplit[1];
					int amount = Integer.parseInt(actionSplit[2]);
					moveExecutor.requestItem(item, amount);
					Robot.getRobotSender().sendMessage("r");
					break;
				case "d":
					item = actionSplit[1];
					amount = Integer.parseInt(actionSplit[2]);
					moveExecutor.dropRequest(item, amount, 1000);
					Robot.getRobotSender().sendMessage("d");
					break;
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
