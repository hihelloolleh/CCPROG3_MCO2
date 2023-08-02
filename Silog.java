import java.util.ArrayList;
/**
 * A subclass of Item representing a Silog meal, a special meal made with selected items.
 * The Sundae class extends the Item class and contains additional methods to handle the selected items
 * and generate strings for preparation details and final silog meal information.
 */
public class Silog extends Item {

    private ArrayList<Item> selectedItems; // An ArrayList to store the selected items that make up the Sundae.
    /**
     * Constructor object for Silog that contains the name, price, calories and list of selected items
     * @param itemName name of the item
     * @param itemPrice price of the item
     * @param itemCalories calories the item contains
     * @param selectedItems list of selected items
     */

    public Silog (String itemName, int itemPrice, int itemCalories, ArrayList<Item> selectedItems) {
        super(itemName, itemPrice, itemCalories);
        this.selectedItems = selectedItems;
    }

    /**
     * Get the selected items that make up the Silog
     * @return An ArrayList containing the selected items.
     */
    public ArrayList<Item> getSelectedItems () {
        return selectedItems;
    }

    /**
     * Set the selected items that make up the Silog to the given ArrayList.
     * @param newSelectedItems The ArrayList of selected items to be set.
     */
    public void setSelectedItems (ArrayList<Item> newSelectedItems) {
        selectedItems = newSelectedItems;
    }

    /**
     * Generate a formatted string representing the preparation details of the Silog.
     * The method iterates through the selected items and creates a preparation string
     * by scooping rice or adding eggs. It also includes the container type for the Silog.
     * @param plate An integer representing the container type (0 for plate, 1 for a bowl).
     * @return A formatted string representing the preparation details of the silog.
     */
    public String toStringPreparation (int plate) {
        String prepareString = "";

        for (Item selectedItem : selectedItems) {
            if (selectedItem.getName().contains("Garlic Rice") || selectedItem.getName().contains("garlic rice")) {
                prepareString += "Scooping 1 cup of " + selectedItem.getName() + "...\n";
            } else {
                prepareString += "Adding Egg " + selectedItem.getName() + "...\n";
            }
        }

        if (plate == 0) {
            prepareString += "Placing all in a plate...\n\n";
        } else {
            prepareString += "Placing all in a bowl...\n\n";
        }

        return prepareString;
    }

    /**
     * Generate a formatted string representing the final information of the Silog.
     * The method includes the price, calorie count, and the names of the selected items.
     * @return A formatted string representing the final information of the Silog.
     */

    @Override
    public String toString () {

        String selectedItemsString = "";
        for (Item selectedItem : selectedItems) {
            selectedItemsString += selectedItem.getName() + "\n";
        }

        String finalString = "";

        finalString += "Price: " + getPrice() + "\n" +
                "Calories: " + getCalories() + "\n" +
                "Ingredients:\n" + selectedItemsString + "\n";

        return finalString;
    }
}