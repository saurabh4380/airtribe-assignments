package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exceptions.EntityNotFoundException;
import com.airtribe.learntrack.exceptions.InvalidDataException;
import com.airtribe.learntrack.services.StudentService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentOperations {

    private final StudentService studentService;
    private final Scanner scanner;

    public StudentOperations(StudentService studentService, Scanner scanner) {
        this.studentService = studentService;
        this.scanner = scanner;
    }
    public void handleAddStudent() {
        System.out.println("Enter First Name");
        var firstName = scanner.nextLine();
        System.out.println("Enter Last Name");
        var lastName = scanner.nextLine();
        System.out.println("Enter Email Address");
        var emailAddress = scanner.nextLine();
        try {
            studentService.addStudent(firstName, lastName, emailAddress);
        } catch (InvalidDataException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void handleViewAllStudent() {

        try {
            var students = studentService.getAllStudents();
            System.out.println("ID | DisplayName | IsActive");

            for (var student : students) {
                displayStudent(student);
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void displayStudent(Student student) {
        System.out.println(student.getId() + " | " + student.getDisplayName() + " | " + student.isActive());
    }

    public void handleSearchStudentById() {

        var tempId = 0;
        try {
            System.out.println("Enter Student Id");
            var studentId = scanner.nextInt();
            tempId = studentId;
            var student = studentService.searchStudent(studentId);
            displayStudent(student);
        } catch (InputMismatchException ex) {
            System.err.println(ex.getMessage());
        } catch (EntityNotFoundException e) {
            System.err.println(String.format("Student with Id : %d does not exist", tempId));
            e.printStackTrace();
        }
    }

    public void handleDeactivateStudent() {

        var tempId = 0;
        try {
            System.out.println("Enter Student Id");
            var studentId = scanner.nextInt();
            tempId = studentId;
            var student = studentService.deactivateStudent(studentId);
            displayStudent(student);
        } catch (InputMismatchException ex) {
            System.err.println(ex.getMessage());
        } catch (EntityNotFoundException e) {
            System.err.println(String.format("Student with Id : %d does not exist", tempId));
        }
    }

}
