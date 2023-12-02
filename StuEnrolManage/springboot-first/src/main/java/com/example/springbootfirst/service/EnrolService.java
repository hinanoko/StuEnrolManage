package com.example.springbootfirst.service;

import com.example.springbootfirst.entity.CourseInfo;
import com.example.springbootfirst.entity.StudentCourse;
import com.example.springbootfirst.mapper.EnrolMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrolService {
    private EnrolMapper enrolMapper;

    @Autowired
    public EnrolService(EnrolMapper enrolMapper)
    {
        this.enrolMapper = enrolMapper;
    }

    public List<CourseInfo> enrol(){
        List<CourseInfo> dbcourseinfo = enrolMapper.getAllCourseInfo();
        return dbcourseinfo;
    }

    public void deleteAllCourses(Integer id){
        enrolMapper.deleteCourses(id);
    }


    public List<StudentCourse> getStudentCourses(String id){
               List<StudentCourse> enrolledCourses = enrolMapper.getStudentCourses(id);
               return enrolledCourses;
    }

}
