package com.example.springbootfirst.service;


import com.example.springbootfirst.common.Result;
import com.example.springbootfirst.entity.Student;

import com.example.springbootfirst.exception.LoginException;
import com.example.springbootfirst.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private StudentMapper studentMapper;

    @Autowired
    public StudentService(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    public Student login(Student student){
     Student dbStudent = studentMapper.selectByemail(student.getEmail());
     if(dbStudent == null){
         throw new LoginException("email didn't exist");
     }

     if(!student.getPassword().equals(dbStudent.getPassword())){
         throw new LoginException("password not match");
     }

     return dbStudent;
    }
}
