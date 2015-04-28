import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Hashtable;

public class IoCContainer {
	// Tabelle zur Ablage der Konfiguration
	Hashtable<Class<?>, Class<?>> typeTable = new Hashtable<Class<?>, Class<?>>();

	// Methode um Abhängigkeiten zu konfigurieren
	public void register(Class<?> dependency, Class<?> implementation) {
		// Konkrete Implementierung wird Abhängigkeitstyp zugeordnet
		typeTable.put(dependency, implementation);
	}

	// Methode um einen Typ unter automatischer Auflösung der Abhängigkeiten zu
	// instanzieren
	public Object resolve(Class classWithDependencies)
			throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		// Ersten Konstruktor der Klasse auswählen (Einschränkung)
		Constructor ctr = classWithDependencies.getConstructors()[0];
		// Liste für die Übergabe der instanziierten Abhängigkeiten an den
		// Konstruktor der Klasse
		ArrayList<Object> initargs = new ArrayList<Object>();

		// Über die Parameter des gewählten Konstruktors iterieren
		for (Class parameter : ctr.getParameterTypes()) {
			// Parameter instanzieren (Achtung: Keine rekursive Auflösung! ->
			// Einschränkung)
			Object arg = typeTable.get(parameter).newInstance();
			// Parameterobjekt in Liste aufnehmen
			initargs.add(arg);
		}

		// Klasse mit automatisch aufgelösten Abhängigkeiten instanzieren und
		// zurückgeben
		
		
		return ctr.newInstance(initargs.toArray());
	
	}
}
