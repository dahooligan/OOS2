package oos2.xml.lab2.sax.dausmann;
/* File: RZCountHandler.java
 * $Id: RZCountHandler.java,v 1.2 2004/09/14 13:25:45 alex Exp $
 */ 
import java.util.*;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Diese Klasse implementiert einen SAX Handler zum
 * Zählen in RZ Dokumenten.
 * 
 * @author Alexander Koenig - koenig@fht-esslingen.de
 */
public class RZCountHandler extends DefaultHandler
{
        int elements = 0;
        int attributes = 0;
          int contents = 0;
  
  public void startElement(java.lang.String uri,
                           java.lang.String localName,
                           java.lang.String qName,
                           Attributes attrs)
  {
        elements++;
        attributes = attributes + attrs.getLength();
    
  }

  public void endDocument()
  {
        System.out.println( "Elements: " + elements);
        System.out.println( "Attributes: " + attributes);
        System.out.println( "Textcontent: " + contents);
        
  }

  public void characters(char[] ch, int start, int length)
  {

        contents++;
  }
  
 }

