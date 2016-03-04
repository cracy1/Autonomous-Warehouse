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

public class MoveExecutor implements Runnable {
	private final DifferentialPilot pilot;
	private final LightSensor leftLightSensor, rightLightSensor;
	
	private Route route;
	private Move currentMove;
	
	private LinkedList<ItemRequest> itemRequests;
	
	private final Display display;
	
	private final float gain = 15.0f;
	private final float maxSpeed = Motor.A.getMaxSpeed();
	
	/**
	 * Construct a MoveExecutor object.
	 * @param robot
	 */
	public MoveExecutor(DifferentialDriveRobot robot){
		pilot = robot.getDifferentialPilot();
		
		leftLightSensor = new LightSensor(SensorPort.S3);
		rightLightSensor = new LightSensor(SensorPort.S4);
		
		route = new Route(); //create a new route.
		itemRequests = new LinkedList<>();
		
		display = new Display();
		
		new Thread(this).start();
	}
	
	/**
	 * Run loop.
	 */
	@Override
	public void run(){
		while(true){
			Delay.msDelay(5);
			currentMove = route.next();
			
			switch(currentMove){ //manage route moves.
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
				case REQUEST_ITEM:
					ItemRequest req = itemRequests.remove(0);
					display.requestItem(req.getName(), req.getAmount());
					break;
				case STOP:
					pilot.stop();
					break;
				default:
					System.out.println("No moves left in route");
					break;
					
			}
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
			
			Motor.C.setSpeed( (maxSpeed/2) - (error * gain)); //proportional line following.
			Motor.B.setSpeed( (maxSpeed/2) + (error * gain));
		}
		
		pilot.travel(0.075);
	}
	
	/**
	 * Add a move to the current route.
	 * @param move
	 * @return
	 */
	public boolean addMove(Move move){
		route.addMove(move);
		return true;
	}
	
	/**
	 * Add an item request to the current route.
	 * @param name
	 * @param amount
	 */
	public void requestItem(String name, int amount){
		itemRequests.add(new ItemRequest(name, amount));
		addMove(Move.REQUEST_ITEM);
	}
}
