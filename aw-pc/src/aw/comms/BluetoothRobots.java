package aw.comms;

import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTInfo;

public class BluetoothRobots {
	public static final NXTInfo RICARDO = new NXTInfo(NXTCommFactory.BLUETOOTH, "Ricardo", "0016531B5974");
	public static final NXTInfo I4 = new NXTInfo(NXTCommFactory.BLUETOOTH, "NXT", "0016530C73B0");
	
	public static final NXTInfo[] NXTs = { RICARDO, I4 };
}
