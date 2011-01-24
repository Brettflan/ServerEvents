package org.bukkit.croemmich.serverevents;

public class DataSourceChat extends DataSource {

	public static String prefix = "ServerEvents";
	public static String prefix_color = "lightblue";
	public static String color = "white";
	
	public DataSourceChat(String prefix, String prefix_color, String color) {
		DataSourceChat.prefix = prefix;
		DataSourceChat.prefix_color = prefix_color;
		DataSourceChat.color = color;
	}

	@Override
	public void displayMessage(String msg) {
		
	}
}