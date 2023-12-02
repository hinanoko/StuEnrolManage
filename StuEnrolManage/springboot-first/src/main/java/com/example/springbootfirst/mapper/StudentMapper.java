package com.example.springbootfirst.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springbootfirst.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface StudentMapper extends BaseMapper<Student> {

    @Select("select * from stutable where email = #{email}")
    Student selectByemail(String email);


}
