package org.bukkit.croemmich.serverevents;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class DataSourceTwitter extends DataSource {

	protected static int rate_limit = 350;
	protected static String accessToken = "";
	protected static String accessTokenSecret = "";
	
	protected static LinkedList<String> twitterQueue = new LinkedList<String>();
	protected TwitterDisplayThread twitterThread = null;
	
	private static TwitterFactory tf;
	
	protected DataSourceTwitter(String accessToken, String accessTokenSecret, int rate_limit) {
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
	protected void displayMessage(String msg) {
		twitterQueue.add(msg);
		if (twitterThread == null) {
			twitterThread = new TwitterDisplayThread();
		} 
		if (!twitterThread.running) {
			twitterThread.start();
		}
	}
	
	protected static void displayTwitterNow(String msg) {
		if (msg != null) {
			Date today;
			String output;
			SimpleDateFormat formatter;

			formatter = new SimpleDateFormat("hh:mm:ss z");
			today = new Date();
			output = formatter.format(today);
			
			if(msg.length() >= 140-output.length()+1) {
				msg = msg.substring(0, 140-output.length()+1);
			}
			
			msg += " " + output;
			
			try {
				Twitter twitter = tf.getInstance();
				twitter.updateStatus(msg);
			} catch (TwitterException e) {
				log.severe("ServerEvents Twitter: " + e.getMessage());
			}
		}
	}
}