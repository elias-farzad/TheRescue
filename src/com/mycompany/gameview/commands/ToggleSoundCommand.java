package com.mycompany.gameview.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.gameworld.GameWorld;

/**
 * Command to toggle the game's sound on or off.
 */
public class ToggleSoundCommand extends Command {
    private GameWorld gw;

    public ToggleSoundCommand(GameWorld gw) {
        super("Toggle Sound");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.toggleSound();
        String soundStatus = gw.isSoundOn() ? "ON" : "OFF";
        Dialog.show("Sound Toggle", "Sound is now " + soundStatus, "OK", null);
    }
}
