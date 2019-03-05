public class Student {
    private String name;
    private String address;
    private double gpa;

    public Student() {}
    public Student(String sName, String sAddess, double sGPA) {
        this.name = sName;
        this.address = sAddess;
        this.gpa = sGPA;
    }

    public String getStudentName() {
        return this.name;
    }

    public String getStudentAddress() {
        return this.address;
    }

    public double getStudentGPA() {
        return this.gpa;
    }

    public String editStudentName(String newName) {
        this.name = newName;
        return "New Name: " + this.name;
    }

    public String editStudentAddress(String newAddress) {
        this.address = newAddress;
        return "New Address: " + this.address;
    }

    public String editStudentGPA(double newGPA) {
        this.gpa = newGPA;
        return "New GPA: " + this.gpa;
    }
}
