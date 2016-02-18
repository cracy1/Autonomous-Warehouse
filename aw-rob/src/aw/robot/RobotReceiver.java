package aw.robot;

import java.io.DataInputStream;
import java.io.IOException;

public class RobotReceiver extends Thread {
	private DataInputStream dis;
	
	public RobotReceiver(DataInputStream dis) {
		this.dis = dis;
	}

	@Override
	public void run() {
		boolean run = true;

		while (run) {
			try {
				int protocol = dis.readInt();

				switch (protocol) {
					case 1:
						char action = dis.readChar();
						System.out.println(action);
						break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
