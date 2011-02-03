package org.bukkit.croemmich.serverevents;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class DataSourceChat extends DataSource {

	protected static String prefix = "[ServerEvents] ";
	protected static String prefix_color = "aqua";
	protected static String color = "white";
	
	private ServerEvents plugin;
	
	protected DataSourceChat(ServerEvents plugin, String prefix, String prefix_color, String color) {
		this.plugin = plugin;
		DataSourceChat.prefix = prefix;
		DataSourceChat.prefix_color = prefix_color;
		DataSourceChat.color = color;
		
		String tmpPrefix_color = ChatColor.valueOf(prefix_color.toUpperCase()).toString();
		String tmpColor = ChatColor.valueOf(color.toUpperCase()).toString();
		
		if (tmpPrefix_color == null || tmpPrefix_color.equalsIgnoreCase("")) {
			log.warning("ServerEvents: invalid prefix_color.");
		} else {
			DataSourceChat.prefix_color = tmpPrefix_color;
		}
		if (tmpColor == null || tmpColor.equalsIgnoreCase("")) {
			log.warning("ServerEvents: invalid chat color.");
		} else {
			DataSourceChat.color = tmpColor;
		}
	}

	@Override
	protected void displayMessage(Messages.Type type, String msg) {
		if (!DataSource.isDisabled(Type.CHAT, type)) {
			msg = prefix_color+prefix+color+msg;
			
			for (Player p : plugin.getServer().getOnlinePlayers()) {
				boolean first = true;
				for (String text : Misc.wrapText(msg, 60)) {
					if (first) {
						p.sendMessage(text);
						first = false;
					} else {
						p.sendMessage(color+text);
					}
					
				}
			}
		}
	}
}