package CSC400.Module8PP;

public class Person {
    
    //Private variables for a Person object.
    private     String      firstName;
    private     String      lastName;
    private     Integer         age;

    /**
    * Purpose: Default constructor method for a Person.
    * Inputs: None.
    * Outputs: None.
    **/
    public Person(){
        this.firstName = "";
        this.lastName = "";
        this.age = -1;
    }

    /**
    * Purpose: Parameterized constructor for a Person.
    * Inputs: String firstname, lastname; int age
    * Outputs: None.
    **/
    public Person(String first, String last, int age){
        this.firstName = first;
        this.lastName = last;
        this.age = age;
    }

    /**
    * Purpose: Setters for the individual parameters for a Person.
    * Inputs: String firstname or lastname; int age.
    * Outputs: None.
    **/
    public void setFirstName(String first){this.firstName = first;}
    public void setLastName(String last){this.lastName = last;}
    public void setAge(Integer age){this.age = age;}

    /**
    * Purpose: Getters for individual parameters for a Person.
    * Inputs: None.
    * Outputs: Returns the parameter named in the method title.
    **/
    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}
    public Integer getAge(){return age;}

    /**
    * Purpose: Create a formatted string for each Person with their info.
    * Inputs: None.
    * Outputs: A string containing firstname, lastname, and age.
    **/
    public String printPersonInfo(){
        return "\nFirst:" + firstName + "\tLast: " + lastName + "\tAge:" + age;
    }
}