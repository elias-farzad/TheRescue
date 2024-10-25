package com.mycompany.gameview.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;

/**
 * Command to exit the game with a confirmation dialog.
 */
public class ExitCommand extends Command {

    public ExitCommand() {
        super("Exit"); // Sets the command name used for the button label
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        // Create Yes and No commands
        Command yes = new Command("Yes");
        Command no = new Command("No");

        // Show the confirmation dialog and capture the user's response
        Command response = Dialog.show("Confirm Exit", "Are you sure you want to exit?", yes, no);

        // Check if the user clicked 'Yes'
        if (response == yes) {
            Display.getInstance().exitApplication();
        }
        // If 'No' is clicked or dialog is dismissed, do nothing
    }
}
