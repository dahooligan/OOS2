package oos2.xml.lab2.dom.dausmann;

import org.w3c.dom.*;

import javax.xml.parsers.*;

public class RZDOMAnalyzer {

	private String domainname;

	public void analyseRecursively(Node node) {
		Node childNode = null;
		handleNodeRepresentation(node);

		for (int i = 0; i < node.getChildNodes().getLength(); i++) {
			childNode = node.getChildNodes().item(i);

			analyseRecursively(childNode);
		}
	}

	private void handleNodeRepresentation(Node node) {
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			if (node.getNodeName().equals("Domainname")) {
				domainname = new String(node.getFirstChild().getNodeValue());
			} else if (node.getNodeName().equals("Name")) {
				System.out.print(node.getFirstChild().getNodeValue() + "."
						+ domainname + ": ");
			} else if (node.getNodeName().equals("Benutzer")) {
				System.out.print("(user: "
						+ parseNodeContents(node, ',') + ") ");
			} else if (node.getNodeName().equals("Prozanz")) {
				System.out.print("(cpu's: "
						+ node.getFirstChild().getNodeValue() + ") ");
			} else if (node.getNodeName().equals("Speicher")) {
				System.out.println("(ram: " + node.getFirstChild().getNodeValue()
						+ ")");
			}
		}
	}
	
	private String parseNodeContents(Node node, char delimiter)
	{
		StringBuilder builder = new StringBuilder();
		
		for(int i=0;i<node.getChildNodes().getLength();i++)
		{
			builder.append(node.getChildNodes().item(i).getNodeValue());
			builder.append(i<node.getChildNodes().getLength()-1 ? delimiter + ' ' : "");
		}
		return builder.toString();
	}

	public static void main(String args[]) throws Exception {
		/* Parser instanziiern */
		DocumentBuilderFactory myFactory = DocumentBuilderFactory.newInstance();
		myFactory.setValidating(true);
		DocumentBuilder myParser = myFactory.newDocumentBuilder();

		/* File parsen - Baum-Aufbau */
		Document myDoc = myParser.parse("res/rz.xml");

		new RZDOMAnalyzer().analyseRecursively(myDoc);

	}
}
