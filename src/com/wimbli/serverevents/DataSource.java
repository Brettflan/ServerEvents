package com.wimbli.serverevents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Logger;
import org.bukkit.ChatColor;

public abstract class DataSource {
	
	protected static final Logger log      = Logger.getLogger("Minecraft");
	protected static ArrayList<DataSource> ds = new ArrayList<DataSource>();
	protected static LinkedList<String> queue = new LinkedList<String>();
	protected static LinkedList<Messages.Type> typeQueue = new LinkedList<Messages.Type>();
	
	protected static HashMap<Type, ArrayList<String>> disabled = new HashMap<Type, ArrayList<String>>();
	
	protected static boolean enableQueue = true;
	protected static boolean disableDefaultDeathMessages = true;
	protected static int mpm = 12;
	protected static int hold = 10;
	
	protected static DisplayThread thread = null;
	
	protected static enum Type {
		TWITTER, DATABASE, FILE, CHAT
	}
	
	protected DataSource() {
		
	}
	
	protected abstract void displayMessage(Messages.Type type, String msg);
	
	protected static void addTwitterDataSource(String username, String password, boolean add_timestamp, int rate_limit, int hour_offset) {
		ds.add(new DataSourceTwitter(username, password, add_timestamp, rate_limit, hour_offset));
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
	
	protected static void display(Messages.Type type, String msg) {
		if (enableQueue) {
			while (queue.size() >= hold) {
				log.info("ServerEvents: Queue is full. Removing message '" + queue.removeLast() + "'");
			}
			queue.add(msg);
			typeQueue.add(type);
			if (thread == null) {
				thread = new DisplayThread();
			} 
			if (!thread.running) {
				thread.start();
			}
		} else {
			displayNow(type, msg);
		}
	}
	
	protected static void displayNow(Messages.Type type, String msg) {
		if (msg != null && !msg.equalsIgnoreCase("")) {
			msg = msg.trim();
			Iterator<DataSource> itr = ds.iterator();
			while (itr.hasNext()) {
    			itr.next().displayMessage(type, msg);
    		}
			log.info("ServerEvents: " + ChatColor.stripColor(msg));
		}
	}
	
	protected static void addToDisabled(Type source, String type) {
		if (disabled.get(source) == null) {
			disabled.put(source, new ArrayList<String>());
		}
		ArrayList<String> al = disabled.get(source);
		al.add(type);
		disabled.put(source, al);
	}
	
	protected static boolean isDisabled(Type type, Messages.Type messageType) {
		ArrayList<String> dis = disabled.get(type);
		if (dis != null) {
			if (dis.contains(messageType.toString().toLowerCase())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}