import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TestVendingView extends JFrame {
    private JLabel vmNameLabel;

    private JPanel vmNamePanel;
    private JPanel slotsPanel;
    private JPanel moneyPanel;
    private JPanel inputPanel;
    private JPanel numButtonPanel;
    private JPanel blankPanel1;
    private JPanel confirmPanel;
    private JPanel blankPanel2;
    private JPanel  insertMoneyPanel;
    private JPanel moneyButtonsPanel;

    private JLabel amountLabel;
    private JLabel itemLabel;
    private JLabel  insertMoneyLabel;

    private JTextArea amountTextArea;
    private JTextArea itemTextArea;

    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button0;
    private JButton clearButton;
    private JButton enterButton;
    private JButton cancelButton;
    private JButton addButton;
    private JButton viewButton;
    private JButton coin20Button;
    private JButton coin10Button;
    private JButton coin5Button;
    private JButton coin1Button;

    public TestVendingView (RegVendingMachine vendingMachine) {
        setTitle("Test Regular Vending Features");
        setSize(680, 725);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.LEFT));

        vmNamePanel = new JPanel();
        vmNamePanel.setLayout(new BorderLayout());
        vmNamePanel.setPreferredSize(new Dimension(645, 40));
        vmNamePanel.setBackground(Color.LIGHT_GRAY);

        vmNameLabel = new JLabel(vendingMachine.getVMName());
        vmNameLabel.setHorizontalAlignment(JLabel.CENTER);

        vmNamePanel.add(vmNameLabel);

        slotsPanel = new JPanel();
        slotsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        slotsPanel.setPreferredSize(new Dimension(410, 525));
        slotsPanel.setBackground(Color.WHITE);

        for (int i = 0; i < vendingMachine.getMaxSlots(); i++) {
                // Create panel to hold the slot components
            JPanel slotPanel = new JPanel();
            slotPanel.setPreferredSize(new Dimension(130, 100));
            slotPanel.setBackground(Color.LIGHT_GRAY);

            JPanel slotNumPanel = new JPanel();
            slotNumPanel.setPreferredSize(new Dimension(125, 18));
            slotNumPanel.setBackground(Color.WHITE);
            JLabel slotNumLabel = new JLabel(String.valueOf(i + 1));
            slotNumPanel.add(slotNumLabel);

                // Components for displaying item names
            JPanel itemNamePanel = new JPanel();
            itemNamePanel.setPreferredSize(new Dimension(125, 18));
            itemNamePanel.setBackground(Color.LIGHT_GRAY);
                // JLabel itemNameLabel = new JLabel("Product Name");  // to test
            JLabel itemNameLabel = new JLabel();
            if (vendingMachine.getSlots().get(i).isEmpty())
                itemNameLabel.setText(" ");
            else
                itemNameLabel.setText(vendingMachine.getSlots().get(i).getItems().get(0).getName());
            itemNamePanel.add(itemNameLabel);

                // Components for displaying item calories
            JPanel itemCalPanel = new JPanel();
            itemCalPanel.setPreferredSize(new Dimension(125, 18));
            itemCalPanel.setBackground(Color.LIGHT_GRAY);
                // JLabel itemCalLabel = new JLabel("Calories: 123");  // to test
            JLabel itemCalLabel = new JLabel();
            if (vendingMachine.getSlots().get(i).isEmpty())
                itemCalLabel.setText("<empty>");
            else
                itemCalLabel.setText("Calories: " + vendingMachine.getSlots().get(i).getItems().get(0).getCalories());
            itemCalPanel.add(itemCalLabel);

                // Components in displaying item prices
            JPanel itemPricePanel = new JPanel();
            itemPricePanel.setPreferredSize(new Dimension(125, 18));
            itemPricePanel.setBackground(Color.LIGHT_GRAY);
            // JLabel itemPriceLabel = new JLabel("PHP 123");  // to test
            JLabel itemPriceLabel = new JLabel();
            if (vendingMachine.getSlots().get(i).isEmpty())
                itemPriceLabel.setText(" ");
            else
                itemPriceLabel.setText("PHP " + vendingMachine.getSlots().get(i).getItems().get(0).getPrice());
            itemPricePanel.add(itemPriceLabel);

            slotPanel.add(slotNumPanel);
            slotPanel.add(itemNamePanel);
            slotPanel.add(itemCalPanel);
            slotPanel.add(itemPricePanel);

            slotsPanel.add(slotPanel);
        }

            // Create text field to display the amount of money being inserted into the VM
        amountLabel = new JLabel("Received Payment: ");
        inputPanel.add(amountLabel);
        amountTextArea = new JTextArea("0");
        amountTextArea.setPreferredSize(new Dimension(230, 40));
        amountTextArea.setEditable(false);
        inputPanel.add(amountTextArea);

            // Create text field to display the selected item to purchase
        itemLabel = new JLabel("Item Selected: ");
        inputPanel.add(itemLabel);
        itemTextArea   = new JTextArea("");
        itemTextArea.setPreferredSize(new Dimension(230, 40));
        itemTextArea.setEditable(false);
        inputPanel.add(itemTextArea);

            // Create panel to separate text areas from number buttons
        blankPanel1 = new JPanel();
        blankPanel1.setPreferredSize(new Dimension(230, 20));
        inputPanel.add(blankPanel1);

            // Create panel to hold number buttons
        numButtonPanel = new JPanel();
        numButtonPanel.setPreferredSize(new Dimension(230, 200));
        numButtonPanel.setLayout(new GridLayout(4, 3, 10, 10));

            // Create number buttons
        button1 = new JButton("1");
        button2 = new JButton("2");
        button3 = new JButton("3");
        button4 = new JButton("4");
        button5 = new JButton("5");
        button6 = new JButton("6");
        button7 = new JButton("7");
        button8 = new JButton("8");
        button9 = new JButton("9");
        button0 = new JButton("0");

        addButton = new JButton("Add");
        viewButton = new JButton("View");

        button1.setFocusable(false);
        button2.setFocusable(false);
        button3.setFocusable(false);
        button4.setFocusable(false);
        button5.setFocusable(false);
        button6.setFocusable(false);
        button7.setFocusable(false);
        button8.setFocusable(false);
        button9.setFocusable(false);
        button0.setFocusable(false);
        addButton.setFocusable(false);
        viewButton.setFocusable(false);

        numButtonPanel.add(button1);
        numButtonPanel.add(button2);
        numButtonPanel.add(button3);
        numButtonPanel.add(button4);
        numButtonPanel.add(button5);
        numButtonPanel.add(button6);
        numButtonPanel.add(button7);
        numButtonPanel.add(button8);
        numButtonPanel.add(button9);
        numButtonPanel.add(button0);
        numButtonPanel.add(addButton);
        numButtonPanel.add(viewButton);

        inputPanel.add(numButtonPanel);

            // Create panel to separate the number and confirmation buttons buttons
        blankPanel2 = new JPanel();
        blankPanel2.setPreferredSize(new Dimension(230, 20));
        inputPanel.add(blankPanel2);

            // Create panel to hold Clear, Enter, and Cancel Buttons
        confirmPanel = new JPanel();
        confirmPanel.setPreferredSize(new Dimension(230, 50));
        confirmPanel.setLayout(new GridLayout(1, 3, 5, 5));

            // Create confirmation buttons
        clearButton = new JButton("Clear");
        enterButton = new JButton("Enter");
        cancelButton = new JButton("Cancel");
        clearButton.setFocusable(false);
        enterButton.setFocusable(false);
        cancelButton.setFocusable(false);
        confirmPanel.add(clearButton);
        confirmPanel.add(enterButton);
        confirmPanel.add(cancelButton);
        inputPanel.add(confirmPanel);

            // Create panel to hold customer's money section
        moneyPanel = new JPanel();
        moneyPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        moneyPanel.setPreferredSize(new Dimension(645, 95));

            // Create panel to hold enterMoneyLabel
        insertMoneyPanel = new JPanel();
        insertMoneyPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        insertMoneyPanel.setPreferredSize(new Dimension(645, 25));

            // Create the label
        insertMoneyLabel = new JLabel("Insert money (PHP): ");
        insertMoneyPanel.add(insertMoneyLabel);

            // Create panel to hold money denomination buttons
        moneyButtonsPanel = new JPanel();
        moneyButtonsPanel.setLayout(new GridLayout(1, 4, 10, 0));
        moneyButtonsPanel.setPreferredSize(new Dimension(645, 55));

            // Create buttons for each denomination
        coin20Button = new JButton("20");
        coin10Button = new JButton("10");
        coin5Button = new JButton("5");
        coin1Button = new JButton("1");

        coin20Button.setFocusable(false);
        coin10Button.setFocusable(false);
        coin5Button.setFocusable(false);
        coin1Button.setFocusable(false);

            // Add buttons to moneyBtsPanel
        moneyButtonsPanel.add(coin20Button);
        moneyButtonsPanel.add(coin10Button);
        moneyButtonsPanel.add(coin5Button);
        moneyButtonsPanel.add(coin1Button);

            // Add components to moneyPanel
        moneyPanel.add(insertMoneyPanel);
        moneyPanel.add(moneyButtonsPanel, BorderLayout.CENTER);

        add(vmNamePanel);
        add(slotsPanel);
        add(inputPanel);
        add(moneyPanel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

        // set Number Buttons
    public void setButton1Listener (ActionListener actionListener) {
        this.button1.addActionListener(actionListener);
    }

    public void setButton2Listener (ActionListener actionListener) {
        this.button2.addActionListener(actionListener);
    }

    public void setButton3Listener (ActionListener actionListener) {
        this.button3.addActionListener(actionListener);
    }

    public void setButton4Listener (ActionListener actionListener) {
        this.button4.addActionListener(actionListener);
    }

    public void setButton5Listener (ActionListener actionListener) {
        this.button5.addActionListener(actionListener);
    }

    public void setButton6Listener (ActionListener actionListener) {
        this.button6.addActionListener(actionListener);
    }

    public void setButton7Listener (ActionListener actionListener) {
        this.button7.addActionListener(actionListener);
    }

    public void setButton8Listener (ActionListener actionListener) {
        this.button8.addActionListener(actionListener);
    }

    public void setButton9Listener (ActionListener actionListener) {
        this.button9.addActionListener(actionListener);
    }

    public void setButton0Listener (ActionListener actionListener) {
        this.button0.addActionListener(actionListener);
    }


        // add button (for Special)
    public void setAddBtnListener (ActionListener actionListener) {
        this.addButton.addActionListener(actionListener);
    }

    public void setEnabledAddBtn (boolean setEnabled) {
        this.addButton.setEnabled(setEnabled);
    }

        // view button (for Special)
    public void setViewBtnListener (ActionListener actionListener) {
        this.viewButton.addActionListener(actionListener);
    }

    public void setEnabledViewBtn (boolean setEnabled) {
        this.viewButton.setEnabled(setEnabled);
    }

        // set Confirmation Buttons
    public void setClearBtnListener (ActionListener actionListener) {
        this.clearButton.addActionListener(actionListener);
    }

    public void setEnterBtnListener (ActionListener actionListener) {
        this.enterButton.addActionListener(actionListener);
    }

    public void setCancelBtnListener (ActionListener actionListener) {
        this.cancelButton.addActionListener(actionListener);
    }

        // set Money Buttons
    public void setMoney20BtnListener (ActionListener actionListener) {
        this.coin20Button.addActionListener(actionListener);
    }

    public void setMoney10BtnListener (ActionListener actionListener) {
        this.coin10Button.addActionListener(actionListener);
    }

    public void setMoney5BtnListener (ActionListener actionListener) {
        this.coin5Button.addActionListener(actionListener);
    }

    public void setMoney1BtnListener (ActionListener actionListener) {
        this.coin1Button.addActionListener(actionListener);
    }


        // Text Fields/Areas
    public String getAmountTextArea () {
        return this.amountTextArea.getText();
    }

    public String getItemTextArea () {
        return this.itemTextArea.getText();
    }

    public void setAmountTextAreaTA (String text) {
        int amountParam = Integer.parseInt(text);
        int currentAmount = Integer.parseInt(amountTextArea.getText());

        this.amountTextArea.setText(String.valueOf(amountParam + currentAmount));
    }

    public void setItemTextAreaTA (String text) {
        this.itemTextArea.setText(this.itemTextArea.getText().concat(text));
    }


        // Clear text fields
    public void clearAmountTextAreaTA () {
        this.amountTextArea.setText("0");
    }

    public void clearItemTextAreaTA () {
        this.itemTextArea.setText("");
    }

    public void clearTextFields () {
        this.amountTextArea.setText("0");
        this.itemTextArea.setText("");
    }
}
