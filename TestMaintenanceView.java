import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TestMaintenanceView extends JFrame {
    private JLabel headingLabel;
    private JPanel headingPanel, buttonsPanel;
    private JButton stockBtn, changePriceBtn, collectMoneyBtn;
    private JButton replenishMoneyBtn, printTransactionBtn, returnBtn;

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

        stockBtn = new JButton("Stock / Restock");
        changePriceBtn = new JButton("Change Price");
        collectMoneyBtn = new JButton("Collect Money");
        replenishMoneyBtn = new JButton("Replenish Money");
        printTransactionBtn = new JButton("Print Transaction Summary");
        returnBtn = new JButton("Return to Test Vending Machine");

        stockBtn.setFocusable(false);
        changePriceBtn.setFocusable(false);
        collectMoneyBtn.setFocusable(false);
        replenishMoneyBtn.setFocusable(false);
        printTransactionBtn.setFocusable(false);
        returnBtn.setFocusable(false);

        headingPanel.add(headingLabel);

        buttonsPanel.add(stockBtn);
        buttonsPanel.add(changePriceBtn);
        buttonsPanel.add(collectMoneyBtn);
        buttonsPanel.add(replenishMoneyBtn);
        buttonsPanel.add(printTransactionBtn);
        buttonsPanel.add(returnBtn);

        add(headingPanel);
        add(buttonsPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }
    public void setStockBtnListener (ActionListener actionListener) {
        this.stockBtn.addActionListener(actionListener);
    }
    public void setChangePriceBtnListener (ActionListener actionListener) {
        this.changePriceBtn.addActionListener(actionListener);
    }
    public void setCollectMoneyBtnListener (ActionListener actionListener) {
        this.collectMoneyBtn.addActionListener(actionListener);
    }

    public void setReplenishMoneyBtnListener (ActionListener actionListener) {
        this.replenishMoneyBtn.addActionListener(actionListener);
    }

    public void setPrintTransactionBtnListener (ActionListener actionListener) {
        this.printTransactionBtn.addActionListener(actionListener);
    }
    public void setReturnBtnListener (ActionListener actionListener) {
        this.returnBtn.addActionListener(actionListener);
    }
}
