package com.mycompany.gameworld;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.io.Log;

public abstract class Rescuer extends GameObject implements IGuided {
	
	public Rescuer(int size, int color) {
		
		super(size, color);
		
	}
	
	@Override
	public void moveLeft() {
		Point currentLocation = getLocation();
		setLocation(new Point(currentLocation.getX() - 10, currentLocation.getY()));
	}

	@Override
	public void moveRight() {
		Point currentLocation = getLocation();
		setLocation(new Point(currentLocation.getX() + 10, currentLocation.getY()));
		
	}

	@Override
	public void moveUp() {
		Point currentLocation = getLocation();
		setLocation(new Point(currentLocation.getX(), currentLocation.getY() + 10));
		
	}

	@Override
	public void moveDown() {
		Point currentLocation = getLocation();
		setLocation(new Point(currentLocation.getX(), currentLocation.getY() - 10));
		
	}

	@Override
	public void jumpToLocation(GameObject target) {
		if (target != null) {
			setLocation(target.getLocation());
			Log.p("Jumped to target at location: " + target.getLocation());
		} else {
			Log.p("No valid target found to jump to.");
		}
	}

}
