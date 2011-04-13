package com.wimbli.serverevents;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Logger;
import org.bukkit.Server;
import com.wimbli.serverevents.Messages.Type;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.java.JavaPlugin;

/**
* @title  ServerEvents
* @description Places server events on twitter, in a database, in a text file.
* @date 2010-01-17
* @author croemmich
*/
public class ServerEvents extends JavaPlugin {
	protected static final Logger log      = Logger.getLogger("Minecraft");
	protected ServerEventsPlayerListener pl   = new ServerEventsPlayerListener(this);
	protected ServerEventsEntityListener el   = new ServerEventsEntityListener(this);
    
    public String name;
    public String version;
    public static final String directory   = "plugins" + File.separatorChar + "ServerEvents" + File.separatorChar;
    public static final String configFile  = directory + "server_events.xml";
    
    public static Server server;
    
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
    	
		log.info(getDescription().getName() + " " + getDescription().getVersion() + " disabled");
    }

    @Override
    public void onEnable() {
		server = this.getServer();
		name = getDescription().getName();
		version = getDescription().getVersion();
    	File confdir = new File("ServerEvents"); 
    	if (confdir.exists()) {
    		File newdir = new File(directory);
    		if (!confdir.renameTo(newdir)) {
    			log.severe(name + ": Could not move the ServerEvents directory to the plugin folder. Please do so and restart your server.");
    			getServer().getPluginManager().disablePlugin(this);
    			return;
    		} else {
    			log.warning("****************************************");
    			log.warning(name + ": The ServerEvents directory has been moved to plugins/ServerEvents. Update file references in server_events.xml and then restart the minecraft server.");
    			log.warning("****************************************");
    			getServer().getPluginManager().disablePlugin(this);
    			return;
    		}
    	}
    	
    	if (!initProps()) {
			log.severe(name + ": Could not initialise " + configFile);
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
    	
    	attachHooks();
    	
    	if (randomMessageThread == null)
    		randomMessageThread = new RandomMessageThread(this);
    	randomMessageThread.start();

		log.info( name + " version " + version + " is enabled!" );
		
		Message msg = Messages.getRandomMessage(Messages.Type.START);
    	if (msg != null) {
    		DataSource.display(Type.START, msg.getMessage());
    	}
    }
    
    protected void attachHooks() {
    	if (Messages.hasMessages(Messages.Type.JOIN))
    		getServer().getPluginManager().registerEvent(Event.Type.PLAYER_JOIN, pl, Priority.Monitor, this);
    	
    	if (Messages.hasMessages(Messages.Type.COMMAND))
    		getServer().getPluginManager().registerEvent(Event.Type.PLAYER_COMMAND_PREPROCESS, pl, Priority.Monitor, this);
    		
    	if (Messages.hasMessages(Messages.Type.QUIT))
    		getServer().getPluginManager().registerEvent(Event.Type.PLAYER_QUIT, pl, Priority.Monitor, this);
    			
    	if (Messages.hasMessages(Messages.Type.DEATH)) {
    		getServer().getPluginManager().registerEvent(Event.Type.ENTITY_DEATH, el, Priority.Monitor, this);
    		getServer().getPluginManager().registerEvent(Event.Type.ENTITY_DAMAGE, el, Priority.Monitor, this);
    	}
    	
    	if (Messages.hasMessages(Messages.Type.BAN)){
    	// TODO:	
    	}
    		
    	if (Messages.hasMessages(Messages.Type.KICK)){
    	// TODO:	
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

		DataParser dp = new DataParser(this);
		return dp.load(configFile);
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
}
