package CSC372_projects.CTA6;

import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.GridLayout;

public class ArrayListDemo {
    
    private ArrayList<Student> studentList;

    public ArrayListDemo(){

        studentList = new ArrayList<>(10);
    }

    //This sets up the user interface for the program.
    public void MainGUI() {

        JFrame mainFrame = new JFrame("Array Sorting Demonstration");
        mainFrame.setSize(600, 800);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1, 2, 2));

        JPanel upperPanel = new JPanel();
        upperPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        upperPanel.setLayout(new GridLayout(4, 3, 2, 2));

        JPanel lowerPanel = new JPanel();
        lowerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        lowerPanel.setLayout(new GridLayout(2, 1, 2, 2));

        JLabel nameLabel = new JLabel("Student Name");
        JLabel rollnoLabel = new JLabel("Student Number");
        JLabel addressLabel = new JLabel("Student Address");

        JTextField nameField = new JTextField(10);
        JTextField rollnoField = new JTextField(10);
        JTextField addressField = new JTextField(10);

        JTextArea studentArrayField = new JTextArea("Student Roster", 10, 10);
        studentArrayField.setLineWrap(true);
        studentArrayField.setEditable(false);
        
        JTextArea studentArraySortedField = new JTextArea("Sorted Roster", 10, 10);
        studentArraySortedField.setLineWrap(true);
        studentArraySortedField.setEditable(false);

        JButton addStudent = new JButton("Add Student");
        JButton reset = new JButton("Clear Roster");
        JButton sortName = new JButton("Sort By Name");
        JButton sortRollno = new JButton("Sort By Student Number");
        JButton defaultStudentList = new JButton("Add Example List");
        JButton exitButton = new JButton("Exit");

        //This button adds the students.
        addStudent.addActionListener(e -> {

            int rollno = ArrayListDemo.stringToInt(rollnoField.getText());
            String name = nameField.getText();
            String address = addressField.getText();
            Student newStudent = new Student(rollno, name, address);

            if(studentList.size() == 10){
                studentArrayField.setText("Reached maximum size of roster! (10 students)" + studentToString(studentList));
            } else { 
                studentList.add(newStudent);
                studentArrayField.setText("Student Added!" + studentToString(studentList));
            }

            nameField.setText("");
            rollnoField.setText("");
            addressField.setText("");
        });

        //Just in case you want to start from scratch, here is a reset button that zeroes out everything (including the array of students)
        reset.addActionListener(e -> {
            nameField.setText("");
            rollnoField.setText("");
            studentList.clear();
            studentArrayField.setText("");
            studentArraySortedField.setText("");
        });

        //This is the button to sort by name.  Notice that it sends the Name Comparator class comparator to the SelectionSort method.  This is how the program knows to name sort on this button action.
        sortName.addActionListener(e -> {
            NameComparator nameComparator = new NameComparator();
            SelectionSort.selectionSortName(studentList, nameComparator);
            studentArraySortedField.setText("Sorted by name!" + studentToString(studentList));
            
        });

        //This is the button to sort by student ID (rollno).  Notice how the appropriate comparator class is called to ensure that the right components of Student objects are being compared.
        sortRollno.addActionListener(e -> {
            RollnoComparator rollNoComparator = new RollnoComparator();
            SelectionSort.selectionSortRollno(studentList, rollNoComparator);
            studentArraySortedField.setText("Sorted by number!" + studentToString(studentList));
        });

        //This button adds ten default students, so you can test the program without entering students manually
        defaultStudentList.addActionListener(e -> {
            Student s0 = new Student(10, "John", "216 Elm Way");
            Student s1 = new Student(8, "Amy", "10 High Circle");
            Student s2 = new Student(3, "Tim", "7 Boulder Lane");
            Student s3 = new Student(6, "Jason", "76 Timbervine Road");
            Student s4 = new Student(5, "Edwark", "100 Infinity Circle");
            Student s5 = new Student(4, "Cornelius", "6 Jumbo Dump");
            Student s6 = new Student(1, "Sigismund", "The Fortress of Silence");
            Student s7 = new Student(7, "X47EAF", "A cabin in the woods");
            Student s8 = new Student(9, "Kevin", "The Eiffel Tower, France");
            Student s9 = new Student(2, "Tina", "4 Avenue B");
            studentList.add(0, s0);
            studentList.add(1, s1);
            studentList.add(2, s2);
            studentList.add(3, s3);
            studentList.add(4, s4);
            studentList.add(5, s5);
            studentList.add(6, s6);
            studentList.add(7, s7);
            studentList.add(8, s8);
            studentList.add(9, s9);

            studentArrayField.setText(studentToString(studentList));
        });

        exitButton.addActionListener(e -> {
            System.exit(0);
        });

        upperPanel.add(nameLabel);
        upperPanel.add(nameField);
        upperPanel.add(addStudent);
        upperPanel.add(rollnoLabel);
        upperPanel.add(rollnoField);
        upperPanel.add(reset);
        upperPanel.add(addressLabel);
        upperPanel.add(addressField);
        upperPanel.add(defaultStudentList);
        upperPanel.add(sortName);
        upperPanel.add(sortRollno);
        upperPanel.add(exitButton);
        
        lowerPanel.add(studentArrayField);
        lowerPanel.add(studentArraySortedField);
        
        mainPanel.add(upperPanel);
        mainPanel.add(lowerPanel);

        mainFrame.add(mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

    //A lot of this program has been copy/pasted from CTA5.  I wasn't sure if I needed these methods, so I left them in.
    public static int stringToInt(String string) { 
        return Integer.parseInt(string); 
    }

    public static String intToString(Integer result) { 
        return String.valueOf(result); 
    }

    //To output the entire array, it was easiest just to write a small method to output all names one by one using a For loop.
    public static String studentToString(ArrayList<Student> studentList) {
        int k;
        String studentsString = "";

        for (k = 0; k < studentList.size(); k++) {
            studentsString = studentsString + "\n" + studentList.get(k).getStudentName() + ", " + studentList.get(k).getStudentRollno() + ", " + studentList.get(k).getStudentAddress();
        }

        return studentsString;
    }
    
    //Main
    public static void main(String[] args) {

        ArrayListDemo startDemo = new ArrayListDemo();
        startDemo.MainGUI();
    }
}