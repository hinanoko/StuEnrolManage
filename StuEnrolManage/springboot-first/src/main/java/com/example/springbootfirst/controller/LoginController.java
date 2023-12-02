package com.example.springbootfirst.controller;


import com.example.springbootfirst.common.Result;
import com.example.springbootfirst.entity.CourseInfo;
import com.example.springbootfirst.entity.Student;
import com.example.springbootfirst.entity.StudentCourse;
import com.example.springbootfirst.mapper.UserMapper;
import com.example.springbootfirst.service.EnrolService;
import com.example.springbootfirst.service.StudentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    UserMapper userMapper;

    @Resource
    private StudentService studentService;

    @Resource
    private EnrolService enrolService;

    @PostMapping
    public Result<?> check(@RequestBody Student student){
        String email = student.getEmail();
        String password = student.getPassword();

        System.out.println(email);
        System.out.println(password);

        Student dbStudent = studentService.login(student);

        System.out.println(dbStudent.getName());

        return Result.success(dbStudent);
    }

    @GetMapping
    public Result<?> numberOfCourses(){
        List<CourseInfo> courseList = enrolService.enrol();
        int num = courseList.size();
        System.out.println(num);
        return Result.success(num);

    }

}
