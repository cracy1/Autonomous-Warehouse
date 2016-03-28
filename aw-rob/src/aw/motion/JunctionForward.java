package aw.motion;


import java.util.ArrayList;

import lejos.nxt.LightSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;
/**
 * 
 *Behaviour for when the robot meets a junction.The robot will iterate through an array of Moves (to be integrated in the big project)
 *to decide what the robot will do
 *
 */
public class JunctionForward implements Behavior
{
	LightSensor right;
	LightSensor left;
	DifferentialPilot pilot;
	float dark;
	//Move[] moves={RIGHT_TURN, LEFT_TURN, HALF_TURN, FULL_TURN, FORWARD};
	String[] list=new String[] {"Left","Right","Forward","Back"};
	int i=0;
	boolean suppressed=false;
	/**
	 * 
	 * @param sensor1 -> right sensor
	 * @param sensor2->left sensor
	 * @param pilot-> the robot
	 * @param darkvalue->the value of the dark line
	 */
	public JunctionForward(LightSensor right,LightSensor left,DifferentialPilot pilot, float darkvalue)
	{
		this.right=right;
		this.left=left;
		this.pilot=pilot;
		this.dark= darkvalue;
		
	}
	/**
	 * takes control whenever both of the sensors touch the dark line 
	 */
	@Override
	public boolean takeControl() {
	
		// TODO Auto-generated method stub
		return (right.getLightValue()<= dark && left.getLightValue()<= dark);
	}
	/**
	 * iterates through a list deciding what moves the robot should do when meeting a junction
	 */
	@Override
	public void action() {
		suppressed=false;
	
		System.out.println("Junction detected");
		while(!suppressed)
		{
			if(!list[i].isEmpty())
				if(list[i].equals("Left"))
						{pilot.travel(100);pilot.rotate(90);i++;suppressed=true;}
				else if(list[i].equals("Right"))
					{pilot.travel(100);pilot.rotate(-90);i++;suppressed=true;}
				else if(list[i].equals("Forward"))
				{pilot.travel(100);i++;suppressed=true;}
				else if(list[i].equals("Back"))
				{
					pilot.rotate(180);i++;suppressed=true;
				}
				else
					System.exit(1);
			
			}	
			
	}
		
		
	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		suppressed=true;
	}

}