package com.mycompany.game;

import com.codename1.io.Log;
import com.codename1.ui.*;
import com.codename1.ui.events.*;
import com.mycompany.gameworld.GameWorld;
import java.lang.String;
import com.codename1.ui.TextField;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;


public class Game  extends Form{
	private GameWorld gw;

	public Game() {
		gw = new GameWorld();
		gw.init();
		play();
	}

	private void play() {

		// Accept and execute user commands that operate on the game world
		Label myLabel = new Label("Enter a Command:");
		this.addComponent(myLabel);

		final TextField myTextField = new TextField();
		this.addComponent(myTextField);

		System.out.println("Before setting up the form.");
		this.show();
		System.out.println("Form displayed.");


		myTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {

					String sCommand=myTextField.getText().toString();

					myTextField.setText("");

					if(sCommand.length() != 0) {

						// Dialog box to confirm the command was received (debug)
						Dialog.show("Command Received", "You entered: " + sCommand, "OK", null);
						Log.p("Command entered: " + sCommand); // Log the command

						switch (sCommand.charAt(0)) {
						case 'a':
							gw.jumpToRandomAlien();
							Dialog.show("Action", "Jumped to random alien.", "OK", null);
							Log.p("Jumped to random alien.");
							break;
						case 'o':
							gw.jumpToRandomAstronaut();
							Dialog.show("Action", "Jumped to a random astronaut.", "OK", null);
							Log.p("Executed command: Jumped to a random astronaut.");
							break;

						case 'r':  // Move spaceship right
							gw.moveSpaceshipRight();
							Dialog.show("Action", "Moved spaceship to the right.", "OK", null);
							Log.p("Executed command: Moved spaceship to the right.");
							break;

						case 'l':  // Move spaceship left
							gw.moveSpaceshipLeft();
							Dialog.show("Action", "Moved spaceship to the left.", "OK", null);
							Log.p("Executed command: Moved spaceship to the left.");
							break;

						case 'u':  // Move spaceship up
							gw.moveSpaceshipUp();
							Dialog.show("Action", "Moved spaceship up.", "OK", null);
							Log.p("Executed command: Moved spaceship up.");
							break;

						case 'd':  // Move spaceship down
							gw.moveSpaceshipDown();
							Dialog.show("Action", "Moved spaceship down.", "OK", null);
							Log.p("Executed command: Moved spaceship down.");
							break;

						case 'e':  // Expand spaceship door
							gw.expandSpaceshipDoor();
							Dialog.show("Action", "Expanded spaceship door.", "OK", null);
							Log.p("Executed command: Expanded spaceship door.");
							break;

						case 'c':  // Contract spaceship door
							gw.contractSpaceshipDoor();
							Dialog.show("Action", "Contracted spaceship door.", "OK", null);
							Log.p("Executed command: Contracted spaceship door.");
							break;

						case 't':  // Tick the game clock
							gw.clockTick();
							Dialog.show("Action", "Game clock ticked.", "OK", null);
							Log.p("Executed command: Ticked the game clock.");
							break;

						case 's':  // Open spaceship door
							gw.openSpaceshipDoor();
							Dialog.show("Action", "Opened spaceship door.", "OK", null);
							Log.p("Executed command: Opened spaceship door.");
							break;

						case 'w':  // Simulate alien-alien collision
							gw.alienCollision();
							Dialog.show("Action", "Simulated alien-alien collision.", "OK", null);
							Log.p("Executed command: Simulated alien-alien collision.");
							break;

						case 'f':  // Simulate alien-astronaut fight
							gw.alienAstronautFight();
							Dialog.show("Action", "Simulated alien-astronaut fight.", "OK", null);
							Log.p("Executed command: Simulated alien-astronaut fight.");
							break;

						case 'p':  // Print game state
							gw.printGameState();
							Dialog.show("Game State", "Check the logs for game state details.", "OK", null);
							Log.p("Executed command: Printed game state.");
							break;

						case 'm':  // Print game world map
							gw.printMap();
							Dialog.show("Map", "Check the logs for the game world map.", "OK", null);
							Log.p("Executed command: Printed game world map.");
							break;

						case 'x':  // Exit the game
							gw.setExitPending(true);  // Set a flag to confirm exit
							Dialog.show("Exit Confirmation", "Are you sure you want to exit? (y/n)", "OK", null);
							Log.p("Executed command: Requested to exit the game.");
							break;

						case 'y':  // Confirm exit
							if (gw.isExitPending()) {
								Dialog.show("Exit", "Exiting the game. Goodbye!", "OK", null);
								Log.p("Executed command: Confirmed exit. Exiting game.");
								System.exit(0);  // Exit the program
							} else {
								Dialog.show("Exit", "No exit request pending.", "OK", null);
								Log.p("Executed command: No exit pending. Exit command ignored.");
							}
							break;

						case 'n':  // Cancel exit
							if (gw.isExitPending()) {
								gw.setExitPending(false);  // Cancel exit confirmation
								Dialog.show("Exit", "Exit canceled.", "OK", null);
								Log.p("Executed command: Canceled exit.");
							} else {
								Dialog.show("Exit", "No exit request pending.", "OK", null);
								Log.p("Executed command: No exit pending. Cancel command ignored.");
							}
							break;

						default:
							// Handle invalid commands
							Dialog.show("Error", "Invalid Command: " + sCommand, "OK", null);
							Log.p("Invalid command entered: " + sCommand);
							break;
						}
					} else {
						// Handle empty command input
						Dialog.show("Error", "No Command Entered.", "OK", null);
						Log.p("No command entered.");
					}
				} catch (Exception e) {
					// Catch and log any unexpected exceptions
					Dialog.show("Exception", "An error occurred: " + e.getMessage(), "OK", null);
					Log.e(e);
				}
			}
		});
	}
}

