package oos2.xml.lab2.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class WriteXMLToConsoleHandler extends DefaultHandler {

	private int depth = 0;

	private void printDelimited(String string, int offset, char delimiter) {
		for (int i = 0; i <= offset; i++) {
			System.out.print(delimiter);
		}
		System.out.println(string);
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		printDelimited(new String(ch).trim(), depth, ' ');
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		StringBuilder builder = new StringBuilder();
		builder.append("<");
		builder.append(qName);

		builder.append(attributes.getLength() == 0 ? "" : " ");
		for (int i = 0; i < attributes.getLength(); i++) {
			builder.append(attributes.getLocalName(i));
			builder.append("=\"");
			builder.append(attributes.getValue(i));
			builder.append("\"");
		}
		builder.append(">");

		printDelimited(builder.toString(), depth, ' ');
		depth++;
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		depth--;
		printDelimited("</" + qName + ">", depth, ' ');
	}

}