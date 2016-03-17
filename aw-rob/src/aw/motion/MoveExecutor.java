package aw.motion;

import java.util.LinkedList;
import java.util.Random;

import aw.config.RobotConfigs;
import aw.display.Display;
import aw.robot.Robot;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;
import rp.robotics.DifferentialDriveRobot;

public class MoveExecutor {
	private final DifferentialPilot pilot;
	private final LightSensor leftLightSensor, rightLightSensor;

	private final Display display;
	
	private final float gain = 15.0f;
	//private final float maxSpeed = Motor.A.getMaxSpeed();
	private final float baseSpeed = Motor.A.getMaxSpeed() / 2f;
	
	/**
	 * Construct a MoveExecutor object.
	 * @param robot
	 */
	public MoveExecutor(DifferentialDriveRobot robot){
		pilot = robot.getDifferentialPilot();
		
		leftLightSensor = new LightSensor(SensorPort.S3);
		rightLightSensor = new LightSensor(SensorPort.S4);

		display = new Display();
		pilot.setTravelSpeed(baseSpeed);
	}
	
	public void execute(Move move){
		switch(move){ //manage route moves.
			case FORWARD:
				forwardToJunction();
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
			case STOP:
				pilot.stop();
				break;			
		}
	}
	
	/**
	 * Move forward until a junction is detected.
	 */
	private void forwardToJunction(){	
		float leftLightLevel = leftLightSensor.getLightValue();
		float rightLightLevel = rightLightSensor.getLightValue();
		
		while((leftLightLevel + rightLightLevel)/2 > 38){
			leftLightLevel = leftLightSensor.getLightValue();
			rightLightLevel = rightLightSensor.getLightValue();
			float error = rightLightLevel - leftLightLevel; //error = difference in left and right sensor values.
			
			Motor.C.forward(); 
			Motor.B.forward();
			
			Motor.C.setSpeed( baseSpeed - (error * gain)); //proportional line following.
			Motor.B.setSpeed( baseSpeed + (error * gain));
		}
		
		pilot.travel(0.075);
	}
	
	
	/**
	 * Add an item request to the current route.
	 * @param name
	 * @param amount
	 */
	public void requestItem(String name, int amount){
		display.requestItem(name, amount);
	}
	
	/**
	 * Add an item drop request to the current route.
	 * @param name
	 * @param amount
	 * @param delay
	 */
	public void dropRequest(String name, int amount, int delay){
		display.dropOffItem(name, amount, delay);
	}
}
