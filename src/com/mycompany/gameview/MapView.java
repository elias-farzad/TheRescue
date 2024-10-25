package com.mycompany.gameview;

import com.codename1.ui.Container;
import com.codename1.ui.plaf.Border;

/**
 * Represents the map area of the game.
 */
public class MapView extends Container {
    
    /**
     * Constructor initializes the container.
     */
    public MapView() {
    	// Create a red line border
        Border redBorder = Border.createLineBorder(2, 0xff0000);

        // Apply the border to the MapView container
        getAllStyles().setBorder(redBorder);
    }
}
