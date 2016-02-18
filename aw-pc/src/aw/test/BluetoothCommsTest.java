package aw.test;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

import aw.comms.BluetoothRobots;
import aw.comms.CommandSender;
import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommException;
import lejos.pc.comm.NXTCommFactory;

public class BluetoothCommsTest {
	private DataOutputStream dos;
	
	public BluetoothCommsTest(){
		Scanner scanner = new Scanner(System.in);
		
		try {
			NXTComm nxtComm = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
			
			if (nxtComm.open(BluetoothRobots.NXT_RICARDO)) {
				dos = new DataOutputStream(nxtComm.getOutputStream());
			}
			
			CommandSender commandSender = new CommandSender(dos);
			
			while (true) {
				char action = scanner.nextLine().charAt(0);
				commandSender.sendMovementCommand(action);
			}
		} catch (NXTCommException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String [] args){
		new BluetoothCommsTest();
	}
}
