package labor;
// Datei: Viereck.java
import java.awt.Graphics;
import java.awt.Rectangle;

public class Viereck extends GrafischesElement {
	int laenge, hoehe;
	Rectangle viereck;

	public Viereck(int x, int y, int laenge, int hoehe) {
		super(x, y);
		this.laenge = laenge;
		this.hoehe = hoehe;
	}

	// Implementierte Methoden der Basisklasse
	@Override
	public void zeichne(Graphics g) {
		// Viereck erstellen
		viereck = new Rectangle(this.x - (int) (0.5 * laenge), this.y
				- (int) (0.5 * hoehe), laenge, hoehe);

		// Viereck zeichnen
		g.fillRect(viereck.x, viereck.y, viereck.width, viereck.height);
	}

	@Override
	public boolean liegtPunktImElement(int x, int y) {
		// impliziter cast macht kein problem weil keine information verloren
		// geht
		return viereck.contains(x, y);
	}
}
