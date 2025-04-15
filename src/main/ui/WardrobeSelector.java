package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import model.Clothing;
import model.Wardrobe;
import persistence.JsonReader;
import persistence.JsonWriter;

// Represents the Wardrobe Selector application
public class WardrobeSelector {

    private Scanner scanner; // receives user input
    private Wardrobe wardrobe; // the clothing types

    private static final String JSON_STORE = "./data/wardrobe.json"; // save file for wardrobe
    private JsonWriter jsonWriter; // JSON writer
    private JsonReader jsonReader; // JSON reader

    /*
     * EFFECTS: runs the wardrobe selector application
     */
    public WardrobeSelector() {
        boolean appRunning = true;
        String input = null;

        scanner = new Scanner(System.in);
        scanner.useDelimiter("\r?\n|\r");

        wardrobe = new Wardrobe();

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        while (appRunning) {
            displayMenu();
            input = scanner.next();
            if (input.equals("q")) {
                System.out.println("\nThank you for using Wardrobe Selector!");
                appRunning = false;
            } else {
                processInput(input);
            }
        }
    }

    /*
     * EFFECTS: displays options for user
     */
    private void displayMenu() {
        System.out.println("\n*** Wardrobe Selector Main Menu ***");
        System.out.println("Select an option by entering the corresponding key:");
        System.out.println("d: Display all clothing in the wardrobe");
        System.out.println("a: Add a piece of clothing to a category");
        System.out.println("r: Remove a piece of clothing to a category");
        System.out.println("t: Toggle the availability of a piece of clothing");
        System.out.println("p: Select a set of clothing based on temperature");
        System.out.println("------------------------------------------------------------");
        System.out.println("s: Save wardrobe to file");
        System.out.println("l: Load wardrobe from file");
        System.out.println("q: Quit application");
    }

    /*
     * REQUIRES: input is one of "d", "a", "r", "t", "p", "s", "l"
     * MODIFIES: this
     * EFFECTS: processes user input
     */
    @SuppressWarnings("methodlength")
    private void processInput(String input) {
        switch (input) {
            case "d":
                printClothing();
                break;
            case "a":
                addClothing();
                break;
            case "r":
                removeClothing();
                break;
            case "t":
                toggleClothingAvailability();
                break;
            case "p":
                temperatureSelector();
                break;
            case "s":
                saveWardrobe();
                break;
            case "l":
                loadWardrobe();
                break;
            default:
                System.out.println("\nThat is not a valid option. Please try again.");
        }
    }

    /*
     * EFFECTS: prints all pieces of clothing, organized by category, with availability shown
     */
    private void printClothing() {
        if (wardrobe.getLongSleeveTops().isEmpty()
                && wardrobe.getShortSleeveTops().isEmpty()
                && wardrobe.getLongBottoms().isEmpty()
                && wardrobe.getShortBottoms().isEmpty()) {
            System.out.println("\nYou have no clothes in your wardrobe to display!");
        }
        if (!wardrobe.getLongSleeveTops().isEmpty()) {
            System.out.println("\nLong Sleeve Tops:");
            printLongSleeveTops();
        }
        if (!wardrobe.getShortSleeveTops().isEmpty()) {
            System.out.println("\nShort Sleeve Tops:");
            printShortSleeveTops();
        }
        if (!wardrobe.getLongBottoms().isEmpty()) {
            System.out.println("\nLong Bottoms:");
            printLongBottoms();
        }
        if (!wardrobe.getShortBottoms().isEmpty()) {
            System.out.println("\nShort Bottoms:");
            printShortBottoms();
        }
    }

    /*
     * EFFECTS: prints all pieces of clothing in long sleeve tops category, with availability shown
     */
    private void printLongSleeveTops() {
        String availability;
        for (Clothing c : wardrobe.getLongSleeveTops()) {
            if (c.getAvailability()) {
                availability = "Available";
            } else {
                availability = "Unavailable";
            }
            System.out.println(c.getClothingName() + " | " + availability);
        }
    }

    /*
     * EFFECTS: prints all pieces of clothing in short sleeve tops category, with availability shown
     */
    private void printShortSleeveTops() {
        String availability;
        for (Clothing c : wardrobe.getShortSleeveTops()) {
            if (c.getAvailability()) {
                availability = "Available";
            } else {
                availability = "Unavailable";
            }
            System.out.println(c.getClothingName() + " | " + availability);
        }
    }

    /*
     * EFFECTS: prints all pieces of clothing in long bottoms category, with availability shown
     */
    private void printLongBottoms() {
        String availability;
        for (Clothing c : wardrobe.getLongBottoms()) {
            if (c.getAvailability()) {
                availability = "Available";
            } else {
                availability = "Unavailable";
            }
            System.out.println(c.getClothingName() + " | " + availability);
        }
    }

    /*
     * EFFECTS: prints all pieces of clothing in short bottoms category, with availability shown
     */
    private void printShortBottoms() {
        String availability;
        for (Clothing c : wardrobe.getShortBottoms()) {
            if (c.getAvailability()) {
                availability = "Available";
            } else {
                availability = "Unavailable";
            }
            System.out.println(c.getClothingName() + " | " + availability);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: creates a new piece of clothing to be added
     */
    private void addClothing() {
        boolean addClothingRunning = true;
        while (addClothingRunning) {
            System.out.println("\nPlease enter the name of the clothing you would like to add.");
            System.out.println("q: Cancel add clothing");
            String addedClothingString = scanner.next();
            if (addedClothingString.equals("")) {
                System.out.println("\nThat is not a valid name. Please try again.");
                continue;
            } else if (addedClothingString.equals("q")) {
                break;
            } else {
                Clothing addedClothing = new Clothing(addedClothingString);
                addCategory(addedClothing);
                addClothingRunning = false;
            }
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds a piece of clothing to the selected category
     */
    @SuppressWarnings("methodlength")
    private void addCategory(Clothing addedClothing) {
        boolean addCategoryRunning = true;
        while (addCategoryRunning) {
            addCategoryMenu();
            String addedCategory = scanner.next();
            switch (addedCategory) {
                case "a":
                    wardrobe.addLongSleeveTop(addedClothing);
                    System.out.println("\n" + addedClothing.getClothingName() + " has been added to Long Sleeve Tops.");
                    break;
                case "b":
                    wardrobe.addShortSleeveTop(addedClothing);
                    System.out.println("\n" + addedClothing.getClothingName() 
                            + " has been added to Short Sleeve Tops.");
                    break;
                case "c":
                    wardrobe.addLongBottom(addedClothing);
                    System.out.println("\n" + addedClothing.getClothingName() + " has been added to Long Bottoms.");
                    break;
                case "d":
                    wardrobe.addShortBottom(addedClothing);
                    System.out.println("\n" + addedClothing.getClothingName() + " has been added to Short Bottoms.");
                    break;
                case "q":
                    break;
                default:
                    System.out.println("\nThat is not a valid category. Please try again.");
                    continue;
            }
            addCategoryRunning = false;
        }
    }

    /*
     * EFFECTS: prints the category menu for adding clothing
     */
    private void addCategoryMenu() {
        System.out.println("\nPlease select the category you would like to add the piece of clothing to.");
        System.out.println("a: Long Sleeve Tops");
        System.out.println("b: Short Sleeve Tops");
        System.out.println("c: Long Bottoms");
        System.out.println("d: Short Bottoms");
        System.out.println("q: Cancel add clothing");
    }

    /*
     * REQUIRES: clothing category contains the clothing
     * MODIFIES: this
     * EFFECTS: removes a piece of clothing from a clothing category
     */
    private void removeClothing() {
        boolean removeClothingRunning = true;
        while (removeClothingRunning) {
            System.out.println("\nPlease enter the name of the clothing you would like to remove.");
            System.out.println("q: Cancel remove clothing");
            String removedClothingString = scanner.next();
            if (removedClothingString.equals("q")) {
                break;
            }
            String removedClothingCategory = wardrobe.removeClothing(removedClothingString);
            if (removedClothingCategory.equals("")) {
                removedClothingString += " does not exist in your wardrobe. Please try again.";
                System.out.println("\n" + removedClothingString);
                continue;
            } else {
                System.out.println("\n" + removedClothingString + " has been removed from " + removedClothingCategory
                        + ".");
                removeClothingRunning = false;
            }
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: toggles the availability of a piece of clothing
     */
    private void toggleClothingAvailability() {
        boolean toggleClothingAvailabilityRunning = true;
        while (toggleClothingAvailabilityRunning) {
            System.out.println("\nPlease enter the name of the clothing you would like to toggle its availability.");
            System.out.println("q: Cancel toggle clothing availability");
            String toggledClothingString = scanner.next();
            if (toggledClothingString.equals("q")) {
                break;
            } 
            String toggledClothingCategory = wardrobe.toggleClothingAvailability(toggledClothingString);
            if (toggledClothingCategory.equals("")) {
                toggledClothingString += " does not exist in your wardrobe. Please try again.";
                System.out.println("\n" + toggledClothingString);
                continue;
            } else {
                System.out.println("\nThe availability of " + toggledClothingString + " in " + toggledClothingCategory);
                toggleClothingAvailabilityRunning = false;
            }
        }
    }

    /*
     * EFFECTS: prints randomly selected, available clothing from certain clothing categories depending on temp (°C)
     */
    private void temperatureSelector() {
        int temp = 0;
        boolean temperatureSelectorRunning = true;
        while (temperatureSelectorRunning) {
            System.out.println("\nPlease enter the temperature in Celsius (°C).");
            System.out.println("q: Cancel temperature selector");
            String temperatureString = scanner.next();
            if (temperatureString.equals("q")) {
                break;
            }
            try {
                temp = Integer.valueOf(temperatureString);
            } catch (NumberFormatException ex) {
                System.out.println("\nThat is not an integer. Please try again.");
                continue;
            }
            if (temperatureMatcher(temp)) {
                temperatureSelectorRunning = false;
            } else {
                break;
            }
        }
    }

    /*
     * EFFECTS: selects from long sleeve tops and long bottoms if temp <= 5 °C, and reminds user to wear a jacket
     *                selects from long sleeve tops and long bottoms if 5 °C < temp <= 20 °C
     *                selects from short sleeve tops and long bottoms if 20 °C < temp <= 30 °C
     *                else selects from short sleeve tops and short bottoms (if temp > 30 °C), and 
     *                reminds user to wear sunscreen
     */
    private boolean temperatureMatcher(int temp) {
        if (temp <= 5) {
            return selectRandomClothingCold(temp);
        } else if ((5 < temp) && (temp <= 20)) {
            return selectRandomClothingCool(temp);
        } else if ((20 < temp) && (temp <= 30)) {
            return selectRandomClothingWarm(temp);
        } else {
            return selectRandomClothingHot(temp);
        }
    }

    /*
     * EFFECTS: selects clothing from long sleeve tops and long bottoms if temp <= 5 °C, and 
     *                reminds user to wear a jacket
     *                return true if clothing was successfully selected, false otherwise
     */
    private boolean selectRandomClothingCold(int temp) {
        boolean nullLongSleeveTop = nullSelectRandomLongSleeveTop();
        boolean nullLongBottom = nullSelectRandomLongBottom();
        if (!nullLongSleeveTop && !nullLongBottom) {
            System.out.println("\nHere is your suggested set of clothes for " + temp + "°C!");
            System.out.println("Long Sleeve Top: " + wardrobe.selectRandomLongSleeveTop().getClothingName());
            System.out.println("Long Bottom: " + wardrobe.selectRandomLongBottom().getClothingName());
            System.out.println("Remember to wear a jacket!");
            return true;
        }
        return false;
    }

    /*
     * EFFECTS: selects clothing from long sleeve tops and long bottoms if 5 °C < temp <= 20 °C
     *                return true if clothing was successfully selected, false otherwise
     */
    private boolean selectRandomClothingCool(int temp) {
        boolean nullLongSleeveTop = nullSelectRandomLongSleeveTop();
        boolean nullLongBottom = nullSelectRandomLongBottom();
        if (!nullLongSleeveTop && !nullLongBottom) {
            System.out.println("\nHere is your suggested set of clothes for " + temp + "°C!");
            System.out.println("Long Sleeve Top: " + wardrobe.selectRandomLongSleeveTop().getClothingName());
            System.out.println("Long Bottom: " + wardrobe.selectRandomLongBottom().getClothingName());
            return true;
        }
        return false;
    }

    /*
     * EFFECTS: selects clothing from short sleeve tops and long bottoms if 20 °C < temp <= 30 °C
     *                return true if clothing was successfully selected, false otherwise
     */
    private boolean selectRandomClothingWarm(int temp) {
        boolean nullShortSleeveTop = nullSelectRandomShortSleeveTop();
        boolean nullLongBottom = nullSelectRandomLongBottom();
        if (!nullShortSleeveTop && !nullLongBottom) {
            System.out.println("\nHere is your suggested set of clothes for " + temp + "°C!");
            System.out.println("Short Sleeve Top: " + wardrobe.selectRandomShortSleeveTop().getClothingName());
            System.out.println("Long Bottom: " + wardrobe.selectRandomLongBottom().getClothingName());
            return true;
        }
        return false;
    }

    /*
     * EFFECTS: selects clothing from short sleeve tops and short bottoms if 30 °C < temp, and 
     *                reminds user to wear sunscreen
     *                return true if clothing was successfully selected, false otherwise
     */
    private boolean selectRandomClothingHot(int temp) {
        boolean nullShortSleeveTop = nullSelectRandomShortSleeveTop();
        boolean nullShortBottom = nullSelectRandomShortBottom();
        if (!nullShortSleeveTop && !nullShortBottom) {
            System.out.println("\nHere is your suggested set of clothes for " + temp + "°C!");
            System.out.println("Short Sleeve Top: " + wardrobe.selectRandomShortSleeveTop().getClothingName());
            System.out.println("Short Bottom: " + wardrobe.selectRandomShortBottom().getClothingName());
            System.out.println("Remember to wear sunscreen!");
            return true;
        }
        return false;
    }

    /*
     * EFFECTS: return true if there are no available long sleeve tops, false otherwise
     */
    private boolean nullSelectRandomLongSleeveTop() {
        if (wardrobe.selectRandomLongSleeveTop() == null) {
            System.out.println("\nYou do not have any available long sleeve tops.");
            System.out.println("Please toggle the availability of a long sleeve top or add a new one.");
            return true;
        }
        return false;
    }

    /*
     * EFFECTS: return true if there are no available short sleeve tops, false otherwise
     */
    private boolean nullSelectRandomShortSleeveTop() {
        if (wardrobe.selectRandomShortSleeveTop() == null) {
            System.out.println("\nYou do not have any available short sleeve tops.");
            System.out.println("Please toggle the availability of a short sleeve top or add a new one.");
            return true;
        }
        return false;
    }

    /*
     * EFFECTS: return true if there are no available long bottoms, false otherwise
     */
    private boolean nullSelectRandomLongBottom() {
        if (wardrobe.selectRandomLongBottom() == null) {
            System.out.println("\nYou do not have any available long bottoms.");
            System.out.println("Please toggle the availability of a long bottom or add a new one.");
            return true;
        }
        return false;
    }

    /*
     * EFFECTS: return true if there are no available short bottoms, false otherwise
     */
    private boolean nullSelectRandomShortBottom() {
        if (wardrobe.selectRandomShortBottom() == null) {
            System.out.println("\nYou do not have any available short bottoms.");
            System.out.println("Please toggle the availability of a short bottom or add a new one.");
            return true;
        }
        return false;
    }
    
    /*
     * EFFECTS: saves the wardrobe to file
     */
    private void saveWardrobe() {
        try {
            jsonWriter.open();
            jsonWriter.write(wardrobe);
            jsonWriter.close();
            System.out.println("\nSaved your current wardrobe to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("\nUnable to write to file: " + JSON_STORE);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: loads wardrobe from file
     */
    private void loadWardrobe() {
        try {
            wardrobe = jsonReader.read();
            System.out.println("\nLoaded your saved wardrobe from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("\nUnable to read from file: " + JSON_STORE);
        }
    }

}