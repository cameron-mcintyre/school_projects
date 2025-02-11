package CSC372_projects.CTA6;

import java.util.Comparator;

/*This class is the Comparator that compares student ID numbers (rollno).  Comparator requires overriding the compare() method to enable comparison between two objects.  This comparison method returns 0 if the two student IDs are equal, a negative value if student1 is < student2, and a positive if student1 > student2.  The values are the calculated "difference" between the objects IDs.*/

public class RollnoComparator implements Comparator<Student> {
    
    @Override
    public int compare(Student student1, Student student2) {
        return Integer.compare(student1.getStudentRollno(), student2.getStudentRollno());
    }
}