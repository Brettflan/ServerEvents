package com.wimbli.serverevents;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageByProjectileEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;


public class ServerEventsEntityListener extends EntityListener {
	
	protected static ArrayList<String> threads = new ArrayList<String>();
	protected static HashMap<String, Long> lastDeath = new HashMap<String, Long>();
	
	//private final ServerEvents plugin;
	ServerEventsEntityListener(ServerEvents plugin) {
		//this.plugin = plugin;
	}

	@Override
	public void onEntityDamage(EntityDamageEvent event) {
		if (!(event.getEntity() instanceof Player)) return;
		
		Player damaged = (Player) event.getEntity();
		if (!event.isCancelled() &&  event.getDamage() >= damaged.getHealth()){
			onEntityDeath(event);
		}
	}

	public void onEntityDeath (EntityDamageEvent event) {
		if(event.isCancelled() == true || !(event.getEntity() instanceof Player)) return;
		Player player = (Player) event.getEntity();
		if (!threads.contains(player.getName())) {
			threads.add(player.getName());
			new DeathThread(player, event).start();
		}
	}
}