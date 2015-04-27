
// Datei: Dreieck.java
import java.awt.Graphics;
import java.awt.Polygon;

. . . public class Dreieck . . . 
{
   int seitenlaenge;
   Polygon dreieck;

  . . . Dreieck(int x, int y, int seitenlaenge) 
   {
      . . .
      . . .
   }

   public . . . getDreieck(int seitenlaenge) 
   {
      // Da Swing keine Methode zur Erzeugung eines Dreiecks
      // anbietet, muss das Dreieck selbst berechnet und
      // anschließend als Polygon zurückgegeben werden
      int xArr[] = {getX()-(seitenlaenge / 2),
                    getX()+(seitenlaenge / 2),getX()};
      int yArr[] = {getY()+(seitenlaenge / 2),getY()+(seitenlaenge/2), 
                    getY()-(seitenlaenge / 2)};

      // Neues Polygon zurückgeben
      . . .(xArr, yArr, 3);
   }

	@Override
	. . .  (Graphics g)
	{
		dreieck = getDreieck(seitenlaenge);
		. . . (dreieck);
	}

	@Override
	. . .  (int x, int y) {
		. . . dreieck . . . (x, y);
	}
}
