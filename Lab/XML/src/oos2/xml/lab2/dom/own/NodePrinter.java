package oos2.xml.lab2.dom.own;

import org.w3c.dom.Node;

// schreibt knoten auf system.out in der form wie im aufgabenblatt gegeben
public class NodePrinter implements NodeHandler
{

	private String domainname = "";
	
	private boolean notFirstEntry = false;
	
	public void handle(Node node) {
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			if (node.getNodeName().equals("Domainname")) {
				domainname = new String(node.getFirstChild().getNodeValue())
						.trim();
			} else if (node.getNodeName().equals("Name")) {
				System.out.print((notFirstEntry ? "\n" : "")
						+ node.getFirstChild().getNodeValue() + "."
						+ domainname + ": ");
				notFirstEntry = true;

			} else if (node.getNodeName().equals("Benutzer")) {
				System.out.print("(user: " + parseNodeContents(node, ',')
						+ ") ");
			} else if (node.getNodeName().equals("Prozanz")) {
				System.out.print("(cpu's: "
						+ node.getFirstChild().getNodeValue() + ") ");
			} else if (node.getNodeName().equals("Speicher")) {
				System.out.print("(ram: " + node.getFirstChild().getNodeValue()
						+ ")");
			}
		}
	}
	
	// gibt einen mit den durch delimiter+' ' getrennten Attributen des Knotens
	// wieder
	private String parseNodeContents(Node node, char delimiter) {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < node.getChildNodes().getLength(); i++) {
			builder.append(node.getChildNodes().item(i).getNodeValue());
			builder.append(i < node.getChildNodes().getLength() - 1 ? delimiter + ' '
					: "");
		}
		return builder.toString();
	}
	
}