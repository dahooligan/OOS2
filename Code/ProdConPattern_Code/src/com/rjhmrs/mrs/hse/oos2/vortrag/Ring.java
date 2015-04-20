package com.rjhmrs.mrs.hse.oos2.vortrag;

//Datei: Ring.java
public class Ring {

	private Node startRef = null; // Anfang des Ringpuffers
	private Node writeRef = null; // Schreibzeiger f�r Producer
	private Node readRef = null; // Lesezeiger f�r Consumer

	public Ring(int size) {
		Node currentRef = null; // aktuelles Element
		while (size > 0) {
			size--;
			Node node = new Node();
			if (startRef == null) {
				startRef = currentRef = node;
			} else {
				currentRef.setNodeRef(node);
				currentRef = node;
			}
		}
		currentRef.setNodeRef(startRef); // Ring schlie�en
		// Schreib- und Lesezeiger auf den Startwert setzen
		writeRef = startRef;
		readRef = startRef;
	}

	public Node getWriteRef() {
		return writeRef;
	}

	public Node getReadRef() {
		return readRef;
	}

	public void setWriteRef(Node ref) {
		writeRef = ref;
	}

	public void setReadRef(Node ref) {
		readRef = ref;
	}

	public void write(Object objRef) {
		Node ref = getWriteRef();
		// Neues Element einf�gen
		ref.setObjRef(objRef);
		// Schreibzeiger auf n�chstes Element setzen
		setWriteRef(ref.getNodeRef());
	}

	public Object read() {
		Node ref = getReadRef();
		// Element auslesen
		Object tmp = ref.getObjRef();
		// Lesezeiger auf n�chstes Element setzen
		setReadRef(ref.getNodeRef());
		return tmp;
	}

	public void printRing() {
		Node aktref = startRef;
		System.out.println("Inhalt des Ringbuffers:");
		do {
			aktref.print();
			aktref = aktref.getNodeRef();
		} while (aktref != startRef);
	}
}