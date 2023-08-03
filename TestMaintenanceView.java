import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TestMaintenanceView extends JFrame {
    private JLabel headingLabel;
    
    private JPanel headingPanel;
    private JPanel buttonsPanel;
    
    private JButton stockButton;
    private JButton changePriceButton;
    private JButton collectMoneyButton;
    private JButton replenishMoneyButton;
    private JButton printTransactionButton;
    private JButton returnButton;

    public TestMaintenanceView(){
        setTitle("Test Maintenance Features");
        setSize(350, 360);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        headingPanel = new JPanel();
        headingPanel.setLayout(new BorderLayout());
        headingPanel.setPreferredSize(new Dimension(350, 60));

        headingLabel = new JLabel("Test Maintenance Features");
        headingLabel.setHorizontalAlignment(JLabel.CENTER);

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(6, 1, 30, 10));

        stockButton = new JButton("Stock / Restock");
        changePriceButton = new JButton("Change Price");
        collectMoneyButton = new JButton("Collect Money");
        replenishMoneyButton = new JButton("Replenish Money");
        printTransactionButton = new JButton("Print Transaction Summary");
        returnButton = new JButton("Return");

        stockButton.setFocusable(false);
        changePriceButton.setFocusable(false);
        collectMoneyButton.setFocusable(false);
        replenishMoneyButton.setFocusable(false);
        printTransactionButton.setFocusable(false);
        returnButton.setFocusable(false);

        headingPanel.add(headingLabel);

        buttonsPanel.add(stockButton);
        buttonsPanel.add(changePriceButton);
        buttonsPanel.add(collectMoneyButton);
        buttonsPanel.add(replenishMoneyButton);
        buttonsPanel.add(printTransactionButton);
        buttonsPanel.add(returnButton);

        add(headingPanel);
        add(buttonsPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }
    
    public void setStockBtnListener (ActionListener actionListener) {
        this.stockButton.addActionListener(actionListener);
    }
    
    public void setChangePriceBtnListener (ActionListener actionListener) {
        this.changePriceButton.addActionListener(actionListener);
    }
    
    public void setCollectMoneyBtnListener (ActionListener actionListener) {
        this.collectMoneyButton.addActionListener(actionListener);
    }

    public void setReplenishMoneyBtnListener (ActionListener actionListener) {
        this.replenishMoneyButton.addActionListener(actionListener);
    }

    public void setPrintTransactionBtnListener (ActionListener actionListener) {
        this.printTransactionButton.addActionListener(actionListener);
    }
    
    public void setReturnBtnListener (ActionListener actionListener) {
        this.returnButton.addActionListener(actionListener);
    }
}
