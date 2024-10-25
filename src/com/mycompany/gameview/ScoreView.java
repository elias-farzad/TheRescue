package com.mycompany.gameview;

import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.gameworld.GameWorld;
import java.util.Observable;
import java.util.Observer;

/**
 * Displays game state information such as score, astronauts rescued, etc.
 */
public class ScoreView extends Container implements Observer {
    
    private Label clockLabel;
    private Label scoreLabel;
    private Label astronautsRescuedLabel;
    private Label aliensSneakedLabel;
    private Label soundLabel;
    
    /**
     * Constructor initializes the labels and layout.
     */
    public ScoreView() {
        super(new BoxLayout(BoxLayout.X_AXIS));
        
        // Initialize Labels
        clockLabel = new Label("Clock: 0");
        scoreLabel = new Label("Score: 0");
        astronautsRescuedLabel = new Label("Astronauts Rescued: 0");
        aliensSneakedLabel = new Label("Aliens Sneaked: 0");
        soundLabel = new Label("Sound: OFF");
        
        // Add Labels to the Container
        this.addAll(clockLabel, scoreLabel, astronautsRescuedLabel, aliensSneakedLabel, soundLabel);
    }
    
    /**
     * Updates the labels based on changes in the GameWorld.
     *
     * @param o   The observable object.
     * @param arg An argument passed to the notifyObservers method.
     */
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof GameWorld) {
            GameWorld gw = (GameWorld) o;
            clockLabel.setText("Clock: " + gw.getGameClock());
            scoreLabel.setText("Score: " + gw.getTotalScore());
            astronautsRescuedLabel.setText("Astronauts Rescued: " + gw.getAstronautsRescued());
            aliensSneakedLabel.setText("Aliens Sneaked: " + gw.getAliensSneakedIn());
            soundLabel.setText("Sound: " + (gw.isSoundOn() ? "ON" : "OFF"));
            this.revalidate();
        }
    }
    
    /**
     * Updates the sound status label.
     *
     * @param isSoundOn Boolean indicating if sound is on.
     */
    public void updateSoundStatus(boolean isSoundOn) {
        soundLabel.setText("Sound: " + (isSoundOn ? "ON" : "OFF"));
        this.revalidate();
    }
}
