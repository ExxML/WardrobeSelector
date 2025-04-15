package ui.tabs;

import model.Clothing;
import ui.WardrobeSelectorApp;

import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;

// Represents a tab that displays all clothing in the wardrobe and its availability
public class WardrobeTab extends JPanel {

    private static final int WIDTH = WardrobeSelectorApp.WIDTH;
    private static final int HEIGHT = WardrobeSelectorApp.HEIGHT;

    private WardrobeSelectorApp wardrobeApp;

    /*
     * EFFECTS: constructs a tab to display clothing
     */
    public WardrobeTab(WardrobeSelectorApp wardrobeApp) {
        this.wardrobeApp = wardrobeApp;

        setBorder(BorderFactory.createEmptyBorder(0, 0, HEIGHT / 14, 0));

        JLabel title = new JLabel("Wardrobe Selector App", SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(WIDTH, HEIGHT / 18));
        add(title);

        displayTopsHeader();
        displayTops();

        displayBottomsHeader();
        displayBottoms();
    }

    /*
     * MODIFIES: this
     * EFFECTS: display tops header
     */
    private void displayTopsHeader() {
        JPanel longSleeveTopsHeader = new JPanel(new GridLayout(1, 2));
        longSleeveTopsHeader.add(new JLabel("Long Sleeve Tops", 
                new ImageIcon("src/res/long_sleeve_top_icon.png"), SwingConstants.CENTER));
        longSleeveTopsHeader.add(new JLabel("Availability", SwingConstants.CENTER));

        JPanel shortSleeveTopsHeader = new JPanel(new GridLayout(1, 2));
        shortSleeveTopsHeader.add(new JLabel("Short Sleeve Tops", 
                new ImageIcon("src/res/short_sleeve_top_icon.png"), SwingConstants.CENTER));
        shortSleeveTopsHeader.add(new JLabel("Availability", SwingConstants.CENTER));

        JPanel topsHeader = new JPanel(new GridLayout(1, 2));
        topsHeader.add(longSleeveTopsHeader);
        topsHeader.add(shortSleeveTopsHeader);
        topsHeader.setPreferredSize(new Dimension(WIDTH, HEIGHT / 18));

        add(topsHeader);
    }

    /*
     * MODIFIES: this
     * EFFECTS: displays long and short sleeve tops and availability
     */
    private void displayTops() {
        ArrayList<Clothing> longSleeveTops = wardrobeApp.getWardrobe().getLongSleeveTops();
        JPanel longSleeveTopsBody = new JPanel(new GridLayout(longSleeveTops.size(), 2));
        for (Clothing c : longSleeveTops) {
            JPanel longSleeveTopsRow = new JPanel(new GridLayout(1, 2));

            longSleeveTopsRow.add(new JLabel(c.getClothingName(), SwingConstants.CENTER));
            longSleeveTopsRow.add(createCheckbox(c));

            longSleeveTopsBody.add(longSleeveTopsRow);
        }

        ArrayList<Clothing> shortSleeveTops = wardrobeApp.getWardrobe().getShortSleeveTops();
        JPanel shortSleeveTopsBody = new JPanel(new GridLayout(shortSleeveTops.size(), 2));
        for (Clothing c : shortSleeveTops) {
            JPanel shortSleeveTopsRow = new JPanel(new GridLayout(1, 2));

            shortSleeveTopsRow.add(new JLabel(c.getClothingName(), SwingConstants.CENTER));
            shortSleeveTopsRow.add(createCheckbox(c));

            shortSleeveTopsBody.add(shortSleeveTopsRow);
        }

        JPanel topsBody = new JPanel(new GridLayout(1, 2));
        topsBody.add(longSleeveTopsBody);
        topsBody.add(shortSleeveTopsBody);
        topsBody.setPreferredSize(new Dimension(WIDTH, HEIGHT / 3));

        add(topsBody);
    }

    /*
     * MODIFIES: this
     * EFFECTS: display bottoms header
     */
    private void displayBottomsHeader() {
        JPanel longBottomsHeader = new JPanel(new GridLayout(1, 2));
        longBottomsHeader.add(new JLabel("Long Bottoms", 
                new ImageIcon("src/res/long_bottom_icon.png"), SwingConstants.CENTER));
        longBottomsHeader.add(new JLabel("Availability", SwingConstants.CENTER));

        JPanel shortBottomsHeader = new JPanel(new GridLayout(1, 2));
        shortBottomsHeader.add(new JLabel("Short Bottoms", 
                new ImageIcon("src/res/short_bottom_icon.png"), SwingConstants.CENTER));
        shortBottomsHeader.add(new JLabel("Availability", SwingConstants.CENTER));

        JPanel bottomsHeader = new JPanel(new GridLayout(1, 2));
        bottomsHeader.add(longBottomsHeader);
        bottomsHeader.add(shortBottomsHeader);
        bottomsHeader.setPreferredSize(new Dimension(WIDTH, HEIGHT / 18));

        add(bottomsHeader);
    }

    /*
     * MODIFIES: this
     * EFFECTS: displays long and short bottoms and availability
     */
    private void displayBottoms() {
        ArrayList<Clothing> longBottoms = wardrobeApp.getWardrobe().getLongBottoms();
        JPanel longBottomsBody = new JPanel(new GridLayout(longBottoms.size(), 2));
        for (Clothing c : longBottoms) {
            JPanel longBottomsRow = new JPanel(new GridLayout(1, 2));

            longBottomsRow.add(new JLabel(c.getClothingName(), SwingConstants.CENTER));
            longBottomsRow.add(createCheckbox(c));

            longBottomsBody.add(longBottomsRow);
        }

        ArrayList<Clothing> shortBottoms = wardrobeApp.getWardrobe().getShortBottoms();
        JPanel shortBottomsBody = new JPanel(new GridLayout(shortBottoms.size(), 2));
        for (Clothing c : shortBottoms) {
            JPanel shortBottomsRow = new JPanel(new GridLayout(1, 2));

            shortBottomsRow.add(new JLabel(c.getClothingName(), SwingConstants.CENTER));
            shortBottomsRow.add(createCheckbox(c));

            shortBottomsBody.add(shortBottomsRow);
        }

        JPanel bottomsBody = new JPanel(new GridLayout(1, 2));
        bottomsBody.add(longBottomsBody);
        bottomsBody.add(shortBottomsBody);
        bottomsBody.setPreferredSize(new Dimension(WIDTH, HEIGHT / 3));

        add(bottomsBody);
    }

    /*
     * REQUIRES: c != null
     * EFFECTS: returns a checkbox to control clothing availability
     */
    private JCheckBox createCheckbox(Clothing c) {
        JCheckBox check = new JCheckBox("");
        check.setSelected(c.getAvailability());
        check.setHorizontalAlignment(SwingConstants.CENTER);
        check.setVerticalAlignment(SwingConstants.CENTER);

        check.addActionListener(e -> {
            JCheckBox checkBox = (JCheckBox) e.getSource();
            boolean availability = checkBox.getModel().isSelected();
            c.setAvailability(availability);
        });

        return check;
    }

}