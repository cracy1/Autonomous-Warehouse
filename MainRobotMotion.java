import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class MainRobotMotion {
	public static void main (String[] args )
	{
		DifferentialPilot pilot = new DifferentialPilot(56, 56, 126, Motor.B, Motor.C, false);
		LightSensor sensor2 = new LightSensor(SensorPort.S2);
		LightSensor sensor3 = new LightSensor(SensorPort.S3);
		
		Behavior b1=new DriveForward(pilot);
		Behavior b2=new JunctionForward(sensor2,sensor3,pilot);
		//Behavior b3=new RightSensor(sensor2,pilot);
		Behavior b4=new BothSensors(sensor3,sensor2,pilot);
		Behavior[] b={b1,b2,b4};
		Arbitrator arby = new Arbitrator(b,true);
	      arby.start();
		
	}

}
