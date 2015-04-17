package com.rjhmrs.mrs.hse.oos2.vortrag;

public class Consumer implements Runnable {
	public void run() {
		while (true) {
			Integer item = (Integer) SyncRing.getInstance().readObj();
			// weitere Verarbeitung ...
		}
	}
}