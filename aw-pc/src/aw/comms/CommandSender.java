package aw.comms;

import java.io.DataOutputStream;
import java.io.IOException;

public class CommandSender {
	private DataOutputStream dos;
	
	public CommandSender(DataOutputStream dos) {
		this.dos = dos;
	}
	
	public void sendCommand(char direction) {
		try {
			dos.writeInt(1);
			dos.writeChar(direction);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
