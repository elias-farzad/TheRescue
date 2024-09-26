// Alien class (extends Opponent)
package com.mycompany.gameworld;

import com.codename1.charts.util.ColorUtil;

public class Alien extends Opponent {

	public Alien() {
		super(ColorUtil.BLUE, 5 * CONSTANT);  // Aliens are blue with fixed speed
	}
	
	@Override
	public void setColor(int newColor) {}  // Aliens' color can't change
	
	@Override
	public String toString() {
	    return "Alien: loc=" + getLocation() + " size=" + getSize() + " speed=" + getSpeed();
	}
}

