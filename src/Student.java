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

    public String getDetails() {
        return "Name: " + this.name + " | Address: " + this.address + " | GPA: " + this.gpa;
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

    public void editStudentName(String newName) {
        this.name = newName;
        System.out.println("New Name: " + this.name);
    }

    public void editStudentAddress(String newAddress) {
        this.address = newAddress;
        System.out.println("New Address: " + this.address);
    }

    public void editStudentGPA(double newGPA) {
        this.gpa = newGPA;
        System.out.println("New GPA: " + this.gpa);
    }
}
