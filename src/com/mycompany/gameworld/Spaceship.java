package com.mycompany.gameworld;

import com.codename1.charts.util.ColorUtil;

public class Spaceship extends Rescuer {

	public Spaceship() {
		super(100, ColorUtil.GREEN);  // Spaceship size starts at 100
	}
	
	@Override
	public void setSize(int newSize) {
		// Ensure the spaceship size stays within bounds
		if (newSize < 50 || newSize > 1000) {
			throw new IllegalArgumentException("Spaceship size cannot be less than 50 or greater than 1000");
		}
		super.setSize(newSize);
	}
	
	// Methods to expand and contract the spaceship's door size
	public void expand() {
		int newSize = getSize() + 10;
		setSize(newSize);
	}
	
	public void contract() {
		int newSize = getSize() - 10;
		setSize(newSize);
	}
	
	@Override
	public void setColor(int newColor) {}  // Spaceship color can't change
	
	@Override
	public String toString() {
	    return "Spaceship: loc=" + getLocation() + " size=" + getSize() + " color=" + getColor();
	}
}
