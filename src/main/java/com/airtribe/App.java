package com.airtribe;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import com.airtribe.learntrack.constants.MenuOptions;
import com.airtribe.learntrack.dtos.EnrollmentDto;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exceptions.EntityNotFoundException;
import com.airtribe.learntrack.exceptions.InvalidDataException;
import com.airtribe.learntrack.services.CourseService;
import com.airtribe.learntrack.services.EnrollmentService;
import com.airtribe.learntrack.services.StudentService;

public class App {
    public static void main(String[] args) throws Exception {
        var sc = new Scanner(System.in);
        var isRunning = true;

        while (isRunning) {
            displayMenuOptions();

            try {
                var selectedMenuItemId = sc.nextInt();
                sc.nextLine();
                var selectedMenu = MenuOptions.ALL_OPTIONS.get(selectedMenuItemId);

                if (selectedMenu != null) {
                    if (selectedMenu.equals(MenuOptions.ADD_NEW_STUDENT)) {
                        handleAddStudent(sc);
                    } else if (selectedMenu.equals(MenuOptions.VIEW_ALL_STUDENTS)) {
                        handleViewAllStudent(sc);
                    } else if (selectedMenu.equals(MenuOptions.SEARCH_STUDENT_BY_ID)) {
                        handleSearchStudentById(sc);
                    } else if (selectedMenu.equals(MenuOptions.DEACTIVATE_A_STUDENT)) {
                        handleDeactivateStudent(sc);
                    } else if (selectedMenu.equals(MenuOptions.ADD_NEW_COURSE)) {
                        handleAddNewCourse(sc);
                    } else if (selectedMenu.equals(MenuOptions.VIEW_ALL_COURSES)) {
                        handleViewAllCourses(sc);
                    } else if (selectedMenu.equals(MenuOptions.ACTIVATE_OR_DEACTIVATE_A_COURSE)) {
                        handleActiveStatusChange(sc);
                    } else if (selectedMenu.equals(MenuOptions.ENROLL_A_STUDENT_IN_COURSE)) {
                        handleEnrollAStudentInCourse(sc);
                    } else if (selectedMenu.equals(MenuOptions.VIEW_ENROLLMENTS_FOR_STUDENT)) {
                        handleViewEnrollmentsForAStudent(sc);
                    } else if (selectedMenu.equals(MenuOptions.UPDATE_ENROLLMENT_STATUS)) {
                        handleUpdateEnrollmentStatus(sc);
                    } else if (selectedMenu.equals(MenuOptions.EXIT)) {
                        isRunning = false;
                    }
                } else {
                    System.err.println("Enter a valid option");
                }
            } catch (InputMismatchException ex) {
                System.err.println("Enter a valid option");
                sc.nextLine();
            }

        }

        sc.close();

    }

    private static void handleUpdateEnrollmentStatus(Scanner sc) {

        try {
            System.out.println("Enter Enrollment Id");
            var enrollmentId = sc.nextInt();
            sc.nextLine();
            System.out.println("Select an enrollment status option");

            var values = EnrollmentStatus.values();

            for (int i = 0; i < values.length; i++) {
                System.out.println(i + " " + values[i].toString());

            }
            var ordinal = sc.nextInt();
            sc.nextLine();

            if (ordinal < 0 || ordinal >= values.length) {
                throw new IllegalArgumentException("Invalid option: " + ordinal);
            }
            var status = values[ordinal];

            var enrollmentService = new EnrollmentService();
            enrollmentService.UpdateEnrollment(enrollmentId, status);
        } catch (EntityNotFoundException e) {
            System.err.println(e.getMessage());

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void handleViewEnrollmentsForAStudent(Scanner sc) {
        try {
            System.out.println("Enter Student Id");
            var studentId = sc.nextInt();
            sc.nextLine();
            var enrollmentService = new EnrollmentService();
            var enrollments = enrollmentService.GetEnrollmentByStudentId(studentId);

            displayEnrollments(enrollments);

        } catch (EntityNotFoundException e) {
            System.err.println("Student not found");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void displayEnrollments(ArrayList<EnrollmentDto> enrollments) {
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

    private static void handleEnrollAStudentInCourse(Scanner sc) {
        try {
            System.out.println("Enter student Id");
            var studentId = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter Course Id");
            var scourseId = sc.nextInt();
            sc.nextLine();

            var enrollmentService = new EnrollmentService();
            enrollmentService.AddNewEnrollment(studentId, scourseId);

        } catch (EntityNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void handleActiveStatusChange(Scanner sc) {
        System.out.println("Enter Course Id");
        var courseId = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Course Status");
        var active = sc.nextBoolean();

        var courseService = new CourseService();
        try {
            courseService.UpdateCourseActiveStatus(courseId, active);
        } catch (EntityNotFoundException e) {
            System.err.println(e.getMessage());

        }
    }

    private static void handleViewAllCourses(Scanner sc) {

        var courseService = new CourseService();

        var courses = courseService.GetAllCourses();
        System.out.println("ID | Name | Description | Duration (in weeks) | Active");

        for (var course : courses) {
            displayCourse(course);
        }
    }

    private static void displayCourse(Course course) {
        System.out.println(course.getId() + " | " + course.getCourseName() + " | " + course.getDescription() + " | "
                + course.getDurationInWeeks() + " | " + course.isActive());

    }

    private static void handleAddNewCourse(Scanner sc) {
        System.out.println("Enter Course Name");
        var courseName = sc.nextLine();
        System.out.println("Enter Description");
        var description = sc.nextLine();
        System.out.println("Enter Duration (in weeks)");
        var durationInWeeks = sc.nextInt();

        var courseService = new CourseService();
        try {
            courseService.AddCourse(courseName, description, durationInWeeks);
        } catch (InvalidDataException e) {
            System.err.println(e.getMessage());
        }

    }

    private static void handleAddStudent(Scanner sc) {
        System.out.println("Enter First Name");
        var firstName = sc.nextLine();
        System.out.println("Enter Last Name");
        var lastName = sc.nextLine();
        System.out.println("Enter Email Address");
        var emailAddress = sc.nextLine();
        var studentService = new StudentService();
        try {
            studentService.AddStudent(firstName, lastName, emailAddress);
        } catch (InvalidDataException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private static void handleViewAllStudent(Scanner sc) {

        var studentService = new StudentService();
        try {
            var students = studentService.GetAllStudents();
            System.out.println("ID | DisplayName | IsActive");

            for (var student : students) {
                displayStudent(student);
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    private static void displayStudent(Student student) {
        System.out.println(student.getId() + " | " + student.getDisplayName() + " | " + student.isActive());
    }

    private static void handleSearchStudentById(Scanner sc) {

        var tempId = 0;
        try {
            System.out.println("Enter Student Id");
            var studentId = sc.nextInt();
            tempId = studentId;
            var studentService = new StudentService();
            var student = studentService.SearchStudent(studentId);
            displayStudent(student);
        } catch (InputMismatchException ex) {
            System.err.println(ex.getMessage());
        } catch (EntityNotFoundException e) {
            System.err.println(String.format("Student with Id : %d does not exist", tempId));
            e.printStackTrace();
        }
    }

    private static void handleDeactivateStudent(Scanner sc) {

        var tempId = 0;
        try {
            System.out.println("Enter Student Id");
            var studentId = sc.nextInt();
            tempId = studentId;
            var studentService = new StudentService();
            var student = studentService.DeactivateStudent(studentId);
            displayStudent(student);
        } catch (InputMismatchException ex) {
            System.err.println(ex.getMessage());
        } catch (EntityNotFoundException e) {
            System.err.println(String.format("Student with Id : %d does not exist", tempId));
        }
    }

    static void displayMenuOptions() {
        System.out.println(System.lineSeparator() + "=========== Main Menu ===========");
        System.out.println("Select an option by ID: ");
        var entries = MenuOptions.ALL_OPTIONS.entrySet().stream().sorted(Map.Entry.comparingByKey()).toList();
        for (var entry : entries) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
    }
}
