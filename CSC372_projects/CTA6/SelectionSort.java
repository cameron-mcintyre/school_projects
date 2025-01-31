package CSC372_projects.CTA6;

import java.util.ArrayList;
import java.util.Comparator;

//SelectionSort contains two methods - one to sort by student name, and one to sort by student ID.
public class SelectionSort {
    
    /*This method sorts by student name.  It contains two For loops that iterate through the entire array for each element in the array.  A temporary variable is used to store the object if it has been found larger than another item in the array.
    The MainGUI method passes a name comparator to this method, so the method knows to call on NameComparator to do the comparison.  For analysis, a console output for each comparison has been added.*/
    public static ArrayList<Student> selectionSortName(ArrayList<Student> studentsList, Comparator<Student> comparator)  {
        int i;
        int j;
        int minStudent;
        Student tempStudent;

        for(i = 0; i < studentsList.size(); i++){
            minStudent = i;
            
            for (j = i + 1; j < studentsList.size(); j++) {

                int comparisonResult = comparator.compare(studentsList.get(minStudent), studentsList.get(j));
                System.out.println("Comparing " + studentsList.get(minStudent).getStudentName() + " with " + studentsList.get(j).getStudentName() + " > " + comparisonResult);

                if (comparisonResult > 0) {
                    minStudent = j;
                }
            }
            tempStudent = studentsList.get(i);
            studentsList.set(i, studentsList.get(minStudent));
            System.out.println("Smallest value = " + studentsList.get(minStudent).getStudentName() + "\n");
            studentsList.set(minStudent, tempStudent);
        }
        return studentsList;
    }

    /*This method sorts by student ID (rollno).  It is basically a copy paste of the first sort method, but is passed the appropriate comparator by the MainGUI method.*/

    public static ArrayList<Student> selectionSortRollno(ArrayList<Student> studentsList, Comparator<Student> comparator){
        int i;
        int j;
        int minStudent;
        Student tempStudent;

        for(i = 0; i < studentsList.size(); i++){
            minStudent = i;
            
            for (j = i + 1; j < studentsList.size(); j++) {

                int comparisonResult = comparator.compare(studentsList.get(minStudent), studentsList.get(j));
                System.out.println("Comparing " + studentsList.get(minStudent).getStudentRollno() + " with " + studentsList.get(j).getStudentRollno() + " > " + comparisonResult);
                
                if (comparisonResult > 0) {
                    minStudent = j;
                }
            }
            tempStudent = studentsList.get(i);
            studentsList.set(i, studentsList.get(minStudent));
            System.out.println("Smallest value = " + studentsList.get(minStudent).getStudentRollno() + "\n");
            studentsList.set(minStudent, tempStudent);  
        }
        return studentsList;
    }
}