import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

//USER OPT TO TEST REGULAR VM
public class TestVMView extends JFrame {
    private JLabel vmNameLabel;
    private JPanel vmNamePanel;
    private JPanel slotsPanel;
    private JPanel inputPanel;
    private JPanel moneyPanel;
    private JLabel amountLabel;
    private JLabel itemLabel;
    private JPanel numBttnPanel;
    private JPanel blankPanel1;
    private JTextArea amountTextArea;
    private JTextArea itemTextArea;
    private JButton button1, button2, button3, button4, button5;
    private JButton button6, button7, button8, button9, button0;
    private JPanel  confirmPanel, blankPanel2;
    private JButton clearBttn, enterBttn, cancelBttn, addBttn, viewBttn;

    private JLabel  insertMoneyLabel;
    private JPanel  insertMoneyPanel, moneyBttnPanel;
    private JButton coin20B, coin10B, coin5B, coin1B;

    public TestVMView(RegVendingMachine regularVM){
        setTitle("Test Regular Vending Features");
        setSize(680, 725);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.LEFT));

            //HOLD THE NAME OF VENDING MACHINE
        vmNamePanel = new JPanel();
        vmNamePanel.setLayout(new BorderLayout());
        vmNamePanel.setPreferredSize(new Dimension(645, 40));
        vmNamePanel.setBackground(Color.LIGHT_GRAY);

            //LABEL FOR THE NAME OF VENDING MACHINE
        vmNameLabel = new JLabel(regularVM.getVMName());
        vmNameLabel.setHorizontalAlignment(JLabel.CENTER);

        vmNamePanel.add(vmNameLabel);

            //PANEL TO HOLD SLOTS
        slotsPanel = new JPanel();
        slotsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        slotsPanel.setPreferredSize(new Dimension(410, 525));
        slotsPanel.setBackground(Color.WHITE);

            //ADD SLOTS IN PANEL
        for (int i = 0; i < regularVM.getMaxSlots(); i++) {

                //PANEL TO HOLD THE SLOT COMPONENTS
            JPanel slotPanel = new JPanel();
            slotPanel.setPreferredSize(new Dimension(130, 100));
            slotPanel.setBackground(Color.LIGHT_GRAY);

                //DISPLAY SLOT NUMBER
            JPanel slotNumPanel = new JPanel();
            slotNumPanel.setPreferredSize(new Dimension(125, 18));
            slotNumPanel.setBackground(Color.WHITE);
            JLabel slotNumLabel = new JLabel(String.valueOf(i + 1));
            slotNumPanel.add(slotNumLabel);

                //DISPLAY ITEM NAMES
            JPanel itemNamePanel = new JPanel();
            itemNamePanel.setPreferredSize(new Dimension(125, 18));
            itemNamePanel.setBackground(Color.LIGHT_GRAY);

            JLabel itemNameLabel = new JLabel();

            if (regularVM.getSlots().get(i).isEmpty()) {
                itemNameLabel.setText(" ");
            } else {
                itemNameLabel.setText(regularVM.getSlots().get(i).getItems().get(0).getName());
                itemNamePanel.add(itemNameLabel);
            }

                //DISPLAYING ITEM CALORIES
            JPanel itemCalPanel = new JPanel();
            itemCalPanel.setPreferredSize(new Dimension(125, 18));
            itemCalPanel.setBackground(Color.LIGHT_GRAY);

            JLabel itemCalLabel = new JLabel();

            if (regularVM.getSlots().get(i).isEmpty())
                itemCalLabel.setText("<empty>");
            else {
                itemCalLabel.setText("Calories: " + regularVM.getSlots().get(i).getItems().get(0).getCalories());
                itemCalPanel.add(itemCalLabel);
            }

                //DISPLAY ITEM PRICE
            JPanel itemPricePanel = new JPanel();
            itemPricePanel.setPreferredSize(new Dimension(125, 18));
            itemPricePanel.setBackground(Color.LIGHT_GRAY);

            JLabel itemPriceLabel = new JLabel();
            if (regularVM.getSlots().get(i).isEmpty())
                itemPriceLabel.setText(" ");
            else {
                itemPriceLabel.setText("PHP " + regularVM.getSlots().get(i).getItems().get(0).getPrice());
                itemPricePanel.add(itemPriceLabel);
            }

                //PANEL TO HOLD TEXT FIELDS
            slotPanel.add(slotNumPanel);
            slotPanel.add(itemNamePanel);
            slotPanel.add(itemCalPanel);
            slotPanel.add(itemPricePanel);

            slotsPanel.add(slotPanel);
        }

            //PANEL TO HOLD TEXT FIELDS
        inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        inputPanel.setPreferredSize(new Dimension(240, 450));

            //DISPLAY AMOUNT OF MONEY INSERTED
        amountLabel = new JLabel("Received Payment: ");
        inputPanel.add(amountLabel);
        amountTextArea = new JTextArea("0");
        amountTextArea.setPreferredSize(new Dimension(230, 40));
        amountTextArea.setEditable(false);
        inputPanel.add(amountTextArea);

            //DISPLAY SELECTED ITEM TO PURCHASE
        itemLabel = new JLabel("Item Selected: ");
        inputPanel.add(itemLabel);
        itemTextArea   = new JTextArea("");
        itemTextArea.setPreferredSize(new Dimension(230, 40));
        itemTextArea.setEditable(false);
        inputPanel.add(itemTextArea);

            //SEPARATE TEXT AREAS FROM NUMBER BUTTONS
        blankPanel1 = new JPanel();
        blankPanel1.setPreferredSize(new Dimension(230, 20));
        inputPanel.add(blankPanel1);

            //HOLD NUMBER BUTTONS
        numBttnPanel = new JPanel();
        numBttnPanel.setPreferredSize(new Dimension(230, 200));
        numBttnPanel.setLayout(new GridLayout(4, 3, 10, 10));

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
        addBttn = new JButton("Add");
        viewBttn = new JButton("View");

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
        addBttn.setFocusable(false);
        viewBttn.setFocusable(false);

        numBttnPanel.add(button1);
        numBttnPanel.add(button2);
        numBttnPanel.add(button3);
        numBttnPanel.add(button4);
        numBttnPanel.add(button5);
        numBttnPanel.add(button6);
        numBttnPanel.add(button7);
        numBttnPanel.add(button8);
        numBttnPanel.add(button9);
        numBttnPanel.add(button0);
        numBttnPanel.add(addBttn);
        numBttnPanel.add(viewBttn);
        inputPanel.add(numBttnPanel);

            //SEPARATE THE NUMBER AND CONFIRMATION BUTTONS
        blankPanel2 = new JPanel();
        blankPanel2.setPreferredSize(new Dimension(230, 20));
        inputPanel.add(blankPanel2);

        confirmPanel = new JPanel();
        confirmPanel.setPreferredSize(new Dimension(230, 50));
        confirmPanel.setLayout(new GridLayout(1, 3, 5, 5));

        clearBttn  = new JButton("Clear");
        enterBttn  = new JButton("Enter");
        cancelBttn = new JButton("Cancel");
        clearBttn.setFocusable(false);
        enterBttn.setFocusable(false);
        cancelBttn.setFocusable(false);
        confirmPanel.add(clearBttn);
        confirmPanel.add(enterBttn);
        confirmPanel.add(cancelBttn);
        inputPanel.add(confirmPanel);

        moneyPanel = new JPanel();
        moneyPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        moneyPanel.setPreferredSize(new Dimension(645, 95));

        insertMoneyPanel = new JPanel();
        insertMoneyPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        insertMoneyPanel.setPreferredSize(new Dimension(645, 25));

        insertMoneyLabel = new JLabel("Insert money (PHP): ");
        insertMoneyPanel.add(insertMoneyLabel);

        moneyBttnPanel = new JPanel();
        moneyBttnPanel.setLayout(new GridLayout(1, 4, 10, 0));
        moneyBttnPanel.setPreferredSize(new Dimension(645, 55));

        coin20B = new JButton("20");
        coin10B = new JButton("10");
        coin5B  = new JButton("5");
        coin1B  = new JButton("1");
        coin20B.setFocusable(false);
        coin10B.setFocusable(false);
        coin5B.setFocusable(false);
        coin1B.setFocusable(false);

        moneyBttnPanel.add(coin20B);
        moneyBttnPanel.add(coin10B);
        moneyBttnPanel.add(coin5B);
        moneyBttnPanel.add(coin1B);

        moneyPanel.add(insertMoneyPanel);
        moneyPanel.add(moneyBttnPanel, BorderLayout.CENTER);

        add(vmNamePanel);
        add(slotsPanel);
        add(inputPanel);
        add(moneyPanel);

        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

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

        //ADD BUTTON FOR SPECIAL
    public void setAddBtnListener (ActionListener actionListener) {
        this.addBttn.addActionListener(actionListener);
    }

    public void setEnabledAddBtn (boolean setEnabled) {

        this.addBttn.setEnabled(setEnabled);
    }

        //VIEW BUTTON FOR SPECIAL
    public void setViewBtnListener (ActionListener actionListener) {
        this.viewBttn.addActionListener(actionListener);
    }
    public void setEnabledViewBtn (boolean setEnabled) {
        this.viewBttn.setEnabled(setEnabled);
    }

        //SET CONFIRMATION BUTTONS

    public void setClearBtnListener (ActionListener actionListener) {
        this.clearBttn.addActionListener(actionListener);
    }

    public void setEnterBtnListener (ActionListener actionListener) {
        this.enterBttn.addActionListener(actionListener);
    }

    public void setCancelBtnListener (ActionListener actionListener) {
        this.cancelBttn.addActionListener(actionListener);
    }
        //SET MONEY BUTTONS
    public void setCoin20BListener (ActionListener actionListener) {
        this.coin20B.addActionListener(actionListener);
    }
    public void setCoin10BListener (ActionListener actionListener) {
        this.coin10B.addActionListener(actionListener);
    }
    public void setCoin5BListener (ActionListener actionListener) {
        this.coin5B.addActionListener(actionListener);
    }
    public void setCoin1BListener (ActionListener actionListener) {
        this.coin1B.addActionListener(actionListener);
    }

        //TEXT FIELDS / AREAS
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

        //CLEAR TEXT FIELDS
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
