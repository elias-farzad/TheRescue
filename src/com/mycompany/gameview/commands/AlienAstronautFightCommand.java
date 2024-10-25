package com.mycompany.gameview.commands;

import com.codename1.ui.Command;
import com.mycompany.gameworld.GameWorld;
import com.codename1.ui.events.ActionEvent;

/**
 * Command to simulate an alien-astronaut fight.
 */
public class AlienAstronautFightCommand extends Command { 
    private GameWorld gw;

    public AlienAstronautFightCommand(GameWorld gw) {
        super("Alien-Astronaut Fight");
        this.gw = gw;
    }
    
    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.alienAstronautFight();
    }
}
