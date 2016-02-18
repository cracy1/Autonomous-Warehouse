package aw.robotics;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import aw.comms.CommandSender;
import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommException;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTInfo;

public class Robot {
	private DataInputStream dis;
	private DataOutputStream dos;
	
	public Robot(){
		try {
			NXTComm nxtComm = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
			
			NXTInfo nxt = new NXTInfo(NXTCommFactory.BLUETOOTH, "Ricardo", "0016531B5974");
			
			if (nxtComm.open(nxt)) {
				dis = new DataInputStream(nxtComm.getInputStream());
				dos = new DataOutputStream(nxtComm.getOutputStream());
			}
			
			CommandSender commandSender = new CommandSender(dos);
			commandSender.sendCommand('F');
			commandSender.sendCommand('L');
		} catch (NXTCommException e) {
			e.printStackTrace();
		}
	}
}
