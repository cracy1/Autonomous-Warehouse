package aw.test;

import java.util.Scanner;

import aw.comms.CommandSender;
import aw.comms.Communication;

public class BluetoothCommsTest {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		Communication.addRobots();

		while (true) {
			String name = scanner.nextLine();
			String input = scanner.nextLine();

			CommandSender cs = Communication.getRobotConnection(name).getCommandSender();

			if (cs != null) {
				for (int i = 0; i < input.length(); i++) {
					char action = input.charAt(i);
					cs.sendCommand(action + "");
				}
			} else {
				System.err.println("Robot with name " + name + " is not connected");
			}
		}
	}
}
