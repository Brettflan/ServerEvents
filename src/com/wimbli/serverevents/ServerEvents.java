package com.wimbli.serverevents;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Logger;
import org.bukkit.Server;
import com.wimbli.serverevents.Messages.Type;
import org.bukkit.plugin.java.JavaPlugin;

/**
* @title  ServerEvents
* @description Places server events on twitter, in a database, in a text file.
* @date 2010-01-17
* @author croemmich
*/
public class ServerEvents extends JavaPlugin {
	protected static ServerEvents serverevents;
	protected static final Logger log      = Logger.getLogger("Minecraft");
	public static Server server;

	public String name;
	public String version;
	public static final String directory   = "plugins" + File.separatorChar + "ServerEvents" + File.separatorChar;
	public static final String configFile  = directory + "server_events.xml";

	private RandomMessageThread randomMessageThread;

	@Override
	public void onDisable() {
		if (randomMessageThread != null) {
			randomMessageThread.stop();
			randomMessageThread = null;
		}

		Message msg = Messages.getRandomMessage(Messages.Type.STOP);
		if (msg != null) {
			DataSource.display(Type.STOP, msg.getMessage());
		}
	}

	@Override
	public void onEnable() {
		server = this.getServer();
		serverevents = this;
		name = getDescription().getName();
		version = getDescription().getVersion();

		if (!initProps()) {
			log.severe(name + ": Could not initialise " + configFile);
			getServer().getPluginManager().disablePlugin(this);
			return;
		}

		getServer().getPluginManager().registerEvents(new ServerEventsListener(), this);
		getCommand("serverevents").setExecutor(new ServerEventsCommand(this));

		if (randomMessageThread == null)
			randomMessageThread = new RandomMessageThread(this);
		randomMessageThread.start();

		Message msg = Messages.getRandomMessage(Messages.Type.START);
		if (msg != null) {
			DataSource.display(Type.START, msg.getMessage());
		}
	}

	protected boolean initProps() {
		File dir = new File(directory);
		if (!dir.exists())
			dir.mkdir();

		File conf = new File(configFile);
		if (!conf.exists()) {
			DefaultConfig.make();
		}

		return reloadData();
	}


	/* API Calls */
	public static void displayMessage (String message) {
		displayMessage(Type.CUSTOM, message);
	}

	public static void displayMessage (Type type, String message) {
		DataSource.display(type, message);
	}

	public static void displayMessage (String message, HashMap<String, String> replacements) {
		displayMessage(Type.CUSTOM, message, replacements);
	}

	public static void displayMessage (Type type, String message, HashMap<String, String> replacements) {
		Message msg = new Message();
		msg.setMessage(message);
		DataSource.display(type, msg.getMessage(replacements));
	}

	public static void addMessage(Messages.Type type, String message) {
		Messages.addMessage(type, new Message(message));
	}

	public static void addMessage(Messages.Type type, String message, HashMap<String, String> params) {
		Messages.addMessage(type, new Message(message, params));
	}

	public static void removeMessage(Messages.Type type, String message) {
		Messages.removeMessage(type, message);
	}

	public static boolean reloadData() {
		Messages.clearAllMessages();
		DataSource.empty();
		DataParser dp = new DataParser(serverevents);
		return dp.load(configFile);
	}
}
