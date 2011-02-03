package org.bukkit.croemmich.serverevents;

import java.util.Random;

import org.bukkit.croemmich.serverevents.Messages.Type;
import org.bukkit.entity.Player;

public class RandomMessageThread implements Runnable {
    private boolean running = false;
    private Thread thread;
    
    private ServerEvents se;
    
    protected RandomMessageThread(ServerEvents se) {
    	this.se = se;
    }

    public void run() {
        while (this.running) {
            try {
                Thread.sleep(Messages.randomDelay);
            } catch (InterruptedException localInterruptedException) {
            }
            
            Player[] players = se.getServer().getOnlinePlayers();
            if (players.length > 0) {
            	Message msg = Messages.getRandomMessage(Messages.Type.RANDOM);
            	if (msg != null) {
            		Random generator = new Random();
            		int idx = generator.nextInt(players.length);
	            	String message = msg.getMessage(Messages.getReplacementsForPlayer(players[idx]));
	            	DataSource.display(Type.RANDOM, message);
            	}
            }
        }
    }

    protected void start() {
        this.running = true;
        thread = new Thread(this);
        thread.start();
    }

    protected void stop() {
        this.running = false;
        thread.interrupt();
    }
}
