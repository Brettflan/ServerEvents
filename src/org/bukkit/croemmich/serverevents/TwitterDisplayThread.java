package org.bukkit.croemmich.serverevents;

import java.util.logging.Logger;

public class TwitterDisplayThread implements Runnable {
    protected boolean running = false;
    private Thread thread;
    
    protected static final Logger log = Logger.getLogger("Minecraft");
    
    protected TwitterDisplayThread() {
    	
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

    protected void start() {
        this.running = true;
        thread = new Thread(this);
        thread.start();
    }

    protected void stop() {
        this.running = false;
        thread.interrupt();
        thread = null;
    }
}
