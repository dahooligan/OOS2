package labor;
// Datei: GrafischesElement.java
import java.awt.Graphics;

public abstract class GrafischesElement {
	// Schwerpunktskoordinaten
	protected int x, y;

	// hier können weitere Eigenschaften, wie bspw. Füllfarbe
	// definiert werden

	// Konstruktor mit Schwerpunktskoordinaten

	public GrafischesElement(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// TODO: Soll das Grafische Element jeweils um x und y verschoben werden
	// oder sollen die Parameter die neuen Schwerpunktkoordinaten sein??

	// Methode zur Verschiebung des Schwerpunktes; durch UML als
	// leaf-Methode deklariert.
	public final void verschiebe(int x, int y) {
		this.x += x;
		this.y += y;

	}

	final public int getX() {
		return x;
	}

	final public int getY() {
		return y;
	}

	// Methodenkopf der Darstellungsroutine; Graphics ist eine
	// Klasse welche das Auftragen von Grafiken auf Komponenten
	// erlaubt. In der zeichne()-Methode wird anschließend das
	// darzustellende Element auf das Graphics-Objekt aufgetragen.
	public abstract void zeichne(Graphics g);

	// Methode zum Überprüfen, ob Punkt im Element liegt
	public abstract boolean liegtPunktImElement(int x, int y);
}
