package com.rjhmrs.mrs.hse.oos2.vortrag;

public class SyncRing extends Ring {
	private static final int SIZE = 100; // Größe des Ringpuffers
	private static SyncRing SyncRingInstace = new SyncRing(SIZE);
	private boolean letzteAktionSchreiben = false;

	private SyncRing(int size) {
		super(size);
	}

	public static SyncRing getInstance() {
		return SyncRingInstace;
	}

	public synchronized void writeObj(Object objRef) {
		Node ref = getWriteRef();
		if (ref == getReadRef()) {
			if (letzteAktionSchreiben == true) {
				try {
					System.out.println("Ringpuffer voll! "
							+ "Producer muss warten...");
					wait();
				} catch (InterruptedException e) {
				}
			}
		}
		letzteAktionSchreiben = true;
		super.write(objRef);
		System.out.println("Producer hat geschrieben: " + objRef);

		notify(); // Consumer wecken
	}

	public synchronized Object readObj() {
		Node ref = getReadRef();
		if (ref == getWriteRef()) {
			if (letzteAktionSchreiben == false) {
				try {
					System.out.println("Ringpuffer leer! "
							+ "Consumer muss warten...");
					wait();
				} catch (InterruptedException e) {
				}
			}
		}
		letzteAktionSchreiben = false;
		Object objRef = super.read();
		System.out.println("Consumer hat gelesen: " + objRef);

		notify(); // Producer wecken
		return objRef;
	}
}