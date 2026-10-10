# Learntrack 

LearnTrack is a console-based Student & Course Management System built using Core Java.
It will allow admins to manage:

- Students
- Courses
- Enrollments

LearnTrack is a Java console application built with [Apache Maven](https://maven.apache.org/).

## Prerequisites
Before you begin, ensure you have the following installed on your system:
* **Java Development Kit (JDK)**: Version 17 or higher
* **Apache Maven**: Version 3.9.0 or higher [Setup Guide](https://maven.apache.org/install.html) OR `choco install maven`

## Steps to run the app:

Clone the app locally and run following commands in the terminal,

```bash
mvn clean package -DskipTests

java -cp target/learntrack-1.0-SNAPSHOT.jar com.airtribe.App
```

Class Diagram: 

```mermaid

classDiagram
    class App {
        +main(String[] args)
        +displayMenuOptions()
    }

    class MenuOptions {
        <<utility>>
        +ADD_NEW_STUDENT: String
        +VIEW_ALL_STUDENTS: String
        +SEARCH_STUDENT_BY_ID: String
        +DEACTIVATE_A_STUDENT: String
        +ADD_NEW_COURSE: String
        +VIEW_ALL_COURSES: String
        +ACTIVATE_OR_DEACTIVATE_A_COURSE: String
        +ENROLL_A_STUDENT_IN_COURSE: String
        +VIEW_ENROLLMENTS_FOR_STUDENT: String
        +UPDATE_ENROLLMENT_STATUS: String
        +EXIT: String
        +ALL_OPTIONS: Map<Integer, String>
    }

    class StudentService {
        -studentRepository: StudentRepository
        +StudentService()
        +addStudent(String, String, String) Student
        +addStudent(String, String, String, boolean) Student
        +getAllStudents() ArrayList~Student~
        +searchStudent(int) Student
        +searchStudent(String emailAddress)
        +deactivateStudent(int) Student
        -validateData(String, String, String)
    }

    class StudentOperations {
        -studentService: StudentService
	    - scanner: Scanner
        +handleAddStudent()
        +handleViewAllStudent(String, String, String) Student
        +handleSearchStudentById() ArrayList~Student~
        +handleDeactivateStudent(int) Student
        -displayStudent(Student student)
    }


    class StudentRepository {
        -students: ArrayList~Student~
        +getAllStudents() ArrayList~Student~
        +addStudent(Student)
        +searchStudentById(int) Optional~Student~
        +searchStudentByEmail(int) Optional~Student~
        +updateStudent(int, Student) Student
    }


    class Person {
        -id: int
        -firstName: String
        -lastName: String
        -email: String
        +Person(int, String, String, String)
        +getId() int
        +setId(int)
        +getFirstName() String
        +setFirstName(String)
        +getLastName() String
        +setLastName(String)
        +getEmail() String
        +setEmail(String)
        +getDisplayName() String
    }

    class Student {
        -batch: String
        -isActive: boolean
        +Student(int, String, String, String)
        +Student(int, String, String)
        +getBatch() String
        +setBatch(String)
        +isActive() boolean
        +setActive(boolean)
        +getDisplayName() String
    }

    class Trainer {
        -TRAINER_SALUTATION: String
        +Trainer(int, String, String, String)
        +Trainer()
        +getDisplayName() String
    }

    class Course {
        -id: int
        -courseName: String
        -description: String
        -durationInWeeks: int
        -isActive: boolean
        +Course()
        +Course(int, String, String, int)
        +getId() int
        +setId(int)
        +getCourseName() String
        +setCourseName(String)
        +getDescription() String
        +setDescription(String)
        +getDurationInWeeks() int
        +setDurationInWeeks(int)
        +isActive() boolean
        +setActive(boolean)
    }

    class Enrollment {
        -id: int
        -studentId: int
        -courseId: int
        -enrollmentDate: Date
        -status: EnrollmentStatus
        +Enrollment(int, int, int, Date, EnrollmentStatus)
        +getId() int
        +setId(int)
        +getStudentId() int
        +setStudentId(int)
        +getCourseId() int
        +setCourseId(int)
        +getEnrollmentDate() Date
        +setEnrollmentDate(Date)
        +getStatus() EnrollmentStatus
        +setStatus(EnrollmentStatus)
    }


    class Validator {
        <<utility>>
        +IsNotNullOrEmpty(String)
        +IsValidEmail(String)
        +IsGreaterThanZero(int)
    }

    class IdGenerator {
        <<utility>>
        +getNextPersonId() int
        +getNextCourseId() int
        +getNextEnrollmentId() int
    }


    class InvalidDataException
    class EntityNotFoundException

    App --> MenuOptions
    App --> StudentOperations
    StudentOperations --> StudentService

    StudentService --> StudentRepository
    StudentService --> Student
    StudentService --> Validator
    StudentService --> IdGenerator
    StudentService --> InvalidDataException

    StudentRepository --> Student


    Person <|-- Student : "extends"
    Person <|-- Trainer : "extends"



    Validator --> InvalidDataException
    StudentRepository --> EntityNotFoundException
```

*Note - Course and Enrollment related classes are not added in the class diagram as they follow the same pattern.*