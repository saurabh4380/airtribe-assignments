package com.airtribe.learntrack.repositories;

import java.util.ArrayList;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exceptions.EntityNotFoundException;
import com.airtribe.learntrack.util.IdGenerator;

public class CourseRepository {
    private static final ArrayList<Course> courses = new ArrayList<>();

    public Course addNewCourse(String name, String descrition, int durationInWeeks) {
        var courseId = IdGenerator.getNextCourseId();
        var course = new Course(courseId, name, descrition, durationInWeeks);
        course.setActive(true);
        courses.add(course);
        return course;
    }

    public ArrayList<Course> GetAll() {
        return courses;
    }

    public Course searchCourseById(int courseId) throws EntityNotFoundException {
        var courseFromDb = courses.stream().filter(x -> x != null && x.getId() == courseId).findFirst();

        if (courseFromDb.isEmpty()) {
            throw new EntityNotFoundException(String.format("Course with ID: %d not found", courseId));
        }

        return courseFromDb.get();
    }

    public Course UpdateCourse(int courseId, Course course) throws EntityNotFoundException {

        var courseFromDb = courses.stream().filter(x -> x != null && x.getId() == courseId).findFirst();

        if (courseFromDb.isEmpty()) {
            throw new EntityNotFoundException(String.format("Course with Id: %d not found", courseId));
        }

        var idx = courses.indexOf(courseFromDb.get());

        courses.set(idx, course);

        return courses.get(idx);
    }
}
