package oos2.xml.lab2.sax.dausmann;
/* File: RZA3SAXReader.java
 * $Id: RZA3SAXReader.java,v 1.2 2004/09/14 13:25:45 alex Exp $
 */ 

import javax.xml.parsers.*;
import java.io.*;
/**
 * Diese Klasse parst RZ Dokumente mittels SAX.
 * 
 * @author Alexander Koenig - koenig@fht-esslingen.de
 */
public class RZA3SAXReader
{
  public static void main(String args[])
    throws Exception
  {
    /* Parser instanzieren */
    SAXParserFactory factory = SAXParserFactory.newInstance();
    factory.setValidating(true);
    SAXParser parser = factory.newSAXParser();

    /* Handler instanzieren */
    RZErrorHandler handler = new RZErrorHandler();
    

    /* Parsen starten. */
    parser.parse(new File("rz.xml"), handler);

  }
}
