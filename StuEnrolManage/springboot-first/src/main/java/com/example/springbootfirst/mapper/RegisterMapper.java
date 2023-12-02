package com.example.springbootfirst.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springbootfirst.entity.StudentCourse;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Mapper
public interface RegisterMapper extends BaseMapper<StudentCourse> {


    @Select("select * from coursetable where coursestuid = #{idcoursetable}")
    List<StudentCourse> selectCourseByID(Integer idcoursetable);


    @Delete("delete from coursetable where idcoursetable = #{id}")
    void deleteByID(Integer id);

    @Select("select * from coursetable where coursestuid = #{idcoursetable}")
    List<StudentCourse> selectCourseByid(String idcoursetable);


}
