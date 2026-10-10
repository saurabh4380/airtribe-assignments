package com.airtribe.learntrack.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exceptions.EntityNotFoundException;
import com.airtribe.learntrack.util.IdGenerator;

public class EnrollmentRepository {
    public static ArrayList<Enrollment> enrollments = new ArrayList<>();

    public Enrollment addEnrollment(int studentId, int courseId, Date enrollmentDate,
                                    EnrollmentStatus enrollmentStatus) {
        var id = IdGenerator.getNextEnrollmentId();
        var enrollment = new Enrollment(id, studentId, courseId, enrollmentDate, enrollmentStatus);
        enrollments.add(enrollment);
        return enrollment;
    }

    public Optional<Enrollment> getEnrollmentById(int enrollmentId) {
        return enrollments.stream().filter(x -> x != null && x.getId() == enrollmentId).findFirst();
    }

    public List<Enrollment> getEnrollmentByStudentId(int studentId) {
        return enrollments.stream().filter(x -> x != null && x.getStudentId() == studentId).toList();
    }

    public Enrollment updateEnrollment(int id, Enrollment enrollment) throws EntityNotFoundException {

        var enrollmentFromDb = enrollments.stream().filter(x -> x != null && x.getId() == id).findFirst();

        if (enrollmentFromDb.isEmpty()) {
            throw new EntityNotFoundException(String.format("Enrollment with Id: %d not found", id));
        }

        var idx = enrollments.indexOf(enrollmentFromDb.get());

        enrollments.set(idx, enrollment);

        return enrollments.get(idx);
    }

}
