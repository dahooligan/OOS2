package labor;
// Datei: Dreieck.java
import java.awt.Graphics;
import java.awt.Polygon;

final public class Dreieck extends GrafischesElement {
	int seitenlaenge;
	Polygon dreieck;

	public Dreieck(int x, int y, int seitenlaenge) {
		super(x, y);
		this.seitenlaenge = seitenlaenge;
	}

	public Polygon getDreieck(int seitenlaenge) {
		// Da Swing keine Methode zur Erzeugung eines Dreiecks
		// anbietet, muss das Dreieck selbst berechnet und
		// anschließend als Polygon zurückgegeben werden
		int xArr[] = { getX() - (seitenlaenge / 2),
				getX() + (seitenlaenge / 2), getX() };
		int yArr[] = { getY() + (seitenlaenge / 2),
				getY() + (seitenlaenge / 2), getY() - (seitenlaenge / 2) };

		// Neues Polygon zurückgeben
		return new Polygon(xArr, yArr, 3);
	}

	@Override
	public void zeichne(Graphics g) {
		dreieck = getDreieck(seitenlaenge);
		g.drawPolygon(dreieck);
	}

	@Override
	public boolean liegtPunktImElement(int x, int y) {
		// impliziter cast macht kein problem weil keine information verloren
		// geht
		return dreieck.contains(x, y);
	}
}
