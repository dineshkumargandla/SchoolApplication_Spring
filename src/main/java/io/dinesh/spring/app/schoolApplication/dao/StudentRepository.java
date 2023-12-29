package io.dinesh.spring.app.schoolApplication.dao;

import io.dinesh.spring.app.schoolApplication.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
