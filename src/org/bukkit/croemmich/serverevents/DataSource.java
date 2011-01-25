package org.bukkit.croemmich.serverevents;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Logger;
public abstract class DataSource {
	
	protected static final Logger log      = Logger.getLogger("Minecraft");
	protected static ArrayList<DataSource> ds = new ArrayList<DataSource>();
	protected static LinkedList<String> queue = new LinkedList<String>();
	
	protected static boolean enableQueue = true;
	protected static int mpm = 12;
	protected static int hold = 10;
	
	protected static DisplayThread thread = null;
	
	protected DataSource() {
		
	}
	
	protected abstract void displayMessage(String msg);
	
	protected static void addTwitterDataSource(String username, String password, int rate_limit) {
		ds.add(new DataSourceTwitter(username, password, rate_limit));
	}
	
	protected static void addDatabaseDataSource(String username, String password, String database, String table, String driver) {
		ds.add(new DataSourceDatabase(username, password, database, table, driver));
	}
	
	protected static void addFileDataSource(String uri, int keep_old) {
		ds.add(new DataSourceFile(uri, keep_old));
	}
	

	protected static void addChatDataSource(ServerEvents plugin, String prefix, String prefix_color, String color) {
		ds.add(new DataSourceChat(plugin, prefix, prefix_color, color));
	}
	
	protected static void display(String msg) {
		if (enableQueue) {
			while (queue.size() >= hold) {
				log.info("ServerEvents: Queue is full. Removing message '" + queue.removeLast() + "'");
			}
			queue.add(msg);
			if (thread == null) {
				thread = new DisplayThread();
			} 
			if (!thread.running) {
				thread.start();
			}
		} else {
			displayNow(msg);
		}
	}
	
	protected static void displayNow(String msg) {
		if (msg != null && !msg.equalsIgnoreCase("")) {
			msg = msg.trim();
			Iterator<DataSource> itr = ds.iterator();
			while (itr.hasNext()) {
    			itr.next().displayMessage(msg);
    		}
			log.info("ServerEvents: " + msg);
		}
	}
}