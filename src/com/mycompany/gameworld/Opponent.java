package com.mycompany.gameworld;

import java.util.Random;
import com.codename1.charts.models.Point;

public abstract class Opponent extends GameObject implements IMoving {
	
	protected static final int CONSTANT = 1;  // Speed multiplier
	
	private int speed;
	private int direction;  // Movement direction in degrees
	
	private static Random rand = new Random();

	public Opponent(int color, int speed) {
		super(rand.nextInt(31) + 20, color);  // Random size between 20-50
		this.speed = speed;
		this.direction = rand.nextInt(360);  // Random direction
	}
	
	// Getters and setters for speed and direction
	public int getSpeed() {
		return speed;
	}
	
	public int getDirection() {
		return direction;
	}
	
	public void setSpeed(int newSpeed) {
		this.speed = newSpeed;
	}
	
	public void setDirection(int newDirection) {
		this.direction = newDirection;
	}
	
	@Override
	public void setSize(int newSize) {}  // Opponent size can't change after creation
	
	@Override
	public void move() {
		Point currentLocation = getLocation();
		float currentX = currentLocation.getX();
		float currentY = currentLocation.getY();
		
		double theta = Math.toRadians(90 - getDirection());  // Convert degrees to radians
		float deltaX = (float)(Math.cos(theta) * getSpeed());
		float deltaY = (float)(Math.sin(theta) * getSpeed());
		
		setLocation(new Point(currentX + deltaX, currentY + deltaY));  // Update position
	}
}
