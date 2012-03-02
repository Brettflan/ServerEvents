package com.wimbli.serverevents;
//Copyright (C) 2010  Ryan Michela
//
//This program is free software: you can redistribute it and/or modify
//it under the terms of the GNU General Public License as published by
//the Free Software Foundation, either version 3 of the License, or
//(at your option) any later version.
//
//This program is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//GNU General Public License for more details.
//
//You should have received a copy of the GNU General Public License
//along with this program.  If not, see <http://www.gnu.org/licenses/>.

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.logging.Level;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;


public final class Register {
	private static boolean cmdLine = true;
	private static boolean isPlayer = true;
//	private static ServerEventsCommand.RegisterCommand sender;
	private static Twitter twitter;
	private static AccessToken accessToken = null;
	private static RequestToken requestToken;

	public static void main(String[] args)
	{
		updateDir();
		execute();
	}

//	public static void register(ServerEventsCommand.RegisterCommand commandSender) {
	public static void register(boolean byPlayer)
	{
//		sender = commandSender;
		cmdLine = false;
		isPlayer = byPlayer;
		updateDir();
		execute();
	}

	private static void execute()
	{
		try
		{
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true)
				.setOAuthConsumerKey("QyuUqx8UFaRLMWORQinphg")
				.setOAuthConsumerSecret("EWORHYNo3JkJgvihiGwFL8tWNHExyhWFilR1Q");

			twitter = new TwitterFactory(cb.build()).getInstance();
			accessToken = null;
			try
			{
				// get request token.
				// this will throw IllegalStateException if access token is already available
				requestToken = twitter.getOAuthRequestToken();

				msg("Open the following URL and grant access to ServerEvents:");
				msg(requestToken.getAuthorizationURL());
				outputToFile("Open the following URL and grant access to ServerEvents: \n"+requestToken.getAuthorizationURL());
				msg("For convenience, this URL has also been output to a file: " + outFile);

				if (cmdLine)
				{
					BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
					while (null == accessToken)
					{
						System.out.print("Enter the PIN(if available) and hit enter after you have granted access. [PIN]:");
						String pin = br.readLine();
						tryPin(pin);
					}
				}
			}
			catch (IllegalStateException ie)
			{
				// access token is already available, or consumer key/secret is not set.
				if(!twitter.getAuthorization().isEnabled())
				{
					msg("OAuth consumer key/secret is not set.");
					exit(-1);
				}
			}
		}
		catch (TwitterException te)
		{
			msg("Failed to get timeline: " + te.getMessage());
			msg("Try revoking access to the ServerEvents application from your Twitter settings page.");
			exit(-1);
		}
		catch (IOException ioe)
		{
			msg("Failed to read the system input.");
			exit(-1);
		}

		if (cmdLine)
			exit(0);
	}

	public static boolean tryPin(String pin)
	{
		if (pin == null)
			pin = "";

		try
		{
			if (pin.length() > 0)
				accessToken = twitter.getOAuthAccessToken(requestToken, pin);
			else
				accessToken = twitter.getOAuthAccessToken(requestToken);
		}
		catch (TwitterException te)
		{
			if (te.getStatusCode() == 401)
				msg("Unable to get the access token. 401: Unauthorized.");
			else
			{
				msg("Unable to get the access token. Stack trace output to log.");
				te.printStackTrace();
			}
			return false;
		}

		msg("Successfully connected to Twitter.");

		msg("******************* IMPORTANT ********************");
		msg("Place these values in your server_events.xml file:");
		msg("accessToken=\"" + accessToken.getToken()+"\"");
		msg("accessTokenSecret=\"" + accessToken.getTokenSecret()+"\"");
		msg("*************************************************");

		outputToFile("<twitter enabled=\"true\" accessToken=\""+accessToken.getToken()+"\" accessTokenSecret=\""+accessToken.getTokenSecret()+"\" rate_limit=\"350\" add_timestamp=\"true\" timestamp_hour_offset=\"0\" />");
		msg("For convenience, a completed entry (which can be copied to server_events.xml) is output to the file: " + outFile);

		if (cmdLine)
			msg("Access granted to Twitter. After updating server_event.xml with the info above, you can start the server up.");
		else
			msg("After editing server_events.xml, you can use \"/serverevents reload\" to reload it without needing to restart the server.");

		return true;
	}

	private static void msg(String text)
	{
		if (cmdLine)
			System.out.println(text);
		else
		{
			ServerEventsCommand.msg(text);
			if (isPlayer)
				ServerEvents.log.log(Level.INFO, text);
		}
	}

	private static String outFile;
	private static void updateDir()
	{
		if (cmdLine)
		{
			outFile = "ServerEvents" + File.separatorChar;
			File folder = new File(outFile);
			if (!folder.exists())
				folder.mkdir();
		}
		else
			outFile = ServerEvents.directory;

		outFile += "twitter_key_info.txt";
	}

	private static void outputToFile(String string)
	{
		try
		{
			FileWriter fstream = new FileWriter(outFile);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(string);
			out.close();
		}
		catch (Exception e)
		{
			msg("Error: " + e.getMessage());
		}
	}

	private static void exit(int level)
	{
		if (cmdLine)
			System.exit(level);
		else
			ServerEventsCommand.endConversation();
	}
}