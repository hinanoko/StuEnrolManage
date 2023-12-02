package com.example.springbootfirst.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springbootfirst.entity.Student;
import com.example.springbootfirst.entity.StudentCourse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<Student> {

    @Select("select * from stutable where email = #{email}")
    Student getStudentByEmail(String email);

    @Select("SELECT * FROM coursetable WHERE coursestuid = #{studentId}")
    List<StudentCourse> getCoursesByStudentId(String studentId);

    @Select("select email from stutable where email = #{email}")
    List<String> getAllEmails(String email);
}
