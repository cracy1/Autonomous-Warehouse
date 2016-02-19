package aw.test;

import java.util.Scanner;

import aw.comms.CommandSender;
import aw.comms.Communication;

public class BluetoothCommsTest {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		Communication.addRobots();

		String name = "NXT";

		CommandSender cs = Communication.getCommandSender(name);

		if (cs != null) {
			while (true) {
				char action = scanner.nextLine().charAt(0);
				cs.sendCommand(action);
			}
		} else {
			System.out.println("CommandSender does not exist for robot with name " + name);
		}
	}
}
