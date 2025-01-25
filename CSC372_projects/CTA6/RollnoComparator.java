package CSC372_projects.CTA6;

import java.util.Comparator;

//This is the student ID comparator.  It's basically the same as the name comparator.
public class RollnoComparator implements Comparator<Student> {
    
    @Override
    public int compare(Student student1, Student student2) {
        return Integer.compare(student1.getStudentRollno(), student2.getStudentRollno());
    }
}