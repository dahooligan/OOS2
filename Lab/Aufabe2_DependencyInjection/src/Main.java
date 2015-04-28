import java.lang.reflect.InvocationTargetException;


public class Main {

	public static void main(String[] args) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
		// Container instanzieren
		...
		
		// Abhängigkeiten konfigurieren
		container.register(IPrintService.class, ConsolePrinter.class);
		...
		
		// Objekt unter automatischer Auflösung der Abhängigkeiten anlegen
		TextProcessor processor = (TextProcessor) ...
		
		// Objekt benutzen
		processor.process("BeispielInput.txt");
	}
}
