package org.bukkit.croemmich.serverevents;

import java.util.HashMap;

import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerListener;

public class ServerEventsPlayerListener extends PlayerListener {
    //private final ServerEvents plugin;

    public ServerEventsPlayerListener(ServerEvents instance) {
       // plugin = instance;
    }
    
    @Override
    public void onPlayerJoin(PlayerEvent event) {
    	Message msg = Messages.getRandomMessage(Messages.Type.JOIN);
    	if (msg != null) {
    		DataSource.display(msg.getMessage(Messages.getReplacementsForPlayer(event.getPlayer())));
    	}
    }

    @Override
    public void onPlayerQuit(PlayerEvent event) {
    	Message msg = Messages.getRandomMessage(Messages.Type.QUIT);
    	if (msg != null) {
    		DataSource.display(msg.getMessage(Messages.getReplacementsForPlayer(event.getPlayer())));
    	}
    	
    	// Clean Up
    	ServerEventsEntityListener.threads.remove(event.getPlayer().getName());
    	ServerEventsEntityListener.lastDeath.remove(event.getPlayer().getName());
    }
    
    @Override
    public void onPlayerCommand(PlayerChatEvent event) {
    	String command = event.getMessage();
    	
    	Message msg = Messages.getRandomCommandMessage(command);
    	if (msg != null) {
    		HashMap<String, String> replacements = Messages.getReplacementsForPlayer(event.getPlayer());
    		replacements.putAll(Messages.getReplacementsForCommand(command));
    		DataSource.display(msg.getMessage(replacements));
    	}
    	
    }
}
