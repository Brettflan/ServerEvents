package com.wimbli.serverevents;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Messages {
	
	protected static final Logger log = Logger.getLogger("Minecraft");

	private static ArrayList<Message> randomMessages  = new ArrayList<Message>();
	private static ArrayList<Message> startMessages   = new ArrayList<Message>();
	private static ArrayList<Message> stopMessages    = new ArrayList<Message>();
	private static ArrayList<Message> joinMessages    = new ArrayList<Message>();
	private static ArrayList<Message> quitMessages    = new ArrayList<Message>();
	private static ArrayList<Message> kickMessages    = new ArrayList<Message>();
	private static ArrayList<Message> banMessages     = new ArrayList<Message>();
	private static ArrayList<Message> commandMessages = new ArrayList<Message>();
	private static ArrayList<Message> deathMessages   = new ArrayList<Message>();
	private static ArrayList<Message> blockMessages   = new ArrayList<Message>();
	
	public static int randomDelay = 1800000;
	
	public static enum Type {
		RANDOM, JOIN, START, STOP, QUIT, KICK, BAN, COMMAND, DEATH, BLOCK, CUSTOM
	}

	public static ArrayList<Message> getMessages(Type type) {
		switch (type) {
			case RANDOM: return randomMessages;
			case JOIN: return joinMessages;
			case START: return startMessages;
			case STOP: return stopMessages;
			case QUIT: return quitMessages;
			case KICK: return kickMessages;
			case BAN: return banMessages;
			case COMMAND: return commandMessages;
			case DEATH: return deathMessages;
			case BLOCK: return blockMessages;
			default: return randomMessages;
		}
	}
	
	public static void addMessage(Type type, String msg) {
		addMessage(type, msg, null);
	}
	
	public static void addMessage(Type type, String msg, HashMap<String, String> params) {
		addMessage(type, new Message(msg, params));
	}
	
	
	public static void addMessage(Type type, Message msg) {
		switch (type) {
			case RANDOM: randomMessages.add(msg); break;
			case JOIN: joinMessages.add(msg); break;
			case QUIT: quitMessages.add(msg); break;
			case START: startMessages.add(msg); break;
			case STOP: stopMessages.add(msg); break;
			case KICK: kickMessages.add(msg); break;
			case BAN: banMessages.add(msg); break;
			case COMMAND: commandMessages.add(msg); break;
			case DEATH: deathMessages.add(msg); break;
			case BLOCK: blockMessages.add(msg); break;
		}
	}

	public static void clearAllMessages() {
		randomMessages.clear();
		startMessages.clear();
		stopMessages.clear();
		joinMessages.clear();
		quitMessages.clear();
		kickMessages.clear();
		banMessages.clear();
		commandMessages.clear();
		deathMessages.clear();
		blockMessages.clear();
	}

	public static void removeMessage(Type type, String msg) {
		ArrayList<Message> tmp = null;
		
		switch (type) {
			case RANDOM: tmp = randomMessages; break;
			case START: tmp = startMessages; break;
			case STOP: tmp = stopMessages; break;
			case JOIN: tmp = joinMessages; break;
			case QUIT: tmp = quitMessages; break;
			case KICK: tmp = kickMessages; break;
			case BAN: tmp = banMessages; break;
			case COMMAND: tmp = commandMessages; break;
			case DEATH: tmp = deathMessages; break;
			case BLOCK: tmp = blockMessages; break;
		}
		
		if (tmp != null) {
			Iterator<Message> i = tmp.iterator();
			while(i.hasNext()) {
				Message message = i.next();
				if (message.getMessage().equalsIgnoreCase(msg)) {
					tmp.remove(message);
				}
			}
		}
		
		switch (type) {
			case RANDOM: randomMessages = tmp; break;
			case JOIN: joinMessages = tmp; break;
			case QUIT: quitMessages = tmp; break;
			case START: startMessages = tmp; break;
			case STOP: stopMessages = tmp; break;
			case KICK: kickMessages = tmp; break;
			case BAN: banMessages = tmp; break;
			case COMMAND: commandMessages = tmp; break;
			case DEATH: deathMessages = tmp; break;
			case BLOCK: blockMessages = tmp; break;
		}
	}
	
	public static Message getRandomMessage(Type type) {
		ArrayList<Message> msgs = getMessages(type);
		if (msgs != null && msgs.size()>0) {
			Random generator = new Random();
			int idx = generator.nextInt(msgs.size());
			return msgs.get(idx);
		}
		return null;
	}
	
	public static HashMap<String, String> getReplacementsForPlayer(Player player) {
		HashMap<String, String> replacements = new HashMap<String, String>();
		if (player != null) {
			InetSocketAddress address = player.getAddress();
			replacements.put("%n", player.getDisplayName());
			replacements.put("%n_health", String.valueOf(player.getHealth()));
			replacements.put("%n_ip", (address == null) ? "Unknown IP" : address.getAddress().getHostAddress());
			replacements.put("%n_hostname", (address == null) ? "Unknown Hostname" : address.getAddress().getCanonicalHostName());
			if (player.getItemInHand().getType() == Material.AIR) {
				replacements.put("%n_item", "nothing");
			} else {
				replacements.put("%n_item", player.getItemInHand().getType().name().toLowerCase().replace("_", " "));
			}
		}
		return replacements;
	}
	
	public static boolean hasMessages(Type type) {
		ArrayList<Message> msgs = getMessages(type);
		if (msgs != null && msgs.size() > 0) {
			return true;
		}
		return false;
	}

	public static Message getRandomCommandMessage(String command) {
		ArrayList<Message> msgs = Messages.getMessages(Messages.Type.COMMAND);
		ArrayList<Message> tmpMsgs = new ArrayList<Message>();
		
		Iterator<Message> itr = msgs.iterator();
		while (itr.hasNext()) {
			Message msg = itr.next();
			boolean full = false;
			boolean match = false;
			
			String fullStr = msg.getParam("full");
			if (fullStr != null) {
				try {
					full = Boolean.parseBoolean(fullStr);
				} catch (Exception e) {
					full = false;
				}
			}
			
			String matchStr = msg.getParam("cmd");
			Pattern p = Pattern.compile("^" + Pattern.quote(matchStr), Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(command);
			if (full) {
				match = m.matches();
			} else {
				match = m.find();
			}
			
			if (match) {
				tmpMsgs.add(msg);
			}
		}
		
		if (tmpMsgs != null && tmpMsgs.size()>0) {
			Random generator = new Random();
			int idx = generator.nextInt(tmpMsgs.size());
			return tmpMsgs.get(idx);
		}
		return null;
	}

	public static HashMap<String, String> getReplacementsForCommand(String command) {
		command = command.trim();
		
		HashMap<String, String> replacements = new HashMap<String, String>();
		replacements.put("%cmd", command);
		
		String[] args = command.split(" ");
		int n = 0;
		for (String arg : args) {
			if (arg != null && !arg.equalsIgnoreCase("")) {
				replacements.put("%cmd" + n, arg);
				n++;
			}
		}
		
		return replacements;
	}
	
	public static Message getRandomDeathMessage(DeathType type, DeathType type2) {
		ArrayList<Message> msgs = Messages.getMessages(Messages.Type.DEATH);
		ArrayList<Message> tmpMsgs = new ArrayList<Message>();
		
		Iterator<Message> itr = msgs.iterator();
		while (itr.hasNext()) {
			Message msg = itr.next();
			
			try {
			if (msg.getParam("killer") == null) {
				tmpMsgs.add(msg);
			} else if (msg.getParam("killer").equalsIgnoreCase(type.getName()) || msg.getParam("killer").equalsIgnoreCase(type2.getName())) {
				tmpMsgs.add(msg);
			}
			} catch (Exception e) {}
		}
		
		if (tmpMsgs != null && tmpMsgs.size()>0) {
			Random generator = new Random();
			int idx = generator.nextInt(tmpMsgs.size());
			return tmpMsgs.get(idx);
		}
		return null;
	}
	
	public static HashMap<String, String> getReplacementsForDeath(DeathType type, Entity entity, int damage) {
		HashMap<String, String> replacements = new HashMap<String, String>();

		String killer = "unknown";
		String item = "unknown";
		
		switch(type) {
			case PLAYER: killer = ((Player)entity).getDisplayName(); item = ((Player)entity).getItemInHand().getType().name().toLowerCase().replace("_", " "); break;
			case CREATURE: killer = "a strange creature"; break;
			case ZOMBIE: killer = "a zombie"; break;
			case GHAST: killer = "a ghast"; break;
			case PIGZOMBIE: killer = "a pig zombie"; break;
			case SKELETON: killer = "a skeleton"; break;
			case SPIDER: killer = "a spider"; break;
			case SLIME: killer = "slime"; break;
			case GIANT: killer = "giant"; break;
			case CONTACT: killer = "a cactus"; break;
			case EXPLOSION: killer = "an explosion"; break;
			case DROWNING: killer = "drowning"; break;
			case FALLING: killer = "falling"; break;
			case BURNING: killer = "burning"; break;
			case LAVA: killer = "lava"; break;
			case SUFFOCATION: killer = "suffocating"; break;
		}
		
		if (item == null || item.equalsIgnoreCase("unknown")) {
			item = killer;
		}
		
		replacements.put("%damage", String.valueOf(damage));
		replacements.put("%killer", killer);
		replacements.put("%killer_item", item);
		return replacements;
	}
	
	public static HashMap<String, String> getReplacementsForServer(Server server) {
		HashMap<String, String> replacements = new HashMap<String, String>();
		
		replacements.put("$name", server.getName());
		Pattern pattern = Pattern.compile(".*\\(MC:\\s(.*)\\).*", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(server.getVersion());
		if (matcher.matches() && matcher.groupCount() > 0) {
			replacements.put("%protocol", matcher.group(1));
		} else {
			replacements.put("%protocol", server.getVersion());
		}
		replacements.put("%version", server.getVersion());
		replacements.put("%ip", "127.0.0.1");
		replacements.put("%hostname", "localhost");
		
		String onlinePlayers = "";
		Player[] players = server.getOnlinePlayers();
		for (int i=0; i<players.length; i++) {
			onlinePlayers += players[i].getDisplayName();
			if (i+1 < players.length) {
				onlinePlayers += ", ";
			}
		}
		
		replacements.put("%players", onlinePlayers);
		
		try {
		    InetAddress addr = InetAddress.getLocalHost();
		    replacements.put("%ip", addr.getHostAddress());
		    replacements.put("%hostname", addr.getCanonicalHostName());
		} catch (UnknownHostException e) {}
		
		return replacements;
	}
}