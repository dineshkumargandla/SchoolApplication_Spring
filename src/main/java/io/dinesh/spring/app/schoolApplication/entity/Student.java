package io.dinesh.spring.app.schoolApplication.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name="student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="flag")
    private int flag;

    @Column(name="class_section")
    private String class_section;

    @Column(name="joinedDate")
    private Date joinedDate;

    public Student() {
    }

    public Student(String firstName, String lastName, String email,String class_Section) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.class_section = class_Section;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", class_section='" + class_section + '\'' +
                ", joined_Date=" + joinedDate +
                '}';
    }
}
