package org.bukkit.croemmich.serverevents;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DefaultConfig {
	
	protected static final Logger log = Logger.getLogger("Minecraft");
	
	public static boolean make() {
		String location = ServerEvents.configFile;
		if (!new File(location).exists()) {
			FileWriter writer = null;
			try {
				writer = new FileWriter(location);
				writer.write(conf);
			} catch (Exception e) {
				log.log(Level.SEVERE, "Exception while creating " + location, e);
			} finally {
				try {
					if (writer != null) {
						writer.close();
						return true;
					}
				} catch (IOException e) {
					log.log(Level.SEVERE, "Exception while closing writer for "	+ location, e);
				}
			}
		}
		return false;
	}
	
	public static String conf = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" + 
			"\r\n" + 
			"<serverevents>\r\n" + 
			"	<conf>\r\n" + 
			"		<!--  Queueing ensures that messages are not displayed too quickly. -->\r\n" + 
			"		<queue enabled=\"true\" messages_per_minute=\"12\" messages_to_hold=\"10\" />\r\n" + 
			"		<file enabled=\"true\" uri=\"ServerEvents"+File.separatorChar+"server_events.txt\" keep_old=\"5\" />\r\n" + 
			"       <!--  To get accessToken and secret run `java -jar ServerEvents.jar` -->\r\n" +
			"		<twitter enabled=\"true\" accessToken=\"{accessToken}\" accessTokenSecret=\"{accessTokenSecret}\" rate_limit=\"350\" />\r\n" + 
			"		<database enabled=\"false\" username=\"\" password=\"\" database=\"jdbc:mysql://localhost:3306/minecraft\" table=\"server_events\" driver=\"com.mysql.jdbc.Driver\" />\r\n" + 
			"	</conf>\r\n" + 
			"\r\n" + 
			"	<!--  Messages to be displayed randomly. Default delay is 30 minutes (30min*60sec*1000mili)-->\r\n" + 
			"	<random enabled=\"true\" delay=\"1800000\">\r\n" + 
			"		<msg>im h a p p y i know i am im sure i am im h a p p y</msg>\r\n" + 
			"		<msg>i may be going slow at the moment im deleting your creations moo ha ha ha haaa</msg>\r\n" + 
			"		<msg>im a little teapot short and stout</msg>\r\n" + 
			"		<msg>were running many custom plugins</msg>\r\n" + 
			"		<msg>shuffling blocks</msg>\r\n" + 
			"		<msg>looking at all diamonds in chests, changing id to saplings...done</msg>\r\n" + 
			"		<msg>dont forget to check the forum at http://www.here.com</msg>\r\n" + 
			"		<msg>if your happy and you know it ...kick a player!</msg>\r\n" + 
			"	</random>\r\n" + 
			"	\r\n" + 
			"	<join enabled=\"true\">\r\n" + 
			"		<msg>ooo look %n is back</msg>\r\n" + 
			"		<msg>if ti isnt my old friend %n, online again</msg>\r\n" + 
			"		<msg>might as well not sign in %n is online</msg>\r\n" + 
			"		<msg>oh a wandering soul has entered the server, hi %n</msg>\r\n" + 
			"		<msg>hello %n</msg>\r\n" + 
			"		<msg>guess who is addicted to minecraft? %n is oh yes he is!</msg>\r\n" + 
			"	</join>\r\n" + 
			"	\r\n" + 
			"	\r\n" + 
			"	<quit enabled=\"true\">\r\n" + 
			"		<msg>no come back %n, we miss you already</msg>\r\n" + 
			"		<msg>*sound of cork being popped* %n has left the server PARTY!</msg>\r\n" + 
			"		<msg>a wondering soul has eleft the server. Bye %n</msg>\r\n" + 
			"		<msg>i regret to inform you %n`s addiction to mine craft is not as great as first believed...he managed to exit</msg>\r\n" + 
			"	</quit>\r\n" + 
			"	\r\n" + 
			"	\r\n" + 
			"	<!-- BAN/KICK Messages NOT WORKING YET! -->\r\n" + 
			"	<!-- Messages to use when a player is kicked or banned -->\r\n" + 
			"	<!-- \r\n" + 
			"		extra replacements:\r\n" + 
			"		%admin => admin who kicked or banned the player\r\n" + 
			"	-->\r\n" + 
			"	<ban enabled=\"false\">\r\n" + 
			"		<msg>\"leave and never come back\" %n - %admin!</msg>\r\n" + 
			"	</ban>\r\n" + 
			"	<kick enabled=\"false\">\r\n" + 
			"		<msg>ugg... leave already %n</msg>\r\n" + 
			"	</kick>\r\n" + 
			"\r\n" + 
			"	<!-- Messages to use when a player uses a command -->\r\n" + 
			"	<!-- cmd must be specified -->\r\n" + 
			"	<!-- \r\n" + 
			"		consider cmd=\"/kill player with polarbear now\"\r\n" + 
			"		\r\n" + 
			"		extra replacements:\r\n" + 
			"		%cmd => whole command used : /kill player with polarbear now\r\n" + 
			"		%cmd0 => base command : /kill\r\n" + 
			"		%cmd1 => first argument : player\r\n" + 
			"		%cmd2 => second argument : with\r\n" + 
			"		%cmd{N} => Nth argument : %cmd3=>polarbear : %cmd4=>now\r\n" + 
			"	-->\r\n" + 
			"	<command enabled=\"true\">\r\n" + 
			"		<msg cmd=\"/i 1 64\" full=\"true\">%n used the command: %cmd</msg>;\r\n" + 
			"	</command>\r\n" + 
			"	\r\n" + 
			"	\r\n" + 
			"	<!-- Messages to use when a player dies. -->\r\n" + 
			"	<!-- If a player is killed by a zombie, a message is chosen at random from messages with killer=\"zombie\", killer=\"creature\" or where a killer is not specified. -->\r\n" + 
			"	<!-- Killers:\r\n" + 
			"			player\r\n" + 
			"			creature\r\n" + 
			"			creeper\r\n" + 
			"			zombie\r\n" + 
			"			ghast\r\n" + 
			"			pigzombie\r\n" + 
			"			skeleton\r\n" + 
			"			spider\r\n" + 
			"			contact (cactus)\r\n" + 
			"			explosion (non creeper)\r\n" + 
			"			drowning (NOT WORKING YET)\r\n" + 
			"			falling\r\n" + 
			"			burning\r\n" + 
			"			lava\r\n" + 
			"			suffocation\r\n" + 
			"	 -->\r\n" + 
			"	<!-- \r\n" + 
			"		extra replacements:\r\n" + 
			"		%damage => amount of damage taken on final blow\r\n" + 
			"		%killer => name of player, creature, fire, water, gravity, or explosion\r\n" + 
			"	 -->\r\n" + 
			"	<death enabled=\"true\">\r\n" + 
			"		<msg>%n is a weakling that was killed by %killer.</msg>\r\n" + 
			"		<msg killer=\"zombie\">munch munch munch grrr do i have any %n between my teeth?</msg>\r\n" + 
			"		<msg killer=\"zombie\">brains no more, sorry %n</msg>\r\n" + 
			"		<msg killer=\"falling\">%n feels gravity sucks</msg>\r\n" + 
			"		<msg killer=\"falling\">%n pancaked</msg>\r\n" + 
			"		<msg killer=\"falling\">clean up isle 10! poor %n</msg>\r\n" + 
			"		<msg killer=\"burning\">%n is hot today!</msg>\r\n" + 
			"		<msg killer=\"burning\">%n is experiencing the disco inferno! oh wait no just the inferno</msg>\r\n" + 
			"		<msg killer=\"burning\">thank god %n was wearing armour...now his ashes will be foil wapped</msg>\r\n" + 
			"		<msg killer=\"drowning\">%n sleeps with the fishes (not in a perverted way more in stone block around the feet way)</msg>\r\n" + 
			"		<msg killer=\"drowning\">i feel sorry for %n he seemed to run out of air</msg>\r\n" + 
			"	</death>\r\n" + 
			"</serverevents>";
}