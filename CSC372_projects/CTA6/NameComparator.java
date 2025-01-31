package CSC372_projects.CTA6;

import java.util.Comparator;

/*This class is the Comparator that compares student names.  Comparator requires overriding the compare() method to enable comparison between two objects.  This comparison method ignores name case and returns 0 if the two student names are equal, a negative value if student1 is < student2, and a positive if student1 > student2.  The values are the calculated "difference" between the objects compared (e.g. Amy.compare(John) = -9 because A is 9 chars ahead of J)*/

public class NameComparator implements Comparator<Student> {
    
    @Override
    public int compare(Student student1, Student student2) {
        return student1.getStudentName().compareToIgnoreCase(student2.getStudentName());
    }
}