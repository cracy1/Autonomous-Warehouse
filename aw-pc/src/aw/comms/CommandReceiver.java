package aw.comms;

import java.io.BufferedReader;
import java.io.IOException;

public class CommandReceiver extends Thread {
	private BufferedReader br;

	public CommandReceiver(BufferedReader br) {
		this.br = br;
	}

	@Override
	public void run() {
		boolean alive = true;
		
		while (alive) {
			try {
				System.out.println("Reading...");
				String input = br.readLine();
				System.out.println("string " + input);
			} catch (IOException e) {
				alive = false;
			}
		}
	}
	
	public String read(){
		String message = "";
		
		try {
			message = br.readLine();
		} catch (IOException e) {
			System.err.println("Lost connection to robot");
		}
		
		return message;
	}
}
