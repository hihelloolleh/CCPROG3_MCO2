import java.util.ArrayList;
/**
 * The Inventory class represents an inventory item that can be stored in a slot.
 * Each slot has a unique number, and it can contain an item with a name, price, and quantity.
 */
public class Inventory {

    private int slotNumber; // the slot number where the item is located
    private String itemName; // the name of the item
    private int itemPrice; // price of the item
    private int itemQuantity; // item quantity in the slot

    /**
     * Constructs a SlotInventory object with the specified slot number, item name, item price, and item quantity.
     *
     * @param slotNumber      The slot number in the vending machine.
     * @param itemName     The name of the item available in the slot.
     * @param itemPrice    The price of the item in the slot.
     * @param itemQuantity The quantity of the item available in the slot.
     */
    public Inventory (int slotNumber, String itemName, int itemPrice, int itemQuantity) {
        this.slotNumber      = slotNumber;
        this.itemName     = itemName;
        this.itemPrice    = itemPrice;
        this.itemQuantity = itemQuantity;
    }

    /**
     * Gets the slot number in the vending machine.
     * @return The slot number.
     */
    public int getSlotNum () {
        return slotNumber;
    }

    /**
     * Gets the name of the item available in the slot.
     * @return The name of the item.
     */
    public String getItemName () {
        return itemName;
    }

    /**
     * Gets the price of the item in the slot.
     * @return The price of the item.
     */
    public int getItemPrice () {
        return itemPrice;
    }

    /**
     * Gets the quantity of the item available in the slot.
     * @return The quantity of the item.
     */

    public int getItemQuantity () {
        return itemQuantity;
    }
}
