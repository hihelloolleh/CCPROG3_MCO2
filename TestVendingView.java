import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TestVendingView extends JFrame {
    private JLabel vmNameLabel;
    private JPanel vmNamePanel, slotsPanel, inputPanel, moneyPanel;

    private JLabel amountLabel, itemLabel;
    private JPanel numBtnPanel, blankPanel1;
    private JTextArea amountTextArea, itemTextArea;
    private JButton button_1, button_2, button_3, button_4, button_5;
    private JButton button_6, button_7, button_8, button_9, button_0;

    private JPanel  confirmPanel, blankPanel_2;
    private JButton clearBtn, enterBtn, cancelBtn, addBtn, viewBtn;

    private JLabel  insertMoneyLabel;
    private JPanel  insertMoneyPanel, moneyBtnsPanel;
    private JButton money20Btn, money10Btn, money5Btn, money1Btn;

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
        numBtnPanel = new JPanel();
        numBtnPanel.setPreferredSize(new Dimension(230, 200));
        numBtnPanel.setLayout(new GridLayout(4, 3, 10, 10));

        // Create number buttons
        button_1 = new JButton("1");
        button_2 = new JButton("2");
        button_3 = new JButton("3");
        button_4 = new JButton("4");
        button_5 = new JButton("5");
        button_6 = new JButton("6");
        button_7 = new JButton("7");
        button_8 = new JButton("8");
        button_9 = new JButton("9");
        button_0 = new JButton("0");
        addBtn   = new JButton("Add");
        viewBtn  = new JButton("View");
        button_1.setFocusable(false);
        button_2.setFocusable(false);
        button_3.setFocusable(false);
        button_4.setFocusable(false);
        button_5.setFocusable(false);
        button_6.setFocusable(false);
        button_7.setFocusable(false);
        button_8.setFocusable(false);
        button_9.setFocusable(false);
        button_0.setFocusable(false);
        addBtn.setFocusable(false);
        viewBtn.setFocusable(false);
        numBtnPanel.add(button_1);
        numBtnPanel.add(button_2);
        numBtnPanel.add(button_3);
        numBtnPanel.add(button_4);
        numBtnPanel.add(button_5);
        numBtnPanel.add(button_6);
        numBtnPanel.add(button_7);
        numBtnPanel.add(button_8);
        numBtnPanel.add(button_9);
        numBtnPanel.add(button_0);
        numBtnPanel.add(addBtn);
        numBtnPanel.add(viewBtn);
        inputPanel.add(numBtnPanel);

        // Create panel to separate the number and confirmation buttons buttons
        blankPanel_2 = new JPanel();
        blankPanel_2.setPreferredSize(new Dimension(230, 20));
        inputPanel.add(blankPanel_2);


        // Create panel to hold Clear, Enter, and Cancel Buttons
        confirmPanel = new JPanel();
        confirmPanel.setPreferredSize(new Dimension(230, 50));
        confirmPanel.setLayout(new GridLayout(1, 3, 5, 5));

        // Create confirmation buttons
        clearBtn  = new JButton("Clear");
        enterBtn  = new JButton("Enter");
        cancelBtn = new JButton("Cancel");
        clearBtn.setFocusable(false);
        enterBtn.setFocusable(false);
        cancelBtn.setFocusable(false);
        confirmPanel.add(clearBtn);
        confirmPanel.add(enterBtn);
        confirmPanel.add(cancelBtn);
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
        moneyBtnsPanel = new JPanel();
        moneyBtnsPanel.setLayout(new GridLayout(1, 4, 10, 0));
        moneyBtnsPanel.setPreferredSize(new Dimension(645, 55));

        // Create buttons for each denomination
        money20Btn = new JButton("20");
        money10Btn = new JButton("10");
        money5Btn  = new JButton("5");
        money1Btn  = new JButton("1");
        money20Btn.setFocusable(false);
        money10Btn.setFocusable(false);
        money5Btn.setFocusable(false);
        money1Btn.setFocusable(false);

        // Add buttons to moneyBtsPanel
        moneyBtnsPanel.add(money20Btn);
        moneyBtnsPanel.add(money10Btn);
        moneyBtnsPanel.add(money5Btn);
        moneyBtnsPanel.add(money1Btn);

        // Add components to moneyPanel
        moneyPanel.add(insertMoneyPanel);
        moneyPanel.add(moneyBtnsPanel, BorderLayout.CENTER);


        // add panels to frame
        add(vmNamePanel);
        add(slotsPanel);
        add(inputPanel);
        add(moneyPanel);

        // display the frame
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    // set Number Buttons
    public void setButton1Listener (ActionListener actionListener) {
        this.button_1.addActionListener(actionListener);
    }

    public void setButton2Listener (ActionListener actionListener) {
        this.button_2.addActionListener(actionListener);
    }

    public void setButton3Listener (ActionListener actionListener) {
        this.button_3.addActionListener(actionListener);
    }

    public void setButton4Listener (ActionListener actionListener) {
        this.button_4.addActionListener(actionListener);
    }

    public void setButton5Listener (ActionListener actionListener) {
        this.button_5.addActionListener(actionListener);
    }

    public void setButton6Listener (ActionListener actionListener) {
        this.button_6.addActionListener(actionListener);
    }

    public void setButton7Listener (ActionListener actionListener) {
        this.button_7.addActionListener(actionListener);
    }

    public void setButton8Listener (ActionListener actionListener) {
        this.button_8.addActionListener(actionListener);
    }

    public void setButton9Listener (ActionListener actionListener) {
        this.button_9.addActionListener(actionListener);
    }

    public void setButton0Listener (ActionListener actionListener) {
        this.button_0.addActionListener(actionListener);
    }


    // add button (for Special)
    public void setAddBtnListener (ActionListener actionListener) {
        this.addBtn.addActionListener(actionListener);
    }

    public void setEnabledAddBtn (boolean setEnabled) {
        this.addBtn.setEnabled(setEnabled);
    }

    // view button (for Special)
    public void setViewBtnListener (ActionListener actionListener) {
        this.viewBtn.addActionListener(actionListener);
    }

    public void setEnabledViewBtn (boolean setEnabled) {
        this.viewBtn.setEnabled(setEnabled);
    }

    // set Confirmation Buttons
    public void setClearBtnListener (ActionListener actionListener) {
        this.clearBtn.addActionListener(actionListener);
    }

    public void setEnterBtnListener (ActionListener actionListener) {
        this.enterBtn.addActionListener(actionListener);
    }

    public void setCancelBtnListener (ActionListener actionListener) {
        this.cancelBtn.addActionListener(actionListener);
    }

    // set Money Buttons
    public void setMoney20BtnListener (ActionListener actionListener) {
        this.money20Btn.addActionListener(actionListener);
    }

    public void setMoney10BtnListener (ActionListener actionListener) {
        this.money10Btn.addActionListener(actionListener);
    }

    public void setMoney5BtnListener (ActionListener actionListener) {
        this.money5Btn.addActionListener(actionListener);
    }

    public void setMoney1BtnListener (ActionListener actionListener) {
        this.money1Btn.addActionListener(actionListener);
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
