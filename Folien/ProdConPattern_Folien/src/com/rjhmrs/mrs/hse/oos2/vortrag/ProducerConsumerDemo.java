package com.rjhmrs.mrs.hse.oos2.vortrag;

public class ProducerConsumerDemo {
	public static void main(String[] args) {
		Thread pRef = new Thread(new Producer());
		Thread cRef = new Thread(new Consumer());
		pRef.start();
		cRef.start();
	}
}