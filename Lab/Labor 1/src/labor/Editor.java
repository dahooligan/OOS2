package labor;

// Datei: Editor.java
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Editor extends JPanel {

	private static final long serialVersionUID = 1L;

	static private Editor editor = null;

	// H�he und Weite des Editors. Au�erdem die H�he der
	// Auswahlleiste
	final static int hoehe = 500, weite = 500;
	final static int auswahlLeisteHoehe = 75;

	// Liste f�r Auswahlelemente
	final static ArrayList<GrafischesElement> auswahlElement = new ArrayList<>();
	// Liste f�r eingezeichnete Elemente
	ArrayList<GrafischesElement> gezeichneteElemente = new ArrayList<>();

	// Referenz auf das n�chste zu zeichnende Objekt
	GrafischesElement naechstesElement = null;

	public Editor() {
		super();
		// Initialisierung der Leistenelemente
		auswahlElement.add(new Viereck(50, hoehe - 50, 25, 25));
		auswahlElement.add(new Dreieck(100, hoehe - 50, 25));

		// Maus-Listener und Programmlogik hinzuf�gen

		addMouseListener(new MouseListener() {

			// Maus wurde geklickt ...
			public void mousePressed(MouseEvent e) {

				// wenn in Leiste ...
				if (e.getY() >= (hoehe - auswahlLeisteHoehe)) {
					// pr�fe, ob LeistenElement gedr�ckt wurde
					for (GrafischesElement g : auswahlElement) {
						// LeistenElement wurde gedr�ckt
						if (g.liegtPunktImElement(e.getX(), e.getY())) {
							// sichere dieses Element tempor�r
							naechstesElement = g;
							break;
						}
					}
				}

				// wenn in Zeichenfl�che ...
				else {

					// pr�fe, ob ein bestehendes Objekt
					// auf der Zeichenfl�che gedr�ckt wurde ...
					for (GrafischesElement g : gezeichneteElemente) {
						// wenn ja ...
						if (g.liegtPunktImElement(e.getX(), e.getY())) {
							// bereite Verschiebung vor.
							naechstesElement = g;
							return;
						}
					}

					// Es wurde kein bestehendes Objekt auf der Zeichen-
					// fl�che gedr�ckt. Pr�fe ob vorher ein LeistenElement
					// ausgew�hlt wurde und nun eingezeichnet werden soll
					if (naechstesElement instanceof Viereck)
						gezeichneteElemente.add(new Viereck(e.getX(), e.getY(),
								50, 50));
					else if (naechstesElement instanceof Dreieck)
						gezeichneteElemente.add(new Dreieck(e.getX(), e.getY(),
								35));
					// Ausgew�hltes Leistenelement wurde eingezeichnet
					// setze n�chstesElement zur�ck
					naechstesElement = null;

					// Neuzeichnen der Darstellung
					repaint();
				}
			}

			// Maus wurde losgelassen
			public void mouseReleased(MouseEvent e) {
				// wenn naechstesElement nicht null und kein
				// Auswahlobjekt ist, soll ein Objekt verschoben werden
				if ((naechstesElement != null)
						&& (auswahlElement.indexOf(naechstesElement) < 0)) {
					naechstesElement.verschiebe(-1
							* (naechstesElement.getX() - e.getX()),
							(naechstesElement.getY() - e.getY()) * -1);
					naechstesElement = null;
				}

				repaint();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// ignore this event
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// ignore this event
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// ignore this event
			}
		});
	}

	// Diese Funktion wird durch repaint() aufgerufen und zeichnet
	// die Oberfl�che. Nur mit dieser Methode kann die zeichne()-
	// Funktion von graphischen Elementen aufgerufen werden.
	@Override
	protected void paintComponent(Graphics g) {
		// Komponenten zeichnen
		super.paintComponent(g);

		// zeichne Elemente auf die Fl�che
		for (GrafischesElement ge : gezeichneteElemente) {
			// Farbe nach Position setzen ...
			g.setColor(Color.getHSBColor(
					(float) 1.0 / (ge.getX() / ((float) ge.getY())), 1, 1));
			// und einzeichnen
			ge.zeichne(g);
		}

		// Auswahlleiste zeichnen ...
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, hoehe - auswahlLeisteHoehe, weite, hoehe);

		// ... mit zugeh�rigen Elementen
		g.setColor(Color.WHITE);
		for (GrafischesElement ge : auswahlElement) {
			ge.zeichne(g);
		}
	}

	static public void main(String[] args) {
		// Fenster anfordern
		JFrame frame = new JFrame();
		// Gr��e setzen
		frame.setSize(weite, hoehe);
		// Nicht vergr��er- oder verkleinerbar
		frame.setResizable(false);
		// wenn Schlie�en-Button gedr�ckt wird, soll Programm
		// beendet werden
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Editor instanziieren und in Fenster einbetten
		frame.add(new Editor());

		// Fenster sichtbar machen
		frame.setVisible(true);
	}

}
