/*Student data are private fields in a student class including:

String name
String address
double GPA */

package CSC372_projects.PortfolioProject;

public class Student {
    
    private String studentName;
    private String studentAddress;
    private double studentGPA;

    //Default constructor, added just in case.
    public Student(){

        studentName = "N/A";
        studentAddress = "N/A";
        studentGPA = 0.0;
    }

    //Specific constructor that properly constructs a student.
    public Student(String studentName, String studentAddress, Double studentGPA){

        this.studentName = studentName;
        this.studentAddress = studentAddress;
        this.studentGPA = studentGPA;
    }

    //Getters and setters for the student attributes.
    public void SetStudentName(String studentName){
        this.studentName = studentName;
    }

    public void SetStudentAddress(String studentAddress){
        this.studentAddress = studentAddress;
    }

    public void SetStudentGPA(Double studentGPA){
        this.studentGPA = studentGPA;
    }

    public String GetStudentName(){
        return studentName;
    }

    public String GetStudentAddress(){
        return studentAddress;
    }

    public Double GetStudentGPA(){
        return studentGPA;
    }

}
