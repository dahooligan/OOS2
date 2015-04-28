
// Datei: Viereck.java
import java.awt.Graphics;
import java.awt.Rectangle;

public class Viereck . . .
{
   int laenge, hoehe;
   Rectangle viereck;

   . . . Viereck(int x, int y, int laenge, int hoehe)
   {
      . . .
      . . .
      . . .
   }

   // Implementierte Methoden der Basisklasse
   @Override
   . . . (Graphics g)
   {
      // Viereck erstellen
      viereck = new Rectangle(this.x - (int) (0.5 * laenge), this.y
                - (int) (0.5 * hoehe), laenge, hoehe);

      // Viereck zeichnen
      g.fillRect(viereck.x, viereck.y, viereck.width,
                 viereck.height);
   }

   @Override
   . . . (int x, int y)
   {
      . . . Viereck . . . (x,y);
   }
}
