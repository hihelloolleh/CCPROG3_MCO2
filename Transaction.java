import java.util.Date;
/**
 * The Transaction class represents a transaction record for an item sold from a vending machine.
 * It stores information about the sold item's slot number, name, price, and the date of the transaction.
 */
public class Transaction {

    private int soldSlotNum; // The slot number where the item was sold.
    private String soldItemName;  // The name of the item sold.
    private int soldItemPrice; // The price of the item sold.
    private Date dateTransacted; // The date and time the transaction occurred.

    /**
     * Constructs a Transaction object with the specified slot number, item name, and item price.
     * The date and time of the transaction are automatically set to the current system time.
     *
     * @param soldSlotNum The slot number where the item was sold.
     * @param soldItemName    The name of the item sold.
     * @param soldItemPrice   The price of the item sold.
     */
    public Transaction (int soldSlotNum, String soldItemName, int soldItemPrice) {
        this.soldSlotNum = soldSlotNum;
        this.soldItemName    = soldItemName;
        this.soldItemPrice   = soldItemPrice;

        dateTransacted = new Date();
    }

    /**
     * Retrieves the item that was sold in the transaction.
     *
     * @return the item that was sold.
     */
    public String getSoldItem() {
        return soldItemName;
    }

    /**
     * Gets the price of the item sold in the transaction.
     *
     * @return The price of the item.
     */
    public int getSoldItemPrice () {
        return soldItemPrice;
    }

    /**
     * Gets the slot number where the item was sold in the transaction.
     *
     * @return The slot number.
     */
    public int getSoldSlotNum () {
        return soldSlotNum;
    }

    /**
     * Retrieves the date when the transaction was transacted.
     *
     * @return the date when the transaction was transacted.
     */
    public Date getDateTransacted() {
        return dateTransacted;
    }
}
