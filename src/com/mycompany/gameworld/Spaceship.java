package com.mycompany.gameworld;

import com.codename1.charts.util.ColorUtil;

/**
 * Represents the Spaceship in the game.
 * Implements the Singleton pattern to ensure only one instance exists.
 */
public class Spaceship extends Rescuer {
    // Static variable to hold the single instance of Spaceship
    private static Spaceship instance = null;

    // Spaceship properties
    private int size;
    private int color;

    /**
     * Private constructor to prevent external instantiation.
     * Initializes the spaceship with default size and color.
     */
    private Spaceship() {
        super(100, ColorUtil.GREEN);  // Spaceship size starts at 100
        this.size = 100;
        this.color = ColorUtil.GREEN;
    }

    /**
     * Public method to provide access to the single instance of Spaceship.
     * Creates the instance if it doesn't exist.
     *
     * @return The singleton instance of Spaceship.
     */
    public static Spaceship getInstance() {
        if (instance == null) {
            instance = new Spaceship();
        }
        return instance;
    }

    /**
     * Overrides the setSize method to ensure the spaceship size stays within bounds.
     *
     * @param newSize The new size to set.
     * @throws IllegalArgumentException if the new size is out of bounds.
     */
    @Override
    public void setSize(int newSize) {
        // Ensure the spaceship size stays within bounds
        if (newSize < 50 || newSize > 1000) {
            throw new IllegalArgumentException("Spaceship size cannot be less than 50 or greater than 1000");
        }
        this.size = newSize;
        super.setSize(newSize);
    }

    /**
     * Expands the spaceship's size by 10 units.
     * Ensures that the new size does not exceed the maximum limit.
     */
    public void expand() {
        int newSize = getSize() + 10;
        setSize(newSize);
    }

    /**
     * Contracts the spaceship's size by 10 units.
     * Ensures that the new size does not fall below the minimum limit.
     */
    public void contract() {
        int newSize = getSize() - 10;
        setSize(newSize);
    }

    /**
     * Overrides the setColor method to prevent changing the spaceship's color.
     *
     * @param newColor The new color to set.
     * @throws UnsupportedOperationException always thrown to prevent color changes.
     */
    @Override
    public void setColor(int newColor) {
        // Spaceship color can't change; throw an exception to notify about the invalid operation
        throw new UnsupportedOperationException("Spaceship color cannot be changed.");
    }

    /**
     * Provides a string representation of the spaceship.
     *
     * @return String detailing the spaceship's location, size, and color.
     */
    @Override
    public String toString() {
        return "Spaceship: loc=" + getLocation() + " size=" + getSize();// + " color=" + ColorUtil.toARGB(getColor());
    }
    

    
    
    // implement this later
    /**
     * Implements collision detection logic with another GameObject.
     *
     * @param obj The other GameObject to check collision with.
     * @return True if colliding, false otherwise.
     */
    //@Override
    /*public boolean collidesWith(GameObject obj) {
        // Implement collision detection logic based on your game's mechanics
        // Placeholder implementation:
        return false;
    }*/
    
    
    
    

}
