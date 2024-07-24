import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
 private String name;
 private int rollNumber;
 private double grade;

 public Student(String name, int rollNumber, double grade) {
 this.name = name;
 this.rollNumber = rollNumber;
 this.grade = grade;
 }

 // Accessor methods
 public String getName() {
 return name;
 }

 public int getRollNumber() {
 return rollNumber;
 }

 public double getGrade() {
 return grade;
 }

 // Mutator methods
 public void setName(String name) {
 this.name = name;
 }

 public void setRollNumber(int rollNumber) {
 this.rollNumber = rollNumber;
 }

 public void setGrade(double grade) {
 this.grade = grade;
 }

 @Override
 public String toString() {
 return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
 }
}

class StudentManagementSystem {
 private List<Student> students;

 public StudentManagementSystem() {
 students = new ArrayList<>();
 }

 public void addStudent(Student student) {
 students.add(student);
 }

 public void removeStudent(int rollNumber) {
 students.removeIf(s -> s.getRollNumber() == rollNumber);
 }

 public Student searchStudent(int rollNumber) {
 for (Student student : students) {
 if (student.getRollNumber() == rollNumber) {
 return student;
 }
 }
 return null; // Not found
 }

 public void displayAllStudents() {
 for (Student student : students) {
 System.out.println(student);
 }
 }

 // Other methods (e.g., read/write to file or database) can be added here
}

public class Main {
 public static void main(String[] args) {
 Scanner scanner = new Scanner(System.in);
 StudentManagementSystem system = new StudentManagementSystem();

 while (true) {
 System.out.println("Options:");
 System.out.println("1. Add student");
 System.out.println("2. Remove student");
 System.out.println("3. Search student");
 System.out.println("4. Display all students");
 System.out.println("5. Exit");
 System.out.print("Enter your choice: ");
 int choice = scanner.nextInt();
 scanner.nextLine(); // Consume newline

 switch (choice) {
 case 1:
 System.out.print("Enter name: ");
 String name = scanner.nextLine();
 System.out.print("Enter roll number: ");
 int rollNumber = scanner.nextInt();
 System.out.print("Enter grade: ");
 double grade = scanner.nextDouble();
 system.addStudent(new Student(name, rollNumber, grade));
 break;
 case 2:
 System.out.print("Enter roll number to remove: ");
 int rollToRemove = scanner.nextInt();
 system.removeStudent(rollToRemove);
 break;
 case 3:
 System.out.print("Enter roll number to search: ");
 int rollToSearch = scanner.nextInt();
 Student foundStudent = system.searchStudent(rollToSearch);
 if (foundStudent != null) {
 System.out.println("Found student: " + foundStudent);
 } else {
 System.out.println("Student not found.");
 }
 break;
 case 4:
 system.displayAllStudents();
 break;
 case 5:
 System.out.println("Exiting. Goodbye!");
 return;
 default:
 System.out.println("Invalid choice. Try again");
 break;
 }
 }
 }
}