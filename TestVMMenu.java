import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TestVMMenu extends JFrame {
    private JLabel headingLabel;
    private JPanel headingPanel;
    private JPanel buttonsPanel;
    private JButton vendingBttn; //test vending
    private JButton maintenanceBttn; //test maintenance
    private JButton returnButton;

    public TestVMMenu(){
        setTitle("Test A Vending Machine");
        setSize(325, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER));

            //PANEL TO HOLD HEADING
        headingPanel = new JPanel();
        headingPanel.setLayout(new BorderLayout());
        headingPanel.setPreferredSize(new Dimension(300, 60));

            //LABEL FOR HEADING
        headingLabel = new JLabel("Test A Vending Machine");
        headingLabel.setHorizontalAlignment(JLabel.CENTER);

            //PANEL TO HOLD BUTTONS
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3, 1, 30, 10));

        vendingBttn = new JButton("Vending Features");
        maintenanceBttn = new JButton("Maintenance Featuers");
        returnButton = new JButton("Return");

        vendingBttn.setFocusable(false);
        maintenanceBttn.setFocusable(false);
        returnButton.setFocusable(false);

            //ACTION COMMAND FOR ACTIONPERFORMED
        vendingBttn.setActionCommand("Vending");
        maintenanceBttn.setActionCommand("Maintenance");
        returnButton.setActionCommand("Return");

        headingPanel.add(headingLabel);
        buttonsPanel.add(vendingBttn);
        buttonsPanel.add(maintenanceBttn);
        buttonsPanel.add(returnButton);

        add(headingPanel);
        add(buttonsPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setVendingBttn (ActionListener e) {
        this.vendingBttn.addActionListener(e);
    }

    public void setMaintenanceBttn (ActionListener e) {
        this.maintenanceBttn.addActionListener(e);
    }

    public void setReturnButton (ActionListener e) {
        this.returnButton.addActionListener(e);
    }
}
