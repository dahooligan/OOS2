package oos2.xml.lab1;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class SaxDinge {

	
	
	public static void main(String[] args) {
		try {
			XMLReader parser = XMLReaderFactory.createXMLReader();
		} catch (SAXException e) {

			
			
			e.printStackTrace();
		}
		
	}
	
	
}
