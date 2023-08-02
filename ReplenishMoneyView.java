import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ReplenishMoneyView extends JFrame{
    private JLabel headingLabel, coin20BLabel, coin10BLabel, coin5BLabel, coin1BLabel;
    private JPanel headingPanel, viewPanel, moneyPanel, buttonPanel;
    private JTextField coin20BTF, coin10BTF, coin5BTF, coin1BTF;
    private JTextArea displayTextArea;
    private JButton viewButton, enterButton, backButton;

    public ReplenishMoneyView(){
        setTitle("Replenish Money");
        setSize(350, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        headingPanel = new JPanel();
        headingPanel.setLayout(new BorderLayout());
        headingPanel.setPreferredSize(new Dimension(300, 50));

        headingLabel = new JLabel("Replenish Money");
        headingLabel.setHorizontalAlignment(JLabel.CENTER);
        headingPanel.add(headingLabel);

        viewPanel = new JPanel();
        viewPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        viewPanel.setPreferredSize(new Dimension(300, 40));

        displayTextArea = new JTextArea();
        displayTextArea.setPreferredSize(new Dimension(300, 80));
        displayTextArea.setEditable(false);

        moneyPanel = new JPanel();
        moneyPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        moneyPanel.setPreferredSize(new Dimension(300, 200));

        coin20BLabel = new JLabel("Enter no. of 20-peso coins to replenish: ");
        coin10BLabel = new JLabel("Enter no. of 10-peso coins to replenish: ");
        coin5BLabel = new JLabel("Enter no. of 5-peso coins to replenish: ");
        coin1BLabel = new JLabel("Enter no. of 1-peso coins to replenish: ");

        coin20BTF = new JTextField(10);
        coin10BTF = new JTextField(10);
        coin5BTF = new JTextField(10);
        coin1BTF = new JTextField(10);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setPreferredSize(new Dimension(300, 80));

        viewButton = new JButton("View Money Box");
        viewButton.setFocusable(false);
        enterButton = new JButton("Enter");
        enterButton.setFocusable(false);
        backButton = new JButton("Back");
        backButton.setFocusable(false);
        viewPanel.add(viewButton);
        buttonPanel.add(enterButton);
        buttonPanel.add(backButton);

        moneyPanel.add(coin20BLabel);
        moneyPanel.add(coin20BTF);
        moneyPanel.add(coin10BLabel);
        moneyPanel.add(coin10BTF);
        moneyPanel.add(coin5BLabel);
        moneyPanel.add(coin5BTF);
        moneyPanel.add(coin1BLabel);
        moneyPanel.add(coin1BTF);

        add(headingPanel);
        add(displayTextArea);
        add(viewPanel);
        add(moneyPanel);
        add(buttonPanel);

        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    public void setViewBtnListener (ActionListener actionListener) {
        this.viewButton.addActionListener(actionListener);
    }

    public void setEnterBtnListener (ActionListener actionListener) {
        this.enterButton.addActionListener(actionListener);
    }

    public void setBackBtnListener (ActionListener actionListener) {
        this.backButton.addActionListener(actionListener);
    }

    public void setDisplayTextArea (String text) {
        this.displayTextArea.setText(text);
    }

    public String getCoin20BTF() {
        return coin20BTF.getText();
    }

    public String getCoin10BTF() {
        return coin10BTF.getText();
    }

    public String getCoin5BTF() {
        return coin5BTF.getText();
    }

    public String getCoin1BTF() {
        return coin1BTF.getText();
    }

    public void clearAllTextFlds () {
        this.coin20BTF.setText("");
        this.coin10BTF.setText("");
        this.coin5BTF.setText("");
        this.coin1BTF.setText("");
    }
}
