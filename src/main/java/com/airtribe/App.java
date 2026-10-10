package com.airtribe;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import com.airtribe.learntrack.constants.MenuOptions;
import com.airtribe.learntrack.services.CourseService;
import com.airtribe.learntrack.services.EnrollmentService;
import com.airtribe.learntrack.services.StudentService;
import com.airtribe.learntrack.ui.CourseOperations;
import com.airtribe.learntrack.ui.EnrollmentOperations;
import com.airtribe.learntrack.ui.StudentOperations;

public class App {
    public static void main(String[] args) throws Exception {
        var scanner = new Scanner(System.in);

        var enrollmentOperations = new EnrollmentOperations(new EnrollmentService(), scanner);
        var courseOperations = new CourseOperations(new CourseService(), scanner);
        var studentOperations = new StudentOperations(new StudentService(), scanner);

        var isRunning = true;

        while (isRunning) {
            displayMenuOptions();

            try {
                var selectedMenuItemId = scanner.nextInt();
                scanner.nextLine();
                var selectedMenu = MenuOptions.ALL_OPTIONS.get(selectedMenuItemId);

                if (selectedMenu != null) {
                    switch (selectedMenu) {
                        case MenuOptions.ADD_NEW_STUDENT -> studentOperations.handleAddStudent();
                        case MenuOptions.VIEW_ALL_STUDENTS -> studentOperations.handleViewAllStudent();
                        case MenuOptions.SEARCH_STUDENT_BY_ID -> studentOperations.handleSearchStudentById();
                        case MenuOptions.DEACTIVATE_A_STUDENT -> studentOperations.handleDeactivateStudent();
                        case MenuOptions.ADD_NEW_COURSE -> courseOperations.handleAddNewCourse();
                        case MenuOptions.VIEW_ALL_COURSES -> courseOperations.handleViewAllCourses();
                        case MenuOptions.ACTIVATE_OR_DEACTIVATE_A_COURSE -> courseOperations.handleActiveStatusChange();
                        case MenuOptions.ENROLL_A_STUDENT_IN_COURSE -> enrollmentOperations.handleEnrollAStudentInCourse();
                        case MenuOptions.VIEW_ENROLLMENTS_FOR_STUDENT -> enrollmentOperations.handleViewEnrollmentsForAStudent();
                        case MenuOptions.UPDATE_ENROLLMENT_STATUS -> enrollmentOperations.handleUpdateEnrollmentStatus();
                        case MenuOptions.EXIT -> isRunning = false;
                    }
                } else {
                    System.err.println("Enter a valid option");
                }
            } catch (InputMismatchException ex) {
                System.err.println("Enter a valid option");
                scanner.nextLine();
            }

        }

        scanner.close();

    }

    static void displayMenuOptions() {
        System.out.println(System.lineSeparator() + "=========== Main Menu ===========");
        System.out.println("Select an option by ID: ");
        var sortedEntries = MenuOptions.ALL_OPTIONS.entrySet()
                                             .stream()
                                             .sorted(Map.Entry.comparingByKey())
                                             .toList();
        for (var entry : sortedEntries) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
    }
}
