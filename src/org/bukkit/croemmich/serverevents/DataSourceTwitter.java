package org.bukkit.croemmich.serverevents;

import java.util.Calendar;
import java.util.LinkedList;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class DataSourceTwitter extends DataSource {

	public static int rate_limit = 350;
	public static String accessToken = "";
	public static String accessTokenSecret = "";
	
	public static LinkedList<String> twitterQueue = new LinkedList<String>();
	TwitterDisplayThread twitterThread = null;
	
	private static TwitterFactory tf;
	
	public DataSourceTwitter(String accessToken, String accessTokenSecret, int rate_limit) {
		DataSourceTwitter.accessToken = accessToken;
		DataSourceTwitter.accessTokenSecret = accessTokenSecret;
		DataSourceTwitter.rate_limit = rate_limit;
		
		if (accessToken.equalsIgnoreCase("{accessToken}") || accessTokenSecret.equalsIgnoreCase("{accessTokenSecret}")) {
			log.warning("*******************************************");
			log.warning("* ServerEvents Twitter is not configured  *");
			log.warning("* run \"java -jar ServerEvents.jar\" now!   *");
			log.warning("*******************************************");
		} else {
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true)
			  .setOAuthAccessToken(accessToken)
			  .setOAuthAccessTokenSecret(accessTokenSecret)
			  .setOAuthConsumerKey("QyuUqx8UFaRLMWORQinphg")
			  .setOAuthConsumerSecret("EWORHYNo3JkJgvihiGwFL8tWNHExyhWFilR1Q");
			tf = new TwitterFactory(cb.build());
		}
	}

	@Override
	public void displayMessage(String msg) {
		twitterQueue.add(msg);
		if (twitterThread == null) {
			twitterThread = new TwitterDisplayThread();
		} 
		if (!twitterThread.running) {
			twitterThread.start();
		}
	}
	
	public static void displayTwitterNow(String msg) {
		if (msg != null) {
			if(msg.length() > 129) {
				msg = msg.substring(0, 128);
			}

			Calendar c = Calendar.getInstance();
			String s = "(" + c.get(Calendar.HOUR_OF_DAY) + ":" +
			           c.get(Calendar.MINUTE) + ":" +
			        c.get(Calendar.SECOND) + ")";
			msg += " " + s;
			
			try {
				Twitter twitter = tf.getInstance();
				twitter.updateStatus(msg);
			} catch (TwitterException e) {
				log.severe("ServerEvents Twitter: " + e.getMessage());
			}
		}
	}
}