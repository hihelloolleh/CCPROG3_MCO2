import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CollectCoinView extends JFrame{
    private JLabel headingLabel;
    private JLabel coin20BLabel;
    private JLabel coin10BLabel;
    private JLabel coin5BLabel;
    private JLabel coin1BLabel;

    private JPanel headingPanel;
    private JPanel viewPanel;
    private JPanel moneyPanel;
    private JPanel buttonPanel;

    private JTextField coin20BTF;
    private JTextField coin10BTF;
    private JTextField coin5BTF;
    private JTextField coin1BTF;

    private JTextArea displayTextArea;

    private JButton viewButton;
    private JButton enterButton;
    private JButton backButton;

    public CollectCoinView(){
        setTitle("Collect Money");
        setSize(350, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        headingPanel = new JPanel();
        headingPanel.setLayout(new BorderLayout());
        headingPanel.setPreferredSize(new Dimension(300, 50));

        headingLabel = new JLabel("Collect Coin");
        headingLabel.setHorizontalAlignment(JLabel.CENTER);
        headingPanel.add(headingLabel);

        viewPanel = new JPanel();
        viewPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        viewPanel.setPreferredSize(new Dimension(300, 40));

        displayTextArea = new JTextArea("");
        displayTextArea.setPreferredSize(new Dimension(300, 80));
        displayTextArea.setEditable(false);

        moneyPanel = new JPanel();
        moneyPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        moneyPanel.setPreferredSize(new Dimension(300, 200));

        coin20BLabel = new JLabel("Enter no. of 20-peso coins to collect: ");
        coin10BLabel = new JLabel("Enter no. of 10=peso coins to collect: ");
        coin5BLabel = new JLabel("Enter no. of 5-peso coins to collect: ");
        coin1BLabel = new JLabel("Enter no. of 1-peso coins to collect: ");

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

    public void setViewBttnListener (ActionListener actionListener) {
        this.viewButton.addActionListener(actionListener);
    }

    public void setEnterBttnListener (ActionListener actionListener) {
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

    public void clearAllTextFields () {
        this.coin20BTF.setText("");
        this.coin10BTF.setText("");
        this.coin5BTF.setText("");
        this.coin1BTF.setText("");
    }
}
