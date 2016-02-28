import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class DriveForward implements Behavior{
	DifferentialPilot pilot;
	boolean supressed=false;
	public DriveForward(DifferentialPilot pilot)
	{
		this.pilot=pilot;
	}

	@Override
	public boolean takeControl() {
	return true;
	}

	@Override
	public void action() {
		supressed=false;
	
		while( !supressed )
		{
			pilot.forward();
		}
		pilot.stop();
		
	}

	@Override
	public void suppress() {
		supressed=true;
		
	}

}
