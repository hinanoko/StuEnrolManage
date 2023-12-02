package com.example.springbootfirst.entity;

import java.util.List;

public class StudentWithCoursesDTO {
    private Student student;
    private List<StudentCourse> courses;

    public StudentWithCoursesDTO(Student student, List<StudentCourse> courses) {
        this.student = student;
        this.courses = courses;
    }

    public Student getStudent() {
        return student;
    }

    public List<StudentCourse> getCourses() {
        return courses;
    }
}
