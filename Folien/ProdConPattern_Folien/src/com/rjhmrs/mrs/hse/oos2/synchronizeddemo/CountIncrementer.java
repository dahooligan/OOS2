package com.rjhmrs.mrs.hse.oos2.synchronizeddemo;

public class CountIncrementer implements Runnable {

	private final String prefix;
	private final Count count;

	public CountIncrementer(Count count, String prefix) {
		this.count = count;
		this.prefix = prefix;
	}

	@Override
	public void run() {
		while (true) {
			int tmp = count.getCounter();
			
			count.increment();
			
			if (count.getCounter() == (tmp + 1)) {
				System.out.println("[" + prefix
						+ "] Wert zwischendurch nicht geändert.");
			} else {
				System.out.println("[" + prefix
						+ "] Wert zwischendurch geändert.");
			}
		}
	}

}
