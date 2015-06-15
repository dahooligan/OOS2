package oos2.xml.lab2.sax.dausmann;


/* File: RZA1SAXReader.java
 * $Id: RZA1SAXReader.java,v 1.2 2004/09/14 13:25:45 alex Exp $
 */ 

import javax.xml.parsers.*;
import java.io.*;
/**
 * Diese Klasse parst RZ Dokumente mittels SAX.
 * 
 * @author Alexander Koenig - koenig@fht-esslingen.de
 */
public class RZA1SAXReader
{
  public static void main(String args[])
    throws Exception
  {
    /* Parser instanzieren */
    SAXParserFactory factory = SAXParserFactory.newInstance();
    factory.setValidating(true);
    SAXParser parser = factory.newSAXParser();

    /* Handler instanzieren */
    RZCountHandler handler = new RZCountHandler();
    

    /* Parsen starten. */
    parser.parse(new File("rz.xml"), handler);

  }
}
