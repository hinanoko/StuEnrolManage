package com.example.springbootfirst.service;

import com.example.springbootfirst.entity.Student;
import com.example.springbootfirst.entity.StudentCourse;
import com.example.springbootfirst.entity.StudentWithCoursesDTO;
import com.example.springbootfirst.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {
    private final UserMapper userMapper;



    @Autowired
    public AdminService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public StudentWithCoursesDTO getStudentWithCoursesByEmail(String email) {
        Student student = userMapper.getStudentByEmail(email);

        List<StudentCourse> courses = new ArrayList<>();
        if (student != null) {
            courses = userMapper.getCoursesByStudentId(student.getId().toString());
        }

        return new StudentWithCoursesDTO(student, courses);
    }

    public void deleteStudentById(Integer studentId){
                  userMapper.deleteById(studentId);
    }
}
