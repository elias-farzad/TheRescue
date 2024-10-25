package com.mycompany.gameview.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.gameworld.GameWorld;

/**
 * Command to move the spaceship to the left.
 */
public class MoveLeftCommand extends Command {
    private GameWorld gw;

    public MoveLeftCommand(GameWorld gw) {
        super("Left");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.moveSpaceshipLeft();
    }
}
