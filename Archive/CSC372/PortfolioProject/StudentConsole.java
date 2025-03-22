/*Portfolio Project Version 1
 *This version is a barebones program that exactly honors the assignment requirements.
 */

package CSC372_projects.PortfolioProject;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class StudentConsole {
    
    public static void main(String[] args) {

        //init variables
        int choice = 1;
        String studentName;
        String studentAddress;
        Double studentGPA;
        String studentListOutput = "";
        LinkedList<Student> studentList = new LinkedList<Student>();
        Scanner scnr = new Scanner(System.in);

        System.out.println("Welcome to the student enrollment utility!\n");
        
        //main loop with choices
        while (true) {
            System.out.println("Press 1 to add a student, or 2 to stop adding students and export to a file!");

            //validate choice input
            try {
                choice = scnr.nextInt();
                scnr.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid value.\n");
                scnr.nextLine();
                continue;
            }

            //add students
            if (choice == 1) {
                System.out.println("Enter student name: ");
                studentName = scnr.nextLine();
                System.out.println("Enter student address: ");
                studentAddress = scnr.nextLine();
                System.out.println("Enter student GPA: ");

                //validate for gpa double
                try {
                    studentGPA = scnr.nextDouble();
                    if (studentGPA > 4.0 || studentGPA < 0.0) {
                        System.out.println("Please enter a valid GPA!  Student info not added. ");
                        continue;
                    } else {
                        studentList.add(new Student(studentName, studentAddress, studentGPA));
                        System.out.println("Completed!");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a valid GPA!  Student info not added.");
                    scnr.nextLine();
                    continue;
                }
            
            //break out of loop to export
            } else if (choice == 2) {
                System.out.println("Data entry complete!");
                break;
            } else { //validate for numbers not 1 or 2
                System.out.println("Error: please enter 1 or 2!\n");
                continue;
            }
        }

        //sort using custom comparator
        studentList.mergeSort(new NameComparator());

        //generate string for export in file
        for (int i = 0; i < studentList.size(); i++) {
            studentListOutput = studentListOutput + studentList.get(i).getStudentName() + ", " + studentList.get(i).getStudentAddress() + ", " + studentList.get(i).getStudentGPA() + "\n";
        }
        
        //create file
        try {
            File studentRecordFile = new File("StudentRecordv1.txt");
            
            if (studentRecordFile.createNewFile()) {
                System.out.println("File created");
            } else {
                System.out.println("File already exists");
            }
        }
        catch (IOException err) {
            System.out.println("Error creating file");
        }

        //write to new file
        try {
            
            LocalDate date = LocalDate.now();

            FileWriter fileWriter = new FileWriter("StudentRecordv1.txt");
            fileWriter.write("---Student Enrollment Record---\n");
            fileWriter.append("---Date of Export: " + date + "---\n\n");
            fileWriter.append(studentListOutput);
            fileWriter.close();
            System.out.println("Wrote to file");
        } catch (IOException err) {
            System.out.println("Error writing to file!");
        } finally {
            System.out.println("Export operation complete!");
        }

        scnr.close();
    }
}