package com.mycompany.gameview.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.gameworld.GameWorld;

/**
 * Command to move the spaceship upwards.
 */
public class MoveUpCommand extends Command {
    private GameWorld gw;

    public MoveUpCommand(GameWorld gw) {
        super("Up"); // Command name used for button label
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.moveSpaceshipUp();
    }
}
