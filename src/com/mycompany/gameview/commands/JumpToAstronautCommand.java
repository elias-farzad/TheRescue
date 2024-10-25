package com.mycompany.gameview.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.gameworld.GameWorld;

/**
 * Command to jump the spaceship to a random astronaut's location.
 */
public class JumpToAstronautCommand extends Command {
    private GameWorld gw;

    public JumpToAstronautCommand(GameWorld gw) {
        super("Jump to Astronaut");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.jumpToRandomAstronaut();
    }
}
