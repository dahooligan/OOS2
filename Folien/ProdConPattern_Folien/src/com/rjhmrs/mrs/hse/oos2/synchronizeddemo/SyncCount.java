package com.rjhmrs.mrs.hse.oos2.synchronizeddemo;

public class SyncCount extends Count {

	@Override
	public synchronized void increment() {
		int tmp = counter;
		counter++;
		waitRandom();
		if (counter != tmp + 1) {
			System.out.println("Hier ist was schief gelaufen");
		}

	}

}
