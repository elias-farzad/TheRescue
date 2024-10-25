package com.mycompany.gameview.components;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.plaf.Style;

/**
 * Custom styled button with predefined styling.
 */
public class StyledButton extends Button {
    
    /**
     * Constructor sets the button's label and applies custom styles.
     * @param label The text to display on the button.
     */
    public StyledButton(String label) {
        super(label);
        applyStyles();
    }
    
    /**
     * Applies custom styles to the button.
     */
    private void applyStyles() {
        Style s = this.getAllStyles();
        s.setBgTransparency(255); // Fully opaque background
        s.setBgColor(0x3366FF); // Example: Blue background
        s.setFgColor(0xFFFFFF); // White text
        s.setPadding(Component.TOP, 5);
        s.setPadding(Component.BOTTOM, 5);
        s.setPadding(Component.LEFT, 10);
        s.setPadding(Component.RIGHT, 10);
    }
}
