package io.dinesh.spring.app.schoolApplication.implementation;

import io.dinesh.spring.app.schoolApplication.Helper.FakeDataGenerator;
import io.dinesh.spring.app.schoolApplication.entity.Student;
import io.dinesh.spring.app.schoolApplication.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class StudentImpl implements StudentService {
    public StudentImpl(EntityManager studentEntityManager, FakeDataGenerator fakeDataGenerator) {
        this.studentEntityManager = studentEntityManager;
        this.fakeDataGenerator = fakeDataGenerator;

    }

    EntityManager studentEntityManager;

    FakeDataGenerator fakeDataGenerator;

    @Override
    public Student getStudentDetailsById(int studentId) {
        Student student = studentEntityManager.find(Student.class,studentId);

        if (student.toString().isEmpty()) {
            throw new RuntimeException("Did not find employee id - " + studentId);
        }

        return student;
    }

    @Override
    public List<Student> getAllStudent() {
        TypedQuery<Student> studentEntityTypedQuery = studentEntityManager.createQuery("From Student",Student.class);
        return studentEntityTypedQuery.getResultList();
    }


    @Override
    @Transactional
    public Student addDummyStudent() {
        Student student = new Student(fakeDataGenerator.getFakeFirstName(), fakeDataGenerator.getFakeLastName(), fakeDataGenerator.getFakeEmail());
        studentEntityManager.persist(student);
        return student;
    }

    @Override
    @Transactional
        public void addDummyStudents(int count) {
        Student student;
        for(int i =1 ; i <=count;i++){
            student =new Student(fakeDataGenerator.getFakeFirstName(), fakeDataGenerator.getFakeLastName(), fakeDataGenerator.getFakeEmail());
            studentEntityManager.persist(student);
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
}
