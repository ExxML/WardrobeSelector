package ui.tabs;

import model.Wardrobe;
import ui.WardrobeSelectorApp;

import javax.swing.*;
import java.awt.*;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// Represents a tab where the user can remove clothing
public class RemoveClothingTab extends JPanel {

    private static final int WIDTH = WardrobeSelectorApp.WIDTH;
    private static final int HEIGHT = WardrobeSelectorApp.HEIGHT;

    private JTextField clothingField;
    
    private WardrobeSelectorApp wardrobeApp;

    /*
     * EFFECTS: constructs a tab to remove a piece of clothing 
     */
    public RemoveClothingTab(WardrobeSelectorApp wardrobeApp) {
        this.wardrobeApp = wardrobeApp;

        setBorder(BorderFactory.createEmptyBorder(HEIGHT / 7, WIDTH / 18, HEIGHT / 7, WIDTH / 18));
        
        JLabel title = new JLabel("Enter the name of the clothing you would like to remove:", SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(WIDTH, HEIGHT / 14));
        add(title);

        clothingField = new JTextField();
        clothingField.setPreferredSize(new Dimension(WIDTH - WIDTH / 3, HEIGHT / 7));
        clothingField.setHorizontalAlignment(SwingConstants.CENTER);
        clothingField.setFont(new Font("Calibri", Font.PLAIN, 22));
        add(clothingField);
        
        displayRemoveButton();
    }

    /*
     * MODIFIES: this and wardrobeApp
     * EFFECTS: displays the remove clothing button 
     */
    private void displayRemoveButton() {
        JButton removeClothingButton = new JButton("Remove Clothing", 
                new ImageIcon("src/res/remove_clothing_icon.png"));
        removeClothingButton.setPreferredSize(new Dimension(WIDTH / 3, HEIGHT / 10));
        add(removeClothingButton);

        removeClothingButton.addActionListener(e -> {
            Wardrobe wardrobe = wardrobeApp.getWardrobe();
            if (wardrobe.removeClothing(clothingField.getText()).equals("")) {
                clothingField.setText(clothingField.getText() + " does not exist in your wardrobe.");
                ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                scheduler.schedule(() -> {
                    clothingField.setText("");
                }, 1500, TimeUnit.MILLISECONDS);
                scheduler.shutdown();
            } else {
                wardrobeApp.loadTabs();
            }
        });
    }

}