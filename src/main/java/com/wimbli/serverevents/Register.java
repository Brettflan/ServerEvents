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
import java.io.IOException;
import java.io.InputStreamReader;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.http.AccessToken;
import twitter4j.http.RequestToken;

/**
 * Example application that uses OAuth method to acquire access to your account.<br>
 * This application illustrates how to use OAuth method with Twitter4J.<br>
 *
 * @author Yusuke Yamamoto - yusuke at mac.com
 */
public final class Register {
    /**
     * Usage: java twitter4j.examples.tweets.UpdateStatus [text]
     *
     * @param args message
     */
	public static void main(String[] args) {
        try {
        	ConfigurationBuilder cb = new ConfigurationBuilder();
        	cb.setDebugEnabled(true)
        	  .setOAuthConsumerKey("QyuUqx8UFaRLMWORQinphg")
        	  .setOAuthConsumerSecret("EWORHYNo3JkJgvihiGwFL8tWNHExyhWFilR1Q");
        	
        	
            Twitter twitter = new TwitterFactory(cb.build()).getInstance();
            AccessToken accessToken = null;
            try {
                // get request token.
                // this will throw IllegalStateException if access token is already available
                RequestToken requestToken = twitter.getOAuthRequestToken();
                

                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                while (null == accessToken) {
                    System.out.println("Open the following URL and grant access to your account:");
                    System.out.println(requestToken.getAuthorizationURL());
                    System.out.print("Enter the PIN(if available) and hit enter after you granted access. [PIN]:");
                    String pin = br.readLine();
                    try {
                        if (pin.length() > 0) {
                            accessToken = twitter.getOAuthAccessToken(requestToken, pin);
                        } else {
                            accessToken = twitter.getOAuthAccessToken(requestToken);
                        }
                    } catch (TwitterException te) {
                        if (401 == te.getStatusCode()) {
                            System.out.println("Unable to get the access token.");
                        } else {
                            te.printStackTrace();
                        }
                    }
                }
                System.out.println("Access granted to Twitter.");
            } catch (IllegalStateException ie) {
                // access token is already available, or consumer key/secret is not set.
                if(!twitter.getAuthorization().isEnabled()){
                    System.out.println("OAuth consumer key/secret is not set.");
                    System.exit(-1);
                }
            }
            System.out.println("Successfully connected to Twitter.");
            
            System.out.println("\n\n*********************");
            System.out.println("***** IMPORTANT *****");
            System.out.println("**************************************************************");
            System.out.println("Place these values in your server_events.xml file:");
            System.out.println("accessToken=\"" + accessToken.getToken()+"\"");
            System.out.println("accessTokenSecret=\"" + accessToken.getTokenSecret()+"\"");
            System.out.println("**************************************************************");
            
            System.out.println("Restart the minecraft server now.");
            System.exit(0);
        } catch (TwitterException te) {
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.out.println("Try revoking access to the ServerEvents application from your Twitter settings page.");
            System.exit(-1);
        } catch (IOException ioe) {
            System.out.println("Failed to read the system input.");
            System.exit(-1);
        }
    }
}