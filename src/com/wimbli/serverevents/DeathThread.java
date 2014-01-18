package com.wimbli.serverevents;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.wimbli.serverevents.Messages.Type;

import org.bukkit.entity.Blaze;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Silverfish;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Wither;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;


public class DeathThread implements Runnable {
    private boolean running = false;
    private Thread thread;
    
    private Player player;
	private EntityDamageEvent.DamageCause damageCause;
	private Entity damager;
	private double damageAmount;
    
    protected static final Logger log = Logger.getLogger("Minecraft");
    
    protected DeathThread(Player player, EntityDamageEvent event) {
    	this.player = player.getPlayer();
    	this.damageCause = (event == null) ? EntityDamageEvent.DamageCause.CUSTOM : event.getCause();
		this.damager = (event instanceof EntityDamageByEntityEvent) ? ((EntityDamageByEntityEvent)event).getDamager() : null;
		this.damageAmount = (event == null) ? 0 : event.getDamage();
		log.log(Level.INFO, "Death Cause: " + damageCause.toString() + "  Damager: " + (damager == null ? "null" : damager.toString()));
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
            
            if (ServerEventsListener.lastDeath.containsKey(player.getName())) {
            	if (ServerEventsListener.lastDeath.get(player.getName())+5000 >= System.currentTimeMillis()) {
            		this.stop();
                	return;
            	}
            }
            
            ServerEventsListener.lastDeath.put(player.getName(), System.currentTimeMillis());

    		DeathType type = DeathType.UNKNOWN;
    		DeathType type2 = DeathType.UNKNOWN;
			if (damageCause != null)
			{
				switch (damageCause) {
					case MAGIC:
					case PROJECTILE:
					case ENTITY_EXPLOSION:
					case ENTITY_ATTACK:
						// for damage caused by projectiles, getDamager() returns the projectile... what we need to know is the source
						if (damager instanceof Projectile) {
							damager = ((Projectile)damager).getShooter();
						}

						if (damager == null) {
							type = DeathType.ORPHAN;
						} else if (damager instanceof Player) {
							type = DeathType.PLAYER;
						} else if (damager instanceof Zombie) {
							type = DeathType.ZOMBIE;
							type2 = DeathType.CREATURE;
						} else if (damager instanceof Skeleton) {
							// see if wither skeleton instead of regular kind
							if (((Skeleton)damager).getSkeletonType() == Skeleton.SkeletonType.WITHER)
								type = DeathType.WITHERSKELETON;
							else
								type = DeathType.SKELETON;
							type2 = DeathType.CREATURE;
						} else if (damager instanceof Spider) {
							type = DeathType.SPIDER;
							type2 = DeathType.CREATURE;
						} else if (damager instanceof CaveSpider) {
							type = DeathType.CAVESPIDER;
							type2 = DeathType.CREATURE;
						} else if (damager instanceof Enderman) {
							type = DeathType.ENDERMAN;
							type2 = DeathType.CREATURE;
						} else if (damager instanceof MagmaCube) {
							type = DeathType.MAGMACUBE;
							type2 = DeathType.CREATURE;
						} else if (damager instanceof Slime) {
							type = DeathType.SLIME;
							type2 = DeathType.CREATURE;
						} else if (damager instanceof Silverfish) {
							type = DeathType.SILVERFISH;
							type2 = DeathType.CREATURE;
						} else if (damager instanceof PigZombie) {
							type = DeathType.PIGZOMBIE;
							type2 = DeathType.CREATURE;
						} else if (damager instanceof Ghast) {
							type = DeathType.GHAST;
							type2 = DeathType.CREATURE;
						} else if (damager instanceof Creeper) {
							type = DeathType.CREEPER;
							type2 = DeathType.CREATURE;
						} else if (damager instanceof Blaze) {
							type = DeathType.BLAZE;
							type2 = DeathType.CREATURE;
						} else if (damager instanceof EnderDragon) {
							type = DeathType.ENDERDRAGON;
							type2 = DeathType.CREATURE;
						} else if (damager instanceof IronGolem) {
							type = DeathType.IRONGOLEM;
							type2 = DeathType.CREATURE;
						} else if (damager instanceof Ocelot) {
							type = DeathType.CAT;
							type2 = DeathType.CREATURE;
						} else if (damager instanceof Witch) {
							type = DeathType.WITCH;
							type2 = DeathType.CREATURE;
						} else if (damager instanceof Wither) {
							type = DeathType.WITHER;
							type2 = DeathType.CREATURE;
						} else if (damager instanceof Wolf) {
							type = DeathType.WOLF;
							type2 = DeathType.CREATURE;
						}
						break;
					case BLOCK_EXPLOSION: type = DeathType.EXPLOSION; break;
					case CONTACT: type = DeathType.CONTACT; break;
					case DROWNING: type = DeathType.DROWNING; break;
					case FALL: type = DeathType.FALLING; break;
					case FIRE:
					case FIRE_TICK: type = DeathType.BURNING; break;
					case LAVA: type = DeathType.LAVA; break;
					case SUFFOCATION: type = DeathType.SUFFOCATION; break;
					case STARVATION: type = DeathType.STARVATION; break;
					case LIGHTNING: type = DeathType.LIGHTNING; break;
				}
			}
    		Message msg = Messages.getRandomDeathMessage(type, type2);
        	
    		if (msg != null) {
        		HashMap<String, String> replacements = Messages.getReplacementsForDeath(type, damager, damageAmount);
        		replacements.putAll(Messages.getReplacementsForPlayer(player));
        		DataSource.display(Type.DEATH, msg.getMessage(replacements));
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
    	ServerEventsListener.threads.remove(player.getName());
        this.running = false;
        thread.interrupt();
        thread = null;
    }
}
