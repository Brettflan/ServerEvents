package org.bukkit.croemmich.serverevents;

import java.util.logging.Logger;

public class TwitterDisplayThread implements Runnable {
    public boolean running = false;
    private Thread thread;
    
    protected static final Logger log = Logger.getLogger("Minecraft");
    
    public TwitterDisplayThread() {
    	
    }

    public void run() {
        while (this.running) {
        	if (DataSourceTwitter.twitterQueue.isEmpty()) {
    			this.stop();
    			return;
    		}
        	
        	String msg = DataSourceTwitter.twitterQueue.remove();
        	DataSourceTwitter.displayTwitterNow(msg);
    		
            try {
                Thread.sleep(3600000/DataSourceTwitter.rate_limit);
            } catch (InterruptedException localInterruptedException) {
            }
        }
    }

    public void start() {
        this.running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        this.running = false;
        thread.interrupt();
        thread = null;
    }
}
