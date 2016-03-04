package aw.comms.test;

import java.io.DataOutputStream;
import java.io.IOException;

import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommException;
import lejos.pc.comm.NXTInfo;

public class RobConnection {
	private NXTInfo nxt;
	
	private DataOutputStream dos;

	public RobConnection(NXTInfo nxt) {
		this.nxt = nxt;
	}

	public void connect(NXTComm nxtComm) {
		try {
			if (nxtComm.open(nxt)) {
				this.dos = new DataOutputStream(nxtComm.getOutputStream());
				System.out.println("Successfully connected to robot '" + nxt.name + "'");
			}
		} catch (NXTCommException e) {
			System.err.println("Failed to connect to robot '" + nxt.name + "'");
			e.printStackTrace();
		}
	}
	
	public void send(int num) {
		try {
			dos.writeInt(num);
			dos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getName() {
		return nxt.name;
	}
}
