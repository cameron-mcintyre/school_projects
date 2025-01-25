package CSC372_projects.CTA6;

import java.util.Comparator;

//This class is the comparator that compares student names.  I'm not very comfortable with comparators, and I'm not totally sure how this works.  I looked at the Comparator abstract class and see that I have to override the Compare method, but shouldn't I also have to override the Equals method?  Ah well, if it works, it works.
public class NameComparator implements Comparator<Student> {
    
    @Override
    public int compare(Student student1, Student student2) {
        return student1.getStudentName().compareToIgnoreCase(student2.getStudentName());
    }
}