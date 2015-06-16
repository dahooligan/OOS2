package oos2.xml.lab2.dom.own;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MyDOMParser {

	class MyDocParser {

		private String currentDomain = "";
		private String currentComputer = "";

		public void visitRecursively(Node node) {
			NodeList childNodes = node.getChildNodes();

			if (node.getNodeType()==Node.ELEMENT_NODE)
		      {
		          if (node.getNodeName().equals("Domainname"))
		          {
		            System.out.println(new String(node.getFirstChild().getNodeValue()).trim());
		          }
		      }
			
			for (int i = 0; i < childNodes.getLength(); i++) {
				Node childNote = childNodes.item(i);
				visitRecursively(childNote);
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
			new MyDOMParser().new MyDocParser().visitRecursively(document);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
