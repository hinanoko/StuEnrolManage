package com.example.springbootfirst. entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("courseinfo")
@Data
public class CourseInfo {
    @TableId(type = IdType.AUTO)
    private Integer idcourseinfo;
    private String courseid;
    private String coursename;
}
