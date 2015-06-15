package oos2.xml.lab2.dom.dausmann;
/* File: RZDOMWriter.java
 * $Id: RZDOMWriter.java,v 1.2 2004/09/14 13:25:45 alex Exp $
 */ 

import java.io.*;
import java.net.InetAddress;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.w3c.dom.ls.*;


/**
 * Diese Klasse parst RZ Dokumente mittels DOM. Wird
 * die main() Methode mit zwei Argumenten aufgerufen,
 * so wird das Dokument modifiziert und serialisiert.
 * 
 * @author Alexander Koenig - koenig@fht-esslingen.de
 */
public class RZDOMTransform {


  /**
   * Hinzufügen eines Rechners an den ersten RZ-Knoten.
   */
  public static void modifyRZ(Document doc, Node node)
  {
    for (int i=0; i<node.getChildNodes().getLength(); i++) {
      Node child=node.getChildNodes().item(i);
      
      if (child.getNodeType()==Node.ELEMENT_NODE) {
        
        
        Element rechner = doc.createElement("Rechner");
        rechner.setAttribute("Typ", "Workstation");        
        InetAddress localhost = null;
        
        try {
                localhost = InetAddress.getLocalHost(); 
        } catch (Exception e) {
                System.err.println("Konnte Host nicht auflösen: "+e.getLocalizedMessage());
        }
        rechner.setAttribute("IP", localhost.toString().split("/")[1]);
        
        child.appendChild(rechner);
        
        Element name = doc.createElement("Name");
        Text text = doc.createTextNode(localhost.getHostName());
        name.appendChild(text);
        rechner.appendChild(name);
       
        Element benutzer = doc.createElement("Benutzer");
        text = doc.createTextNode(System.getProperty("user.name"));
        benutzer.appendChild(text);
        rechner.appendChild(benutzer);

        Element prozanz = doc.createElement("Prozanz");
        text = doc.createTextNode(Integer.toString(Runtime.getRuntime().availableProcessors()));
        prozanz.appendChild(text);
        rechner.appendChild(prozanz);

        break;
      }
    }
  }
  
  private static void modifyDocument(Document doc)
  {
    for (int i=0; i<doc.getChildNodes().getLength(); i++) {
      Node child=doc.getChildNodes().item(i);
      
      if (child.getNodeType()==Node.ELEMENT_NODE) {
        modifyRZ(doc, child);
        break;
      }
    }
  }
  
  /**
   * Dokument mittles Xerces Serializer schreiben, 
   * @param filename Name der Datei.
   * @param doc Zu schreibendes Dokument.
   */
  public static void store(String filename, Document doc) {
     //write the output to specified file.

     try {
        Transformer trans = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource (doc);
        FileOutputStream os = new FileOutputStream (filename);
        StreamResult result = new StreamResult (os);
        trans.transform(source, result);
        } catch (IOException ioe) {}
          catch (TransformerConfigurationException tce) {}
          catch (TransformerException tfe) {}

    }
  /**
   * main() Methode - mit einem Kommandozeilen-Argument
   * (Input-Filename) aufrufen für Aufgabe 5.1, mit zweien
   * (zusätzlicher Filename für Output) für 5.2 und 5.3.
   */
  public static void main(String args[])
    throws Exception
  {
    /* Parser instanziern */
    DocumentBuilderFactory myFactory=DocumentBuilderFactory.newInstance();
    myFactory.setValidating(true);
    DocumentBuilder myParser = myFactory.newDocumentBuilder();
    
    /* File parsen - Baum-Aufbau */     
    Document myDoc = myParser.parse("rz.xml");
    
    modifyDocument(myDoc);
    
    store("rznew.xml", myDoc);

  }
}

