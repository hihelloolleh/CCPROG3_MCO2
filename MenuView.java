import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuView extends JFrame {
    private JPanel  headingPanel, mainBtnsPanel;
    private JLabel menuHeading;
    private JButton createVMBttn, testVMBttn, QuitButton;

    public MenuView (){
        setTitle("Main Menu");
        setSize(300, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER));

        headingPanel = new JPanel();
        headingPanel.setLayout(new BorderLayout());
        headingPanel.setPreferredSize(new Dimension(300, 60));

        menuHeading = new JLabel("Vending Machine Simulator");
        menuHeading.setHorizontalAlignment(JLabel.CENTER);

        mainBtnsPanel = new JPanel();
        mainBtnsPanel.setLayout(new GridLayout(3, 1, 30, 10));

        createVMBttn = new JButton("Create Vending Machine");
        testVMBttn = new JButton("Test Vending Machine");
        QuitButton = new JButton("Quit");

        createVMBttn.setFocusable(false);
        testVMBttn.setFocusable(false);
        QuitButton.setFocusable(false);

        headingPanel.add(menuHeading);

        mainBtnsPanel.add(createVMBttn);
        mainBtnsPanel.add(testVMBttn);
        mainBtnsPanel.add(QuitButton);

        add(headingPanel);
        add(mainBtnsPanel,BorderLayout.CENTER);

        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    public void setCreateVMBttn (ActionListener actionListener) {
        this.createVMBttn.addActionListener(actionListener);
    }

    public void setTestVMBttn (ActionListener actionListener) {
        this.testVMBttn.addActionListener(actionListener);
    }

    public void setQuitButton (ActionListener actionListener) {
        this.QuitButton.addActionListener(actionListener);
    }
}
