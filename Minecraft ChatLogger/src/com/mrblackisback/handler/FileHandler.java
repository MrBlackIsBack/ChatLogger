package com.mrblackisback.handler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {

	private File file;

	public FileHandler(String name, File path) {

		file = new File(path, name + ".txt");

		if (!(file.exists())) {
			try {
				file.createNewFile();
			} catch (IOException e) {
			}
		} else
			return;
	}

	public void write(String toWrite) {
		try {
			FileWriter fw = new FileWriter(file, true);

			fw.write(toWrite + "\n");

			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public File getFile() {
		return this.file;
	}
}
