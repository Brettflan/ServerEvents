package org.bukkit.croemmich.serverevents;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class DeathThread implements Runnable {
    private boolean running = false;
    private Thread thread;
    
    private Player player;
    private EntityDamageEvent event;
    
    protected static final Logger log = Logger.getLogger("Minecraft");
    
    protected DeathThread(Player player, EntityDamageEvent event) {
    	this.player = player;
    	this.event = event;
    }

    public void run() {
        while (this.running) {
            try {
                Thread.sleep(150);
            } catch (InterruptedException localInterruptedException) {
            }
            
            if (player.getHealth() > 0) {
            	this.stop();
            	return;
            }
            
            if (ServerEventsEntityListener.lastDeath.containsKey(player.getName())) {
            	if (ServerEventsEntityListener.lastDeath.get(player.getName())+5000 >= System.currentTimeMillis()) {
            		this.stop();
                	return;
            	}
            }
            
            ServerEventsEntityListener.lastDeath.put(player.getName(), System.currentTimeMillis());

            Entity damager = null;
    		DeathType type = DeathType.UNKNOWN;
    		DeathType type2 = DeathType.UNKNOWN;
    		switch (event.getCause()) {
    			case ENTITY_ATTACK: 
    				damager = ((EntityDamageByEntityEvent)event).getDamager();
    				if (damager instanceof Player) {
    					type = DeathType.PLAYER;
    				} else if (damager instanceof Zombie) {
    					type = DeathType.ZOMBIE;
    					type2 = DeathType.CREATURE;
    				} else if (damager instanceof Skeleton) {
    					type = DeathType.SKELETON;
    					type2 = DeathType.CREATURE;
    				} else if (damager instanceof Spider) {
    					type = DeathType.SPIDER;
    					type2 = DeathType.CREATURE;
    				} else if (damager instanceof Slime) {
    					type = DeathType.SLIME;
    					type2 = DeathType.CREATURE;
    				} else if (damager instanceof PigZombie) {
    					type = DeathType.PIGZOMBIE;
    					type2 = DeathType.CREATURE;
    				} else if (damager instanceof Ghast) {
    					type = DeathType.GHAST;
    					type2 = DeathType.CREATURE;
    				}
    				break;
    			case BLOCK_EXPLOSION: type = DeathType.EXPLOSION; break;
    			case CONTACT: type = DeathType.CONTACT; break;
    			case DROWNING: type = DeathType.BURNING; break; //TODO: CHANGE THIS WHEN BUKKIT IS FIXED
    			case ENTITY_EXPLOSION: type = DeathType.CREEPER; type2 = DeathType.CREATURE; break;
    			case FALL: type = DeathType.FALLING; break;
    			case FIRE:
    			case FIRE_TICK: type = DeathType.BURNING; break;
    			case LAVA: type = DeathType.LAVA; break;
    			case SUFFOCATION: type = DeathType.SUFFOCATION; break;
    		}
    		Message msg = Messages.getRandomDeathMessage(type, type2);
        	
    		if (msg != null) {
        		HashMap<String, String> replacements = Messages.getReplacementsForDeath(type, damager, event.getDamage());
        		replacements.putAll(Messages.getReplacementsForPlayer(player));
        		DataSource.display(msg.getMessage(replacements));
        	}

            this.stop();
        }
    }

    protected void start() {
        this.running = true;
        thread = new Thread(this);
        thread.start();
    }

    protected void stop() {
    	ServerEventsEntityListener.threads.remove(player.getName());
        this.running = false;
        thread.interrupt();
        thread = null;
    }
}
