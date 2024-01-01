package io.dinesh.spring.app.schoolApplication.rest;

import io.dinesh.spring.app.schoolApplication.Exceptions.StudentNotFoundException;
import io.dinesh.spring.app.schoolApplication.Helper.FakeDataGenerator;
import io.dinesh.spring.app.schoolApplication.constants.CustomMessages;
import io.dinesh.spring.app.schoolApplication.entity.Student;
import io.dinesh.spring.app.schoolApplication.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    CustomMessages customMessages;

    DateTimeFormatter formatter  = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();

    @GetMapping("/studentDetails")
    public Student getStudentById(@RequestParam int id) throws StudentNotFoundException {
        return studentService.getStudentDetailsById(id);
    }

    @GetMapping("/allStudentDetails")
    public List<Student> getStudents() {
        return studentService.getAllStudent();
    }

    @PostMapping("/addDummyStudent")
    public ResponseEntity<Student> addStudent() {
        Student student = studentService.addDummyStudent();
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PostMapping("/addDummyStudents")
    public ResponseEntity<String> addStudent(@RequestParam int count) {
        studentService.addDummyStudents(count);
        return new ResponseEntity<>(customMessages.DUMMY_STUDENT_CREATION + count, HttpStatus.CREATED);
    }

    @GetMapping("/getStudentsBasedOnLastName")
    public List<Student> getStudentsBasedOnLastName(@RequestParam String lastName) {
        return studentService.getDetailsBasedOnLastName(lastName);
    }

    @GetMapping("/getStudentsBasedOnFirstName")
    public List<Student> getStudentsBasedOnFirstName(@RequestParam String firstName) {
        return studentService.getDetailsBasedOnFirstName(firstName);
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<Student> UpdateStudentDetails(@PathVariable int id, @RequestBody Student studentInfo) {
        studentInfo.setId(id);
        studentService.saveStudent(studentInfo);
        return new ResponseEntity<>(studentInfo, HttpStatus.CREATED);
    }

    @PostMapping("/student")
    public ResponseEntity<Student> addStudentDetails(@RequestBody Student studentInfo) {
        studentInfo.setId(0);
        studentInfo.setFlag(1);
        studentInfo.setJoinedDate(new Date(formatter.format(now)));
        studentService.saveStudent(studentInfo);
        return new ResponseEntity<>(studentInfo, HttpStatus.CREATED);
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<String> deleteStudentDetails(@PathVariable int id, @RequestParam(required = false, defaultValue = "false") boolean permanentDelete) throws StudentNotFoundException {
        Student studentInfo = studentService.getStudentDetailsById(id);
        if (permanentDelete) {
            studentService.deleteStudent(studentInfo.getId());
        } else {
            studentInfo.setFlag(0);
            studentService.saveStudent(studentInfo);
        }
        return new ResponseEntity<>(customMessages.DELETE_STUDENT_MESSAGE+id, HttpStatus.ACCEPTED);
    }

    @PostMapping("/restoreStudent/{id}")
    public ResponseEntity<String> restoreStudentDetails(@PathVariable int id) throws StudentNotFoundException {
        Student studentInfo = studentService.getStudentDetails(id);
        studentInfo.setFlag(1);
        studentService.restoreStudent(studentInfo);
        return  new ResponseEntity<>(customMessages.RESTORE_STUDENT+id, HttpStatus.ACCEPTED);
    }
}


