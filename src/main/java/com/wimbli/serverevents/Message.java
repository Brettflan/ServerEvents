package com.wimbli.serverevents;

import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.logging.Logger;

public class Message {

	protected static final Logger log = Logger.getLogger("Minecraft");
	
	private String msg;
	private HashMap<String, String> params;
	
	public Message(){};
	
	public Message(String msg) {
		setMessage(msg);
	}
	
	public Message(String msg, HashMap<String, String> params) {
		setMessage(msg);
		setParams(params);
	}
	
	public String getMessage() {
		if (ServerEvents.server != null) {
			return getMessage(Messages.getReplacementsForServer(ServerEvents.server));
		}
		return msg;
	}
	
	public String getMessage(HashMap<String, String> replacements) {
		if (ServerEvents.server != null) {
			replacements.putAll(Messages.getReplacementsForServer(ServerEvents.server));
		}
		String tmp = msg;
		TreeSet<String> ts = new TreeSet<String>(replacements.keySet());
		Iterator<String> itr = ts.descendingIterator();
		while(itr.hasNext()) {
			String key = itr.next();
			String value = replacements.get(key);
			tmp = tmp.replace(key, value);
		}
		return tmp;
	}
	
	public void setMessage (String msg) {
		this.msg = msg;
	}
	
	public void setParams (HashMap<String, String> params) {
		this.params = params;
	}
	
	public void addParam(String name, String value) {
		if (params == null) {
			params = new HashMap<String, String>();
		}
		
		params.put(name, value);
	}
	
	public void removeParam(String name) {
		if (params.containsKey(name))
			params.remove(name);
	}
	
	public String getParam(String name) {
		return params.get(name);
	}
}
