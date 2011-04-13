package com.wimbli.serverevents;

import java.util.HashMap;

import com.wimbli.serverevents.Messages.Type;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerListener;

public class ServerEventsPlayerListener extends PlayerListener {
    //private final ServerEvents plugin;

    public ServerEventsPlayerListener(ServerEvents instance) {
       // plugin = instance;
    }
    
    @Override
    public void onPlayerJoin(PlayerJoinEvent event) {
    	Message msg = Messages.getRandomMessage(Messages.Type.JOIN);
    	if (msg != null) {
    		DataSource.display(Type.JOIN, msg.getMessage(Messages.getReplacementsForPlayer(event.getPlayer())));
    	}
    }

    @Override
    public void onPlayerQuit(PlayerQuitEvent event) {
    	Message msg = Messages.getRandomMessage(Messages.Type.QUIT);
    	if (msg != null) {
    		DataSource.display(Type.QUIT, msg.getMessage(Messages.getReplacementsForPlayer(event.getPlayer())));
    	}
    	
    	// Clean Up
    	ServerEventsEntityListener.threads.remove(event.getPlayer().getName());
    	ServerEventsEntityListener.lastDeath.remove(event.getPlayer().getName());
    }
    
    @Override
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
    	String command = event.getMessage();
    	
    	Message msg = Messages.getRandomCommandMessage(command);
    	if (msg != null) {
    		HashMap<String, String> replacements = Messages.getReplacementsForPlayer(event.getPlayer());
    		replacements.putAll(Messages.getReplacementsForCommand(command));
    		DataSource.display(Type.COMMAND, msg.getMessage(replacements));
    	}
    	
    }
}
