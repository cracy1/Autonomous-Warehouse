import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;


public class BothSensors implements Behavior {

	LightSensor sensor1;
	LightSensor sensor2;
	DifferentialPilot pilot;
	boolean suppressed=false;
	public BothSensors(LightSensor sensor1,LightSensor sensor2,DifferentialPilot pilot)
	{
		this.sensor1=sensor1;
		this.sensor2=sensor2;
		this.pilot=pilot;
	}
	
	public boolean takeControl() {
		int leftLightLevel;
		int rightLightLevel;
		leftLightLevel = sensor2.getLightValue();
		rightLightLevel = sensor1.getLightValue();
			return (leftLightLevel + rightLightLevel)/2 > 38 ;
	}
	
	@Override
	public void action() {
		suppressed=false;
		int leftLightLevel;
		int rightLightLevel;
		float error;
		leftLightLevel = sensor2.getLightValue();
		rightLightLevel = sensor1.getLightValue();
		float gain = 15.0f;
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
				suppressed=true;
			}
		
	}

	@Override
	public void suppress() {
		
			suppressed=true;
			
		
	}

}