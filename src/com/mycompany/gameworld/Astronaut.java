package com.mycompany.gameworld;

import com.codename1.charts.util.ColorUtil;

public class Astronaut extends Opponent {
	
	private int health; 

	public Astronaut(int health) {
		super(ColorUtil.red(255), health * CONSTANT);  // Astronauts start with full health
		this.health = health;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int newHealth) {
		this.health = newHealth;
	}
	
	public void updateColorBasedOnHealth() {
        if (this.health >= 4) {
            setColor(ColorUtil.rgb(255, 0, 0)); // Bright Red
        } else if (this.health == 3) {
            setColor(ColorUtil.rgb(255, 165, 0)); // Orange
        } else if (this.health == 2) {
            setColor(ColorUtil.YELLOW); // Yellow
        } else if (this.health <= 1) {
            setColor(ColorUtil.LTGRAY); // Grey
        }
	}
	
	@Override
	public String toString() {
	    return "Astronaut: location: " + getLocation() + 
	    		" size: " + getSize() + 
	    		" speed: " + getSpeed() + 
	    		" direction: " + getDirection() + 
	    		" health: " + getHealth();
	}
}
