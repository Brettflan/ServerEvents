package org.bukkit.croemmich.serverevents;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Logger;
public abstract class DataSource {
	
	protected static final Logger log      = Logger.getLogger("Minecraft");
	private static ArrayList<DataSource> ds = new ArrayList<DataSource>();
	public static LinkedList<String> queue = new LinkedList<String>();
	
	public static boolean enableQueue = true;
	public static int mpm = 12;
	public static int hold = 10;
	
	public static DisplayThread thread = null;
	
	public DataSource() {
		
	}
	
	public abstract void displayMessage(String msg);
	
	public static void addTwitterDataSource(String username, String password, int rate_limit) {
		ds.add(new DataSourceTwitter(username, password, rate_limit));
	}
	
	public static void addDatabaseDataSource(String username, String password, String database, String table, String driver) {
		ds.add(new DataSourceDatabase(username, password, database, table, driver));
	}
	
	public static void addFileDataSource(String uri, int keep_old) {
		ds.add(new DataSourceFile(uri, keep_old));
	}
	

	public static void addChatDataSource(ServerEvents plugin, String prefix, String prefix_color, String color) {
		ds.add(new DataSourceChat(plugin, prefix, prefix_color, color));
	}
	
	public static void display(String msg) {
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
	
	public static void displayNow(String msg) {
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