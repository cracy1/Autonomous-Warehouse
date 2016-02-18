package aw.comms;

import java.io.DataOutputStream;
import java.io.IOException;

public class CommandSender {
	private DataOutputStream dos;
	
	public CommandSender(DataOutputStream dos) {
		this.dos = dos;
	}
	
	public void sendMovementCommand(char action) {
		try {
			dos.writeInt(1);
			dos.writeChar(action);
			dos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
