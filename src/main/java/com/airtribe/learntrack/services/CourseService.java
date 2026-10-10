package com.airtribe.learntrack.services;

import java.util.ArrayList;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exceptions.EntityNotFoundException;
import com.airtribe.learntrack.exceptions.InvalidDataException;
import com.airtribe.learntrack.repositories.CourseRepository;
import com.airtribe.learntrack.util.Validator;

public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService() {
        courseRepository = new CourseRepository();
    }

    public Course addCourse(String name, String descrption, int durationInWeeks) throws InvalidDataException {
        validateData(name, descrption, durationInWeeks);
        return courseRepository.addNewCourse(name, descrption, durationInWeeks);
    }

    public ArrayList<Course> getAllCourses() {
        return courseRepository.getAll();
    }

    public Course searchCourse(int courseId) throws EntityNotFoundException {
        return courseRepository.searchCourseById(courseId);
    }

    public Course updateCourseActiveStatus(int courseId, boolean isActive) throws EntityNotFoundException {
        var course = courseRepository.searchCourseById(courseId);
        course.setActive(isActive);
        course = courseRepository.updateCourse(courseId, course);
        return course;
    }

    public void validateData(String name, String description, int durationInWeeks) throws InvalidDataException {
        Validator.isNotNullOrEmpty(name);
        Validator.isNotNullOrEmpty(description);
        Validator.isGreaterThanZero(durationInWeeks);

    }

}
