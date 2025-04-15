package ui.tabs;

import ui.WardrobeSelectorApp;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// Represents a tab where the user can be suggested a set of clothing based on the temperature
public class SuggestClothingTab extends JPanel {

    private static final int WIDTH = WardrobeSelectorApp.WIDTH;
    private static final int HEIGHT = WardrobeSelectorApp.HEIGHT;

    private JTextField temperatureField;
    private JPanel clothingSuggestionPanel;
    private JPanel clothingWarningPanel;
    
    private WardrobeSelectorApp wardrobeApp;

    /*
     * EFFECTS: constructs a tab to suggest a set of clothing based on temperature
     */
    public SuggestClothingTab(WardrobeSelectorApp wardrobeApp) {
        this.wardrobeApp = wardrobeApp;

        setBorder(BorderFactory.createEmptyBorder(HEIGHT / 7, WIDTH / 18, HEIGHT / 7, WIDTH / 18));

        JLabel title = new JLabel("Enter the temperature in Celsius (°C):", SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(WIDTH, HEIGHT / 14));
        add(title);

        temperatureField = new JTextField();
        temperatureField.setPreferredSize(new Dimension(WIDTH - WIDTH / 3, HEIGHT / 7));
        temperatureField.setHorizontalAlignment(SwingConstants.CENTER);
        temperatureField.setFont(new Font("Calibri", Font.PLAIN, 22));
        add(temperatureField);

        clothingSuggestionPanel = new JPanel(new GridLayout(4, 1));
        clothingSuggestionPanel.setBorder(new EmptyBorder(HEIGHT / 16, WIDTH / 9, HEIGHT / 16, WIDTH / 9));
        clothingWarningPanel = new JPanel(new GridLayout(2, 1));
        clothingWarningPanel.setBorder(new EmptyBorder(HEIGHT / 16, WIDTH / 9, HEIGHT / 16, WIDTH / 9));

        displaySuggestButton();
        
    }

    /*
     * MODIFIES: this
     * EFFECTS: displays the suggest clothing button
     */
    private void displaySuggestButton() {
        JButton suggestClothingButton = new JButton("Suggest Clothing", 
                new ImageIcon("src/res/suggest_clothing_icon.png"));
        suggestClothingButton.setPreferredSize(new Dimension(WIDTH / 3, HEIGHT / 10));
        add(suggestClothingButton);

        suggestClothingButton.addActionListener(e -> {
            temperatureSelector();
        });
    }

    /*
     * EFFECTS: prints randomly selected, available clothing from certain clothing categories depending on temp (°C)
     */
    private void temperatureSelector() {
        try {
            double temp = Double.valueOf(temperatureField.getText());
            temperatureMatcher(temp);
        } catch (NumberFormatException ex) {
            temperatureField.setText(temperatureField.getText() + " is not a number.");
            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            scheduler.schedule(() -> {
                temperatureField.setText("");
            }, 1500, TimeUnit.MILLISECONDS);
            scheduler.shutdown();
        }
    }

    /*
     * REQUIRES: temp is a double
     * EFFECTS: selects from long sleeve tops and long bottoms if temp <= 5 °C, and reminds user to wear a jacket
     *                selects from long sleeve tops and long bottoms if 5 °C < temp <= 20 °C
     *                selects from short sleeve tops and long bottoms if 20 °C < temp <= 30 °C
     *                else selects from short sleeve tops and short bottoms (if temp > 30 °C), and 
     *                reminds user to wear sunscreen
     */
    private void temperatureMatcher(double temp) {
        if (temp <= 5) {
            selectRandomClothingCold(temp);
        } else if ((5 < temp) && (temp <= 20)) {
            selectRandomClothingCool(temp);
        } else if ((20 < temp) && (temp <= 30)) {
            selectRandomClothingWarm(temp);
        } else {
            selectRandomClothingHot(temp);
        }
    }

    /*
     * REQUIRES: temp is a double
     * EFFECTS: selects clothing from long sleeve tops and long bottoms if temp <= 5 °C, and 
     *                reminds user to wear a jacket
     */
    private void selectRandomClothingCold(double temp) {
        if (!nullSelectRandomLongSleeveTop() && !nullSelectRandomLongBottom()) {
            JLabel suggestedMessage = new JLabel("Here is your suggested set of clothes for " 
                    + temp + "°C!", SwingConstants.LEFT);
            JLabel top = new JLabel("Long Sleeve Top: " 
                    + wardrobeApp.getWardrobe().selectRandomLongSleeveTop().getClothingName(),
                    new ImageIcon("src/res/long_sleeve_top_icon.png"), SwingConstants.LEFT);
            JLabel bottom = new JLabel("Long Bottom: " 
                    + wardrobeApp.getWardrobe().selectRandomLongBottom().getClothingName(), 
                    new ImageIcon("src/res/long_bottom_icon.png"), SwingConstants.LEFT);
            JLabel reminder = new JLabel("Remember to wear a jacket!", SwingConstants.LEFT);

            displaySuggestion(suggestedMessage, top, bottom, reminder);
        }
    }

    /*
     * REQUIRES: temp is a double
     * EFFECTS: selects clothing from long sleeve tops and long bottoms if 5 °C < temp <= 20 °C
     */
    private void selectRandomClothingCool(double temp) {
        if (!nullSelectRandomLongSleeveTop() && !nullSelectRandomLongBottom()) {
            JLabel suggestedMessage = new JLabel("Here is your suggested set of clothes for " 
                    + temp + "°C!", SwingConstants.LEFT);
            JLabel top = new JLabel("Long Sleeve Top: " 
                    + wardrobeApp.getWardrobe().selectRandomLongSleeveTop().getClothingName(),
                    new ImageIcon("src/res/long_sleeve_top_icon.png"), SwingConstants.LEFT);
            JLabel bottom = new JLabel("Long Bottom: " 
                    + wardrobeApp.getWardrobe().selectRandomLongBottom().getClothingName(), 
                    new ImageIcon("src/res/long_bottom_icon.png"), SwingConstants.LEFT);

            displaySuggestion(suggestedMessage, top, bottom, null);
        }
    }

    /*
     * REQUIRES: temp is a double
     * EFFECTS: selects clothing from short sleeve tops and long bottoms if 20 °C < temp <= 30 °C
     */
    private void selectRandomClothingWarm(double temp) {
        if (!nullSelectRandomShortSleeveTop() && !nullSelectRandomLongBottom()) {
            JLabel suggestedMessage = new JLabel("Here is your suggested set of clothes for " 
                    + temp + "°C!", SwingConstants.LEFT);
            JLabel top = new JLabel("Short Sleeve Top: " 
                    + wardrobeApp.getWardrobe().selectRandomShortSleeveTop().getClothingName(),
                    new ImageIcon("src/res/short_sleeve_top_icon.png"), SwingConstants.LEFT);
            JLabel bottom = new JLabel("Long Bottom: " 
                    + wardrobeApp.getWardrobe().selectRandomLongBottom().getClothingName(), 
                    new ImageIcon("src/res/long_bottom_icon.png"), SwingConstants.LEFT);

            displaySuggestion(suggestedMessage, top, bottom, null);
        }
    }

    /*
     * REQUIRES: temp is a double
     * EFFECTS: selects clothing from short sleeve tops and short bottoms if 30 °C < temp, and 
     *                reminds user to wear sunscreen
     */
    private void selectRandomClothingHot(double temp) {
        if (!nullSelectRandomShortSleeveTop() && !nullSelectRandomShortBottom()) {
            JLabel suggestedMessage = new JLabel("Here is your suggested set of clothes for " 
                    + temp + "°C!", SwingConstants.LEFT);
            JLabel top = new JLabel("Short Sleeve Top: " 
                    + wardrobeApp.getWardrobe().selectRandomShortSleeveTop().getClothingName(),
                    new ImageIcon("src/res/short_sleeve_top_icon.png"), SwingConstants.LEFT);
            JLabel bottom = new JLabel("Short Bottom: " 
                    + wardrobeApp.getWardrobe().selectRandomShortBottom().getClothingName(), 
                    new ImageIcon("src/res/short_bottom_icon.png"), SwingConstants.LEFT);
            JLabel reminder = new JLabel("Remember to wear sunscreen!", SwingConstants.LEFT);

            displaySuggestion(suggestedMessage, top, bottom, reminder);
        }
    }

    /*
     * EFFECTS: return true if there are no available long sleeve tops, false otherwise
     */
    private boolean nullSelectRandomLongSleeveTop() {
        if (wardrobeApp.getWardrobe().selectRandomLongSleeveTop() == null) {
            displayClothingWarning("long sleeve top");
            return true;
        }
        return false;
    }

    /*
     * EFFECTS: return true if there are no available short sleeve tops, false otherwise
     */
    private boolean nullSelectRandomShortSleeveTop() {
        if (wardrobeApp.getWardrobe().selectRandomShortSleeveTop() == null) {
            displayClothingWarning("short sleeve top");
            return true;
        }
        return false;
    }

    /*
     * EFFECTS: return true if there are no available long bottoms, false otherwise
     */
    private boolean nullSelectRandomLongBottom() {
        if (wardrobeApp.getWardrobe().selectRandomLongBottom() == null) {
            displayClothingWarning("long bottom");
            return true;
        }
        return false;
    }

    /*
     * EFFECTS: return true if there are no available short bottoms, false otherwise
     */
    private boolean nullSelectRandomShortBottom() {
        if (wardrobeApp.getWardrobe().selectRandomShortBottom() == null) {
            displayClothingWarning("short bottom");
            return true;
        }
        return false;
    }

    /*
     * MODIFIES: this
     * EFFECTS: displays the warning message for a missing clothing
     */
    private void displayClothingWarning(String clothingType) {
        remove(clothingWarningPanel);
        remove(clothingSuggestionPanel);

        JLabel message1 = new JLabel("You do not have any available " + clothingType + "s.", SwingConstants.LEFT);
        JLabel message2 = new JLabel("Please toggle the availability of a " 
                + clothingType + " or add a new one.", SwingConstants.LEFT);
        message1.setFont(new Font("Calibri", Font.PLAIN, 22));
        message2.setFont(new Font("Calibri", Font.PLAIN, 22));

        clothingWarningPanel = new JPanel(new GridLayout(2, 1));
        clothingWarningPanel.setBorder(new EmptyBorder(HEIGHT / 16, WIDTH / 9, HEIGHT / 16, WIDTH / 9));
        clothingWarningPanel.add(message1);
        clothingWarningPanel.add(message2);

        add(clothingWarningPanel);
        wardrobeApp.setVisible(true);
    }

    /*
     * MODIFIES: this
     * EFFECTS: displays messages for the suggested set of clothing
     */
    private void displaySuggestion(JLabel suggestedMessage, JLabel top, JLabel bottom, JLabel reminder) {
        remove(clothingWarningPanel);
        remove(clothingSuggestionPanel);

        suggestedMessage.setFont(new Font("Calibri", Font.PLAIN, 22));
        Map<TextAttribute, Object> attributes = new HashMap<>(suggestedMessage.getFont().getAttributes());
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        suggestedMessage.setFont(suggestedMessage.getFont().deriveFont(attributes));
        top.setFont(new Font("Calibri", Font.PLAIN, 22));
        bottom.setFont(new Font("Calibri", Font.PLAIN, 22));

        clothingSuggestionPanel = new JPanel(new GridLayout(4, 1));
        clothingSuggestionPanel.setBorder(new EmptyBorder(HEIGHT / 16, WIDTH / 9, HEIGHT / 16, WIDTH / 9));
        clothingSuggestionPanel.add(suggestedMessage);
        clothingSuggestionPanel.add(top);
        clothingSuggestionPanel.add(bottom);

        if (reminder != null) {
            reminder.setFont(new Font("Calibri", Font.PLAIN, 22));
            clothingSuggestionPanel.add(reminder);
        }

        add(clothingSuggestionPanel);
        wardrobeApp.setVisible(true);
    }

}