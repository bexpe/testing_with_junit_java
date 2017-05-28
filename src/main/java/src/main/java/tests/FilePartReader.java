package src.main.java.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * Created by beata on 25.05.17.
 */
public class FilePartReader {
	private String filePath = "";
	private Integer fromLine;
	private Integer toLine;

	public FilePartReader() {
	}

	public void setup(String filePath, Integer fromLine, Integer toLine) {
		this.filePath = filePath;
		this.fromLine = fromLine;
		this.toLine = toLine;
		if (toLine < fromLine || fromLine < 1) {
			throw new IllegalArgumentException();
		}
	}

	public String read(String filePath) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(filePath));
		String text = scanner.useDelimiter("\\A").next();
		scanner.close();
		return text;
	}

	public String readLines() throws FileNotFoundException {
		StringBuilder sb = new StringBuilder();
		FilePartReader lines = new FilePartReader();
		this.filePath.toString();
		String allFile = lines.read("src/main/resources/test_data.txt");
		String[] allFileLines = allFile.split("\r\n|\r|\n");
		int i = 1;
		while (i <= allFileLines.length) {
			for (String line: allFileLines) {

				if ( i >= this.fromLine && i <= this.toLine) {
					sb.append(line + "\n");
				}
				i++;
			}
		}
	return sb.toString();
	}
}
