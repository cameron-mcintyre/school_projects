package CSC372_projects.PortfolioProject;

import java.util.Comparator;

//compare names
public class NameComparator implements Comparator<Student> {
    
    @Override
    public int compare(Student student1, Student student2) {
        return student1.getStudentName().compareToIgnoreCase(student2.getStudentName());
    }
}