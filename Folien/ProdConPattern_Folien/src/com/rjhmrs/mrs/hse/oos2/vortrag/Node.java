package com.rjhmrs.mrs.hse.oos2.vortrag;

public class Node {
	private Node nodeRef = null; // nächstes Element
	private Object objRef = null; // Inhalt des Elements

	public Object getObjRef() {
		return objRef;
	}

	public void setObjRef(Object objRef) {
		this.objRef = objRef;
	}

	public Node getNodeRef() {
		return nodeRef;
	}

	public void setNodeRef(Node nodeRef) {
		this.nodeRef = nodeRef;
	}

	public void print() {
		System.out.println("Inhalt: " + objRef);
	}
}
