package oos2.xml.lab2.dom;
import org.w3c.dom.*;
import javax.xml.parsers.*;

public class RZDOMAnalyzer{

  public static void analyzeRechner(Node node, String domainname)
  {
    for (int i=0; i<node.getChildNodes().getLength(); i++)
    {
      Node child=node.getChildNodes().item(i);
      
      if (child.getNodeType()==Node.ELEMENT_NODE)
      {
          if (child.getNodeName().equals("Name"))
          {
            System.out.print(child.getFirstChild().getNodeValue() + "." + domainname  + ": ");
          }
          else if (child.getNodeName().equals("Benutzer"))
          {
            System.out.print("(user: " + child.getFirstChild().getNodeValue() + ") ");
          }
          else if (child.getNodeName().equals("Prozanz"))
          {
            System.out.print("(cpu's: " + child.getFirstChild().getNodeValue() + ") ");
          }
          else if (child.getNodeName().equals("Speicher"))
          {
            System.out.print("(ram: " + child.getFirstChild().getNodeValue()+ ")");
          }
      }
    }
    System.out.println("");
  }

  public static void analyzeDomain(Node node)
  {
    String domainname=new String("UNDEFINED");
    
    for (int i=0; i<node.getChildNodes().getLength(); i++)
    {
      Node child=node.getChildNodes().item(i);
      
      if (child.getNodeType()==Node.ELEMENT_NODE)
      {
          if (child.getNodeName().equals("Domainname"))
          {
            domainname=new String(child.getFirstChild().getNodeValue());
          }
          else
          {
            analyzeRechner(child, domainname);
          }
      }
    }
  }


  public static void analyzeRZ(Node node)
  {
    for (int i=0; i<node.getChildNodes().getLength(); i++)
    {
      Node child=node.getChildNodes().item(i);
      
      if (child.getNodeType()==Node.ELEMENT_NODE)
          analyzeDomain(child);
    }
  }

  public static void analyzeDocument(Document doc)
  {
    for (int i=0; i<doc.getChildNodes().getLength(); i++)
    {
      Node child=doc.getChildNodes().item(i);
      
      if (child.getNodeType()==Node.ELEMENT_NODE)
      {
        analyzeRZ(child);
      }
    }
  }
  
  public static void main(String args[])
    throws Exception
  {
    /* Parser instanziern */
    DocumentBuilderFactory myFactory = DocumentBuilderFactory.newInstance();
    myFactory.setValidating(true);
    DocumentBuilder myParser = myFactory.newDocumentBuilder();
    
    /* File parsen - Baum-Aufbau */     
    Document myDoc = myParser.parse("rz.xml");
    
    analyzeDocument(myDoc);
  }
}
