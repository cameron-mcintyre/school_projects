/*Portfolio Project Version 2
 *This version includes a GUI and some nice features, but does not exactly comply with assignment requirements.
 */

package CSC372_projects.PortfolioProject;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class StudentGUI extends Student {
    
    public static void main(String[] args){

        LinkedList<Student> studentList = new LinkedList<Student>();

        //create UI
        JFrame mainFrame = new JFrame("Student Record Input");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(700, 500);
        
        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setLayout(new GridLayout(1,2,10, 10));
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10 ,10 ,10));

        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new GridLayout(1, 1, 10, 10));
        displayPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel studentNameLabel = new JLabel("Student Name: ");
        JLabel studentAddressLabel = new JLabel("Student Address: ");
        JLabel studentGPALabel = new JLabel("Student GPA: ");
        JTextArea numStudents = new JTextArea(10, 10);
        numStudents.setEditable(false);
        JTextField studentNameField = new JTextField();
        JTextField studentAddressField = new JTextField();
        JTextField studentGPAField = new JTextField();
        JButton submitStudentRecord = new JButton("Enter Record");
        JButton exitButton = new JButton("Exit");
        JButton showStudents = new JButton("List Students");
        JButton exportStudents = new JButton("File Export");

        mainPanel.add(studentNameLabel);
        mainPanel.add(studentNameField);
        mainPanel.add(studentAddressLabel);
        mainPanel.add(studentAddressField);
        mainPanel.add(studentGPALabel);
        mainPanel.add(studentGPAField);
        mainPanel.add(submitStudentRecord);
        mainPanel.add(exitButton);
        mainPanel.add(showStudents);
        mainPanel.add(exportStudents);
        mainPanel.setBackground(Color.LIGHT_GRAY);

        displayPanel.add(numStudents);
        displayPanel.setBackground(Color.LIGHT_GRAY);

        backgroundPanel.add(mainPanel);
        backgroundPanel.add(displayPanel);

        mainFrame.add(backgroundPanel);
        mainFrame.setVisible(true);

        //adding student records
        submitStudentRecord.addActionListener(e -> {
            
            String studentName, studentAddress, studentGpa;
            Double studentGPAdouble;

            studentName = studentNameField.getText();
            studentAddress = studentAddressField.getText();
            studentGpa = studentGPAField.getText();

            if (studentName.isEmpty() || studentAddress.isEmpty() || studentGpa.isEmpty()) {
                numStudents.setText("Fields cannot be blank!");

            } else {
                studentGPAdouble = Double.valueOf(studentGpa);

                if (studentGPAdouble > 4.0 || studentGPAdouble < 0) {
                    numStudents.setText("GPA cannot be greater than 4.0 or less than 0.0!");
                } else {
                    //sort with every new addition
                    Student newStudent = new Student(studentName, studentAddress, studentGPAdouble);
                    studentList.add(newStudent);
                    studentList.sort(new NameComparator());
    
                    studentNameField.setText("");
                    studentAddressField.setText("");
                    studentGPAField.setText("");

                    numStudents.setText("Student added!\n" + "Number of students: " + studentList.size());
                }
            }
        });

        //exit
        exitButton.addActionListener(e -> {
            System.exit(0);
        });

        //list students
        showStudents.addActionListener(e -> {
            
            if (studentList.size() == 0) {
                numStudents.setText("No students enrolled.");
            } else {
                String studentListOutput = listStudents(studentList);
                numStudents.setText(studentListOutput);
            }
        });

        //export to file
        exportStudents.addActionListener(e -> {
            
            String studentListOutput = listStudents(studentList);

            try {
                File studentRecordFile = new File("StudentRecordv2.txt");
                
                if (studentRecordFile.createNewFile()) {
                    System.out.println("File created");
                } else {
                    System.out.println("File already exists");
                }
            }
            catch (IOException err) {
                System.out.println("Error creating file");
                err.printStackTrace();
            }

            try {

                LocalDate date = LocalDate.now();

                FileWriter fileWriter = new FileWriter("StudentRecordv2.txt");
                fileWriter.write("---Student Enrollment Record---\n");
                fileWriter.append("---Date of Export: " + date + "---\n\n");
                fileWriter.append(studentListOutput);
                fileWriter.close();
                System.out.println("Wrote to file");
            } catch (IOException err) {
                System.out.println("Error writing to file!");
                err.printStackTrace();
            } finally {
                numStudents.setText("Export operation complete!");
            }
        });
    }

    //create string of all student info
    public static String listStudents(LinkedList<Student> studentList) {

        String studentListOutput = "";

        for (int i = 0; i < studentList.size(); i++) {
            studentListOutput = studentListOutput + studentList.get(i).getStudentName() + ", " + studentList.get(i).getStudentAddress() + ", " + studentList.get(i).getStudentGPA() + "\n";
        }

        return studentListOutput;
    }
}