package com.airtribe.learntrack.repositories;

import java.util.ArrayList;
import java.util.Optional;

import com.airtribe.learntrack.entity.Student;

public class StudentRepository {

    private static final ArrayList<Student> students = new ArrayList<>();

    public ArrayList<Student> getAllStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public Optional<Student> searchStudentById(int studentId) {
        return students.stream().filter(x -> x != null && x.getId() == studentId).findFirst();
    }

    public Student updateStudent(int studentId, Student student) {
        var studentFromList = students.stream().filter(x -> x != null && x.getId() == studentId).findFirst();

        if (studentFromList.isPresent()) {
            var indexOfStudent = students.indexOf(studentFromList.get());
            students.set(indexOfStudent, student);
        } else {
            System.err.println("No element found in students list");
        }

        return studentFromList.get();
    }
}
