package oos2.xml.lab2.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class NamesInDomainToConsoleHandler extends DefaultHandler {

	private String currentElement = "";
	private boolean namesBegun = false;

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		currentElement = qName;
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		currentElement = "";
		if(qName == "Domain")
		{
			namesBegun = false;
			System.out.println();
		}
		
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (currentElement.equals("Domainname")) {
			System.out.println("Domain: " + new String(ch).trim());
		}
		if (currentElement.equals("Benutzer")) {
			if (!namesBegun) {
				System.out.print("Users: ");
				System.out.print(new String(ch).substring(start,length).trim());
				namesBegun = true;
			} else {
				System.out.print(", " + new String(ch).substring(start,length).trim());
			}
		}
	}

}