package oos2.xml.lab2.sax;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyOwnParser {

	private SAXParserFactory parserFactory = SAXParserFactory.newInstance();
	private SAXParser parser;

	public MyOwnParser() {
		// prepare parserFactory
		try {
			parserFactory.setNamespaceAware(false);
			parserFactory.setValidating(true);
			parser = parserFactory.newSAXParser();
		} catch (ParserConfigurationException e) {
			System.err.println("Error while configuring the parser.");
			e.printStackTrace();
		} catch (SAXException e) {
			System.err.println("Error while parsing.");
			e.printStackTrace();
		}
	}

	public void parse(String xmlFileName, DefaultHandler handler)
			throws SAXException {
		if (xmlFileName == null || xmlFileName.trim().length() == 0) {
			System.err.println("xmlPath must not be empty");
			return;
		}
		try {
			parser.parse(new File(xmlFileName), handler);
		} catch (IOException e) {
			System.err
					.println("Error while reading the file at " + xmlFileName);
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		MyOwnParser myOwnParser = new MyOwnParser();
		try {
			myOwnParser.parse("res/rz.xml", new WriteXMLToConsoleHandler());
			myOwnParser.parse("res/rz_extended.xml",
					new WriteXMLToConsoleHandler());
		} catch (SAXException e1) {
			System.err.println("This should not have happened.");
			e1.printStackTrace();
		}

		try {
			myOwnParser.parse("res/rz_corrupt.xml",
					new WriteXMLToConsoleHandler());
		} catch (SAXException e) {
			System.err.println("corrupt document caused exception as expected");
		}

		try {
			myOwnParser.parse("res/rz_extended.xml",
					new NamesInDomainToConsoleHandler());
		} catch (SAXException e) {
			System.err.println("this should not have happened");
		}
	}
}
