package com.example.springbootfirst.mapper;


import com.example.springbootfirst.entity.CourseInfo;
import com.example.springbootfirst.entity.StudentCourse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EnrolMapper{

    @Select("select * from courseinfo")
    List<CourseInfo> getAllCourseInfo();

    @Select("delete from coursetable where coursestuid = #{id}")
    void deleteCourses(Integer id);

    @Select("select * from coursetable where coursestuid = #{id}")
    List<StudentCourse> getStudentCourses(String id);

}
