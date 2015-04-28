public class Main {

	static public void main(String[] args) {
		// Bekomme Referenz auf die Registratur
		Registratur registratur = Registratur.getRegistratur();

		// Registriere eine Datenquelle als Komponente
		// ...
		registratur.registriereKomponente("Datenquelle", new DatenquelleImpl());
		new Plotter().plot();
		// plotten ...
	}

}
