import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TXTReader implements IReader {

	@Override
	public String read(String path) {
		File file = new File(path);

		if (!file.canRead() || !file.isFile()) {
			System.err.println("File at " + path + " not found.");
			System.exit(0);
		}

		FileReader fr = null;
		int c;
		StringBuffer buff = new StringBuffer();
		try {
			fr = new FileReader(file);
			while ((c = fr.read()) != -1) {
				buff.append((char) c);
			}
			fr.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return buff.toString();
	}

}
