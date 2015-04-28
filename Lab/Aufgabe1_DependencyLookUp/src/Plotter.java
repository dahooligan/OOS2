import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Plotter extends JPanel {

	final private int plotterLength, plotterHeight;

	// Verschiebung der Punkte und Linien, zwecks Übersichtlichkeit
	private int yVerschiebung = 5, xVerschiebung = 25;
	private JFrame frame = new JFrame();

   // Die Klasse Plotter besitzt eine Referenz auf eine Datenquelle
	private IDatenquelle datenquelle;

	public Plotter() {
		this(800,400); // Standard-Größe
	}

	public Plotter(int length, int height) {
		plotterLength = length;
		plotterHeight = height;
		
		// Die Größe von Frame ist die absolute Größe des Fensters
		// Daher wird durch Offsets Platz geschaffen, um
		// jeden Punkt ohne Verschiebung anzeigen zu können
		frame.setSize(plotterLength + 40, plotterHeight + 50);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
	}

	public void plot() {
	   // Datenquelle von der Registratur anfordern
      ...

      // Wenn keine Datenquelle in der Registratur vorhanden ist, 
      // beende den Plotvorgang
      ...
      
      // Durch den Aufruf der Methode setVisible() wird veranlasst,
      // dass die Methode paintComponent() aufgerufen wird und
      // damit das Fenster gezeichnet wird.
      frame.setVisible(true);
	}

	public void setDatenquelle(IDatenquelle datenquelle) {
		this.datenquelle = datenquelle;
	}

	@Override
	protected void paintComponent(Graphics g) {
		ArrayList<Point> datenreihe = datenquelle.getDatenreihe();

		// Wenn weniger als zwei Punkte in Datenreihe, verlasse Funktion
		if (datenreihe.size() <= 1)
			return;

		// x- und y-Punkte-Array erstellen
		ArrayList<Integer> xPunkte = new ArrayList<Integer>();
		ArrayList<Integer> yPunkte = new ArrayList<Integer>();
		for (Point p : datenreihe) {
			xPunkte.add(p.x);
			yPunkte.add(p.y);
		}

		// Minimum und Maximum-Punkte der x- und y-Reihe bestimmen und sichern
		Point minPunkte = new Point(Collections.min(xPunkte), Collections.min(yPunkte));
		Point maxPunkte = new Point(Collections.max(xPunkte), Collections.max(yPunkte));

		// Offset errechnen, um Position der Punkte auf das Fenster anzupassen
		Point offset = new Point(plotterLength, plotterHeight);
		// Differenz der Min- und Max-Werte der x- und y-Wertreihe pruefen, damit nicht durch 0 geteilt wird.
		if ((Math.abs(minPunkte.x) + maxPunkte.x) != 0)
			offset.x = (int) (plotterLength / (Math.abs(minPunkte.x) + maxPunkte.x));
		if ((Math.abs(minPunkte.y) + maxPunkte.y) != 0)
			offset.y = (int) (plotterHeight / (Math.abs(minPunkte.y) + maxPunkte.y));

		// Plot-Funktion
		for (int i = 0; i < datenreihe.size(); i++) {
			// Linie zwischen Punkt und nachfolgenden Punkt einzeichnen
			if (i < (datenreihe.size() - 1)) {
				g.setColor(Color.RED);
				g.drawLine((Math.abs(minPunkte.x) + xPunkte.get(i)) * offset.x + xVerschiebung,
						plotterHeight - (Math.abs(minPunkte.y) + yPunkte.get(i)) * offset.y + yVerschiebung,
						(Math.abs(minPunkte.x) + xPunkte.get(i + 1)) * offset.x + xVerschiebung,
						plotterHeight - (Math.abs(minPunkte.y) + yPunkte.get(i + 1)) * offset.y + yVerschiebung);
			}

			// Beschriftung einzeichen
			g.setColor(Color.BLACK);

			// Beschriftung der y-Achsenpunkte einzeichnen
			g.drawLine(0, plotterHeight - (Math.abs(minPunkte.y) + yPunkte.get(i)) * offset.y + yVerschiebung, 1,
					plotterHeight - (Math.abs(minPunkte.y) + yPunkte.get(i)) * offset.y + yVerschiebung);
			g.drawString("" + yPunkte.get(i), 3,
					plotterHeight + 5 - (Math.abs(Collections.min(yPunkte)) + yPunkte.get(i)) * offset.y
							+ yVerschiebung);

			// Beschriftung der x-Achsenpunkte einzeichnen
			g.drawLine((Math.abs(minPunkte.x) + xPunkte.get(i)) * offset.x + xVerschiebung, plotterHeight + 25,
					(Math.abs(minPunkte.x) + xPunkte.get(i)) * offset.x + xVerschiebung, plotterHeight + 20);
			g.drawString("" + xPunkte.get(i), (Math.abs(minPunkte.x) + xPunkte.get(i)) * offset.x + xVerschiebung,
					plotterHeight + 20);

			// Punkt markieren
			g.drawOval((Math.abs(minPunkte.x) + xPunkte.get(i)) * offset.x + xVerschiebung - 3,
					plotterHeight - (Math.abs(minPunkte.y) + yPunkte.get(i)) * offset.y + yVerschiebung - 3, 6, 6);
		}
	}
}
