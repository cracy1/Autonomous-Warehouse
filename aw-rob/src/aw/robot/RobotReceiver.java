package aw.robot;

import java.io.DataInputStream;
import java.io.IOException;

import aw.motion.Move;
import aw.motion.MoveExecutor;
import rp.robotics.DifferentialDriveRobot;

public class RobotReceiver extends Thread {
	private DataInputStream dis;
	private MoveExecutor moveExecutor;
	
	public RobotReceiver(DataInputStream dis, DifferentialDriveRobot robot) {
		this.dis = dis;
		this.moveExecutor = new MoveExecutor(robot);
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				char action = dis.readChar();
				
				switch(action){
				case 'f':
					moveExecutor.addMove(Move.FORWARD);
					break;
				case 'l':
					moveExecutor.addMove(Move.LEFT_TURN);
					break;
				case 'r':
					moveExecutor.addMove(Move.RIGHT_TURN);
					break;
				}
				
				//int protocol = dis.readInt();

//				switch (protocol) {
//					case 1:
//						char action = dis.readChar();
//						System.out.println(action);
//						break;
//				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
