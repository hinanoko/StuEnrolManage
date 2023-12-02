package com.example.springbootfirst.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springbootfirst.entity.CourseInfo;
import com.example.springbootfirst.entity.StudentCourse;
import com.example.springbootfirst.mapper.RegisterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterService extends ServiceImpl<RegisterMapper, StudentCourse> {
    private RegisterMapper registerMapper;

    @Autowired
    public RegisterService(RegisterMapper registerMapper){
        this.registerMapper = registerMapper;
    }

    public List<StudentCourse> findCourseById(Integer courseStuId){
           List<StudentCourse> dbStudentCourse = registerMapper.selectCourseByID(courseStuId);
           return dbStudentCourse;
    }

    public void deleteStudent(Integer id){
        registerMapper.deleteByID(id);
    }

}
