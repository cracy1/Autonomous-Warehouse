package aw.robot;

import java.io.DataOutputStream;
import java.io.IOException;

public class RobotSender {
	private DataOutputStream dos;
	
	public RobotSender(DataOutputStream dos) {
		this.dos = dos;
	}
	
	public void sendString(String string) {
		try {
			dos.writeChars(string);
			dos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
