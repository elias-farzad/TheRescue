package com.mycompany.gameview.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.gameworld.GameWorld;

/**
 * Command to jump the spaceship to a random alien's location.
 */
public class JumpToAlienCommand extends Command {
    private GameWorld gw;

    public JumpToAlienCommand(GameWorld gw) {
        super("Jump to Alien");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.jumpToRandomAlien();
    }
}
