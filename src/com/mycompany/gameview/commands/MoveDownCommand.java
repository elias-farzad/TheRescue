package com.mycompany.gameview.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.gameworld.GameWorld;

/**
 * Command to move the spaceship downwards.
 */
public class MoveDownCommand extends Command {
    private GameWorld gw;

    public MoveDownCommand(GameWorld gw) {
        super("Down");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.moveSpaceshipDown();
    }
}
