/**
 * The Money class represents a collection of coins with different denominations.
 * It provides methods to manage and retrieve the number of coins for each denomination,
 * add and subtract coins, and compute the total amount of money in the collection.
 */
public class Cash {
    private int num20; // number of 20 coins with denominations
    private int num10; // number of 10 coins denominations
    private int num5; // number 0f 5 coins denominations
    private int num1; // number of 1 coins denominations
    private Cash profitMoney; // the money earned by the vending machine
    private Cash changeMoney; // the cash for making change

    /**
     * Constructs a Cash object with the specified number of coins for each denomination.
     * @param num20 The number of 20-coin denominations.
     * @param num10 The number of 10-coin denominations.
     * @param num5  The number of 5-coin denominations.
     * @param num1  The number of 1-coin denominations.
     */
    public Cash(int num20, int num10, int num5, int num1)
    {
        this.num20 = num20;
        this.num10 = num10;
        this.num5 = num5;
        this.num1 = num1;
    }

    /**
     * Retrieves the number of coins with a denomination of 20.
     * @return the number of coins with a denomination of 20.
     */
    public int getNum20Coin() {
        return num20;
    }

    /**
     * Retrieves the number of coins with a denomination of 10.
     * @return the number of coins with a denomination of 10.
     */
    public int getNum10Coin() {
        return num10;
    }

    /**
     * Retrieves the number of coins with a denomination of 5.
     * @return the number of coins with a denomination of 5.
     */
    public int getNum5Coin() {
        return num5;
    }

    /**
     * Retrieves the number of coins with a denomination of 1.
     * @return the number of coins with a denomination of 1.
     */
    public int getNum1Coin() {
        return num1;
    }

    /**
     * Adds coins to the collection for each denomination.
     * @param num20 the number of coins with a denomination of 20 to add.
     * @param num10 the number of coins with a denomination of 10 to add.
     * @param num5 the number of coins with a denomination of 5 to add.
     * @param num1  the number of coins with a denomination of 1 to add.
     */
    public void addCoins(int num20, int num10, int num5, int num1) {
        this.num20 += num20;
        this.num10 += num10;
        this.num5 += num5;
        this.num1 += num1;
    }

    /**
     * Subtracts coins from the collection for each denomination.
     * @param num20 the number of coins with a denomination of 20 to subtract.
     * @param num10 the number of coins with a denomination of 10 to subtract.
     * @param num5  the number of coins with a denomination of 5 to subtract.
     * @param num1 the number of coins with a denomination of 1 to subtract.
     */
    public void subtractCoins(int num20, int num10, int num5, int num1) {
        this.num20 -= num20;
        this.num10 -= num10;
        this.num5 -= num5;
        this.num1 -= num1;

        if (this.num20 < 0) {
            this.num20 = 0;
        }
        if (this.num10 < 0) {
            this.num10 = 0;
        }
        if (this.num5 < 0) {
            this.num5 = 0;
        }
        if (this.num1 < 0) {
            this.num1 = 0;
        }
    }

    /**
     * Computes the total amount of money in the collection.
     * @return the total amount of money.
     */
    public int computeTotalAmount() {
        int amount20Coins = num20 * 20;
        int amount10Coins = num10 * 10;
        int amount5Coins = num5 * 5;
        int amount1Coins = num1;

        return amount20Coins + amount10Coins + amount5Coins + amount1Coins;
    }
}