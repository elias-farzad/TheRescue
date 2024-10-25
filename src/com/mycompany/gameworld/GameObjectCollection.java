package com.mycompany.gameworld;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class GameObjectCollection implements ICollection {
	
	private ArrayList<GameObject> gameObjects;
	
	public GameObjectCollection() {
		gameObjects = new ArrayList<>();
	}

	@Override
	public void add(GameObject obj) {
		gameObjects.add(obj);		
	}
	
    @Override
    public boolean remove(GameObject obj) {
        return gameObjects.remove(obj);
    }

	@Override
	public IIterator getIterator() {
		return new GameObjectIterator();
	}
	
	private class GameObjectIterator implements IIterator {
		
		private int currentIndex = 0;
        private boolean canRemove = false; // To track if remove() can be called

		@Override
		public boolean hasNext() {
			return currentIndex < gameObjects.size();
		}

		@Override
		public GameObject getNext() {
			if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the collection.");
            }
            canRemove = true;
            return gameObjects.get(currentIndex++);
		}

		@Override
		public void remove() {
			if (!canRemove) {
                throw new IllegalStateException("Cannot remove before calling getNext().");
            }
            // Remove the element at currentIndex - 1
            gameObjects.remove(currentIndex--);
            canRemove = false;
			
		}
		
	}

}
