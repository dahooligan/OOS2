import java.lang.reflect.InvocationTargetException;

public class Main {

	public static void main(String[] args) throws IllegalArgumentException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException {
		// Container instanzieren
		IoCContainer container = new IoCContainer();

		// Abhängigkeiten konfigurieren
		container.register(IPrintService.class, ConsolePrinter.class);
		container.register(IReader.class, TXTReader.class);
		//

		// Objekt unter automatischer Auflösung der Abhängigkeiten anlegen
		TextProcessor processor = (TextProcessor) container
				.resolve(TextProcessor.class);

		// Objekt benutzen
		processor.process("aBeispielInput.txt");
	}
}
