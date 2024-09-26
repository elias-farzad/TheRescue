package com.mycompany.gameworld;

public interface IGuided {
	
	void moveLeft();
	void moveRight();
	void moveUp();
	void moveDown();
	void jumpToLocation(GameObject location);

}
