package com.wimbli.serverevents;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DefaultConfig {
	
	protected static final Logger log = Logger.getLogger("Minecraft");
	protected static final String defaultCfgInJar = "/default.xml";

	protected static boolean make() {
		String location = ServerEvents.configFile;
		if (!new File(location).exists()) {
			InputStream jarURL = ServerEvents.serverevents.getClass().getResourceAsStream(defaultCfgInJar);
			try {
				copyFile(jarURL, new File(location));
			} catch (Exception ex) {
				log.log(Level.SEVERE, null, ex);
			}
		}
		return false;
	}

	protected static void copyFile(InputStream in, File out) throws Exception {
		InputStream fis = in;
		FileOutputStream fos = new FileOutputStream(out);
		try {
			byte[] buf = new byte[1024];
			int i = 0;
			while ((i = fis.read(buf)) != -1) {
				fos.write(buf, 0, i);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (fis != null) {
				fis.close();
			}
			if (fos != null) {
				fos.close();
			}
		}
	}
}