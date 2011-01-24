package org.bukkit.croemmich.serverevents;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;

public class DataSourceFile extends DataSource {

	public static String uri;
	public static int keep_old = 5;
	
	public DataSourceFile(String uri, Integer keep_old) {
		DataSourceFile.uri = uri;
		DataSourceFile.keep_old = keep_old;
		
		if (!init()) {
			log.warning("ServerEvents: Could not make text file at: " + uri);
		}
	}

	private boolean init() {
		String location = uri;
		if (!new File(location).exists()) {
			FileWriter writer = null;
			try {
				writer = new FileWriter(location);
				writer.write("");
			} catch (Exception e) {
				log.log(Level.SEVERE, "Exception while creating " + location, e);
				return false;
			} finally {
				try {
					if (writer != null) {
						writer.close();
						return true;
					}
				} catch (IOException e) {
					log.log(Level.SEVERE, "Exception while closing writer for "	+ location, e);
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public void displayMessage(String msg) {
		FileWriter writer = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(uri)));
			String line = "";
			StringBuilder toSave = new StringBuilder(msg+"\r\n");
			int n = 1;
			while ((line = reader.readLine()) != null && n < keep_old) {
				toSave.append(line+"\r\n");
				n++;
			}
			reader.close();
			writer = new FileWriter(uri);
			writer.write(toSave.toString());
		} catch (Exception e1) {
			log.log(Level.SEVERE, "Exception while writing to " + uri);
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException ex) {
				log.log(Level.SEVERE, "Exception while closing writer for "	+ uri);
			}
		}
	}
	
}