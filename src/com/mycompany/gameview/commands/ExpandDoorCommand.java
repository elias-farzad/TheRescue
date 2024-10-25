package com.mycompany.gameview.commands;

import com.codename1.ui.Command;
import com.mycompany.gameworld.GameWorld;
import com.codename1.ui.events.ActionEvent;

/**
 * Command to expand the spaceship's door.
 */
public class ExpandDoorCommand extends Command {
    private GameWorld gw;
    
    public ExpandDoorCommand(GameWorld gw) {
        super("Expand Door");
        this.gw = gw;
    }
    
    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.expandSpaceshipDoor();
    }
}
