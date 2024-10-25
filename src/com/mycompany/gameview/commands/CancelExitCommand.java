package com.mycompany.gameview.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.gameworld.GameWorld;

/**
 * Command to cancel an exit request.
 */
public class CancelExitCommand extends Command {
    private GameWorld gw;

    public CancelExitCommand(GameWorld gw) {
        super("Cancel Exit");
        this.gw = gw;
    }
    
    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.cancelExit();
    }
}
