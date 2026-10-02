package com.airtribe.learntrack.services;

import java.util.ArrayList;
import java.util.Optional;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exceptions.EntityNotFoundException;
import com.airtribe.learntrack.exceptions.InvalidDataException;
import com.airtribe.learntrack.repositories.StudentRepository;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.Validator;

public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService() {
        studentRepository = new StudentRepository();
    }

    public Student AddStudent(String firstName, String lastName, String email) throws InvalidDataException {

        validateData(firstName, lastName, email);

        var id = IdGenerator.getNextPersonId();
        var student = new Student(id, firstName, lastName, email);
        student.setActive(true);
        studentRepository.addStudent(student);
        return student;
    }

    public ArrayList<Student> GetAllStudents() {
        return studentRepository.getAllStudents();
    }

    public Student SearchStudent(int studentId) throws EntityNotFoundException {
        Optional<Student> student = studentRepository.searchStudentById(studentId);
        if (student.isEmpty()) {
            throw new EntityNotFoundException(String.format("Student with Id: %d not found", studentId));
        }
        return student.get();
    }

    public Student DeactivateStudent(int studentId) throws EntityNotFoundException {
        Optional<Student> studentOptional = studentRepository.searchStudentById(studentId);
        if (studentOptional.isEmpty()) {
            throw new EntityNotFoundException(String.format("Student with Id: %d not found", studentId));
        }
        var student = studentOptional.get();
        student.setActive(false);

        studentRepository.updateStudent(studentId, student);

        return student;
    }

    private void validateData(String firstName, String lastName, String email) throws InvalidDataException {
        try {
            Validator.IsNotNullOrEmpty(firstName);
            Validator.IsNotNullOrEmpty(lastName);
            Validator.IsValidEmail(email);
        } catch (InvalidDataException ex) {
            throw ex;
        }
    }
}
