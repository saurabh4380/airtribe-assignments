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

    public Course AddCourse(String name, String descrption, int durationInWeeks) throws InvalidDataException {
        validateData(name, descrption, durationInWeeks);
        return courseRepository.addNewCourse(name, descrption, durationInWeeks);
    }

    public ArrayList<Course> GetAllCourses() {
        return courseRepository.GetAll();
    }

    public Course searchCourse(int courseId) throws EntityNotFoundException {
        return courseRepository.searchCourseById(courseId);
    }

    public Course UpdateCourseActiveStatus(int courseId, boolean isActive) throws EntityNotFoundException {
        var course = courseRepository.searchCourseById(courseId);
        course.setActive(isActive);
        course = courseRepository.UpdateCourse(courseId, course);
        return course;
    }

    public void validateData(String name, String description, int durationInWeeks) throws InvalidDataException {
        Validator.IsNotNullOrEmpty(name);
        Validator.IsNotNullOrEmpty(description);
        Validator.IsGreaterThanZero(durationInWeeks);

    }

}
