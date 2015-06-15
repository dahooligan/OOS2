package oos2.xml.lab2.sax.dausmann;
/* File: RZSearchHandler.java
 * $Id: RZSearchHandler.java,v 1.2 2004/09/14 13:25:45 alex Exp $
 */ 
import java.util.*;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Diese Klasse implementiert einen SAX Handler zum
 * Suchen in RZ Dokumenten mit der Fehlerbehandlung
 * 
 * @author Alexander Koenig - koenig@fht-esslingen.de
 */
public class RZErrorHandler extends DefaultHandler
{
        String domain = "somewhere.com";
        Boolean checkDomain = false;
        Boolean foundDomain = false;
        Boolean addUser = false;
        Set<String> users = new HashSet<String>();
  
  public void startElement(java.lang.String uri,
                           java.lang.String localName,
                           java.lang.String qName,
                           Attributes attributes)
  {
        if (qName.equals("Domainname")) {
           checkDomain = true;
           return;
        }
        if (foundDomain && qName.equals("Benutzer")) {
           addUser = true;
           return;
        }
    
  }

  public void endElement(java.lang.String uri,
                         java.lang.String localName,
                         java.lang.String qName)
  {
        if (foundDomain && qName.equals("Domain")) {
                foundDomain = false; 

                System.out.printf ("Users: ");
                for (String user : users)
                        System.out.printf (user + ", ");
                System.out.println ();
        
                return;
        }
        if (qName.equals("Benutzer")) {addUser = false; return;}

  }

  public void characters(char[] ch, int start, int length)
  {
        String data = new String(ch, start, length);
        data = data.trim();

        if (checkDomain && data.equals(domain)) {
                foundDomain = true;
                System.out.println ("Domain: " + domain); 
                checkDomain = false; 
                return;
        }
        if (addUser) {users.add(data); return;}
  }

  public void warning (SAXParseException e) throws SAXException
  {
        String msg = new String ( "Line: " + e.getLineNumber() +
                                  ", Column: " + e.getColumnNumber() +
                                  ", Error: " + e.getMessage());
        throw new SAXException (msg);
  }
  public void error (SAXParseException e) throws SAXException
  {
        String msg = new String ( "Line: " + e.getLineNumber() +
                                  ", Column: " + e.getColumnNumber() +
                                  ", Error: " + e.getMessage());
        throw new SAXException (msg);
  }
  public void fatalError (SAXParseException e) throws SAXException
  {
        String msg = new String ( "Line: " + e.getLineNumber() +
                                  ", Column: " + e.getColumnNumber() +
                                  ", Error: " + e.getMessage());
        throw new SAXException (msg);

  }
}

