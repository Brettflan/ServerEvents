package org.bukkit.croemmich.serverevents;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Logger;
import org.bukkit.Server;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
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
    
    public static final String name        = "ServerEvents";
    public static final String version     = "1.1";
    public static final String directory   = "ServerEvents" + File.separatorChar;
    public static final String configFile  = directory + "server_events.xml";
    
    private RandomMessageThread randomMessageThread;
    
    public ServerEvents(PluginLoader pluginLoader, Server instance, PluginDescriptionFile desc, File folder, File plugin, ClassLoader cLoader) {
        super(pluginLoader, instance, desc, folder, plugin, cLoader);
    }

    @Override
    public void onDisable() {
    	if (randomMessageThread != null) {
    		randomMessageThread.stop();
    		randomMessageThread = null;
    	}
    	
		log.info(name + " " + version + " disabled");
    }

    @Override
    public void onEnable() {
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
    }
    
    protected void attachHooks() {
    	if (Messages.hasMessages(Messages.Type.JOIN))
    		getServer().getPluginManager().registerEvent(Event.Type.PLAYER_JOIN, pl, Priority.Monitor, this);
    	
    	if (Messages.hasMessages(Messages.Type.COMMAND))
    		getServer().getPluginManager().registerEvent(Event.Type.PLAYER_COMMAND, pl, Priority.Monitor, this);
    		
    	if (Messages.hasMessages(Messages.Type.QUIT))
    		getServer().getPluginManager().registerEvent(Event.Type.PLAYER_QUIT, pl, Priority.Monitor, this);
    			
    	if (Messages.hasMessages(Messages.Type.DEATH)) {
    		getServer().getPluginManager().registerEvent(Event.Type.ENTITY_DEATH, el, Priority.Monitor, this);
    		getServer().getPluginManager().registerEvent(Event.Type.ENTITY_DAMAGED, el, Priority.Monitor, this);
    		getServer().getPluginManager().registerEvent(Event.Type.ENTITY_DAMAGEDBY_BLOCK, el, Priority.Monitor, this);
    		getServer().getPluginManager().registerEvent(Event.Type.ENTITY_DAMAGEDBY_ENTITY, el, Priority.Monitor, this);
    		getServer().getPluginManager().registerEvent(Event.Type.ENTITY_DAMAGEDBY_PROJECTILE, el, Priority.Monitor, this);
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
		DataSource.display(message);
	}
	
	public static void displayMessage (String message, HashMap<String, String> replacements) {
		Message msg = new Message();
		msg.setMessage(message);
		DataSource.display(msg.getMessage(replacements));
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
