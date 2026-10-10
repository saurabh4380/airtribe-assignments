package com.airtribe.learntrack.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

import com.airtribe.learntrack.dtos.EnrollmentDto;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exceptions.EntityNotFoundException;
import com.airtribe.learntrack.repositories.EnrollmentRepository;

public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final StudentService studentService;
    private final CourseService courseService;

    public EnrollmentService() {
        enrollmentRepository = new EnrollmentRepository();
        studentService = new StudentService();
        courseService = new CourseService();
    }

    public Enrollment addNewEnrollment(int studentId, int courseId) throws EntityNotFoundException {

        var student = studentService.searchStudent(studentId);

        var course = courseService.searchCourse(courseId);

        var enrollment = enrollmentRepository.addEnrollment(student.getId(), course.getId(), Date.from(Instant.now()),
                EnrollmentStatus.ACTIVE);

        return enrollment;
    }

    public ArrayList<EnrollmentDto> getEnrollmentByStudentId(int studentId) throws EntityNotFoundException {

        var student = studentService.searchStudent(studentId);

        var enrollments = enrollmentRepository.getEnrollmentByStudentId(student.getId());

        var enrollmentList = new ArrayList<EnrollmentDto>();

        for (var enrollment : enrollments) {
            var enrollmentDto = new EnrollmentDto(enrollment.getId(), enrollment.getStudentId(),
                    enrollment.getCourseId(), enrollment.getEnrollmentDate(), enrollment.getStatus());
            enrollmentDto.setStudent(student);
            var course = courseService.searchCourse(enrollment.getCourseId());
            enrollmentDto.setCourse(course);

            enrollmentList.add(enrollmentDto);
        }

        return enrollmentList;
    }

    public Enrollment updateEnrollment(int enrollmentId, EnrollmentStatus enrollmentStatus)
            throws EntityNotFoundException {
        var enrollmentFromDb = enrollmentRepository.getEnrollmentById(enrollmentId);
        if (enrollmentFromDb.isEmpty()) {
            throw new EntityNotFoundException("");
        }

        var enrollment = enrollmentFromDb.get();

        enrollment.setStatus(enrollmentStatus);

        enrollmentRepository.updateEnrollment(enrollmentId, enrollment);
        return enrollment;
    }
}