import java.util.ArrayList;
import java.util.Scanner;

public class main {
    // Insertion sorting method
    public static ArrayList<Student> sortByName(ArrayList<Student> stuList) {
        int i;
        int j;
        for (i = 1; i < stuList.size(); i++) {
            j = i;
            while (j > 0 && stuList.get(j).getStudentName().compareToIgnoreCase(stuList.get(j - 1).getStudentName()) < 0) {
                Student sortedItem = stuList.get(j);
                stuList.set(j, stuList.get(j - 1));
                stuList.set(j - 1, sortedItem);
                j--;
            }
        }
        return stuList;
    }

    // Recursion function
    public static ArrayList<Student> getStudents(int studentSize, ArrayList<Student> stuList) {

        if (studentSize == 0) {
            return stuList;
        } else {
            Scanner scnr = new Scanner(System.in);
            double stuGPA;

            System.out.println("New Student Name:");
            String stuName = scnr.next();
            System.out.println("New Student Address:");
            String stuAddress = scnr.next();
            System.out.println("New Student GPA:");
            while(!scnr.hasNextDouble()) {
                System.out.println("Please enter a valid number. Ex: 3.2");
                scnr.next();
            }
            stuGPA = scnr.nextDouble();

            Student newStudent = new Student(stuName, stuAddress, stuGPA);
            stuList.add(newStudent);
            studentSize--;
            getStudents(studentSize, stuList);
            return stuList;
        }
    }

    public static void printList(ArrayList<Student> stuList) {
        int i;
        for (i = 0; i < stuList.size(); i++) {
            System.out.println(i+1 + ") " + stuList.get(i).getStudentName());
        }
        System.out.println("--------------------------");
    }

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        // Initiate Student list
        ArrayList<Student> students = new ArrayList<>();
        // while loop boolean
        boolean start = true;
        // Initial list size
        int listSize;

        System.out.println("How many Students do you have? (You can add a new student if you have a new student)");
        while(!scnr.hasNextInt()) {
            System.out.println("Sorry, please enter a whole number");
            scnr.next();
        }
        listSize = scnr.nextInt();
        int action;

        // Main loop
        while(start) {
            if (students.size() <= 0) {
                students = getStudents(listSize, students);
            }
            // Options
            System.out.println("What would you like to do next?");
            System.out.println("1) Add a new Student");
            System.out.println("2) Edit a Student");
            System.out.println("3) Remove a Student");
            System.out.println("4) Print Student List");
            System.out.println("5) Exit");
            while(!scnr.hasNextInt()) {
                System.out.println("Please enter a valid choice. ex. enter '2' to edit student");
                scnr.next();
            }
            action = scnr.nextInt();
            if (action == 1) {
                students = getStudents(1, students);
                System.out.println("Student added!");
            }
            else if (action == 2) {
                students = sortByName(students);
                int studentId;
                boolean isEditing = true;
                int editAction;
                printList(students);
                System.out.println("Please enter student number");
                while(!scnr.hasNextInt()) {
                    System.out.println("Please enter a valid number");
                    scnr.next();
                }
                studentId = scnr.nextInt();
                if (studentId > students.size() || studentId < 0) {
                    System.out.println("Invalid option...");
                    isEditing = false;
                }
                while(isEditing) {
                    Student selectedStudent = students.get(studentId - 1);
                    System.out.println("What would you like to change?");
                    System.out.println("1) Name");
                    System.out.println("2) Address");
                    System.out.println("3) GPA");
                    System.out.println("4) Go Back");
                    while(!scnr.hasNextInt()) {
                        System.out.println("Please enter a valid number");
                        scnr.next();
                    }
                    editAction = scnr.nextInt();
                    if (editAction == 1) {
                        System.out.println("Enter a new Name");
                        String newName = scnr.next();
                        String oldName = selectedStudent.getStudentName();
                        System.out.println("Old Name: " + oldName);
                        selectedStudent.editStudentName(newName);
                    } else if (editAction == 2) {
                        System.out.println("Enter a new Address");
                        String newAddess = scnr.next();
                        String oldAddress = selectedStudent.getStudentAddress();
                        System.out.println("Old Address: " + oldAddress);
                        selectedStudent.editStudentAddress(newAddess);
                    } else if (editAction == 3){
                        System.out.println("Enter a new GPA");
                        while(!scnr.hasNextDouble()) {
                            System.out.println("Please enter a valid number. Ex: 3.2");
                            scnr.next();
                        }
                        double newGPA = scnr.nextDouble();
                        double oldGPA = selectedStudent.getStudentGPA();
                        System.out.println("Old GPA: " + oldGPA);
                        selectedStudent.editStudentGPA(newGPA);
                    } else if (editAction == 4) {
                        isEditing = false;
                    } else {
                        System.out.println("Invalid option...");
                    }
                }
            } else if (action == 3) {
                students = sortByName(students);
                int studentId;
                printList(students);
                System.out.println("Please enter student number");
                while(!scnr.hasNextInt()) {
                    System.out.println("Please enter a valid number");
                    scnr.next();
                }
                studentId = scnr.nextInt();
                if (studentId < 0 || studentId > students.size()) {
                    System.out.println("Deleting: " + students.get(studentId - 1).getDetails());
                    students.remove(studentId - 1);
                } else {
                    System.out.println("Invalid option... (Nothing happened)");
                }
            } else if (action == 4) {
                int i;
                for (i = 0; i < students.size(); i++) {
                    System.out.println(students.get(i).getDetails());
                }
            } else if (action == 5) {
                System.out.println("Goodbye!");
                start = false;
            } else {
                System.out.println("Invalid option...");
            }
        }
    }
}
