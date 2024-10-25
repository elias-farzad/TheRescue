package com.mycompany.gameview.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.gameworld.GameWorld;

/**
 * Command to advance the game clock.
 */
public class ClockTickCommand extends Command {
    private GameWorld gw;

    public ClockTickCommand(GameWorld gw) {
        super("Clock Tick");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.clockTick();
    }
}
