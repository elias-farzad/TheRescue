package com.mycompany.gameview.commands;

import com.codename1.ui.Command;
import com.mycompany.gameworld.GameWorld;
import com.codename1.ui.events.ActionEvent;

/**
 * Command to open the spaceship's door and update the score.
 */
public class OpenDoorCommand extends Command {
    private GameWorld gw;

    public OpenDoorCommand(GameWorld gw) {
        super("Open Door");
        this.gw = gw;
    }
    
    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.openSpaceshipDoor();
    }
}
