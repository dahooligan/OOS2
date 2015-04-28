public class TextProcessor {
	private IReader reader;
	private IPrintService printService;
	
	public TextProcessor(IReader reader, IPrintService printService) {
		this.reader = reader;
		this.printService = printService;
	}
	
	public void process(String path) {
		String text = this.reader.read(path);
		this.printService.print("Inhalt der Datei " + path + ":");
		this.printService.print(text);
	}
}
