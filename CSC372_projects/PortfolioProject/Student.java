package CSC372_projects.PortfolioProject;

public class Student {
    
    private String studentName;
    private String studentAddress;
    private double studentGPA;

    //default constructor
    public Student(){

        studentName = "N/A";
        studentAddress = "N/A";
        studentGPA = 0.0;
    }

    //parameterized constructor
    public Student(String studentName, String studentAddress, Double studentGPA){

        this.studentName = studentName;
        this.studentAddress = studentAddress;
        this.studentGPA = studentGPA;
    }

    //getters and setters
    public void setStudentName(String studentName){
        this.studentName = studentName;
    }

    public void setStudentAddress(String studentAddress){
        this.studentAddress = studentAddress;
    }

    public void setStudentGPA(Double studentGPA){
        this.studentGPA = studentGPA;
    }

    public String getStudentName(){
        return studentName;
    }

    public String getStudentAddress(){
        return studentAddress;
    }

    public Double getStudentGPA(){
        return studentGPA;
    }
}