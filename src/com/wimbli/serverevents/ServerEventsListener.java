package com.wimbli.serverevents;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.wimbli.serverevents.Messages.Type;


public class ServerEventsListener implements Listener
{
	protected static ArrayList<String> threads = new ArrayList<String>();
	protected static HashMap<String, Long> lastDeath = new HashMap<String, Long>();

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerDeath(PlayerDeathEvent event)
	{
		if (DataSource.disableDefaultDeathMessages)
			event.setDeathMessage(null);

		Player player = event.getEntity();

		if (threads.contains(player.getName())) return;

		threads.add(player.getName());
		new DeathThread(player, player.getLastDamageCause()).start();
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		if (!Messages.hasMessages(Type.JOIN)) return;
		Message msg = Messages.getRandomMessage(Messages.Type.JOIN);
		if (msg != null)
			DataSource.display(Type.JOIN, msg.getMessage(Messages.getReplacementsForPlayer(event.getPlayer())));
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		if (!Messages.hasMessages(Type.QUIT)) return;
		Message msg = Messages.getRandomMessage(Messages.Type.QUIT);
		if (msg != null)
			DataSource.display(Type.QUIT, msg.getMessage(Messages.getReplacementsForPlayer(event.getPlayer())));

		// Clean Up
		threads.remove(event.getPlayer().getName());
		lastDeath.remove(event.getPlayer().getName());
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event)
	{
		if (!Messages.hasMessages(Type.COMMAND) || event.isCancelled()) return;
		String command = event.getMessage();

		Message msg = Messages.getRandomCommandMessage(command);
		if (msg == null) return;

		HashMap<String, String> replacements = Messages.getReplacementsForPlayer(event.getPlayer());
		replacements.putAll(Messages.getReplacementsForCommand(command));
		DataSource.display(Type.COMMAND, msg.getMessage(replacements));
	}
}
