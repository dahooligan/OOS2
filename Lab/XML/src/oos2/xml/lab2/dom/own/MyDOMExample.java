package oos2.xml.lab2.dom.own;

import java.io.File;
import java.io.OutputStream;
import java.io.Writer;

import org.w3c.dom.*;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

import javax.xml.parsers.*;

public class MyDOMExample {

	
	// rekursives durchlaufen des dokuments
	public void analyseRecursively(Node node, NodeHandler nodeHandler) {
		Node childNode = null;
		nodeHandler.handle(node);

		for (int i = 0; i < node.getChildNodes().getLength(); i++) {
			childNode = node.getChildNodes().item(i);

			analyseRecursively(childNode, nodeHandler);
		}
	}

	// fügt einen neuen Computer in das Dokument ein. Die Stelle wo dies
	// geschieht ist nicht parameterisiert
	public void addComputerToNodeExample(Document document) {
		for (int i = 0; i < document.getChildNodes().getLength(); i++) {
			Node child = document.getChildNodes().item(i);

			Node domainNode = findRecursively(child, "Domain");

			if (child.getNodeType() == Node.ELEMENT_NODE) {
				Element rechner = document.createElement("Rechner");
				rechner.setAttribute("Typ", "server");
				rechner.setAttribute("IP", "fht-esslingen.de");

				domainNode.appendChild(rechner);

				Element name = document.createElement("Name");
				Text text = document.createTextNode("new machine");
				name.appendChild(text);
				rechner.appendChild(name);

				Element benutzer = document.createElement("Benutzer");
				text = document.createTextNode("maseit04");
				benutzer.appendChild(text);
				rechner.appendChild(benutzer);

				Element prozanz = document.createElement("Prozanz");
				text = document.createTextNode("8");
				prozanz.appendChild(text);
				rechner.appendChild(prozanz);

				break;
			}
		}
	}

	
	//durchsucht das dokument rekursiv nach einem knoten mit dem gegebenen namen
	//wird kein knoten mit dem namen gefunden wird null zurück gegeben
	private Node findRecursively(Node node, String name) {
		for (int i = 0; i < node.getChildNodes().getLength(); i++) {
			Node child = node.getChildNodes().item(i);
			if (child.getNodeType() == Node.ELEMENT_NODE) {
				if (child.getNodeName().equals(name)) {
					return child;
				}
			}
			findRecursively(child, name);
		}
		return null;
	}

	// Schreibt das gegebene org.w3c.dom.Document an den gegebenen Pfad. 
	// Arbeitsweise ist aus dem RZDOMWriter übernommen
	public void writeDOMToFile(final String filePath, Document doc) {
		DOMImplementation impl = doc.getImplementation();
		DOMImplementationLS feat = (DOMImplementationLS) impl.getFeature("LS",
				"3.0");
		LSSerializer writer = feat.createLSSerializer();

		writer.write(doc, new LSOutput() {
			private String encoding = "utf-8";
			OutputStream byteStream;
			Writer characterStream;
			String systemId = new File(filePath).toURI().toString();

			public void setSystemId(String systemId) {
				this.systemId = systemId;
			}

			public void setEncoding(String encoding) {
				this.encoding = encoding;
			}

			public void setCharacterStream(Writer characterStream) {
				this.characterStream = characterStream;
			}

			public void setByteStream(OutputStream byteStream) {
				this.byteStream = byteStream;
			}

			public String getSystemId() {
				return systemId;
			}

			public String getEncoding() {
				return encoding;
			}

			public Writer getCharacterStream() {
				return characterStream;
			}

			public OutputStream getByteStream() {
				return byteStream;
			}
		});
	}

	
	
	public static void main(String args[]) throws Exception {
		/* Parser instanziieren */
		DocumentBuilderFactory myFactory = DocumentBuilderFactory.newInstance();
		myFactory.setValidating(true);
	
		DocumentBuilder myParser = myFactory.newDocumentBuilder();
		MyDOMExample myDOMExample = new MyDOMExample();
		
		//dokument parsen
		Document document = myParser.parse("res/rz.xml");
		//und auf der konsole ausgeben
		myDOMExample.analyseRecursively(document, new NodePrinter());

		try {
			myDOMExample.analyseRecursively(myParser
					.parse("res/rz_corrupt.xml"), new NodePrinter());
		} catch (Exception e) {
			System.err.println("corrupt document caused exception as expected");
		}

		myDOMExample.addComputerToNodeExample(document);
		myDOMExample.writeDOMToFile("res/rz_new_node.xml", document);

		myDOMExample.analyseRecursively(myParser.parse("res/rz_new_node.xml"), new NodePrinter());

	}
}
