import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * The RegularVendingMachine class represents a vending machine with regular functionality.
 * It manages items, money, and transactions in the vending machine.
 */
    public class RegVendingMachine
    {
        private String VMname; // the name of the vending machine
        private ArrayList<Slot> slots; // the collection of slots in the vending machine
        private int SlotMax; // the maximum number of slots
        private Cash cashStorage; // the storage for profit and change machine money
        private Cash receivedPayment; // the payment received from the customer
        private ArrayList<Transaction> transactions; // the transaction log for keeping track of sales
        private ArrayList<Inventory> startInventory;
        private ArrayList<Inventory> endInventory;

        /**
         * Constructs a VendingMachine object with the specified name and slot maximum capacity.
         *
         * @param VMname   The name of the vending machine.
         * @param SlotMax The maximum capacity of slots in the vending machine.
         */
        public RegVendingMachine(String VMname, int SlotMax)
        {
            this.VMname = VMname;
            this.SlotMax = SlotMax;

            slots = new ArrayList<Slot>(SlotMax);
            cashStorage = new Cash(10, 10, 10, 10);   // initial number of change coins are 10 each denomination
            receivedPayment = new Cash(0, 0, 0, 0);          // nothing has bought yet initially
            transactions = new ArrayList<Transaction>();
            startInventory = new ArrayList<Inventory>();
            endInventory = new ArrayList<Inventory>();
        }


        /**
         * Obtains the name of the vending machine
         * @return The name of the vending machine
         */
        public String getVMName()
        {
            return VMname;
        }

        /**
         * Obtains the collection of slots in the vending machine
         * @return The collection of slots in the vending machine
         */
        public ArrayList<Slot> getSlots()
        {
            return slots;
        }

        /**
         * Obtains the maximum capacity of slots in the vending machine
         * @return The maximum capacity of slots in the vending machine
         */
        public int getMaxSlots()
        {
            return SlotMax;
        }

        /**
         * Obtains the money box for profit and change machine money
         * @return The change and profit money
         */
        public Cash getMoneyBox()
        {
            return cashStorage;
        }

        /**
         * Obtains the received payment from the customer in a transaction
         * @return The received payment from the customer
         */
        public Cash getReceivedPayment()
        {
            return receivedPayment;
        }

        /**
         * Obtains the transaction record for keeping track of sales
         * @return The transaction record
         */
        public ArrayList<Transaction> getTransactions()
        {
            return transactions;
        }

        /**
         * Obtains the list of starting inventory items in the vending machine
         * @return The starting inventory
         */
        public ArrayList<Inventory> getStartInventory () {
            return startInventory;
        }

        /**
         * Obtains the list of ending inventory items in the vending machine
         * @return The ending inventory
         */
        public ArrayList<Inventory> getEndInventory () {
            return endInventory;
        }


        // When creating a Vending Machine
        /**
         * Adds a new slot with the specified maximum capacity to the vending machine.
         * @param maxCapacity The maximum capacity of the slot to be added.
         */
        public void addSLot (int maxCapacity) {
            for (int i = 0; i < SlotMax; i++)
                slots.add(new Slot(i, maxCapacity));
        }

        // VENDING FEATURES

        /**
         * Obtains the payment from the customer in a transaction
         * @param paymentMoney The money received from the customer
         */
        public void receivePayment(Cash paymentMoney)
        {
            receivedPayment = paymentMoney;
        }

        /**
         * Dispenses the item from the specified slot number if the payment is sufficient
         * @param slotNum The slot number from which the product should be dispensed
         * @return The item dispensed
         */
        public Item dispensedItem (int slotNum) {
            Item dispensedItem = slots.get(slotNum - 1).getItems().get(0);
            slots.get(slotNum - 1).getItems().remove(0);

            return dispensedItem;
        }

        /**
         * Calculates and dispenses the change amount for the specified slot number
         * @param slotNum The slot number for which change should be dispensed
         * @return The change dispensed
         */
        public Cash dispenseChange (int slotNum) {
            Cash dispensedMoney;
            int current20Coins = cashStorage.getNum20Coin();
            int current10Coins = cashStorage.getNum10Coin();
            int current5Coins  = cashStorage.getNum5Coin();
            int current1Coins  = cashStorage.getNum1Coin();
            int remainingAmount = receivedPayment.computeTotalAmount() - slots.get(slotNum - 1).getItems().get(0).getPrice();
            boolean canDispemse = true;

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
                canDispemse = false;
            } else {
                current1Coins -= num1CointoDispense;
            }

            if (canDispemse == false) {
                dispensedMoney = new Cash(0, 0, 0, 0);
            } else {
                dispensedMoney = new Cash(num20CointoDispense, num10CointoDispense, num5CointoDispense, num1CointoDispense);
            }

            return dispensedMoney;
        }


        // MAINTENANCE FEATURES

        /**
         * Stocks the specified quantity of an item into the given slot.
         * @param slotNum  The slot number where the item is to be stocked.
         * @param item     The item to be stocked.
         * @param quantity The quantity of items to be stocked.
         */
        public void stock (int slotNum, Item item, int quantity) {
            for (int i = 0; i < quantity; i++) {
                slots.get(slotNum - 1).addItem(item);
            }
        }

        /**
         * Sets the price of the item with the specified name to the new price.
         * @param itemName The name of the item.
         * @param newCost The new price to set for the item.
         */
        public void setPrice (String itemName, int newCost) {
            for (Slot slot : slots) {
                if (!slot.isEmpty()) {
                    if (slot.getItems().get(0).getName().equalsIgnoreCase(itemName)) {
                        for (int i = 0; i < slot.getItems().size(); i++) {
                            slot.getItems().get(i).setPrice(newCost);
                        }
                    }
                }
            }
        }
        /**
         * Collects money from the vending machine money box.
         * @param num20 The number of 20-peso coins to collect.
         * @param num10 The number of 10-peso coins to collect.
         * @param num5 The number of 5-peso coins to collect.
         * @param num1 The number of 1-peso coins to collect.
         * @return The collected money
         */
        public Cash collectMoney (int num20, int num10, int num5, int num1) {
            Cash collectedMoney = new Cash(num20, num10, num5, num1);
            cashStorage.subtractCoins(num20, num10, num5, num1);

            return collectedMoney;
        }

        /**
         * Refills the money in the vending machine money box.
         * @param num20 The number of 20-peso coins to replenish.
         * @param num10 The number of 10-peso coins to replenish.
         * @param num5 The number of 5-peso coins to replenish.
         * @param num1 The number of 1-peso coins to replenish.
         */
        public void refillMoney (int num20, int num10, int num5, int num1) {
            cashStorage.addCoins(num20, num10, num5, num1);
        }

        /**
         * Records the starting inventory of each slot in the vending machine after restocking.
         */
        public void recordStartInventory () {
            int i = 1;
            for (Slot slot : slots) {
                if (slot.isEmpty())
                    startInventory.add(null);
                else {
                    startInventory.add(new Inventory(i, slot.getItems().get(0).getName(), slot.getItems().get(0).getPrice(), slots.get(i - 1).getItems().size()));
                    i++;
                }
            }
        }

        /**
         * Records the ending inventory of each slot in the vending machine before restocking.
         */
        public void recordEndInventory () {
            int i = 1;
            for (Slot slot : slots) {
                if (slot.isEmpty())
                    endInventory.add(null);
                else {
                    startInventory.add(new Inventory(i, slot.getItems().get(0).getName(), slot.getItems().get(0).getPrice(), slots.get(i - 1).getItems().size()));
                    i++;
                }
            }
        }

        /**
         * Prints the starting inventory of each slot in the vending machine
         * @return A formatted string containing the starting inventory details
         */
        public String printStartingInventory () {
            String startingInventoryString = "Starting Inventory\n\n";
            int i = 0;

            startingInventoryString += "Slot No.               Item               Price (PHP)              Quantity\n";
            for (Inventory inventory : startInventory) {
                startingInventoryString += inventory.getSlotNum() + "                " +
                        inventory.getItemName() + "                 " +
                        inventory.getItemPrice() + "                 " +
                        inventory.getItemQuantity() + " / " + slots.get(i).getMaxCapacity() + "\n";
                i++;
            }

            return startingInventoryString;
        }

        /**
         * Prints the ending inventory of each slot in the vending machine
         * @return A formatted string containing the ending inventory details.
         */
        public String printEndingInventory () {
            String endingInventoryString = "Ending Inventory\n\n";
            int i = 0;

            endingInventoryString = "Slot No.               Item               Price (PHP)              Quantity\n";
            for (Inventory inventory : endInventory) {
                endingInventoryString += inventory.getSlotNum() + "                " +
                        inventory.getItemName() + "                 " +
                        inventory.getItemPrice() + "                 " +
                        inventory.getItemQuantity() + " / " + slots.get(i).getMaxCapacity() + "\n";
                i++;
            }

            return endingInventoryString;
        }

        /**
         * Prints the transaction log of all transactions made in the vending machine.
         * @return A formatted string containing the transaction log details.
         */
        public String printTransactionLog () {
            int totalSales = 0;
            String transactionLogString = "                           Slot No.      Item       Price\n";
            SimpleDateFormat dateForm = new SimpleDateFormat("[MM / dd / Y | h:mm:ss a]");
            int i = 0;

            for (Transaction transaction : transactions) {
                transactionLogString += i + "  " + dateForm.format(transaction.getDateTransacted()) + "     " +
                        transaction.getSoldSlotNum() + "        " +
                        transaction.getSoldItem() + "        PHP" +
                        transaction.getSoldItemPrice() + "\n";
                i++;
                totalSales += transaction.getSoldItemPrice();
            }

            transactionLogString += "\nTotal Sales: " + totalSales +
                    "\nTotal Items Sold: " + 0 + "\n";

            return transactionLogString;
        }
    }
