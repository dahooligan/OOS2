package com.rjhmrs.mrs.hse.oos2.vortrag;

public class SyncRing extends Ring {
	private static final int SIZE = 5; // Größe des Ringpuffers
	private static SyncRing SyncRingInstace = new SyncRing(SIZE);
	private boolean letzteAktionSchreiben = false;

	// Singleton
	private SyncRing(int size) {
		super(size);
	}

	public static SyncRing getInstance() {
		return SyncRingInstace;
	}

	/*
	 * synchronized: "When one thread is executing a synchronized method for
	 * an object, all other threads that invoke synchronized methods for the
	 * same object block (suspend execution) until the first thread is done with
	 * the object."
	 */
	public synchronized void writeObj(Object objRef) {
		Node ref = getWriteRef();
		if (ref == getReadRef()) {
			if (letzteAktionSchreiben == true) {
				try {
					System.out.println("Ringpuffer voll! "
							+ "Producer muessen warten...");
					wait();
				} catch (InterruptedException e) {
				}
			}
		}
		letzteAktionSchreiben = true;
		super.write(objRef);
		System.out.println("In den SyncRing wurde geschrieben: " + objRef);
		notify(); // Consumer wecken
	}

	public synchronized Object readObj() {
		Node ref = getReadRef();
		if (ref == getWriteRef()) {
			if (letzteAktionSchreiben == false) {
				try {
					System.out.println("Ringpuffer leer! "
							+ "Consumer muessen warten...");
					wait();
				} catch (InterruptedException e) {
				}
			}
		}
		letzteAktionSchreiben = false;
		Object objRef = super.read();
		System.out.println("Aus dem SyncRing wurde gelesen: " + objRef);

		notify(); // Producer wecken
		return objRef;
	}
}