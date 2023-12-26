package io.dinesh.spring.app.schoolApplication.rest;

import io.dinesh.spring.app.schoolApplication.Helper.FakeDataGenerator;
import io.dinesh.spring.app.schoolApplication.entity.Student;
import io.dinesh.spring.app.schoolApplication.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    FakeDataGenerator fakeDataGenerator;

    @GetMapping("/studentDetails")
    public Student getStudentById(@RequestParam int id) {
        Student theStudent = studentService.getStudentDetailsById(id);
        if (theStudent == null) {
            throw new RuntimeException("Employee id not found - " + id);
        }
        return theStudent;
    }

    @GetMapping("/allStudentDetails")
    public List<Student> getStudents() {
        return studentService.getAllStudent();
    }

//    @PostMapping("/addStudent")
//    public Student addStudent(@RequestBody Student theStudent){
//        return studentService.addStudent(theStudent);
//    }

    @PostMapping("/addDummyStudent")
    public ResponseEntity<Student> addStudent() {
         Student student = studentService.addDummyStudent();
         return new ResponseEntity<>(new Student(student.getFirstName(),student.getLastName(), student.getEmail()),HttpStatus.CREATED);
    }

    @PostMapping("/addDummyStudents")
    public ResponseEntity<String> addStudent(@RequestParam int count) {
         studentService.addDummyStudents(count);
        return new ResponseEntity<>("Number of Dummy Students created are " +count,HttpStatus.CREATED);
    }

    @GetMapping("/getStudentsBasedOnLastName")
    public List<Student> getStudentsBasedOnLastName(@RequestParam String lastName) {
        return studentService.getDetailsBasedOnLastName(lastName);
    }

    @GetMapping("/getStudentsBasedOnFirstName")
    public List<Student> getStudentsBasedOnFirstName(@RequestParam String firstName) {
        return studentService.getDetailsBasedOnFirstName(firstName);
    }
}
