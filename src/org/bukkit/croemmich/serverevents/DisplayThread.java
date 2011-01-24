package org.bukkit.croemmich.serverevents;

import java.util.logging.Logger;

public class DisplayThread implements Runnable {
    public boolean running = false;
    private Thread thread;
    
    protected static final Logger log = Logger.getLogger("Minecraft");
    
    public DisplayThread() {
    	
    }

    public void run() {
        while (this.running) {
        	if (DataSource.queue.isEmpty()) {
    			this.stop();
    			return;
    		}
        	
        	String msg = DataSource.queue.remove();
    		DataSource.displayNow(msg);
    		
            try {
                Thread.sleep(60000/DataSource.mpm);
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
