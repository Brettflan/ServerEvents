package org.bukkit.croemmich.serverevents;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum DeathType {
	PLAYER("player"), CREATURE("creature"), CREEPER("creeper"), ZOMBIE("zombie"), 
	GHAST("ghast"), PIGZOMBIE("pigzombie"), SKELETON("skeleton"), SPIDER("spider"), 
	CONTACT("contact"), EXPLOSION("explosion"), DROWNING("drowning"), FALLING("falling"), 
	BURNING("burning"), LAVA("lava"), SUFFOCATION("suffocation"), GIANT("giant"), 
	SLIME("slime"), UNKNOWN("unknown");

    private String name;
    
    private static final Map<String, DeathType> mapping
            = new HashMap<String, DeathType>();

    static {
        for (DeathType type : EnumSet.allOf(DeathType.class)) {
            mapping.put(type.name, type);
        }
    }

    private DeathType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public static DeathType fromName(String name) {
        return mapping.get(name);
    }
}