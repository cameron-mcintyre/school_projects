package CSC372_projects.CTA6;

//Not a lot to comment about in this class - this class consists of the attributes of a Student and some getters, setters, and constructors.
public class Student {
    
    private int rollno;
    private String name;
    private String address;

    public int getStudentRollno() {
        return rollno;
    }

    public String getStudentName() {
        return name;
    }

    public String getStudentAddress() {
        return address;
    }

    public void setStudentRollno(int rollno) {
        this.rollno = rollno;
    }

    public void setStudentName(String name) {
        this.name = name;
    }

    public void setStudentAddress(String address) {
        this.address = address;
    }

    public Student() {
        this.name = "";
        this.rollno = -1;
        this.address = "N/A";
    }

    public Student(int rollno, String name, String address) {
        this.rollno = rollno;
        this.name = name;
        this.address = address;
    }
}