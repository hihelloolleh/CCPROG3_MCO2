/**
 * The Item class represents an item with a name, price, and calories.
 * It provides methods to retrieve and modify the properties of the item.
 */
public class Item {
    private String itemName;// name of the item
    private int itemPrice; // price of the item
    private int itemCalories; // amount of calories the item contains
    
    /**
     * Constructs an Item object with the specified name, price, and calories.
     * @param itemName     the name of the item.
     * @param itemPrice    the price of the item.
     * @param itemCalories the number of calories in the item.
     */
    public Item(String itemName, int itemPrice, int itemCalories) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCalories = itemCalories;
    }
    
    /**
     * Retrieves the name of the item.
     * @return the name of the item.
     */
    public String getName() {
        return itemName;
    }
    
    /**
     * Retrieves the price of the item.
     * @return the price of the item.
     */
    public int getPrice() {
        return itemPrice;
    }
    
    /**
     * Retrieves the number of calories in the item.
     * @return the number of calories in the item.
     */
    public int getCalories() {
        return itemCalories;
    }
    
    /**
     * Sets the price of the item to the specified new price.
     * @param newPrice the new price to set for the item.
     */
    public void setPrice(int newPrice) {
        itemPrice = newPrice;
    }
    
    /**
     * Checks if this item is equal to another object.
     * Two Item objects are considered equal if their name, price, and calorie count are the same.
     * @param obj the object to compare with.
     * @return true if the object is an Item and has the same name, price, and calories as this item; false otherwise.
     */
	@Override
	public boolean equals (Object obj) {
		Item item = (Item) obj;
		
		if (item == null)
			return false;
	
		
		return item.getName() == getName() &&
			   item.getPrice() == getPrice () &&
			   item.getCalories() == getCalories();
	}
}
