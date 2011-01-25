package org.bukkit.croemmich.serverevents;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Colors {
	BLACK("black","\u00A70"), NAVY("navy","\u00A71"), GREEN("green","\u00A78"), 
	DARKPURPLE("darkpurple","\u00A79"), LIGHTGREEN("lightgreen","\u00A7a"), 
	LIGHTBLUE("lightblue","\u00A7b"), ROSE("rose","\u00A7c"), LIGHTPURPLE("lightpurple","\u00A7d"), 
	YELLOW("yellow","\u00A7e"), WHITE("white","\u00A7f");
	
    private String name;
    private String character;
    
    private static final Map<String, Colors> mapping = new HashMap<String, Colors>();

    static {
        for (Colors type : EnumSet.allOf(Colors.class)) {
            mapping.put(type.name, type);
        }
    }

    private Colors(String name, String character) {
        this.name = name;
        this.character = character;
    }

    public String getName() {
        return name;
    }
    
    public static Colors fromName(String name) {
        return mapping.get(name);
    }
    
    public String getCharacter() {
    	return character;
    }
}