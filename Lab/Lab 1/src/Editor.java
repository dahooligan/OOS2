
// Datei: Editor.java
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

import labor.GrafischesElement;
import labor.Viereck;

public class Editor extends JPanel 
{
   // Höhe und Weite des Editors. Außerdem die Höhe der
   // Auswahlleiste
   final static int hoehe = 500, weite = 500;
   final static int auswahlLeisteHoehe = 75;

   // Liste für Auswahlelemente
   final static ArrayList< . . . > auswahlElement = new 
                                       . . .;
   // Liste für eingezeichnete Elemente
   ArrayList< . . . > gezeichneteElemente = new                                                             
                                       . . .;

   // Referenz auf das nächste zu zeichnende Objekt
   GrafischesElement naechstesElement = null;

   static public void main(String[] args) 
   {
      // Fenster anfordern
      JFrame frame = new JFrame();
      // Größe setzen
      frame.setSize(weite, hoehe);
      // Nicht vergrößer- oder verkleinerbar
      . . .
      // wenn Schließen-Button gedräckt wird, soll Programm
      // beendet werden
      . . .;
      
      // Editor instanziieren und in Fenster einbetten 
      frame.add(new Editor());

      // Fenster sichtbar machen
      . . .
   }

	
   public Editor() {
      super();
      // Initialisierung der Leistenelemente
      auswahlElement.add(new Viereck(50, hoehe - 50, 25, 25));
      auswahlElement.add(new Dreieck(100, hoehe - 50, 25));

      // Maus-Listener und Programmlogik hinzufügen
      . . .  
      {
         // Maus wurde geklickt ...
         public void . . . 
         {
        	 
            // wenn in Leiste ...
            if (e.getY() >= (hoehe - auswahlLeisteHoehe)) 
            {
               // prüfe, ob LeistenElement gedrückt wurde
               for (GrafischesElement g : auswahlElement) 
               {
                  // LeistenElement wurde gedrückt
                  if (g.liegtPunktImElement(e.getX(), e.getY())) 
                  {
                     // sichere dieses Element temporär
                     naechstesElement = g;
                     break;
                  }
               }
            }
            
            // wenn in Zeichenfläche ...
            else {
            	
               // prüfe, ob ein bestehendes Objekt
               // auf der Zeichenfläche gedrückt wurde ...
               for (GrafischesElement g : gezeichneteElemente) 
               {
            	  // wenn ja ... 
                  if (g.liegtPunktImElement(e.getX(), e.getY()))
                  {
                     // bereite Verschiebung vor.
                     naechstesElement = g;
                     return;
                  }
               }

               // Es wurde kein bestehendes Objekt auf der Zeichen-
               // fläche gedrückt. Prüfe ob vorher ein LeistenElement 
               // ausgewählt wurde und nun eingezeichnet werden soll
               if (naechstesElement instanceof Viereck)
                  gezeichneteElemente.add(new Viereck(e.getX(),
                                              e.getY(), 50, 50));
               else if (naechstesElement instanceof Dreieck)
                  gezeichneteElemente.add(new Dreieck(e.getX(),   
                                                      e.getY(),35));
               // Ausgewähltes Leistenelement wurde eingezeichnet
               // setze nächstesElement zurück
               naechstesElement = null;

               // Neuzeichnen der Darstellung
               repaint();
            }
         }

         // Maus wurde losgelassen
         public void . . .
         {
            // wenn naechstesElement nicht null und kein
            // Auswahlobjekt ist, soll ein Objekt verschoben werden
            if ((naechstesElement != null) && 
                (auswahlElement.indexOf(naechstesElement) < 0))
            {
               naechstesElement.verschiebe(-1 * 
                     (naechstesElement.getX() - e.getX()),     
                     (naechstesElement.getY() - e.getY()) * -1);
               naechstesElement = null;
            }

            repaint();
         }
      });
   }

   // Diese Funktion wird durch repaint() aufgerufen und zeichnet
   // die Oberfläche. Nur mit dieser Methode kann die zeichne()-
   // Funktion von graphischen Elementen aufgerufen werden.
   @Override
   protected void paintComponent(Graphics g) 
   {
      // Komponenten zeichnen
      super.paintComponent(g);

      // zeichne Elemente auf die Fläche
      for (GrafischesElement ge : gezeichneteElemente) 
      {
         // Farbe nach Position setzen ...
         g.setColor(Color.getHSBColor((float) 1.0 / (ge.getX() /  
                                       ((float) ge.getY())), 1, 1));
         // und einzeichnen
         ge.zeichne(g);
      }

      // Auswahlleiste zeichnen ...
      g.setColor(Color.DARK_GRAY);
      g.fillRect(0, hoehe - auswahlLeisteHoehe, weite, hoehe);
		
      // ... mit zugehörigen Elementen
      g.setColor(Color.WHITE);
      for (GrafischesElement ge : auswahlElement) 
      {
         ge.zeichne(g);
      }
   }

}
