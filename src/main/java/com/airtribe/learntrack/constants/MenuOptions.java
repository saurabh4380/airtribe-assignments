package com.airtribe.learntrack.constants;

import java.util.Map;

public class MenuOptions {

    public static final String ADD_NEW_STUDENT = "Add new student";
    public static final String VIEW_ALL_STUDENTS = "View all students";
    public static final String SEARCH_STUDENT_BY_ID = "Search student by ID";
    public static final String DEACTIVATE_A_STUDENT = "Deactivate a student";
    public static final String ADD_NEW_COURSE = "Add new course";
    public static final String VIEW_ALL_COURSES = "View all courses";
    public static final String ACTIVATE_OR_DEACTIVATE_A_COURSE = "Activate/Deactivate a course";
    public static final String ENROLL_A_STUDENT_IN_COURSE = "Enroll a student in a course";
    public static final String VIEW_ENROLLMENTS_FOR_STUDENT = "View enrollments for a student";
    public static final String UPDATE_ENROLLMENT_STATUS = "Mark enrollment as completed/cancelled";
    public static final String EXIT = "EXIT";

    public static final Map<Integer, String> ALL_OPTIONS = Map.ofEntries(Map.entry(1, ADD_NEW_STUDENT),
            Map.entry(2, VIEW_ALL_STUDENTS), Map.entry(3, SEARCH_STUDENT_BY_ID), Map.entry(4, DEACTIVATE_A_STUDENT),
            Map.entry(5, ADD_NEW_COURSE), Map.entry(6, VIEW_ALL_COURSES), Map.entry(7, ACTIVATE_OR_DEACTIVATE_A_COURSE),
            Map.entry(8, ENROLL_A_STUDENT_IN_COURSE), Map.entry(9, VIEW_ENROLLMENTS_FOR_STUDENT),
            Map.entry(10, UPDATE_ENROLLMENT_STATUS), Map.entry(99, EXIT));

}
