package com.airtribe.learntrack.dtos;

import java.util.Date;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;

public class EnrollmentDto extends Enrollment {
    public EnrollmentDto(int id, int studentId, int courseId, Date enrollmentDate, EnrollmentStatus status) {
        super(id, studentId, courseId, enrollmentDate, status);
    }

    private Student student;
    private Course course;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}