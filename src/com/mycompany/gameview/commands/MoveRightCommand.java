package com.mycompany.gameview.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.gameworld.GameWorld;

/**
 * Command to move the spaceship to the right.
 */
public class MoveRightCommand extends Command {
    private GameWorld gw;

    public MoveRightCommand(GameWorld gw) {
        super("Right");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.moveSpaceshipRight();
    }
}
