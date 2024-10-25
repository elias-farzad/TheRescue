package com.mycompany.gameview.commands;

import com.codename1.ui.Command;
import com.mycompany.gameworld.GameWorld;
import com.codename1.ui.events.ActionEvent;

/**
 * Command to create a new alien.
 */
public class CreateAlienCommand extends Command {
    private GameWorld gw;
    
    public CreateAlienCommand(GameWorld gw) {
        super("Create Alien");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.createNewAlien();
    }
}
