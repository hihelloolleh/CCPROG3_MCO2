import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CreateVMView extends JFrame {
    private JPanel headingPanel;
    private JPanel namePanel;
    private JPanel slotsPanel;
    private JPanel capacityPanel;
    private JPanel buttonsPanel;
    private JPanel blankPanel;

    private JLabel headingLabel;
    private JLabel nameLabel;
    private JLabel slotsLabel;
    private JLabel capacityLabel;

    private JTextField nameTF;
    private JTextField slotsTF;
    private JTextField capacityTF;

    private JButton clearButton;
    private JButton createButton;
    private JButton cancelButton;

    public CreateVMView(){
        setTitle("Create Vending Machine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 325);
        setResizable(false);
        setLayout(new FlowLayout(FlowLayout.CENTER));

            //PANEL TO HOLD COMPONENTS
        headingPanel = new JPanel();
        headingPanel.setLayout(new BorderLayout());
        headingPanel.setPreferredSize(new Dimension(359, 60));

        namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        namePanel.setPreferredSize(new Dimension(350, 30));

        slotsPanel = new JPanel();
        slotsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        slotsPanel.setPreferredSize(new Dimension(350, 30));

        capacityPanel = new JPanel();
        capacityPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        capacityPanel.setPreferredSize(new Dimension(350, 50));

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 3, 10, 10));

        blankPanel = new JPanel();
        blankPanel.setPreferredSize(new Dimension(350, 30));

            //LABEL FOR HEADINGS
        headingLabel = new JLabel(" ");
        headingLabel.setHorizontalAlignment(JLabel.CENTER);

        nameLabel = new JLabel("Enter name of Vending Machine: ");
        nameTF = new JTextField(10);

        slotsLabel = new JLabel("Enter number of slots: ");
        slotsTF = new JTextField(10);

        capacityLabel = new JLabel("Enter max capacity of each slot: ");
        capacityTF = new JTextField(10);

        clearButton = new JButton("Clear");
        clearButton.setPreferredSize(new Dimension(100, 30));
        clearButton.setFocusable(false);

        createButton = new JButton("Create");
        createButton.setPreferredSize(new Dimension(100, 30));
        createButton.setFocusable(false);

        cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(100, 30));
        cancelButton.setFocusable(false);

        headingPanel.add(headingLabel);
        namePanel.add(nameLabel);
        namePanel.add(nameTF);
        slotsPanel.add(slotsLabel);
        slotsPanel.add(slotsTF);
        capacityPanel.add(capacityLabel);
        capacityPanel.add(capacityTF);
        buttonsPanel.add(clearButton);
        buttonsPanel.add(createButton);
        buttonsPanel.add(cancelButton);

        add(headingPanel);
        add(namePanel);
        add(slotsPanel);
        add(capacityPanel);
        add(blankPanel);
        add(buttonsPanel);
        add(buttonsPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    public void setClearButton(ActionListener e){
        this.clearButton.addActionListener(e);
    }

    public void setCreateButton(ActionListener e){
        this.createButton.addActionListener(e);
    }

    public void setCancelButton(ActionListener e){
        this.cancelButton.addActionListener(e);
    }

    public void setEnabledCreateButton(boolean IsEnabled){
        this.createButton.setEnabled(isEnabled());
    }

    public void setHeadingLabel(String text){
        this.headingLabel.setText(text);
    }
    public String getNameTFText () {

        return this.nameTF.getText();
    }
    public String getSlotsTFText () {

        return this.slotsTF.getText();
    }

    public String getCapacityTFText () {

        return this.capacityTF.getText();
    }

    public void clearTextFields () {
        this.nameTF.setText("");
        this.slotsTF.setText("");
        this.capacityTF.setText("");
    }
}
