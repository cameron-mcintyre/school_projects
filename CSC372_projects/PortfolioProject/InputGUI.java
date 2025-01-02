package CSC372_projects.PortfolioProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InputGUI extends JFrame implements ActionListener {
    
    InputGUI(){

        //Main GUI frame creation
        JFrame mainFrame = new JFrame("Student Record Input");
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainFrame.setSize(500, 400);

        //Only need one panel.  Not doing GridBag for this one, whew!
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 2, 10, 10));

        //Set up all the components in a panel
        JLabel studentNameLabel = new JLabel("Enter Student Name: ");
        JLabel studentAddressLabel = new JLabel("Enter Student Address: ");
        JLabel studentGPALabel = new JLabel("Enter Student GPA: ");
        JLabel emptyLabel - new JLabel(""); //I want the button on the right side, not the left.  Consider trying ComponentOrientation as well to do this.
        JTextField studentNameField = new JTextField();
        JTextField studentAddressField = new JTextField();
        JTextField studentGPAField = new JTextField();
        JButton submitStudentRecord = new JButton("Enter Record");

        //Add all the components onto the gridlayout
        mainPanel.add(studentNameLabel);
        mainPanel.add(studentNameField);
        mainPanel.add(studentAddressLabel);
        mainPanel.add(studentAddressField);
        mainPanel.add(studentGPALabel);
        mainPanel.add(studentGPAField);
        mainPanel.add(emptyLabel);
        mainPanel.add(submitStudentRecord);

        //Add panel to frame
        mainFrame.add(mainPanel);

        //Set up the button to listen to stuff
        submitStudentRecord.addActionListener(new ActionListener() {

        }

    }

    class SubmitRecordsButtonListener implements ActionListener {

        private JTextField studentNameField;
        private JTextField studentAddressField;
        private JTextField studentGPAField;

    }
}
