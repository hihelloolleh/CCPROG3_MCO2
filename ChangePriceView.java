import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ChangePriceView extends JFrame{
    private JPanel headingPanel;
    private JPanel viewPricePanel;
    private JPanel itemPanel;
    private JPanel buttonsPanel;

    private JLabel  headingLabel;
    private JLabel itemLabel;
    
    private JButton viewPriceButton;
    private JButton enterButton;
    private JButton cancelButton;
    
    private JTextField itemTF;
    private JTextArea viewPricesArea;

    public ChangePriceView(){
        setTitle("Change Price of Item/s");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(485, 500);
        setLayout(new FlowLayout(FlowLayout.CENTER));

        headingPanel= new JPanel();
        headingPanel.setLayout(new BorderLayout());
        headingPanel.setPreferredSize(new Dimension(400, 45));

        viewPricePanel = new JPanel();
        viewPricePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        viewPricePanel.setPreferredSize(new Dimension(400, 40));

        itemPanel = new JPanel();
        itemPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        itemPanel.setPreferredSize(new Dimension(200, 50));

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.setPreferredSize(new Dimension(400, 40));

        headingLabel = new JLabel("Change Price of Item/s");
        headingLabel.setHorizontalAlignment(JLabel.CENTER);

        viewPricesArea = new JTextArea();
        viewPricesArea.setPreferredSize(new Dimension(400, 240));
        viewPricesArea.setEditable(false);

        viewPriceButton = new JButton("View Prices");

        itemLabel = new JLabel("Enter item to change price: ");
        itemLabel.setHorizontalAlignment(JLabel.CENTER);

        itemTF = new JTextField(17);

        enterButton = new JButton("Enter");
        enterButton.setFocusable(false);

        cancelButton = new JButton("Cancel");
        cancelButton.setFocusable(false);

        headingPanel.add(headingLabel);
        viewPricePanel.add(viewPriceButton);
        itemPanel.add(itemLabel);
        itemPanel.add(itemTF);
        buttonsPanel.add(enterButton);
        buttonsPanel.add(cancelButton);

        add(headingPanel);
        add(viewPricesArea);
        add(viewPricePanel);
        add(itemPanel);
        add(buttonsPanel);

        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    public void setViewPriceBtnListener (ActionListener actionListener) {
        this.viewPriceButton.addActionListener(actionListener);
    }

    public void setEnterBtnListener (ActionListener actionListener) {
        this.enterButton.addActionListener(actionListener);
    }

    public void setCancelBtnListener (ActionListener actionListener) {
        this.cancelButton.addActionListener(actionListener);
    }

    public String getViewPricesArea () {
        return this.viewPricesArea.getText();
    }

    public void setViewPricesArea (String text) {
        this.viewPricesArea.setText(text);
    }

    public String getItemTF() {
        return this.itemTF.getText();
    }
    
    public void clearItemTxtFld () {
        this.itemTF.setText("");
    }
}
