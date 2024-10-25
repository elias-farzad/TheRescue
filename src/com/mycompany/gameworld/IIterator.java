package com.mycompany.gameworld;

public interface IIterator {
	
	boolean hasNext();
	GameObject getNext();
	void remove();
	
}
