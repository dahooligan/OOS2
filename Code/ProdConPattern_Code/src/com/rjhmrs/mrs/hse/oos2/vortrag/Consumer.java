package com.rjhmrs.mrs.hse.oos2.vortrag;


public class Consumer implements Runnable {
	public void run() {
		while (true) {
			try {
				Thread.sleep(200);
				Integer item = (Integer) SyncRing.getInstance().readObj();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// weitere Verarbeitung ...
		}
	}
}
