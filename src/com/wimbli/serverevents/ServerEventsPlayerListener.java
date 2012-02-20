package com.wimbli.serverevents;

import java.util.HashMap;

import com.wimbli.serverevents.Messages.Type;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;


public class ServerEventsPlayerListener implements Listener {

	@EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent event) {
    	if (!Messages.hasMessages(Type.JOIN)) return;
    	Message msg = Messages.getRandomMessage(Messages.Type.JOIN);
    	if (msg != null) {
    		DataSource.display(Type.JOIN, msg.getMessage(Messages.getReplacementsForPlayer(event.getPlayer())));
    	}
    }

	@EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerQuit(PlayerQuitEvent event) {
    	if (!Messages.hasMessages(Type.QUIT)) return;
    	Message msg = Messages.getRandomMessage(Messages.Type.QUIT);
    	if (msg != null) {
    		DataSource.display(Type.QUIT, msg.getMessage(Messages.getReplacementsForPlayer(event.getPlayer())));
    	}
    	
    	// Clean Up
    	ServerEventsEntityListener.threads.remove(event.getPlayer().getName());
    	ServerEventsEntityListener.lastDeath.remove(event.getPlayer().getName());
    }
    
	@EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
    	if (!Messages.hasMessages(Type.COMMAND) || event.isCancelled()) return;
    	String command = event.getMessage();
    	
    	Message msg = Messages.getRandomCommandMessage(command);
    	if (msg != null) {
    		HashMap<String, String> replacements = Messages.getReplacementsForPlayer(event.getPlayer());
    		replacements.putAll(Messages.getReplacementsForCommand(command));
    		DataSource.display(Type.COMMAND, msg.getMessage(replacements));
    	}
    	
    }
}
