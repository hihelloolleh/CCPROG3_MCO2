import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CreateVMMenu extends JFrame { //Create New Vending Machine Window
    private JPanel headingPanel;
    private JLabel headingLabel;
    private JPanel buttonPanel;
    private JButton createRVMBttn;
    private JButton createSVMBttn;
    private JButton returnButton;

    public CreateVMMenu(){
        setTitle("Create Vending Machine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 250);
        setLayout(new FlowLayout(FlowLayout.CENTER));

            //panel for label
        headingPanel = new JPanel();
        headingPanel.setLayout(new BorderLayout());
        headingPanel.setPreferredSize(new Dimension(300, 60));

            //label for heading
        headingLabel = new JLabel("Create Vending Machine");
        headingLabel.setHorizontalAlignment(JLabel.CENTER);

            //panel for buttons
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 30, 10));

            //buttons
        createRVMBttn = new JButton("Regular Vending Machine");
        createSVMBttn = new JButton("Special Vending Machine");
        returnButton = new JButton("Return");

            //set action command for actionPerformed
        createRVMBttn.setActionCommand("Regular VM");
        createSVMBttn.setActionCommand("Special VM");
        returnButton.setActionCommand("Return");

        headingPanel.add(headingLabel);
        buttonPanel.add(createRVMBttn);
        buttonPanel.add(createSVMBttn);
        buttonPanel.add(returnButton);

        add(headingPanel);
        add(buttonPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    public void setCreateRVMBttn (ActionListener e){
        this.createRVMBttn.addActionListener(e);
    }

    public void setCreateSVMBttn (ActionListener e){
        this.createSVMBttn.addActionListener(e);
    }

    public void setReturnButton (ActionListener e){
        this.returnButton.addActionListener(e);
    }

    
}
