package CSC400.Module8PP;
import javax.swing.*;

import java.awt.*;

public class UserInterface {
    
    PersonQueue peopleList = new PersonQueue();

    /**
    * Purpose: User interface creation method.  Creates an interface with 9 buttons, a displayfield, and 3 labels.
    * Inputs: None.
    * Outputs: None.
    **/
    public void UserInterfaceGUI(){
        
        //Set up the panels and frame
        JFrame          mainFrame           = new JFrame("BUF Roster Sorter");
        JPanel          mainPanel           = new JPanel();
        JPanel          buttonsPanel        = new JPanel();
        JPanel          displayPanel        = new JPanel();
        BorderLayout    mainPanelLayout     = new BorderLayout(10,10);
        GridLayout      buttonLayout        = new GridLayout(4, 3, 10, 10);
        GridLayout      displayLayout       = new GridLayout(1, 1, 10, 10);
        

        //labels
        JLabel          firstNameLabel      = new JLabel("First Name");
        JLabel          lastNameLabel       = new JLabel("Last Name");
        JLabel          ageLabel            = new JLabel("Age");
        //JLabel          blankJLabel         = new JLabel();

        //person info fields
        JTextField      firstNameField      =   new JTextField();
        JTextField      lastNameField       =   new JTextField();
        JTextField      ageField            =   new JTextField();

        //buttons
        JButton         addButton           =   new JButton("Add Person");
        JButton         sortAgeButton       =   new JButton("Sort by Age");
        JButton         sortNameButton      =   new JButton("Sort by Name");
        JButton         printAllButton      =   new JButton("Print All");
        JButton         exitButton          =   new JButton("Exit");
        JButton         addRandomsButton    =   new JButton("Add Test People");

        //output display
        JTextArea       displayField        =   new JTextArea(10, 10); displayField.setEditable(false);

        //Staff the display panel
        displayPanel.setLayout                  (displayLayout);
        displayPanel.add                        (displayField);

        //Staff the button panel
        buttonsPanel.setLayout                  (buttonLayout);
        buttonsPanel.add                        (firstNameLabel);
        buttonsPanel.add                        (lastNameLabel);
        buttonsPanel.add                        (ageLabel);
        buttonsPanel.add                        (firstNameField);
        buttonsPanel.add                        (lastNameField);
        buttonsPanel.add                        (ageField);
        buttonsPanel.add                        (addButton);
        buttonsPanel.add                        (sortAgeButton);
        buttonsPanel.add                        (sortNameButton);
        buttonsPanel.add                        (printAllButton);
        buttonsPanel.add                        (addRandomsButton);
        buttonsPanel.add                        (exitButton);

        //Staff main panel
        mainPanel.setLayout                     (mainPanelLayout);
        mainPanel.add                           (displayPanel, BorderLayout.PAGE_START);
        mainPanel.add                           (buttonsPanel, BorderLayout.PAGE_END);
        mainPanel.setBorder                     (BorderFactory.createEmptyBorder(10,10,10,10));

        //Staff the frame with the panels
        mainFrame.add                           (mainPanel);
        mainFrame.setDefaultCloseOperation      (JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible                    (true);
        mainFrame.pack                          ();
    
        /**
        * Purpose: Adds a person to the list based on user inputs.
        * Inputs: Accepts firstname, lastname, and age from the JTextField fields.
        * Calls: The addPerson() method is called to add someone to the PersonQueue queue.
        **/
        addButton.addActionListener(_ -> {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String age = ageField.getText();

            try{
                Integer ageInt = Integer.parseInt(age);
                peopleList.addPerson(new Person(firstName, lastName, ageInt));
                displayField.setText("New person added: ");
                firstNameField.setText("");
                lastNameField.setText("");
                ageField.setText("");
            } catch(NumberFormatException e){
                System.out.println("Error converting age input into a number.");
            }
        });

        /**
        * Purpose: Adds five pre-built names for easy testing.
        * Inputs: None.
        * Calls: The addPerson() method is called to add five people.
        **/
        addRandomsButton.addActionListener(_ -> {
            peopleList.addPerson(new Person("Cam", "McIntyre", 36));
            peopleList.addPerson(new Person("Ashley", "McIntyre", 35));
            peopleList.addPerson(new Person("Herbert", "Pensado", 42));
            peopleList.addPerson(new Person("Brian", "McIntyre", 67));
            peopleList.addPerson(new Person("Annie", "Seely", 14));
            displayField.setText("Test list added.");
        });

        /**
        * Purpose: Exits the program gracefully.
        * Inputs: None
        * Calls: System.exit(0) is called.
        **/
        exitButton.addActionListener(_ -> {
            System.exit(0);
        });

        /**
        * Purpose: Quick sorts the list of people by age.
        * Inputs: None
        * Calls: The sortPeopleList() method is called and sent 1 as the sortType (age sort)
        **/
        sortAgeButton.addActionListener(_ -> {
            peopleList.sortPeopleList(1);
            displayField.setText("Sorted by age: " + peopleList.printAllPeople());
        });

        /**
        * Purpose: Quick sorts the list of people by name.
        * Inputs: None
        * Calls: The sortPeopleList() method is called and sent 2 as the sortType (name sort)
        **/
        sortNameButton.addActionListener(_ -> {
            peopleList.sortPeopleList(2);
            displayField.setText("Sorted by name: " + peopleList.printAllPeople());
        });

        /**
        * Purpose: Outputs the list of people into the display field.
        * Inputs: None
        * Calls: printAllPeople() is called for the people queue.
        **/
        printAllButton.addActionListener(_ -> {
            displayField.setText(peopleList.printAllPeople());
        });
    }
}