// The main game world class
package com.mycompany.gameworld;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import com.codename1.charts.models.Point;
import com.codename1.io.Log;

public class GameWorld extends Observable {
	
	private static GameWorld instance = null; //null
	
	private boolean exitPending = false;  // Flag for exit confirmation
	
	private Random rand = new Random();
	
	private int totalScore = 0;
	private int astronautsRescued = 0;
	private int aliensSneakedIn = 0;
	private int gameClock = 0;
	private boolean soundOn;
	
	private GameObjectCollection gameObjects;
	
	/**
     * Private constructor to enforce Singleton pattern.
     */
	private GameWorld() {
		gameObjects = new GameObjectCollection();
        totalScore = 0;
        gameClock = 0;
        astronautsRescued = 0;
        aliensSneakedIn = 0;
        soundOn = false; // Sound is off by default
	}
	
	/**
     * Provides global access to the single instance of GameWorld.
     * @return The singleton instance of GameWorld.
     */
	public static GameWorld getInstance() {
        if (instance == null) {
            instance = new GameWorld();
            instance.init(); // Initialize game objects
        }
        return instance;
    }
	
	/**
     * Public method to notify observers about a change.
     * Encapsulates the protected setChanged() method (for one instance).
     */
    public void notifyObserversChange() {
        setChanged();
        notifyObservers();
    }
	
	/**
     * Initializes the game world with game objects like Spaceship, Astronauts, and Aliens.
     */
	public void init() {
		// Add Spaceship (Singleton ensures only one exists)
        gameObjects.add(Spaceship.getInstance());
        
     // Add Astronauts
        for (int i = 0; i < 4; i++) {
            Astronaut astronaut = new Astronaut(5);
            gameObjects.add(astronaut);
        }

        // Add Aliens
        for (int i = 0; i < 3; i++) {
            Alien alien = new Alien();
            gameObjects.add(alien);
        }

        // Notify observers of the initial state
        setChanged();
        notifyObservers();
	}
	
	/**
     * Adds a game object to the collection and notifies observers.
     * @param obj The GameObject to add.
     */
    public void addGameObject(GameObject obj) {
        gameObjects.add(obj);
        setChanged();
        notifyObservers();
    }
    
    /**
     * Removes a game object from the collection and notifies observers.
     * @param obj The GameObject to remove.
     */
    public void removeGameObject(GameObject obj) {
        gameObjects.remove(obj);
        setChanged();
        notifyObservers();
    }
    
    /**
     * Retrieves the GameObjectCollection.
     * @return The GameObjectCollection instance.
     */
    public GameObjectCollection getGameObjectCollection() {
        return gameObjects;
    }
    
    /**
     * Increments the game clock and notifies observers.
     */
    public void incrementGameClock() {
        gameClock++;
        setChanged();
        notifyObservers();
    }

    /**
     * Updates the total score and notifies observers.
     * @param score The amount to add to the total score.
     */
    public void updateTotalScore(int score) {
        totalScore += score;
        setChanged();
        notifyObservers();
    }
    
    /**
     * Increments the count of astronauts rescued and notifies observers.
     */
    public void rescueAstronaut() {
        astronautsRescued++;
        setChanged();
        notifyObservers();
    }

    /**
     * Increments the count of aliens sneaked in and notifies observers.
     */
    public void sneakAlien() {
        aliensSneakedIn++;
        setChanged();
        notifyObservers();
    }
    
    /**
     * Creates a new Alien and adds it to the game world.
     */
    public void createNewAlien() {
        Alien newAlien = new Alien();
        
        // Generate random positions within the game boundaries
        int x = rand.nextInt(1000);
        int y = rand.nextInt(1000);
        
        newAlien.setLocation(new Point(x, y));
        
        addGameObject(newAlien);
        
        setChanged();
        notifyObservers();
    }


    /**
     * Toggles the sound state and notifies observers.
     */
    public void toggleSound() {
        soundOn = !soundOn;
        setChanged();
        notifyObservers();
    }

    //Getters for game state variables

    public int getTotalScore() {
        return totalScore;
    }

    public int getGameClock() {
        return gameClock;
    }

    public int getAstronautsRescued() {
        return astronautsRescued;
    }

    public int getAliensSneakedIn() {
        return aliensSneakedIn;
    }

    public boolean isSoundOn() {
        return soundOn;
    }
	
    /**
     * Retrieves a random Astronaut from the GameObjectCollection.
     * @return A random Astronaut or null if none exist.
     */
    public Astronaut getRandomAstronaut() {
        ArrayList<GameObject> astronauts = new ArrayList<>();
        IIterator iterator = gameObjects.getIterator();
        while (iterator.hasNext()) {
            GameObject obj = iterator.getNext();
            if (obj instanceof Astronaut) {
                astronauts.add(obj);
            }
        }
        if (!astronauts.isEmpty()) {
            return (Astronaut) astronauts.get(rand.nextInt(astronauts.size()));
        }
        return null;
    }

    /**
     * Retrieves a random Alien from the GameObjectCollection.
     * @return A random Alien or null if none exist.
     */
    public Alien getRandomAlien() {
        ArrayList<GameObject> aliens = new ArrayList<>();
        IIterator iterator = gameObjects.getIterator();
        while (iterator.hasNext()) {
            GameObject obj = iterator.getNext();
            if (obj instanceof Alien) {
                aliens.add(obj);
            }
        }
        if (!aliens.isEmpty()) {
            return (Alien) aliens.get(rand.nextInt(aliens.size()));
        }
        return null;
    }
	
    /**
     * Commands the spaceship to jump to a random Astronaut's location.
     */
    public void jumpToRandomAstronaut() {
        Astronaut randomAstronaut = getRandomAstronaut();
        if (randomAstronaut != null) {
            Spaceship.getInstance().jumpToLocation(randomAstronaut);
            setChanged();
    	    notifyObservers();
        } else {
            Log.p("No astronauts available to jump to.");
        }
    }

    /**
     * Commands the spaceship to jump to a random Alien's location.
     */
    public void jumpToRandomAlien() {
        Alien randomAlien = getRandomAlien();
        if (randomAlien != null) {
        	Spaceship.getInstance().jumpToLocation(randomAlien);
            setChanged();
    	    notifyObservers();
        } else {
            Log.p("No aliens available to jump to.");
        }
    }
	
	// Other game methods
	public void expandSpaceshipDoor() {
		Spaceship.getInstance().expand();
		
		setChanged();
	    notifyObservers();
	}
	
	public void contractSpaceshipDoor() {
		Spaceship.getInstance().contract();
		
		setChanged();
	    notifyObservers();
	}
	
	public void moveSpaceshipLeft() {
		Spaceship.getInstance().moveLeft();
		
		setChanged();
	    notifyObservers();
	}
	
	public void moveSpaceshipRight() {
		Spaceship.getInstance().moveRight();
		
		setChanged();
	    notifyObservers();
	}
	
	public void moveSpaceshipUp() {
		Spaceship.getInstance().moveUp();
		
		setChanged();
	    notifyObservers();
	}
	
	public void moveSpaceshipDown() {
		Spaceship.getInstance().moveDown();
		
		setChanged();
	    notifyObservers();
	}
	
	/**
	 * Advances the game clock and moves all moving game objects.
	 */
	public void clockTick() {
	    IIterator iterator = gameObjects.getIterator();
	    while (iterator.hasNext()) {
	        GameObject obj = iterator.getNext();
	        if (obj instanceof IMoving) {
	            ((IMoving)obj).move();
	        }
	    }
	    gameClock++;
	    setChanged();
	    notifyObservers();
	}

	/**
	 * Handles the logic for opening the spaceship door and processing objects within its vicinity.
	 */
	public void openSpaceshipDoor() {
	    // Access spaceship via Singleton
	    Spaceship spaceship = Spaceship.getInstance();
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

	    // List to collect objects to remove after iteration to avoid ConcurrentModificationException
	    ArrayList<GameObject> objectsToRemove = new ArrayList<>();

	    // Iterate over GameObjectCollection to find objects at the door
	    IIterator iterator = gameObjects.getIterator();
	    while (iterator.hasNext()) {
	        GameObject obj = iterator.getNext();
	        Point objLocation = obj.getLocation();
	        if (objLocation.getX() >= leftBoundary && objLocation.getX() <= rightBoundary &&
	            objLocation.getY() >= bottomBoundary && objLocation.getY() <= topBoundary) {
	            objectsToRemove.add(obj);
	        }
	    }

	    // Process objects at the door
	    for (GameObject obj : objectsToRemove) {
	        if (obj instanceof Astronaut) {
	            Astronaut astronaut = (Astronaut) obj;
	            Log.p("Rescuing Astronaut with Health: " + astronaut.getHealth());
	            currScore += (astronaut.getHealth() * 2);  // Score based on health
	            currAstronautsRescued++;
	            gameObjects.remove(obj);
	        } else if (obj instanceof Alien) {
	            Log.p("Alien sneaked in!");
	            currScore -= 10;  // Deduct points for aliens
	            currAliensSneakedIn++;
	            gameObjects.remove(obj);
	        }
	    }

	    // Update scores
	    totalScore += currScore;
	    astronautsRescued += currAstronautsRescued;
	    aliensSneakedIn += currAliensSneakedIn;

	    // Logging
	    Log.p("Current Score Adjustment: " + currScore);
	    Log.p("Total Score: " + totalScore);
	    Log.p("Total Astronauts Rescued: " + astronautsRescued);
	    Log.p("Total Aliens Sneaked In: " + aliensSneakedIn);

	    // Notify observers about the state change
	    setChanged();
	    notifyObservers();
	}


	/**
	 * Simulates an alien-alien collision and creates a new alien at an offset location.
	 */
	public void alienCollision() {
	    // Collect all aliens from the collection
	    ArrayList<Alien> aliens = new ArrayList<>();
	    IIterator iterator = gameObjects.getIterator();
	    while (iterator.hasNext()) {
	        GameObject obj = iterator.getNext();
	        if (obj instanceof Alien) {
	            aliens.add((Alien) obj);
	        }
	    }

	    if (aliens.size() >= 2) {
	        Alien randomAlien = aliens.get(rand.nextInt(aliens.size()));
	        Point alienLocation = randomAlien.getLocation();
	        float offsetX = rand.nextFloat() * 20 - 10;
	        float offsetY = rand.nextFloat() * 20 - 10;

	        Alien newAlien = new Alien();
	        newAlien.setLocation(new Point(alienLocation.getX() + offsetX, alienLocation.getY() + offsetY));
	        gameObjects.add(newAlien);

	        // Notify observers about the new alien
	        setChanged();
	        notifyObservers();
	    } else {
	        Log.p("Not enough aliens to simulate collision.");
	    }
	}

	/**
	 * Handles the fight between a random Alien and a random Astronaut.
	 * Decreases the Astronaut's health and updates their speed and color.
	 */
	public void alienAstronautFight() {
	    Astronaut randomAstronaut = getRandomAstronaut();
	    Alien randomAlien = getRandomAlien();

	    if (randomAstronaut != null && randomAlien != null) {
	        int newHealth = randomAstronaut.getHealth() - 1;
	        randomAstronaut.setHealth(newHealth);
	        randomAstronaut.setSpeed(newHealth * Astronaut.CONSTANT);
	        randomAstronaut.updateColorBasedOnHealth();

	        if (newHealth <= 0) {
	            randomAstronaut.setSpeed(0);  // Astronaut stops moving
	            Log.p("Astronaut has been defeated by an alien.");
	            // Optionally remove the defeated astronaut from the collection
	            gameObjects.remove(randomAstronaut);
	            rescueAstronaut(); // Increments the count
	        }

	        // Notify observers about the state change
	        setChanged();
	        notifyObservers();
	    } else {
	        Log.p("Not enough astronauts or aliens to initiate a fight.");
	    }
	}


	/**
	 * Prints the current game state, including scores and counts.
	 */
	public void printGameState() {
	    Log.p("Game State:");
	    Log.p("Total Score: " + totalScore);
	    Log.p("Astronauts Rescued: " + astronautsRescued);

	    // Count remaining astronauts and aliens
	    int astronautsLeft = 0;
	    int aliensLeft = 0;

	    IIterator iterator = gameObjects.getIterator();
	    while (iterator.hasNext()) {
	        GameObject obj = iterator.getNext();
	        if (obj instanceof Astronaut) {
	            astronautsLeft++;
	        } else if (obj instanceof Alien) {
	            aliensLeft++;
	        }
	    }

	    Log.p("Astronauts left: " + astronautsLeft);
	    Log.p("Aliens Sneaked In: " + aliensSneakedIn);
	    Log.p("Aliens left: " + aliensLeft);
	    Log.p("Game Clock: " + gameClock);
	}

	/**
	 * Prints the details of all game objects in the collection.
	 */
	public void printMap() {
	    Log.p("Map State:");
	    IIterator iterator = gameObjects.getIterator();
	    while (iterator.hasNext()) {
	        GameObject obj = iterator.getNext();
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
	
	public void cancelExit() {
        this.exitPending = false;
        setChanged();
        notifyObservers();
    }
}
