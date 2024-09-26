// The main game world class
package com.mycompany.gameworld;

import java.util.ArrayList;
import java.util.Random;
import com.codename1.charts.models.Point;
import com.codename1.io.Log;

public class GameWorld {
	
	private boolean exitPending = false;  // Flag for exit confirmation
	
	private int totalScore = 0;
	private int astronautsRescued = 0;
	private int aliensSneakedIn = 0;
	private int gameClock = 0;
	
	Random rand = new Random();  // For randomization
	
	private Spaceship spaceship;  // Spaceship object
	
	private final int width = 1000;
	private final int height = 1000;
	
	ArrayList<GameObject> gameObjList = new ArrayList<GameObject>();  // Store all game objects
	ArrayList<GameObject> astronautList = new ArrayList<>();  // List of astronauts
	ArrayList<GameObject> alienList = new ArrayList<>();  // List of aliens
	
	public void init() {
		// Initialize the game with spaceship, astronauts, and aliens
		spaceship = new Spaceship();
		
		// Create astronauts and aliens
		Astronaut astronaut1 = new Astronaut(5);
		Astronaut astronaut2 = new Astronaut(5);
		Astronaut astronaut3 = new Astronaut(5);
		Astronaut astronaut4 = new Astronaut(5);
		
		Alien alien1 = new Alien();
		Alien alien2 = new Alien();
		Alien alien3 = new Alien();
		
		// Add them to the game object list
		gameObjList.add(spaceship);
		gameObjList.add(astronaut1);
		gameObjList.add(astronaut2);
		gameObjList.add(astronaut3);
		gameObjList.add(astronaut4);
		gameObjList.add(alien1);
		gameObjList.add(alien2);
		gameObjList.add(alien3);
		
		// Populate astronaut and alien lists separately
		for (GameObject obj : gameObjList) {
			if (obj instanceof Astronaut) {
				astronautList.add(obj);
			}
		}
		
		for (GameObject obj : gameObjList) {
			if (obj instanceof Alien) {
				alienList.add(obj);
			}
		}
	}
	
	// Get a random astronaut from the list
	public GameObject getRandomAstronaut() {
		if (!astronautList.isEmpty()) {
			return astronautList.get(rand.nextInt(astronautList.size()));
		}
		return null;
	}
	
	public void jumpToRandomAstronaut() {
		GameObject randomAstronaut = getRandomAstronaut();
		spaceship.jumpToLocation(randomAstronaut);
	}
	
	// Get a random alien from the list
	public GameObject getRandomAlien() {
		if (!alienList.isEmpty()) {
			return alienList.get(rand.nextInt(alienList.size()));
		}
		return null;
	}
	
	// Other game methods
	public void expandSpaceshipDoor() {
		spaceship.expand();
	}
	
	public void contractSpaceshipDoor() {
		spaceship.contract();
	}
	
	public void jumpToRandomAlien() {
		GameObject randomAlien = getRandomAlien();
		spaceship.jumpToLocation(randomAlien);
	}
	
	public void moveSpaceshipLeft() {
		spaceship.moveLeft();
	}
	
	public void moveSpaceshipRight() {
		spaceship.moveRight();
	}
	
	public void moveSpaceshipUp() {
		spaceship.moveUp();
	}
	
	public void moveSpaceshipDown() {
		spaceship.moveDown();
	}
	
	public void clockTick() {
		for (GameObject obj : gameObjList) {
			if (obj instanceof IMoving) {
				((IMoving)obj).move();  // Move all moving objects
			}
		}
		gameClock++;  // Increment clock
	}
	
	public void openSpaceshipDoor() {
		// Handle logic to open the spaceship door and process objects at the door
		Point doorLocation = spaceship.getLocation();
		int doorSize = spaceship.getSize();

		// Calculate boundaries of the door
		float leftBoundary = doorLocation.getX() - doorSize / 2;
		float rightBoundary = doorLocation.getX() + doorSize / 2;
		float bottomBoundary = doorLocation.getY() - doorSize / 2;
		float topBoundary = doorLocation.getY() + doorSize / 2;

		// Variables to track scores
		int currScore = 0;
		int currAstronautsRescued = 0;
		int currAliensSneakedIn = 0;

		// Check objects at the door
		ArrayList<GameObject> objectsAtDoor = new ArrayList<>();
		for (GameObject obj : gameObjList) {
			Point objLocation = obj.getLocation();
			if (objLocation.getX() >= leftBoundary && objLocation.getX() <= rightBoundary &&
				objLocation.getY() >= bottomBoundary && objLocation.getY() <= topBoundary) {
				objectsAtDoor.add(obj);
			}
		}

		// Process objects at the door
		for (GameObject obj : objectsAtDoor) {
			if (obj instanceof Astronaut) {
				Astronaut astronaut = (Astronaut) obj;
				currScore += (astronaut.getHealth() * 2);  // Score based on health
				astronautsRescued++;
				gameObjList.remove(astronaut);
			} else if (obj instanceof Alien) {
				currScore -= 10;  // Deduct points for aliens
				aliensSneakedIn++;
				gameObjList.remove(obj);
			}
		}

		// Update scores
		totalScore += currScore;
		this.astronautsRescued += currAstronautsRescued;
		this.aliensSneakedIn += currAliensSneakedIn;
	}

	public void alienCollision() {
		// Simulate an alien-alien collision and create a new alien
		if (alienList.size() >= 2) {
			Alien randomAlien = (Alien) getRandomAlien();
			Point alienLocation = randomAlien.getLocation();
			float offsetX = rand.nextFloat() * 20 - 10;
			float offsetY = rand.nextFloat() * 20 - 10;

			Alien newAlien = new Alien();
			newAlien.setLocation(new Point(alienLocation.getX() + offsetX, alienLocation.getY() + offsetY));
			gameObjList.add(newAlien);
		}
	}

	public void alienAstronautFight() {
		// Handle fight between alien and astronaut
		if (!astronautList.isEmpty() && !alienList.isEmpty()) {
			Astronaut randomAstronaut = (Astronaut) getRandomAstronaut();
			int newHealth = randomAstronaut.getHealth() - 1;
			randomAstronaut.setHealth(newHealth);
			randomAstronaut.setSpeed(newHealth * Astronaut.CONSTANT);

			if (newHealth <= 0) {
				randomAstronaut.setSpeed(0);  // Astronaut stops moving
			}
		}
	}

	public void printGameState() {
		Log.p("Game State:");
		Log.p("Total Score: " + totalScore);
		Log.p("Astronauts Rescued: " + astronautsRescued);
		Log.p("Aliens Sneaked In: " + aliensSneakedIn);
		Log.p("Game Clock: " + gameClock);
	}
	
	public void printMap() {
		for (GameObject obj : gameObjList) {
			Log.p(obj.toString());
		}
	}

	// Exit flag methods
	public boolean isExitPending() {
	    return exitPending;
	}

	public void setExitPending(boolean exitPending) {
	    this.exitPending = exitPending;
	}
}
