package aw.comms;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Handles receiving commands from the robot to PC.
 */
public class CommandReceiver extends Thread {
	private String name;
	private BufferedReader br;

	private ArrayList<BluetoothCommandListener> listeners = new ArrayList<>();

	/**
	 * Creates an instance of the class for the PC's input stream
	 * (BufferedReader) from the robot.
	 * 
	 * @param name
	 *            The name of the robot.
	 * @param br
	 *            The PC's BufferedReader for a robot
	 */
	public CommandReceiver(String name, BufferedReader br) {
		this.name = name;
		this.br = br;
	}

	/**
	 * The main loop of this class. When a message is received from the robot,
	 * it is read, output to the console and sent to the listeners.
	 */
	@Override
	public void run() {
		boolean alive = true;

		while (alive) {
			try {
				String input = br.readLine();
				System.out.println(input);
				sendCommand(input);
			} catch (IOException e) {
				System.err.println("Connection lost");
				alive = false;
			}
		}
	}

	/**
	 * Sends a command to the listeners.
	 * @param command The command
	 */
	private void sendCommand(String command) {
		for (BluetoothCommandListener listener : listeners) {
			listener.commandReceived(name, command);
		}
	}

	/**
	 * Adds a listener to this object to receive commands from the robot.
	 * 
	 * @param listener
	 *            The class implementing the BluetoothCommandListener interface
	 *            that will be receiving commands
	 */
	public void addBluetoothCommandListener(BluetoothCommandListener listener) {
		listeners.add(listener);
	}
}
