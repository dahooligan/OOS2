package com.rjhmrs.mrs.hse.oos2.synchronizeddemo;

import java.util.Random;

public class Count {

	protected int counter = 0;

	public int getCounter() {
		return counter;
	}

	public void increment() {
		counter++;
		waitABit(4);
	}


	protected void waitABit(int k) {
		if (k == 0) {
			return;
		}
		for (int i = 0; i <= 10; i++) {
			waitABit(k - 1);
		}
	}

	protected void waitRandom() {
		int nextInt = new Random().nextInt(5);
		System.out.println("waiting for " + nextInt);
		waitABit(nextInt);
	}

}
