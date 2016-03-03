package aw.test;

import aw.comms.BluetoothCommandListener;
import aw.comms.Communication;

public class CommandReceiverTest implements BluetoothCommandListener {
	
	public CommandReceiverTest() {
		Communication.getRobotConnection("Ricardo").getCommandReceiver().addBluetoothCommandListener(this);
	}

	@Override
	public void commandReceived(String command) {
		System.out.println(command);
	}
}
