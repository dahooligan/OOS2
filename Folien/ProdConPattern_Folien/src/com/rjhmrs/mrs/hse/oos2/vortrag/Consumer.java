package com.rjhmrs.mrs.hse.oos2.vortrag;

public class Consumer implements Runnable {
	public void run() {
		while (true) {
			try {
				Thread.sleep(200);
				Integer item = (Integer) SyncRing.getInstance().readObj();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// weitere Verarbeitung ...
		}
	}
}