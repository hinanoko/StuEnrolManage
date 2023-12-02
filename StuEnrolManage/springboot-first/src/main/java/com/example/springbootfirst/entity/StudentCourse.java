package com.example.springbootfirst.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("coursetable")
@Data
public class StudentCourse {
    @TableId(type = IdType.AUTO)
    private Integer idcoursetable;
    private String coursename;
    private String courseid;
    private Integer coursemark;
    private String coursestuid;

}
