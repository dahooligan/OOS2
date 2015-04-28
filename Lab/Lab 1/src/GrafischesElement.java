
// Datei: GrafischesElement.java
import java.awt.Graphics;

public . . . class GrafischesElement
{
   // Schwerpunktskoordinaten
   protected int x, y;

   // hier können weitere Eigenschaften, wie bspw. Füllfarbe
   // definiert werden
   
   // Konstruktor mit Schwerpunktskoordinaten
   
   . . .
   
   // Methode zur Verschiebung des Schwerpunktes; durch . . . als
   // leaf-Methode deklariert.
   . . . public void verschiebe(int x, int y)
   {
      . . .
      
   }

   . . . public int getX()
   {
      return x;
   }

   . . . public int getY()
   {
      return y;
   }

   // Methodenkopf der Darstellungsroutine; Graphics ist eine
   // Klasse welche das Auftragen von Grafiken auf Komponenten
   // erlaubt. In der zeichne()-Methode wird anschließend das
   // darzustellende Element auf das Graphics-Objekt aufgetragen.
   public . . . void zeichne(Graphics g);

   // Methode zum Überprüfen, ob Punkt im Element liegt
   public . . . boolean liegtPunktImElement (int x, int y);
}
