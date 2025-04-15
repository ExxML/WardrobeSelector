package ui.tabs;

import model.Clothing;
import model.Wardrobe;
import ui.WardrobeSelectorApp;

import javax.swing.*;
import java.awt.*;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// Represents a tab where the user can add clothing
public class AddClothingTab extends JPanel {

    private static final int WIDTH = WardrobeSelectorApp.WIDTH;
    private static final int HEIGHT = WardrobeSelectorApp.HEIGHT;

    private JButton addClothingButton;
    private JRadioButton longSleeveTopButton;
    private JRadioButton shortSleeveTopButton;
    private JRadioButton longBottomButton;
    private JRadioButton shortBottomButton;
    private JTextField clothingField;
    
    private String clothingTypeSelected;
    private WardrobeSelectorApp wardrobeApp;

    /*
     * EFFECTS: constructs a tab to add a piece of clothing
     */
    public AddClothingTab(WardrobeSelectorApp wardrobeApp) {
        this.wardrobeApp = wardrobeApp;

        setBorder(BorderFactory.createEmptyBorder(HEIGHT / 7, WIDTH / 18, HEIGHT / 7, WIDTH / 18));

        JLabel title = new JLabel("Enter the name of the clothing you would like to add:", SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(WIDTH, HEIGHT / 14));
        add(title);

        clothingTypeSelected = null;

        clothingField = new JTextField();
        clothingField.setPreferredSize(new Dimension(WIDTH - WIDTH / 3, HEIGHT / 7));
        clothingField.setHorizontalAlignment(SwingConstants.CENTER);
        clothingField.setFont(new Font("Calibri", Font.PLAIN, 22));
        add(clothingField);

        displayClothingTypeButtons();

        displayAddButton();

    }

    /*
     * MODIFIES: this
     * EFFECTS: displays the clothing type select buttons in a group
     */
    private void displayClothingTypeButtons() {
        longSleeveTopButton = new JRadioButton("Long Sleeve Top", false);
        shortSleeveTopButton = new JRadioButton("Short Sleeve Top", false); 
        longBottomButton = new JRadioButton("Long Bottom", false); 
        shortBottomButton = new JRadioButton("Short Bottom", false);

        longSleeveTopButton.setIcon(new ImageIcon("src/res/long_sleeve_top_icon.png"));
        shortSleeveTopButton.setIcon(new ImageIcon("src/res/short_sleeve_top_icon.png"));
        longBottomButton.setIcon(new ImageIcon("src/res/long_bottom_icon.png"));
        shortBottomButton.setIcon(new ImageIcon("src/res/short_bottom_icon.png"));

        ButtonGroup buttons = new ButtonGroup();
        buttons.add(longSleeveTopButton);
        buttons.add(shortSleeveTopButton);
        buttons.add(longBottomButton);
        buttons.add(shortBottomButton);

        setClothingTypeListeners();

        JPanel clothingTypePanel = new JPanel();
        clothingTypePanel.add(shortBottomButton, SwingConstants.CENTER);
        clothingTypePanel.add(longBottomButton, SwingConstants.CENTER);
        clothingTypePanel.add(shortSleeveTopButton, SwingConstants.CENTER);
        clothingTypePanel.add(longSleeveTopButton, SwingConstants.CENTER);
        add(clothingTypePanel);
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets listeners for the clothing type buttons
     */
    private void setClothingTypeListeners() {
        longSleeveTopButton.addActionListener(e -> {
            clearButtonsBackground();
            clothingTypeSelected = "Long Sleeve Top";
            longSleeveTopButton.setBackground(new Color(255, 255, 255));
        });
        shortSleeveTopButton.addActionListener(e -> {
            clearButtonsBackground();
            clothingTypeSelected = "Short Sleeve Top";
            shortSleeveTopButton.setBackground(new Color(255, 255, 255));
        });
        longBottomButton.addActionListener(e -> {
            clearButtonsBackground();
            clothingTypeSelected = "Long Bottom";
            longBottomButton.setBackground(new Color(255, 255, 255));
        });
        shortBottomButton.addActionListener(e -> {
            clearButtonsBackground();
            clothingTypeSelected = "Short Bottom";
            shortBottomButton.setBackground(new Color(255, 255, 255));
        });
    }

    /*
     * MODIFIES: this
     * EFFECTS: clears the background of all the clothing type buttons
     */
    private void clearButtonsBackground() {
        longSleeveTopButton.setBackground(null);
        shortSleeveTopButton.setBackground(null);
        longBottomButton.setBackground(null);
        shortBottomButton.setBackground(null);
    }

    /*
     * MODIFIES: this
     * EFFECTS: displays the add clothing button 
     */
    private void displayAddButton() {
        addClothingButton = new JButton("Add Clothing", new ImageIcon("src/res/add_clothing_icon.png"));
        addClothingButton.setPreferredSize(new Dimension(WIDTH / 3, HEIGHT / 10));
        add(addClothingButton);

        createAddClothingButtonListener();
    }

    /*
     * MODIFIES: this and wardrobeApp
     * EFFECTS: sets up listener for add clothing button
     */
    private void createAddClothingButtonListener() {
        addClothingButton.addActionListener(e -> {
            if (clothingTypeSelected == null) {
                displayMissingClothingTypeWarning();
            } else {
                Wardrobe wardrobe = wardrobeApp.getWardrobe();
                Clothing newClothing = new Clothing(clothingField.getText());
                switch (clothingTypeSelected) {
                    case "Long Sleeve Top":
                        wardrobe.addLongSleeveTop(newClothing);
                        break;
                    case "Short Sleeve Top":
                        wardrobe.addShortSleeveTop(newClothing);
                        break;
                    case "Long Bottom":
                        wardrobe.addLongBottom(newClothing);
                        break;
                    case "Short Bottom":
                        wardrobe.addShortBottom(newClothing);
                        break;
                }
                wardrobeApp.loadTabs();
            }
        });
    }

    /*
     * MODIFIES: this
     * EFFECTS: displays a message and flashes warning to remind the user to select a clothing type
     */
    private void displayMissingClothingTypeWarning() {
        String currentText = clothingField.getText();
        clothingField.setText("Please select the clothing category.");
        flashButtonsBackground();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(() -> {
            clothingField.setText(currentText);
        }, 1000, TimeUnit.MILLISECONDS);
        scheduler.shutdown();
    }

    /*
     * MODIFIES: this
     * EFFECTS: flashes the background of the clothing type buttons red
     */
    private void flashButtonsBackground() {
        longSleeveTopButton.setBackground(new Color(255, 0, 0));
        shortSleeveTopButton.setBackground(new Color(255, 0, 0));
        longBottomButton.setBackground(new Color(255, 0, 0));
        shortBottomButton.setBackground(new Color(255, 0, 0));

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(() -> {
            clearButtonsBackground();
        }, 1000, TimeUnit.MILLISECONDS);
        scheduler.shutdown();
    }
}