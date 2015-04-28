import java.lang.reflect.InvocationTargetException;


public class Main {

	public static void main(String[] args) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
		// Container instanzieren
		...
		
		// Abh�ngigkeiten konfigurieren
		container.register(IPrintService.class, ConsolePrinter.class);
		...
		
		// Objekt unter automatischer Aufl�sung der Abh�ngigkeiten anlegen
		TextProcessor processor = (TextProcessor) ...
		
		// Objekt benutzen
		processor.process("BeispielInput.txt");
	}
}
