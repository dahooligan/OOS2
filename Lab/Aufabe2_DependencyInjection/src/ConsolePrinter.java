public class ConsolePrinter implements IPrintService {

	@Override
	public void print(String message) {
		System.out.println(message);		
	}

}
