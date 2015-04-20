package com.rjhmrs.mrs.hse.oos2.vortrag;

public class Producer implements Runnable {
	public void run() {
		int count = 0;
		while (true) {
			try {
				Thread.sleep(150);
				SyncRing.getInstance().writeObj(new Integer(count));
				count++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}