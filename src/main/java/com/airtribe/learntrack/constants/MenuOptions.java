package com.airtribe.learntrack.constants;

import java.util.Map;

public class MenuOptions {

    public static final String ADD_NEW_STUDENT = "Add new student";
    public static final String VIEW_ALL_STUDENTS = "View all students";
    public static final String SEARCH_STUDENT_BY_ID = "Search student by ID";
    public static final String DEACTIVATE_A_STUDENT = "Deactivate a student";
    public static final String EXIT = "EXIT";

    public static final Map<Integer, String> ALL_OPTIONS = Map.ofEntries(Map.entry(1, ADD_NEW_STUDENT),
            Map.entry(2, VIEW_ALL_STUDENTS), Map.entry(3, SEARCH_STUDENT_BY_ID), Map.entry(4, DEACTIVATE_A_STUDENT),
            Map.entry(9, EXIT));

}
