import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class main {
    // Insertion sorting method
    /*
    * Loops through the array and compares the student's name with .compareToIgnoreCase
    * index j & j - 1
    * if student's name at index j is first than the student's name at index j - 1 then switch the students
    * repeat until loop has been completed.
    */
    public static ArrayList<Student> sortByName(ArrayList<Student> stuList) {
        int i;
        int j;
        // initial loop
        for (i = 1; i < stuList.size(); i++) {
            j = i;
            // loop to compare students
            while (j > 0 && stuList.get(j).getStudentName().compareToIgnoreCase(stuList.get(j - 1).getStudentName()) < 0) {
                Student sortedItem = stuList.get(j);
                stuList.set(j, stuList.get(j - 1));
                stuList.set(j - 1, sortedItem);
                j--;
            }
        }
        // return the sorted list
        return stuList;
    }

    // Recursion function
    public static ArrayList<Student> getStudents(int studentSize, ArrayList<Student> stuList) {
        /*
        * As long as the studentSize is not zero, the user will keep adding a new student
        * Student size is determined by the user before starting
        */
        if (studentSize == 0) {
            return stuList;
        } else {
            Scanner scnr = new Scanner(System.in);
            double stuGPA;
            // Gets the information of the new student to be added
            System.out.println("New Student Name:");
            String stuName = scnr.nextLine();
            System.out.println("New Student Address:");
            String stuAddress = scnr.nextLine();
            System.out.println("New Student GPA:");
            // Makes sure the next input is a double
            while(!scnr.hasNextDouble()) {
                System.out.println("Please enter a valid number. Ex: 3.2");
                scnr.next();
            }
            stuGPA = scnr.nextDouble();
            // Adds the new student to the list
            Student newStudent = new Student(stuName, stuAddress, stuGPA);
            stuList.add(newStudent);
            studentSize--;
            getStudents(studentSize, stuList);
            return stuList;
        }
    }
    // Prints the list to the console.
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
                // Adds a new student to the list using the getStudent() method default studentSize = 1
                students = getStudents(1, students);
                System.out.println("Student added!");
            }
            else if (action == 2) {
                // Edits the a specific Student
                students = sortByName(students);
                int studentId;
                boolean isEditing = true;
                int editAction;
                // Display the available students to edit
                printList(students);
                System.out.println("Please enter student number");
                while(!scnr.hasNextInt()) {
                    System.out.println("Please enter a valid number");
                    scnr.next();
                }
                studentId = scnr.nextInt();
                // check to see if the option entered is valid cannot be 0 or lower and cannot be a higher number than the size of the list
                if (studentId > students.size() || studentId < 0) {
                    // if student is not available the next loop will not run
                    System.out.println("Invalid option...");
                    isEditing = false;
                }
                // edit student loop
                while(isEditing) {
                    Student selectedStudent = students.get(studentId - 1);
                    // edit loop
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
                        // Edit the student name
                        System.out.println("Enter a new Name");
                        String newName = scnr.next();
                        String oldName = selectedStudent.getStudentName();
                        System.out.println("Old Name: " + oldName);
                        selectedStudent.editStudentName(newName);
                    } else if (editAction == 2) {
                        // Edit the student address
                        System.out.println("Enter a new Address");
                        String newAddess = scnr.next();
                        String oldAddress = selectedStudent.getStudentAddress();
                        System.out.println("Old Address: " + oldAddress);
                        selectedStudent.editStudentAddress(newAddess);
                    } else if (editAction == 3){
                        // edit the student GPA
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
                        // exits back to the main loop
                        isEditing = false;
                    } else {
                        // loop continues
                        System.out.println("Invalid option...");
                    }
                }
            } else if (action == 3) {
                // removes a student from the list
                students = sortByName(students);
                int studentId;
                printList(students);
                System.out.println("Please enter student number");
                while(!scnr.hasNextInt()) {
                    System.out.println("Please enter a valid number");
                    scnr.next();
                }
                studentId = scnr.nextInt();
                // check to see if student is available, number must be equal or less than the list size and greater than 0
                if (studentId < 0 || studentId > students.size()) {
                    // if the student index is in range, then the students gets removed from the list
                    System.out.println("Deleting: " + students.get(studentId - 1).getDetails());
                    students.remove(studentId - 1);
                } else {
                    System.out.println("Invalid option... (Nothing happened)");
                }
            } else if (action == 4) {
                int i;
                boolean writing = true;
                int tries = 0;
                students = sortByName(students);
                while (writing && tries != 5) {
                    try {
                        System.out.println("Where in your computer would you like the list saved? (must not end with a / ex: c:/temp)");
                        String dirLocation = scnr.next();
                        FileWriter fileToWrite = new FileWriter(dirLocation + "/students.txt");
                        PrintWriter printToFile = new PrintWriter(fileToWrite);
                        printToFile.println("Student List");
                        printToFile.println("---------------");
                        for (i = 0; i < students.size(); i++) {
                            printToFile.println(students.get(i).getDetails());
                        }
                        printToFile.close();
                        writing = false;
                    } catch (IOException e) {
                        System.out.println("Cannot print to that location");
                    }
                    // You have 5 tries to print your list
                    tries++;
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
