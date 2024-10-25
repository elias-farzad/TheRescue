package com.mycompany.game;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.gameworld.GameWorld;
import com.mycompany.gameview.ScoreView;
import com.mycompany.gameview.MapView;
import com.mycompany.gameview.commands.*;
import com.mycompany.gameview.components.StyledButton;
import com.codename1.ui.Container;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Toolbar;

/**
 * The main application class acting as the Controller.
 * Extends Form and manages the GUI components.
 */
public class Game extends Form {
    
    private GameWorld gw;
    
    // UI Components
    private ScoreView scoreView;
    private MapView mapView;
    
    // Command Buttons
    private StyledButton expandDoorButton;
    private StyledButton contractDoorButton;
    private StyledButton openDoorButton;
    private StyledButton moveRightButton;
    private StyledButton moveLeftButton;
    private StyledButton moveUpButton;
    private StyledButton moveDownButton;
    private StyledButton jumpToAstronautButton;
    private StyledButton jumpToAlienButton;
    private StyledButton createAlienButton;
    private StyledButton alienAstronautFightButton;
    private StyledButton clockTickButton;
    private StyledButton toggleSoundButton;
    private StyledButton exitButton;
    
    // Commands
    private ExpandDoorCommand expandDoorCommand;
    private ContractDoorCommand contractDoorCommand;
    private OpenDoorCommand openDoorCommand;
    private MoveRightCommand moveRightCommand;
    private MoveLeftCommand moveLeftCommand;
    private MoveUpCommand moveUpCommand;
    private MoveDownCommand moveDownCommand;
    private JumpToAstronautCommand jumpToAstronautCommand;
    private JumpToAlienCommand jumpToAlienCommand;
    private CreateAlienCommand createAlienCommand;
    private AlienAstronautFightCommand alienAstronautFightCommand;
    private ClockTickCommand clockTickCommand;
    private ToggleSoundCommand toggleSoundCommand;
    private ExitCommand exitCommand;
    
    /**
     * Constructor initializes the Model and View, and sets up the GUI.
     */
    public Game() {
        super("Space Rescue Game", new BorderLayout());
        
        // Initialize GameWorld (Model)
        gw = GameWorld.getInstance();
        gw.init();
        
        // Initialize UI Components
        initUI();
        
        // Set up Side Menu
        setupSideMenu();
        
        // Set up Key Bindings
        setupKeyBindings();
        
        // Register ScoreView as Observer
        gw.addObserver(scoreView);
        
        // Show the form
        this.show();
    }

    /**
     * Initializes the UI components and layout.
     */
    private void initUI() {
        // Initialize ScoreView and MapView
        scoreView = new ScoreView();
        mapView = new MapView();
        
        // Add ScoreView to North and MapView to Center
        this.add(BorderLayout.NORTH, scoreView);
        this.add(BorderLayout.CENTER, mapView);
        
        // Initialize Command Buttons
        expandDoorButton = new StyledButton("Expand Door");
        contractDoorButton = new StyledButton("Contract Door");
        openDoorButton = new StyledButton("Open Door");
        moveRightButton = new StyledButton("Move Right");
        moveLeftButton = new StyledButton("Move Left");
        moveUpButton = new StyledButton("Move Up");
        moveDownButton = new StyledButton("Move Down");
        jumpToAstronautButton = new StyledButton("Jump to Astronaut");
        jumpToAlienButton = new StyledButton("Jump to Alien");
        createAlienButton = new StyledButton("Create Alien");
        alienAstronautFightButton = new StyledButton("Alien Astronaught Fight");
        clockTickButton = new StyledButton("Clock Tick");
        toggleSoundButton = new StyledButton("Toggle Sound");
        exitButton = new StyledButton("Exit");
        
        // Initialize Command Objects
        expandDoorCommand = new ExpandDoorCommand(gw);
        contractDoorCommand = new ContractDoorCommand(gw);
        openDoorCommand = new OpenDoorCommand(gw);
        moveRightCommand = new MoveRightCommand(gw);
        moveLeftCommand = new MoveLeftCommand(gw);
        moveUpCommand = new MoveUpCommand(gw);
        moveDownCommand = new MoveDownCommand(gw);
        jumpToAstronautCommand = new JumpToAstronautCommand(gw);
        jumpToAlienCommand = new JumpToAlienCommand(gw);
        createAlienCommand = new CreateAlienCommand(gw);
        alienAstronautFightCommand = new AlienAstronautFightCommand(gw);
        clockTickCommand = new ClockTickCommand(gw);
        toggleSoundCommand = new ToggleSoundCommand(gw);
        exitCommand = new ExitCommand();
        
        // Assign Commands to Buttons
        expandDoorButton.setCommand(expandDoorCommand);
        contractDoorButton.setCommand(contractDoorCommand);
        openDoorButton.setCommand(openDoorCommand);
        moveRightButton.setCommand(moveRightCommand);
        moveLeftButton.setCommand(moveLeftCommand);
        moveUpButton.setCommand(moveUpCommand);
        moveDownButton.setCommand(moveDownCommand);
        jumpToAstronautButton.setCommand(jumpToAstronautCommand);
        jumpToAlienButton.setCommand(jumpToAlienCommand);
        createAlienButton.setCommand(createAlienCommand);
        alienAstronautFightButton.setCommand(alienAstronautFightCommand);
        clockTickButton.setCommand(clockTickCommand);
        toggleSoundButton.setCommand(toggleSoundCommand);
        exitButton.setCommand(exitCommand);
        
        // Create Containers for West, East, and South
        Container westContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container eastContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container southContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        
        // Add Buttons to Containers
        westContainer.addAll(moveUpButton, moveDownButton);
        eastContainer.addAll(moveLeftButton, moveRightButton);
        southContainer.addAll(expandDoorButton, contractDoorButton, openDoorButton,
                               jumpToAstronautButton, jumpToAlienButton, createAlienButton,
                               alienAstronautFightButton, clockTickButton, toggleSoundButton, exitButton);
        
        // Add Containers to the Form
        this.add(BorderLayout.WEST, westContainer);
        this.add(BorderLayout.EAST, eastContainer);
        this.add(BorderLayout.SOUTH, southContainer);
    }
    
    /**
     * Sets up the side menu with "Score", "Sound", "About", and "Exit" items.
     */
    private void setupSideMenu() {
        Toolbar tb = this.getToolbar();
        
        // Add "Score" Menu Item
        tb.addMaterialCommandToSideMenu("Score", FontImage.MATERIAL_SCORE, e -> {
            // Implement Score Command
            Dialog.show("Score", "Current Score Details are in the Score View.", "OK", null);
        });
        
        // Add "Sound" Menu Item with Checkbox
        CheckBox soundCheckbox = new CheckBox("Sound");
        soundCheckbox.setSelected(gw.isSoundOn());
        soundCheckbox.addActionListener(e -> {
            gw.toggleSound();
            soundCheckbox.setSelected(gw.isSoundOn());
            scoreView.updateSoundStatus(gw.isSoundOn());
        });
        tb.addComponentToSideMenu(soundCheckbox);
        
        // Add "About" Menu Item
        tb.addMaterialCommandToSideMenu("About", FontImage.MATERIAL_INFO, e -> {
            Dialog.show("About", "App by Elias Farzad\nCourse: Object Oriented Design\nVersion: 2.0", "OK", null);
        });
        
        // Add "Exit" Menu Item
        tb.addMaterialCommandToSideMenu("Exit", FontImage.MATERIAL_EXIT_TO_APP, e -> {
            exitCommand.actionPerformed(null);
        });
        
        // Add "Help" Menu Item to the Right Side of the Title Bar
        tb.addCommandToRightBar("Help", null, e -> {
            Dialog.show("Help", "Commands:\n" +
                                      "a: Jump to Alien\n" +
                                      "o: Jump to Astronaut\n" +
                                      "r: Move Right\n" +
                                      "l: Move Left\n" +
                                      "u: Move Up\n" +
                                      "d: Move Down\n" +
                                      "e: Expand Door\n" +
                                      "c: Contract Door\n" +
                                      "t: Clock Tick\n" +
                                      "s: Open Door\n" +
                                      "w: Alien Creation\n" +
                                      "f: Alien-Astronaut Fight\n" +
                                      "x: Exit Game\n" +
                                      "Sound can be toggled via the Sound button or menu item.", "OK", null);
        });
    }
    
    /**
     * Sets up key bindings for various commands.
     */
    private void setupKeyBindings() {
        this.addKeyListener('a', e -> jumpToAlienCommand.actionPerformed(null));
        this.addKeyListener('o', e -> jumpToAstronautCommand.actionPerformed(null));
        this.addKeyListener('r', e -> moveRightCommand.actionPerformed(null));
        this.addKeyListener('l', e -> moveLeftCommand.actionPerformed(null));
        this.addKeyListener('u', e -> moveUpCommand.actionPerformed(null));
        this.addKeyListener('d', e -> moveDownCommand.actionPerformed(null));
        this.addKeyListener('e', e -> expandDoorCommand.actionPerformed(null));
        this.addKeyListener('c', e -> contractDoorCommand.actionPerformed(null));
        this.addKeyListener('t', e -> clockTickCommand.actionPerformed(null));
        this.addKeyListener('s', e -> openDoorCommand.actionPerformed(null));
        this.addKeyListener('w', e -> createAlienCommand.actionPerformed(null));
        this.addKeyListener('f', e -> alienAstronautFightCommand.actionPerformed(null));
        this.addKeyListener('x', e -> exitCommand.actionPerformed(null));
        this.addKeyListener('y', e -> exitCommand.actionPerformed(null)); // Assuming 'y' confirms exit
        this.addKeyListener('n', e -> exitCommand.actionPerformed(null));
    }
    
}