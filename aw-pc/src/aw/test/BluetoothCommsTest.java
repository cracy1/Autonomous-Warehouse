package aw.test;

import java.util.Scanner;

import aw.comms.CommandSender;
import aw.comms.Communication;

public class BluetoothCommsTest {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		Communication.addRobots();

		String name = "Ricardo";

		CommandSender cs = Communication.getCommandSender(name);

		if (cs != null) {
			while (true) {
				String input = scanner.nextLine();
				
				for (int i = 0; i < input.length(); i++) {
					char action = input.charAt(i);
					cs.sendActionCommand(action);
				}
			}
		} else {
			System.out.println("CommandSender does not exist for robot with name " + name);
		}
	}
}
