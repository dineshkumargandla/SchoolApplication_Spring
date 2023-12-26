package io.dinesh.spring.app.schoolApplication.service;

import io.dinesh.spring.app.schoolApplication.entity.Student;

import java.util.List;

public interface StudentService {

    Student getStudentDetailsById(int studentId);

    List<Student> getAllStudent();
    Student addDummyStudent();
    void addDummyStudents(int count);

    List<Student> getDetailsBasedOnLastName(String lastName);

    List<Student> getDetailsBasedOnFirstName(String firstName);
}
