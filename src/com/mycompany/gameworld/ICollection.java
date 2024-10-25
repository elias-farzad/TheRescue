package com.mycompany.gameworld;

public interface ICollection {
	
	void add(GameObject obj);
    boolean remove(GameObject obj);
	IIterator getIterator();

}
