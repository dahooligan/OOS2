// Datei: Registratur.java
import java.util.HashMap;
//Die Klasse Registratur kann beliebig viele Objekte verwalten. 
//Jedes Objekt wird durch einen String-Schluessel registriert und 
//kann durch diesen angefordert werden.
public class Registratur {

    private HashMap<String, Object> komponenten = new HashMap<String, Object>();
    private static Registratur registratur = null;

   public static Registratur getRegistratur() {
        // ...
    }

    public Object getKomponente(String komponentenBezeichnung) {
        // ...
    }

    public void registriereKomponente(String bezeichnung, Object komponente) {
        // ...
    }
}
