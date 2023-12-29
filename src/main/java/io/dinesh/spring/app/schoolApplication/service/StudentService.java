package io.dinesh.spring.app.schoolApplication.service;

import io.dinesh.spring.app.schoolApplication.Exceptions.StudentNotFoundException;
import io.dinesh.spring.app.schoolApplication.entity.Student;

import java.util.List;

public interface StudentService {

    Student getStudentDetailsById(int studentId) throws StudentNotFoundException;

    Student getStudentDetails(int studentId) throws StudentNotFoundException;

    List<Student> getAllStudent();
    Student addDummyStudent();
    void addDummyStudents(int count);

    List<Student> getDetailsBasedOnLastName(String lastName);

    List<Student> getDetailsBasedOnFirstName(String firstName);

    Student saveStudent(Student studentInfo);

    void deleteStudent(int id);

    Student restoreStudent(Student student);

}
