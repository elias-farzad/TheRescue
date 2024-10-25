package com.mycompany.gameview.commands;

import com.codename1.ui.Command;
import com.mycompany.gameworld.GameWorld;
import com.codename1.ui.events.ActionEvent;

/**
 * Command to contract the spaceship's door.
 */
public class ContractDoorCommand extends Command {
    private GameWorld gw;
    
    public ContractDoorCommand(GameWorld gw) {
        super("Contract Door");
        this.gw = gw;
    }
    
    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.contractSpaceshipDoor();
    }
}
