// Astronaut class (extends Opponent)
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
	
	@Override
	public String toString() {
	    return "Astronaut: loc=" + getLocation() + " size=" + getSize() + " speed=" + getSpeed() + " health=" + getHealth();
	}
}
