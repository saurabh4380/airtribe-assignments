package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exceptions.EntityNotFoundException;
import com.airtribe.learntrack.exceptions.InvalidDataException;
import com.airtribe.learntrack.services.CourseService;

import java.util.Scanner;

public class CourseOperations {

    private final CourseService courseService;
    private final Scanner scanner;

    public CourseOperations(CourseService courseService, Scanner scanner) {
        this.courseService = courseService;
        this.scanner = scanner;
    }

    public void handleActiveStatusChange() {
        System.out.println("Enter Course Id");
        var courseId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Course Status");
        var active = scanner.nextBoolean();

        try {
            courseService.updateCourseActiveStatus(courseId, active);
        } catch (EntityNotFoundException e) {
            System.err.println(e.getMessage());

        }
    }

    public void handleViewAllCourses() {

        var courses = courseService.getAllCourses();
        System.out.println("ID | Name | Description | Duration (in weeks) | Active");

        for (var course : courses) {
            displayCourse(course);
        }
    }

    private void displayCourse(Course course) {
        System.out.println(course.getId() + " | " + course.getCourseName() + " | " + course.getDescription() + " | "
                + course.getDurationInWeeks() + " | " + course.isActive());

    }

    public void handleAddNewCourse() {
        System.out.println("Enter Course Name");
        var courseName = scanner.nextLine();
        System.out.println("Enter Description");
        var description = scanner.nextLine();
        System.out.println("Enter Duration (in weeks)");
        var durationInWeeks = scanner.nextInt();

        try {
            courseService.addCourse(courseName, description, durationInWeeks);
        } catch (InvalidDataException e) {
            System.err.println(e.getMessage());
        }

    }
}
