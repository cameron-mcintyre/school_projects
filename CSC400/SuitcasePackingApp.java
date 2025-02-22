package CSC400;
import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;


public class SuitcasePackingApp {
    
    public static void main(String[] args) {

        LinkedList<Suitcase<String>> suitcasesList = new LinkedList<Suitcase<String>>();
        Suitcase<String> suitcase1 = new Suitcase<String>();
        Suitcase<String> suitcase2 = new Suitcase<String>();
        suitcasesList.add(0, suitcase1);
        suitcasesList.add(1, suitcase2);
        suitcasePackingGUI(suitcasesList);
    }

    public static void suitcasePackingGUI(LinkedList<Suitcase<String>> suitcasesList) {

        //layouts used
        BorderLayout frameBorderLayout = new BorderLayout(10,10);
        GridLayout inputPanelLayout = new GridLayout(2, 2, 10, 10);
        GridLayout outputPanelLayout = new GridLayout(3,1,10,10);
        GridLayout buttonPanelLayout = new GridLayout(3, 2, 10, 10);

        //build out main frame
        JFrame mainFrame = new JFrame("Suitcase Packing Application");
        mainFrame.setSize(600, 800);
        mainFrame.setLayout(frameBorderLayout);

        //build input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(inputPanelLayout);
        JLabel inputPanelBagSelect = new JLabel("Select Bag");
        inputPanelBagSelect.setHorizontalAlignment(JLabel.CENTER);
        JLabel inputPanelInputLabel = new JLabel("Input");
        inputPanelInputLabel.setHorizontalAlignment(JLabel.CENTER);
        String[] suitcasesComboBoxList = {"None Selected", "Suitcase 1", "Suitcase 2"};
        JComboBox<String> inputPanelBagDropdown = new JComboBox<String>(suitcasesComboBoxList);
        JTextField inputPanelInputBox = new JTextField();
        inputPanel.add(inputPanelBagSelect);
        inputPanel.add(inputPanelInputLabel);
        inputPanel.add(inputPanelBagDropdown);
        inputPanel.add(inputPanelInputBox);

        //build output panel
        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(outputPanelLayout);
        JLabel outputLabel = new JLabel("Suitcase Display");
        JTextArea outputTextArea = new JTextArea();
        JTextArea helpText = new JTextArea("~~~Welcome to the suitcase packing application!~~~\n~~~You can bring two suitcases on your trip; It's time to pack!~~~\n1. To add an item, select the suitcase, enter an item into the input and click pack.\n2. To remove an item, input the integer key value for the item in suitcase.\n3. To combine your two suitcases, click merge.\n4. To remove duplicate items, click remove duplicate items.\n5. Your two suitcases can be displayed at any time.");
        outputLabel.setHorizontalAlignment(JLabel.CENTER);
        helpText.setLineWrap(true);
        helpText.setEditable(false);
        helpText.setWrapStyleWord(true);
        outputTextArea.setLineWrap(true);
        outputTextArea.setWrapStyleWord(true);
        outputPanel.add(outputLabel);
        outputPanel.add(outputTextArea);
        outputPanel.add(helpText);

        //build out button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(buttonPanelLayout);
        JButton addItem = new JButton("Pack Item");
        JButton removeItem = new JButton("Unpack Item");
        JButton removeDuplicates = new JButton("Remove Duplicate Items");
        JButton mergeSuitcases = new JButton("Combine Suitcase 2 Into 1");
        JButton displaySuitcase1 = new JButton("Show Suitcase 1 Contents");
        JButton displaySuitcase2 = new JButton("Show Suitcase 2 Contents");
        buttonPanel.add(addItem);
        buttonPanel.add(removeItem);
        buttonPanel.add(removeDuplicates);
        buttonPanel.add(mergeSuitcases);
        buttonPanel.add(displaySuitcase1);
        buttonPanel.add(displaySuitcase2);

        //indicates that the dropdown menu was changed
        inputPanelBagDropdown.addActionListener(e -> {
            String selection = (String)inputPanelBagDropdown.getSelectedItem();
            if (selection.equals("Suitcase 1")) {
                outputTextArea.setText("Suitcase 1 Selected!\nNumber of items in suitcase 1: " + suitcasesList.get(0).size() + "\nItems in suitcase 1: " + suitcasesList.get(0).printBag()); 
            } else if (selection.equals("Suitcase 2")) {
                outputTextArea.setText("Suitcase 2 Selected!\nNumber of items in suitcase 2: " + suitcasesList.get(1).size() + "\nItems in suitcase 2: " + suitcasesList.get(1).printBag());
            } else {
                outputTextArea.setText("");
            }
        });

        //add bag button actionlistener
        addItem.addActionListener(e -> {
            String selection = (String)inputPanelBagDropdown.getSelectedItem();
            String inputItem = inputPanelInputBox.getText();

            if (selection.equals("Suitcase 1")) {
                suitcasesList.get(0).add(inputItem);
                outputTextArea.setText("Item added to suitcase #1.  New suitcase 1 inventory: \n" + suitcasesList.get(0).printBag());
            } else if (selection.equals("Suitcase 2")) {
                suitcasesList.get(1).add(inputItem);
                outputTextArea.setText("Item added to suitcase #2.  New suitcase 2 inventory: \n" + suitcasesList.get(1).printBag());
            } else if (selection.equals("None Selected")) {
                outputTextArea.setText("A mystery item was added to one of your suitcases.");
                Random rand = new Random();
                int randInt = rand.nextInt(2);
                if (randInt == 0) {
                    suitcasesList.get(0).add(suitcasesList.get(0).addRandomItem());
                } else {
                    suitcasesList.get(1).add(suitcasesList.get(1).addRandomItem());
                }
            }
        });

        //removes an item of choice
        removeItem.addActionListener(e -> {
            String selection = (String)inputPanelBagDropdown.getSelectedItem();
            try {
                int inputItem = Integer.parseInt(inputPanelInputBox.getText());
                if (selection.equals("Suitcase 1")) { //TODO: make it so you can remove items by inputting their value, instead of key
                    suitcasesList.get(0).remove(inputItem);
                    outputTextArea.setText("Item removed from suitcase #1.  New suitcase 1 inventory: \n" + suitcasesList.get(0).printBag());
                } else if (selection.equals("Suitcase 2")) {
                    suitcasesList.get(1).remove(inputItem);
                    outputTextArea.setText("Item removed from suitcase #2.  New suitcase 2 inventory: \n" + suitcasesList.get(1).printBag());
                } else if (selection.equals("None Selected")) {
                    outputTextArea.setText("Please select a suitcase!");
                }
            } catch (NumberFormatException error) {
                outputTextArea.setText("You need to enter the integer key value to remove an item.");
            }
        });

        //remove duplicates
        removeDuplicates.addActionListener(e -> {
            String selection = (String)inputPanelBagDropdown.getSelectedItem();
            Suitcase<String> tempSuitcase = new Suitcase<String>();

            if (selection.equals("Suitcase 1")) {
                tempSuitcase = suitcasesList.get(0).distinct();
                suitcasesList.set(0, tempSuitcase);
                outputTextArea.setText("Removed duplicates from suitcase #1.  New suitcase 1 inventory: \n" + suitcasesList.get(0).printBag());
            } 
            else if (selection.equals("Suitcase 2")) {
                tempSuitcase = suitcasesList.get(1).distinct();
                suitcasesList.set(1, tempSuitcase);
                outputTextArea.setText("Removed duplicates from suitcase #2.  New suitcase 2 inventory: \n" + suitcasesList.get(1).printBag());
            } 
            else if (selection.equals("None Selected")) {
                outputTextArea.setText("Please select a suitcase!");
            }
        });

        //merge suitcase 2 into 1
        mergeSuitcases.addActionListener(e -> {
            suitcasesList.get(0).merge(suitcasesList.get(1));
            outputTextArea.setText("New suitcase 1! \n" + suitcasesList.get(0).printBag());
            suitcasesList.get(1).removeAll();
        });

        //displays suitcase 1
        displaySuitcase1.addActionListener(e -> {
            outputTextArea.setText("Number of items in suitcase 1: " + suitcasesList.get(0).size() + "\nItems in suitcase 1: " + suitcasesList.get(0).printBag());
            inputPanelBagDropdown.setSelectedItem(suitcasesComboBoxList[1]);
        });

        //displays suitcase 2
        displaySuitcase2.addActionListener(e -> {
            outputTextArea.setText("Number of items in suitcase 2: " + suitcasesList.get(1).size() + "\nItems in suitcase 2: " + suitcasesList.get(1).printBag());
            inputPanelBagDropdown.setSelectedItem(suitcasesComboBoxList[2]);
        });

        //drop into main Jframe
        mainFrame.add(inputPanel, BorderLayout.PAGE_START);
        mainFrame.add(buttonPanel, BorderLayout.CENTER);
        mainFrame.add(outputPanel, BorderLayout.PAGE_END);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.pack();
    }
}
