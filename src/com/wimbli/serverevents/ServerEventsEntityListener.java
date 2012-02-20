package com.wimbli.serverevents;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;


public class ServerEventsEntityListener implements Listener {
	
	protected static ArrayList<String> threads = new ArrayList<String>();
	protected static HashMap<String, Long> lastDeath = new HashMap<String, Long>();

	@EventHandler(priority = EventPriority.MONITOR)
	public void onEntityDeath(EntityDeathEvent event) {
		if(!(event instanceof PlayerDeathEvent))
			return;

		if (DataSource.disableDefaultDeathMessages) {
			PlayerDeathEvent death = (PlayerDeathEvent) event;
			death.setDeathMessage(null);
		}

		Player player = (Player) event.getEntity();

		if (!threads.contains(player.getName())) {
			threads.add(player.getName());
			new DeathThread(player, player.getLastDamageCause()).start();
		}
	}
}