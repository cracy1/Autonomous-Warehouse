package aw.comms.test;

import java.util.ArrayList;
import java.util.Scanner;

import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommException;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTInfo;

public class CommsTest {
	public static void main(String[] args) {
		try {
			NXTComm nxtComm = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
			
			NXTInfo[] nxts = {
					new NXTInfo(NXTCommFactory.BLUETOOTH, "Ricardo", "0016531B5974"),
					new NXTInfo(NXTCommFactory.BLUETOOTH, "NXT", "0016530C73B0")
			};
			
			ArrayList<RobConnection> connections = new ArrayList<>();
			
			for (NXTInfo nxt : nxts) {
				connections.add(new RobConnection(nxt));
			}
			
			System.out.println("Attemping to connect to " + nxts.length + " robots");
			
			for (RobConnection robConn : connections) {
				robConn.connect(nxtComm);
			}
			
			System.out.println("Robot connections complete");
			
			System.out.println("Enter a number to send to all robots");
			
			Scanner scanner = new Scanner(System.in);
			
			while (true) {
				int input = scanner.nextInt();
				
				for (RobConnection robConn : connections) {
					robConn.send(input);
					System.out.println(input + " sent to '" + robConn.getName() + "'");
				}
			}
		} catch (NXTCommException e) {
			e.printStackTrace();
		}
	}
}
