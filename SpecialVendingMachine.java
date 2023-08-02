import java.util.ArrayList;
import java.text.SimpleDateFormat;
/**
 * The SpecialVendingMachine` class is a subclass of the `RegularVendingMachine` class, representing a vending machine with specialized products and customizable items.
 * It contains a specialized product name, a shopping cart to hold selected items for purchase, and a customizable item that customers can create based on the selected items.
 * This class provides methods to add and remove items from the shopping cart, compute the total price and total calories of the shopping cart, view the contents of the shopping cart,
 * dispense individual items from the shopping cart, create a custom item based on the selected items, and dispense the custom item.
 * The class also includes an overloaded method to dispense change if the received payment is more than the total price of the shopping cart.
 */
public class SpecialVendingMachine extends RegVendingMachine{
    private ArrayList<Item> shoppingCart;  // the array of items selected by the customer to purchase


    /**
     * Constructs a new SpecialVendingMachine` object with the given name, maximum number of slots, and specialized product.
     * @param name  The name of the special vending machine.
     * @param maxSlots  The maximum number of slots for storing items in the vending machine.
     */    
    public SpecialVendingMachine(String name, int maxSlots) {
        super(name, maxSlots);
        shoppingCart = new ArrayList<Item>();
    }

    /**
     * Gets the shopping cart, which is an array of items selected by the customer for purchase.
     * @return The shopping cart containing selected items.
     */    
    public ArrayList<Item> getShoppingCart () {
        return shoppingCart;
    }

    /**
     * Adds an item to the shopping cart if it is available in the vending machine slots.
     * If the item is found and added to the cart, it will be removed from its slot.
     * @param item The item to be added to the shopping cart.
     */
    public boolean addItemToCart (Item item) {
        for (Slot slot : super.getSlots()) {
            if (!slot.isEmpty()) {
                if (slot.getItems().get(0).equals(item)) {
                    shoppingCart.add(item);           // if available, add item to shopping cart
                    slot.getItems().remove(0);  // then remove item from slot
                    return true;
                }
            }
        }

        return false; // if item is unavailable
    }

    /**
     * Removes an item from the shopping cart if it exists in the cart.
     * @param item The item to be removed from the shopping cart.
     */
    public void removeItemFromCart (Item item) {
        if (shoppingCart.contains(item))
            shoppingCart.remove(item);

        // return item back to slot
        for (Slot slot : super.getSlots()) {
            if (!slot.isEmpty()) {
                if (slot.getItems().get(0).equals(item)) {
                    slot.getItems().add(item);
                }
            }
        }

        // if the slot where the item was retrieved was emptied when the customer previously added the item to shopping list
        for (Slot slot : super.getSlots()) {
            // add the item where it encounters an empty slot, ideally the slot where the item is initially stored
            if (slot.isEmpty()) {
                slot.addItem(item);
            }
        }
    }

    /**
     * Computes the total price of all items in the shopping cart.
     * @return The total price of the items in the shopping cart.
     */
    public int computeTotalPriceOfCart () {
        int totalPrice = 0;  // total price of the entire shopping cart

        for (Item item : shoppingCart) 
            totalPrice += item.getPrice();

        return totalPrice;
    }

    /**
     * Computes the total calories of all items in the shopping cart.
     * @return The total calories of the items in the shopping cart.
     */
    public int computeTotalCaloriesOfCart () {
        int totalCalories = 0;  // total calories of the entire shopping cart

        for (Item item : shoppingCart) 
            totalCalories += item.getCalories();

        return totalCalories;
    }

    /**
     * Views the contents of the shopping cart and returns a formatted string representation of the items.
     * @return A string representation of the items in the shopping cart.
     */
    public String viewShoppingCart () {
        String shoppingCartString = "";
        int i = 1;

        for (Item item : shoppingCart) {
            shoppingCartString.concat("Item #" + i + 
                                      "  Name: " + item.getName() + "\n" + 
                                      "  Price: " + item.getPrice() + "\n" + 
                                      "  Calories: " + item.getCalories() + "\n\n");
            i++;
        }
        return shoppingCartString;
    }

    /**
     * Dispenses the individual items from the shopping cart if the customer decides not to create a custom item.
     * @return An ArrayList of items dispensed from the shopping cart, or null if no items are dispensed.
     */
    public ArrayList<Item> dispenseShoppingCartItems () {
        ArrayList<Item> dispensedItems = new ArrayList<Item>();

        if (shoppingCart.size() <= 0) {
            // if shopping Cart is empty, dispense nothing
            dispensedItems = null;
        }
        else {
            // if received payment is insufficient to purchase items in shopping cart
            if (super.getReceivedPayment().computeTotalAmount() < computeTotalPriceOfCart())
                dispensedItems = null;
            // if the received payment is sufficient to purchase items in shopping cart
            else {
                for (Item item : shoppingCart) {
                    dispensedItems.add(item);
                    shoppingCart.remove(item);
                }
            }
        }
        return dispensedItems;
    }

    /**
     * Check if it is possible to make a silog based on the items in the shopping cart.
     * @return True if it is possible to make a silog; otherwise, false.
     */
    public boolean isPossibleForSilog () {
        int numOfRice = 0;
        int numOfEggs = 0;

        for (Item item : shoppingCart) {
            if (item.getName().contains("Eggs") || item.getName().contains("eggs"))
                numOfEggs++;
            else
                numOfRice++;
        }

        // requirement for a silog is to have at least 1 rice and 1 egg
        if (numOfRice > 0 && numOfEggs > 0)
            return true;
        else
            return false;
    }

    /**
     * Make a SilogMeal using the items in the shopping cart if it is possible to make one.
     * @return The created Sundae object or null if it is not possible to make a SilogMeal.
     */
    public Silog makeSilogMeal () {
        Silog silogMeal;
        int silogMealPrice    = computeTotalCaloriesOfCart();
        int silogMealCalories = computeTotalPriceOfCart();

        if (isPossibleForSilog()) {
            silogMeal = new Silog("SilogMeal", silogMealPrice, silogMealCalories, shoppingCart);
        } else {
            silogMeal = null;
        }

        return silogMeal;
    }


    /// Method Overloading
    /**
     * Dispense change in coins based on the difference between the received payment and the total price of the cart.    * @return A Money object representing the coins to be dispensed as change.
     */
    public Cash dispenseChange () {
        Cash dispensedMoney;
        int current20Coins = super.getMoneyBox().getNum20Coin();
        int current10Coins = super.getMoneyBox().getNum10Coin();
        int current5Coins  = super.getMoneyBox().getNum5Coin();
        int current1Coins  = super.getMoneyBox().getNum1Coin();
        int remainingAmount = super.getReceivedPayment().computeTotalAmount() - computeTotalPriceOfCart();
        boolean canDispense = true;

        int num20CointoDispense = remainingAmount / 20;
        if (num20CointoDispense > current20Coins) {
            num20CointoDispense = current20Coins;
        }
        remainingAmount -= num20CointoDispense * 20;
        current20Coins -= num20CointoDispense;

        int num10CointoDispense = remainingAmount / 10;
        if (num10CointoDispense > current10Coins) {
            num10CointoDispense = current10Coins;
        }
        remainingAmount -= num10CointoDispense * 10;
        current10Coins -= num10CointoDispense;

        int num5CointoDispense = remainingAmount / 5;
        if (num5CointoDispense > current5Coins) {
            num5CointoDispense = current5Coins;
        }
        remainingAmount -= num5CointoDispense * 5;
        current5Coins -= num5CointoDispense;

        int num1CointoDispense = remainingAmount / 1;
        if (num1CointoDispense > current1Coins) {
            canDispense = false;
        } else {
            current1Coins -= num1CointoDispense;
        }

        if (canDispense == false) {
            dispensedMoney = new Cash(0, 0, 0, 0);
        } else {
            dispensedMoney = new Cash(num20CointoDispense, num10CointoDispense, num5CointoDispense, num1CointoDispense);
        }

        return dispensedMoney;
    }

    /**
     * Generate a formatted string representing the transaction log of the vending machine.
     * The transaction log includes details of each item sold, such as slot number, item name, and price,
     * along with the total sales and total items sold.
     * @return A formatted string representing the transaction log.
     */
    @Override
    public String printTransactionLog () {
        int totalSales = 0;
        String transactionLogString = "                           Slot No.      Item       Price\n";
        SimpleDateFormat dateForm = new SimpleDateFormat("[MM / dd / Y | h:mm:ss a]");
        int i = 0;

        for (Transaction treansaction : super.getTransactions()) {
            transactionLogString += i + "  " + dateForm.format(treansaction.getDateTransacted()) + "     " +
                    treansaction.getSoldItem() + "        PHP" +
                    treansaction.getSoldItemPrice() + "\n";
            i++;
            totalSales += treansaction.getSoldItemPrice();
        }

        transactionLogString += "\nTotal Sales: " + totalSales +
                "\nTotal Items Sold: " + 0 + "\n";

        return transactionLogString;
    }
}
