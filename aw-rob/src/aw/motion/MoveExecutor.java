package aw.motion;

import java.util.Random;

import aw.config.RobotConfigs;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;
import rp.robotics.DifferentialDriveRobot;

public class MoveExecutor implements Runnable {
	private DifferentialDriveRobot robot;
	private DifferentialPilot pilot;
	
	private Route route;
	private Move currentMove;
	
	private Random ra;
	
	LightSensor leftLightSensor, rightLightSensor;
	
	float gain = 15.0f;
	float maxSpeed = Motor.A.getMaxSpeed();
	
	public MoveExecutor(DifferentialDriveRobot robot){
		robot = new DifferentialDriveRobot(RobotConfigs.CASTOR_BOT);
		pilot = robot.getDifferentialPilot();
		leftLightSensor = new LightSensor(SensorPort.S3);
		rightLightSensor = new LightSensor(SensorPort.S4);
		
		route = new Route(); //create a new route.
		
		ra = new Random();
		
		route.add(Move.FORWARD);
		currentMove = route.next();
		
		new Thread(this).start();
	}
	
	@Override
	public void run(){
		while(true) loop();
	}
	/**
	 * Program loop.
	 */
	private void loop(){
		Delay.msDelay(5);
		
		float leftLightLevel = leftLightSensor.getLightValue();
		float rightLightLevel = rightLightSensor.getLightValue();
		
		float error = rightLightLevel - leftLightLevel; //error = difference in left and right sensor values.

		if((leftLightLevel + rightLightLevel)/2 < 38){ //if the robot is at a grid intersection.
			pilot.travel(0.075); //move forwards such that the wheels are on the line.
			
			switch(currentMove){ //manage route moves.
				case FORWARD:
					// we don't need to do anything here.
					break;
				case FULL_TURN:
					pilot.rotate(360);
					break;
				case HALF_TURN:
					pilot.rotate(180);
					break;
				case LEFT_TURN:
					pilot.rotate(-90);
					break;
				case RIGHT_TURN:
					pilot.rotate(90);
					break;
				case RANDOM:
					pilot.rotate(ra.nextBoolean() ? 90: -90);
					break;
				case STOP:
					//System.exit(0);
					break;
			}
			
			//pilot.travel(0.075); //travel forwards to avoid duplicate detection of lines.
			
			currentMove = route.next(); //get the next move.
		
		}
		else if(currentMove != Move.STOP){ //robot is not at an intersections.
			Motor.C.forward(); 
			Motor.B.forward();
			
			Motor.C.setSpeed( (maxSpeed/2) - (error * gain)); //proportional line following.
			Motor.B.setSpeed( (maxSpeed/2) + (error * gain));
		}
	}
	
	public boolean addMove(Move move){
		route.addMove(move);
		return true;
	}
}
