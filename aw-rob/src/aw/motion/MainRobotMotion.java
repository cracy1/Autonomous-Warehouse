package aw.motion;

import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
/**
 * 
 * @author suiu
 *arbitrator that decides which behaviour takes control and when others get suppressed
 */
public class MainRobotMotion {
	public static void main (String[] args )
	{
		
		float darkvalue;
		float lightervalue;
		
		DifferentialPilot pilot = new DifferentialPilot(56, 56, 126, Motor.B, Motor.C, false);
		LightSensor sensor2 = new LightSensor(SensorPort.S2);
		LightSensor sensor3 = new LightSensor(SensorPort.S3);
		
		//Deciding which is the dark line at the beggining of the program
		Button.waitForAnyPress();
		float var1,var2;
		var1=sensor2.getLightValue();
		var2=sensor3.getLightValue();
		if(var1<var2)
		{
			darkvalue=var1;
			lightervalue=var2;
		}
		else
		{
		darkvalue =var2; 
		lightervalue= var1;
		}
		Behavior b1=new DriveForward(pilot);
		Behavior b2=new JunctionForward(sensor2,sensor3,pilot,darkvalue);
		//Behavior b3=new RightSensor(sensor2,pilot);
		Behavior b4=new BothSensors(sensor3,sensor2,pilot,darkvalue);
		Behavior[] b={b1,b4,b2};
		Button.waitForAnyPress();
		Arbitrator arby = new Arbitrator(b,true);
	      arby.start();
		
	}

}
