package aw.comms;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class CommandReceiver extends Thread {
	private BufferedReader br;
	
	private ArrayList<BluetoothCommandListener> listeners = new ArrayList<>();

	public CommandReceiver(BufferedReader br) {
		this.br = br;
	}

	@Override
	public void run() {
		boolean alive = true;
		
		while (alive) {
			try {
				String input = br.readLine();
				sendCommand(input);
			} catch (IOException e) {
				System.err.println("Connection lost");
				alive = false;
			}
		}
	}
	
	private void sendCommand(String command) {
		for (BluetoothCommandListener listener : listeners) {
			listener.commandReceived(command);
		}
	}
	
	// Communication.getRobotConnection("name").getCommandReceiver.addBluetoothCommandListener(this/class implementing BCL)
	public void addBluetoothCommandListener(BluetoothCommandListener listener) {
		listeners.add(listener);
	}
}
