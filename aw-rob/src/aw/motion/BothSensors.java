package aw.motion;

import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

/**
 * 
 *Behaviour for following the line using proportional motor speed adjustement 
 *
 */
public class BothSensors implements Behavior {

	LightSensor sensor1;
	LightSensor sensor2;
	DifferentialPilot pilot;
	int leftLightLevel;
	int rightLightLevel;
	boolean suppressed=false;
	float dark;
	/**
	 * 
	 * @param sensor1 -> right sensor
	 * @param sensor2->left sensor
	 * @param pilot-> the robot
	 * @param darkvalue->the value of the dark line
	 */
	public BothSensors(LightSensor sensor1,LightSensor sensor2,DifferentialPilot pilot, float  darkvalue)
	{
		this.sensor1=sensor1;
		this.sensor2=sensor2;
		this.pilot=pilot;
		this.dark = darkvalue;
		// Input the value of the sensors
		leftLightLevel = sensor2.getLightValue();
		rightLightLevel = sensor1.getLightValue();
		
	}
	
	/**
	 * takes control when one of the sensors touches the dark line
	 */
	public boolean takeControl() {
		leftLightLevel = sensor2.getLightValue();
		rightLightLevel = sensor1.getLightValue();
		
		System.out.println((leftLightLevel + rightLightLevel)/2 );
			return (leftLightLevel + rightLightLevel)/2 > dark ;
	}
	/**
	 * Adjust the robot to follow to line.It uses the value recorded by the sensors and the error between them to adjust de motor of the wheel
	 * so that the robot would steer smoothly following the line
	 */
	
	@Override
	public void action() {
		suppressed=false;
		
		float error;
		float gain = 15.0f;//calculated gain 
		float maxSpeed = Motor.A.getMaxSpeed();
		while(!suppressed )
			{
			leftLightLevel = sensor2.getLightValue();
			rightLightLevel = sensor1 .getLightValue();
			error = rightLightLevel - leftLightLevel;

			Motor.C.forward(); 
			Motor.B.forward();
			
			Motor.C.setSpeed( (maxSpeed/2) - (error * gain)); //proportional line following.
			Motor.B.setSpeed( (maxSpeed/2) + (error * gain));
		
			}
		
	}

	@Override
	public void suppress() {
		
			suppressed=true;
			
		
	}

}