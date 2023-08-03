import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StockView extends JFrame{
    private JPanel headingPanel;
    private JPanel viewInvPanel;
    private JPanel slotNumPanel;
    private JPanel buttonsPanel;
    
    private JLabel  headingLabel;
    private JLabel slotNumLabel;
    
    private JButton viewInvBttn;
    private JButton enterButton;
    private JButton cancelButton;
    
    private JTextField slotNumTF;
    
    private JTextArea invTxtArea;

    public StockView(){
        setTitle("Stock / Restock");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(485, 500);
        setLayout(new FlowLayout(FlowLayout.CENTER));

        headingPanel= new JPanel();
        headingPanel.setLayout(new BorderLayout());
        headingPanel.setPreferredSize(new Dimension(400, 45));

        viewInvPanel = new JPanel();
        viewInvPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        viewInvPanel.setPreferredSize(new Dimension(400, 40));

        slotNumPanel = new JPanel();
        slotNumPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        slotNumPanel.setPreferredSize(new Dimension(200, 50));

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.setPreferredSize(new Dimension(400, 40));

        headingLabel = new JLabel("Stock / Restock");
        headingLabel.setHorizontalAlignment(JLabel.CENTER);

        invTxtArea = new JTextArea();
        invTxtArea.setPreferredSize(new Dimension(400, 240));
        invTxtArea.setEditable(false);

        viewInvBttn = new JButton("View Inventory");

        slotNumLabel = new JLabel("Enter slot to stock: ");
        slotNumLabel.setHorizontalAlignment(JLabel.CENTER);

        slotNumTF = new JTextField(17);

        enterButton = new JButton("Enter");
        enterButton.setFocusable(false);

        cancelButton = new JButton("Cancel");
        cancelButton.setFocusable(false);

        headingPanel.add(headingLabel);
        viewInvPanel.add(viewInvBttn);
        slotNumPanel.add(slotNumLabel);
        slotNumPanel.add(slotNumTF);
        buttonsPanel.add(enterButton);
        buttonsPanel.add(cancelButton);

        add(headingPanel);
        add(invTxtArea);
        add(viewInvPanel);
        add(slotNumPanel);
        add(buttonsPanel);

        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    public void setViewInvBtnListener (ActionListener actionListener) {
        this.viewInvBttn.addActionListener(actionListener);
    }

    public void setEnterBtnListener (ActionListener actionListener) {
        this.enterButton.addActionListener(actionListener);
    }
    
    public void setCancelBtnListener (ActionListener actionListener) {
        this.cancelButton.addActionListener(actionListener);
    }

    public String getInvTxtArea () {
        return this.invTxtArea.getText();
    }

    public void setInvTxtArea (String text) {
        this.invTxtArea.setText(text);
    }

    public void clearInvTxtArea () {
        this.invTxtArea.setText("");
    }

    public String getSlotNumTF() {
        return this.slotNumTF.getText();
    }

    public void clearSlotNumTxtFld () {
        this.slotNumTF.setText("");
    }
}
