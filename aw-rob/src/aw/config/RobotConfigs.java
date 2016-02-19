package aw.config;
import lejos.nxt.Motor;
import rp.config.WheeledRobotConfiguration;

public class RobotConfigs {
	/**
	 * Configuration for Nick's Castorbot build. The numbers may be different
	 * for you. Measurements in m.
	 */
	public static final WheeledRobotConfiguration CASTOR_BOT = new WheeledRobotConfiguration(
			0.056f, 0.120f, 0.230f, Motor.B, Motor.C);

}
