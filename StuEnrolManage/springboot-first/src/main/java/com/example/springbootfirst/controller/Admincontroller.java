package com.example.springbootfirst.controller;

import com.example.springbootfirst.common.Result;
import com.example.springbootfirst.entity.Student;
import com.example.springbootfirst.mapper.StudentMapper;
import com.example.springbootfirst.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/changeName")
public class Admincontroller {

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private UserMapper userMapper;

    @PutMapping
    public Result<?> changeName(@RequestBody Map<String, String> requestParams){
        String studentId = requestParams.get("studentId");
        String studentName = requestParams.get("studentName");
        System.out.println(studentId);
        System.out.println(studentName);

        Student student = studentMapper.selectById(studentId);
        if (student == null) {
            return Result.error("Student not found");
        }

        student.setName(studentName);

        studentMapper.updateById(student);


        return Result.success("Name changed successfully");
    }

    @GetMapping
    public Result<?> getAllEmail(@RequestParam String email){
        List<String> emails = userMapper.getAllEmails(email);
        if(emails.contains(email)){
            System.out.println(111);
            return Result.success(true);
        } else{
            System.out.println(222);
            return Result.success(false);
        }
    }

}
