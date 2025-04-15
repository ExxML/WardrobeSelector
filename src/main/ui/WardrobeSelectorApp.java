package ui;

import model.Wardrobe;
import model.event.EventLog;
import ui.tabs.*;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// Represents a class that runs the user interface
public class WardrobeSelectorApp extends JFrame {

    public static final int WARDROBE_TAB_INDEX = 0;
    public static final int ADD_CLOTHING_TAB_INDEX = 1;
    public static final int REMOVE_CLOTHING_TAB_INDEX = 2;
    public static final int SUGGEST_CLOTHING_TAB_INDEX = 3;

    public static final int WIDTH = 900;
    public static final int HEIGHT = 700;

    private JMenuBar menuBar;
    private JTabbedPane sidebar;
    
    private Wardrobe wardrobe;

    private static final String JSON_STORE = "./data/wardrobe.json"; // save file for wardrobe
    private JsonWriter jsonWriter; // JSON w riter
    private JsonReader jsonReader; // JSON reader

    /*
     * EFFECTS: constructs wardrobe UI
     */
    public WardrobeSelectorApp() {
        super("Wardrobe App");

        Dimension size = Toolkit.getDefaultToolkit().getScreenSize(); 
        setLocation((int)size.getWidth() / 2 - WIDTH / 2, (int)size.getHeight() / 2 - HEIGHT / 2);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        createWindowListener();

        wardrobe = new Wardrobe();

        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);

        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.TOP);

        menuBar = createMenuBar();
        setJMenuBar(menuBar);

        loadTabs();
        add(sidebar);

        setVisible(true);
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds a new window listener to print logs on close
     */
    private void createWindowListener() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                Iterator<model.event.Event> iterator = EventLog.getInstance().iterator();
                while (iterator.hasNext()) {
                    System.out.println(iterator.next().getDescription());
                }
                dispose();
            }
        });
    }

    /*
     * MODIFIES: this
     * EFFECTS: creates and returns a new menu
     */
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        fileMenu.add(createLoadMenu());
        fileMenu.add(createSaveMenu());

        menuBar.add(fileMenu);

        return menuBar;
    }

    /*
     * MODIFIES: this
     * EFFECTS: creates and returns the menu to save data
     */
    private JMenuItem createSaveMenu() {
        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(wardrobe);
                    jsonWriter.close();
                    System.out.println("\nSaved your current wardrobe to " + JSON_STORE);
                } catch (FileNotFoundException exception) {
                    System.out.println("\nUnable to write to file: " + JSON_STORE);
                }
            }
        });
        return saveMenuItem;
    }

    /*
     * MODIFIES: this
     * EFFECTS: creates and returns the menu item to load data
     */
    private JMenuItem createLoadMenu() {
        JMenuItem loadMenuItem = new JMenuItem("Load");
        loadMenuItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    wardrobe = jsonReader.read();
                    loadTabs();
                    System.out.println("\nLoaded your saved wardrobe from " + JSON_STORE);
                } catch (IOException exception) {
                    System.out.println("\nUnable to read from file: " + JSON_STORE);
                }
            }
        });
        return loadMenuItem;
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds wardrobe, add clothing, remove clothing, and suggest clothing tabs to UI
     */
    public void loadTabs() {
        sidebar.removeAll();

        JPanel wardrobeTab = new WardrobeTab(this);
        JPanel addClothingTab = new AddClothingTab(this);
        JPanel removeClothingTab = new RemoveClothingTab(this);
        JPanel suggestClothingTab = new SuggestClothingTab(this);

        sidebar.add(wardrobeTab, WARDROBE_TAB_INDEX);
        sidebar.add(addClothingTab, ADD_CLOTHING_TAB_INDEX);
        sidebar.add(removeClothingTab, REMOVE_CLOTHING_TAB_INDEX);
        sidebar.add(suggestClothingTab, SUGGEST_CLOTHING_TAB_INDEX);

        sidebar.setTitleAt(WARDROBE_TAB_INDEX, "Wardrobe");
        sidebar.setTitleAt(ADD_CLOTHING_TAB_INDEX, "Add Clothing");
        sidebar.setTitleAt(REMOVE_CLOTHING_TAB_INDEX, "Remove Clothing");
        sidebar.setTitleAt(SUGGEST_CLOTHING_TAB_INDEX, "Suggest Clothing");
    }

    // Getters

    public Wardrobe getWardrobe() {
        return wardrobe;
    }

}