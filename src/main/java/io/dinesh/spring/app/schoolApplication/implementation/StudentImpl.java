package io.dinesh.spring.app.schoolApplication.implementation;

import io.dinesh.spring.app.schoolApplication.Exceptions.StudentNotFoundException;
import io.dinesh.spring.app.schoolApplication.Helper.FakeDataGenerator;
import io.dinesh.spring.app.schoolApplication.constants.CustomMessages;
import io.dinesh.spring.app.schoolApplication.dao.StudentRepository;
import io.dinesh.spring.app.schoolApplication.entity.Student;
import io.dinesh.spring.app.schoolApplication.service.FakeData;
import io.dinesh.spring.app.schoolApplication.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StudentImpl implements StudentService {

    DateTimeFormatter formatter  = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();

    @Autowired
    public StudentImpl(StudentRepository studentRepository,EntityManager studentEntityManager) {
        this.studentRepository = studentRepository;
        this.studentEntityManager = studentEntityManager;
    }

    @Autowired
    CustomMessages customMessages;

    @Autowired
    FakeData fakeDataGenerator;

    StudentRepository studentRepository;

    EntityManager studentEntityManager;


    @Override
    public Student getStudentDetailsById(int id) throws StudentNotFoundException {
        Optional<Student> student = studentRepository.findById(id);

        Student theStudent = null;
        if (student.isPresent() && student.get().getFlag()==1) {
            theStudent = student.get();
        }
        else {
            throw new StudentNotFoundException(customMessages.StudentNotFound + id);
        }

        return theStudent;
    }

    @Override
    public Student getStudentDetails(int studentId) throws StudentNotFoundException {
        Optional<Student> student = studentRepository.findById(studentId);

        Student theStudent = null;
        if (student.isPresent()) {
            theStudent = student.get();
        }
        else {
            throw new StudentNotFoundException(customMessages.StudentNotFound + studentId);
        }

        return theStudent;
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }


    @Override
    @Transactional
    public Student addDummyStudent() {
        Student student = new Student(fakeDataGenerator.getFakeFirstName(), fakeDataGenerator.getFakeLastName(), fakeDataGenerator.getFakeEmail(), fakeDataGenerator.getFakeClassSection());
        student.setId(0);
        student.setFlag(1);
        student.setJoinedDate(new Date(formatter.format(now)));
        studentRepository.save(student);
        return student;
    }

    @Override
    @Transactional
        public void addDummyStudents(int count) {
        Student student;
        for(int i =1 ; i <=count;i++){
            addDummyStudent();
        }
    }

    @Override
    public List<Student> getDetailsBasedOnLastName(String lastName) {
        TypedQuery<Student> fetchStudentDetails= studentEntityManager.createQuery("From Student WHERE lastName=:theData",Student.class);
        fetchStudentDetails.setParameter("theData",lastName);
        return fetchStudentDetails.getResultList();
    }

    @Override
    public List<Student> getDetailsBasedOnFirstName(String firstName) {
        TypedQuery<Student> fetchStudentDetails= studentEntityManager.createQuery("From Student WHERE firstName=:theData",Student.class);
        fetchStudentDetails.setParameter("theData",firstName);
        return fetchStudentDetails.getResultList();
    }

    @Override
    public void saveStudent(Student studentInfo) {
        studentRepository.save(studentInfo);
    }

    @Override
    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    @Override
    public void restoreStudent(Student studentInfo) {
        studentRepository.save(studentInfo);
    }
}
