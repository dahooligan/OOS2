package oos2.xml.lab2;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MyDOMParser {

	class MyDocParser {

		public void handleDocument(Document doc) {
			for (int i = 0; i < doc.getChildNodes().getLength(); i++) {
				Node child = doc.getChildNodes().item(i);
				if (child.getNodeType() == Node.ELEMENT_NODE) {
					System.out.println("asd");
				}
			}

		}

	}

	// public.somewhere.com: (user: anonymous) (cpu’s: 1)

	public static void main(String[] args) {

		String xmlFile = "res/rz.xml";
		try {
			// Parser instanziieren
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			factory.setValidating(true);
			factory.setNamespaceAware(false);
			DocumentBuilder parser = factory.newDocumentBuilder();
			// File parsen - Baum aufbauen
			Document document = parser.parse(xmlFile);
			new MyDOMParser().new MyDocParser().handleDocument(document);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
