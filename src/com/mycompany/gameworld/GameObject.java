// Base class for all game objects
package com.mycompany.gameworld;

import java.util.Random;
import com.codename1.charts.models.Point;

public abstract class GameObject {
	private int size;
	private Point location;
	private int color;
	
	private static Random rand = new Random();
	
	public GameObject(int size, int color) {
		// Randomly assign location
		float X = (rand.nextFloat() * 1000);
		float Y = (rand.nextFloat() * 1000);
		
		this.size = size;
		this.location = new Point(X, Y);  // Random initial location
		this.color = color;
	}
	
	// Getters and setters
	public int getSize() {
		return size;
	}
	
	public Point getLocation() {
		return location;
	}
	
	public int getColor() {
		return color;
	}
	
	public void setSize(int newSize) {
		this.size = newSize;
	}
	
	public void setLocation(Point newLocation) {
		this.location = newLocation;
	}
	
	public void setColor(int newColor) {
		this.color = newColor;
	}
}
