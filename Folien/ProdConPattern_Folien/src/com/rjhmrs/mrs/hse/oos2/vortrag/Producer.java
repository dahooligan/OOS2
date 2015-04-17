package com.rjhmrs.mrs.hse.oos2.vortrag;

public class Producer implements Runnable {
	public void run() {
		int count = 0;
		while (true) {
			SyncRing.getInstance().writeObj(new Integer(count));
			count++;
		}
	}
}