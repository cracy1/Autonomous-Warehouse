package aw.file;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class ReadFile {

	private String path;

	public ReadFile(String file_path) {

		path = file_path;

	}

	/**
	 * Returns the number of lines in a file
	 * 
	 * @return the number of lines in a file
	 * @throws IOException
	 */
	public int countLines() throws IOException {

		FileReader fileToRead = new FileReader(path);
		BufferedReader bf = new BufferedReader(fileToRead);

		int numberOfLines = 0;

		while ((bf.readLine()) != null) {
			numberOfLines++;

		}

		bf.close();

		return numberOfLines;

	}

	/**
	 * 
	 * @return an array of strings, each row being stored as an entry in the
	 *         array
	 * @throws IOException
	 */
	public String[] OpenFile() throws IOException {

		FileReader fr = new FileReader(path);
		BufferedReader textReader = new BufferedReader(fr);

		int numberOfLines = countLines();

		String[] items = new String[numberOfLines];

		for (int i = 0; i < numberOfLines; i++) {

			items[i] = textReader.readLine();

		}
		textReader.close();
		return items;

	}

}
