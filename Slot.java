import java.util.ArrayList;
/**
 * The Slot class represents a slot that can hold items. Each slot has a maximum capacity,
 * and items can be added or removed from the slot.
 */
public class Slot {
    private int slotNumber; // number of the slot
    private ArrayList<Item> items; // list of items in the slot
    private int maxCapacity; // maximum number of items each slot can hold

    /**
     * Constructs an Slot object with the specified slot number and maximum capacity.
     * @param slotNumber  the number of the slot.
     * @param maxCapacity the maximum capacity of the slot to hold items.
     */
    public Slot(int slotNumber, int maxCapacity) {
        this.slotNumber = slotNumber;
        this.maxCapacity = maxCapacity;

        items = new ArrayList<Item>(maxCapacity);
    }

    /**
     * Retrieves the number of the slot.
     * @return the number of the slot.
     */
    public int getSlotNumber() {
        return slotNumber;
    }

    /**
     * Retrieves the list of items in the slot.
     * @return the ArrayList of items in the slot.
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * Retrieves the maximum capacity of the slot to hold items.
     * @return the maximum capacity of the slot.
     */
    public int getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * Adds an item to the slot if the slot is not full.
     * @param item the item to be added to the slot.
     * @return true if the item was successfully added; false if the slot is full.
     */
    public boolean addItem(Item item) {
        if (items.size() < maxCapacity) {
            items.add(item);
            return true;
        }
        return false;
    }

    /**
     * Removes an item from the slot if the slot is not empty.
     * @param item the item to be removed
     * @return true if an item was successfully removed; false if the slot is empty.
     */
    public boolean removeItem(Item item) {
        if (items.size() > 0) {
            items.remove(items.size());
            return true;
        }

        return false;
    }

    /**
     * Checks if the slot is full.
     * @return true if the slot is full; false otherwise.
     */
    public boolean isFull() {
        if(items.size() >= maxCapacity)
                return true;
        return false;
    }

    /**
     * Checks if the slot is empty.
     * @return true if the slot is empty; false otherwise.
     */
    public boolean isEmpty () {
        if (items.size() <= 0)
            return true;
        return false;
    }

}
