package aw.comms;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class CommandReceiver extends Thread {
	private String name;
	private BufferedReader br;
	
	private ArrayList<BluetoothCommandListener> listeners = new ArrayList<>();

	public CommandReceiver(String name, BufferedReader br) {
		this.name = name;
		this.br = br;
	}

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
	
	private void sendCommand(String command) {
		for (BluetoothCommandListener listener : listeners) {
			listener.commandReceived(name, command);
		}
	}
	
	// Communication.getRobotConnection("name").getCommandReceiver.addBluetoothCommandListener(this/class implementing BCL)
	public void addBluetoothCommandListener(BluetoothCommandListener listener) {
		listeners.add(listener);
	}
}
