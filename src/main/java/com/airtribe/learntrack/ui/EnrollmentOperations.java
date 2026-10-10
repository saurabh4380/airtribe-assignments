package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.dtos.EnrollmentDto;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exceptions.EntityNotFoundException;
import com.airtribe.learntrack.services.EnrollmentService;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EnrollmentOperations {

    private final EnrollmentService enrollmentService;
    private final Scanner scanner;

    public EnrollmentOperations(EnrollmentService enrollmentService, Scanner scanner){
        this.enrollmentService = enrollmentService;
        this.scanner = scanner;
    }

    public void handleUpdateEnrollmentStatus() {

        try {
            System.out.println("Enter Enrollment Id");
            var enrollmentId = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Select an enrollment status option");

            var values = EnrollmentStatus.values();

            for (int i = 0; i < values.length; i++) {
                System.out.println(i + " " + values[i].toString());

            }
            var ordinal = scanner.nextInt();
            scanner.nextLine();

            if (ordinal < 0 || ordinal >= values.length) {
                throw new IllegalArgumentException("Invalid option: " + ordinal);
            }
            var status = values[ordinal];
            enrollmentService.updateEnrollment(enrollmentId, status);
        } catch (EntityNotFoundException | IllegalArgumentException | InputMismatchException e) {
            System.err.println(e.getMessage());

        }
    }

    public void handleViewEnrollmentsForAStudent() {
        try {
            System.out.println("Enter Student Id");
            var studentId = scanner.nextInt();
            scanner.nextLine();
            var enrollments = enrollmentService.getEnrollmentByStudentId(studentId);

            displayEnrollments(enrollments);

        } catch (EntityNotFoundException e) {
            System.err.println("Student not found");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    public void displayEnrollments(ArrayList<EnrollmentDto> enrollments) {
        System.out.println("ID | Student Name | Course Name | Enrollment Date | Status");

        if (enrollments.size() > 0) {
            for (var enrollment : enrollments) {
                System.out.println(enrollment.getId() + " | " + enrollment.getStudent().getDisplayName() + " | "
                        + enrollment.getCourse().getCourseName() + " | " + enrollment.getEnrollmentDate() + " | "
                        + enrollment.getStatus());

            }
        } else {
            System.err.println("No enrollments present");
        }
    }

    public void handleEnrollAStudentInCourse() {
        try {
            System.out.println("Enter student Id");
            var studentId = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter Course Id");
            var courseId = scanner.nextInt();
            scanner.nextLine();

            enrollmentService.addNewEnrollment(studentId, courseId);

        } catch (EntityNotFoundException | IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

}
