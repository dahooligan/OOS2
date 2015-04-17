package com.rjhmrs.mrs.hse.oos2.synchronizeddemo;

public class Demo {


	
	public static void main(String[] args) {
//		Count count = new SimpleCount();
		Count count = new SyncCount();
		
		Thread firstThread = new Thread(new CountIncrementer(count, "first "));
		Thread secondThread = new Thread(new CountIncrementer(count, "second"));

		firstThread.start();
		secondThread.start();
	}
	
}
