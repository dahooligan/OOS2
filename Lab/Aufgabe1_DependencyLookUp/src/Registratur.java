// Datei: Registratur.java
import java.util.HashMap;
//Die Klasse Registratur kann beliebig viele Objekte verwalten. 
//Jedes Objekt wird durch einen String-Schluessel registriert und 
//kann durch diesen angefordert werden.

public class Registratur {

	private HashMap<String, Object> registeredComponents = new HashMap<String, Object>();
	private static Registratur instance = null;

	public static Registratur getRegistratur() {
		if (instance == null) {
			instance = new Registratur();
		}
		return instance;
	}

	/**
	 * Get the component registered under the name komponentenBezeichnung. This
	 * implementation is NOT case-sensitvive. Components will be registered
	 * under the name komponentenBezeichnung.toLowerCase()
	 * 
	 * @param komponentenBezeichnung
	 *            the name under which the component has been registered
	 * @return the component registered under the name komponentenBezeichnung.
	 *         Will return <code>null</code> if the registered component was
	 *         null or if no component has been registered under that name.
	 */
	public Object getKomponente(String komponentenBezeichnung) {
		return registeredComponents.get(komponentenBezeichnung.toLowerCase());
	}

	/**
	 * Register the component komponente under the name bezeichnung. Component
	 * may be registered with several values for bezeichnung more than once. If
	 * there is already a component registered under the given name the
	 * previously stored combination name / component will be overwritten. This
	 * implementation is not case-sensitive regarding the names under which the
	 * components have been registered.
	 * 
	 * @param bezeichnung
	 *            the name under which the component shall be registered in this
	 *            {@link Registratur}
	 * @param komponente
	 *            the component to register under the name bezeichnung.
	 */
	public void registriereKomponente(String bezeichnung, Object komponente) {
		registeredComponents.put(bezeichnung.toLowerCase(), komponente);
	}
}
