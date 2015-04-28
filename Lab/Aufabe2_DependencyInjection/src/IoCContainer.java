import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Hashtable;

public class IoCContainer {
	// Tabelle zur Ablage der Konfiguration
	Hashtable<Class<?>, Class<?>> typeTable = new Hashtable<Class<?>, Class<?>>();

	// Methode um Abh�ngigkeiten zu konfigurieren
	public void register(Class<?> dependency, Class<?> implementation) {
		// Konkrete Implementierung wird Abh�ngigkeitstyp zugeordnet
		typeTable.put(dependency, implementation);
	}

	// Methode um einen Typ unter automatischer Aufl�sung der Abh�ngigkeiten zu
	// instanzieren
	public Object resolve(Class classWithDependencies)
			throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		// Ersten Konstruktor der Klasse ausw�hlen (Einschr�nkung)
		Constructor ctr = classWithDependencies.getConstructors()[0];
		// Liste f�r die �bergabe der instanziierten Abh�ngigkeiten an den
		// Konstruktor der Klasse
		ArrayList<Object> initargs = new ArrayList<Object>();

		// �ber die Parameter des gew�hlten Konstruktors iterieren
		for (Class parameter : ctr.getParameterTypes()) {
			// Parameter instanzieren (Achtung: Keine rekursive Aufl�sung! ->
			// Einschr�nkung)
			Object arg = typeTable.get(parameter).newInstance();
			// Parameterobjekt in Liste aufnehmen
			initargs.add(arg);
		}

		// Klasse mit automatisch aufgel�sten Abh�ngigkeiten instanzieren und
		// zur�ckgeben
		
		
		return ctr.newInstance(initargs.toArray());
	
	}
}
