package CSC372_projects.CTA2;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.text.NumberFormat;

public class BankingGUI extends JFrame implements ActionListener {

    private JPanel bankingGUIapp;
    private JLabel headerLabel;
    private JLabel initialLabel;
    private JFormattedTextField initialInput;
    private JLabel withdrawalLabel;
    private JFormattedTextField withdrawalInput;
    private JLabel depositLabel;
    private JFormattedTextField depositInput;
    private JLabel balanceLabel;
    private JTextField balanceText;
    private JButton calculateButton;
    private double balanceAmount = 0.0;

    BankingGUI(){

        GridBagConstraints layoutConst = null;

        setTitle("Banking User Interface");

        bankingGUIapp = new JPanel(new GridBagLayout());
        
        headerLabel = new JLabel("~CSC372 Community Credit Union~");

        initialLabel = new JLabel("Enter your initial balance: ");
        withdrawalLabel = new JLabel("Enter amount for withdrawal: ");
        depositLabel = new JLabel("Enter the amount for deposit: ");
        balanceLabel = new JLabel("Your current balance: ");

        calculateButton = new JButton("Process balance change");
        calculateButton.addActionListener(this);

        initialInput = new JFormattedTextField(NumberFormat.getNumberInstance());
        initialInput.setEditable(true);
        initialInput.setValue(0.0);
        initialInput.setText("0.00");
        initialInput.setColumns(15);

        withdrawalInput = new JFormattedTextField(NumberFormat.getNumberInstance());
        withdrawalInput.setEditable(true);
        withdrawalInput.setValue(0.0);
        withdrawalInput.setText("0.00");
        withdrawalInput.setColumns(15);

        depositInput = new JFormattedTextField(NumberFormat.getNumberInstance());
        depositInput.setEditable(true);
        depositInput.setValue(0.0);
        depositInput.setText("0.00");
        depositInput.setColumns(15);

        balanceText = new JTextField(10);
        balanceText.setEditable(false);

        add(bankingGUIapp);

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Page Break~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 10);
        layoutConst.gridx = 0;
        layoutConst.gridy = 0;
        bankingGUIapp.add(headerLabel, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 10);
        layoutConst.gridx = 0;
        layoutConst.gridy = 1;
        bankingGUIapp.add(initialLabel, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 10);
        layoutConst.gridx = 1;
        layoutConst.gridy = 1;
        bankingGUIapp.add(initialInput, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 10);
        layoutConst.gridx = 0;
        layoutConst.gridy = 2;
        bankingGUIapp.add(withdrawalLabel, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 10);
        layoutConst.gridx = 1;
        layoutConst.gridy = 2;
        bankingGUIapp.add(withdrawalInput, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 10);
        layoutConst.gridx = 0;
        layoutConst.gridy = 3;
        bankingGUIapp.add(depositLabel, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 10);
        layoutConst.gridx = 1;
        layoutConst.gridy = 3;
        bankingGUIapp.add(depositInput, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 10);
        layoutConst.gridx = 0;
        layoutConst.gridy = 4;
        bankingGUIapp.add(balanceLabel, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 10);
        layoutConst.gridx = 1;
        layoutConst.gridy = 4;
        bankingGUIapp.add(balanceText, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 10);
        layoutConst.gridx = 0;
        layoutConst.gridy = 5;
        bankingGUIapp.add(calculateButton, layoutConst);
    }

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Page Break~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public void actionPerformed(ActionEvent event){
        double withdrawalAmount;
        double depositAmount;

        if (initialInput.isEditable()){
            balanceAmount = ((Number)initialInput.getValue()).doubleValue();
            initialInput.setEditable(false);
        } else {
            withdrawalAmount = ((Number)withdrawalInput.getValue()).doubleValue();
            depositAmount = ((Number)depositInput.getValue()).doubleValue();
            balanceAmount = balanceAmount + depositAmount - withdrawalAmount;
        }

        balanceText.setText(Double.toString(balanceAmount));
        initialInput.setText(Double.toString(balanceAmount));
        
        //withdrawalInput.setValue(0.0);
        //depositInput.setValue(0.0);
    }

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Page Break~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public static void main(String[] args){
        
        double balance = 0.0;

        BankingGUI bankingGUI = new BankingGUI();
        bankingGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bankingGUI.pack();
        bankingGUI.setVisible(true);
    }
}
