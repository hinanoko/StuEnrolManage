package com.example.springbootfirst.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("stutable")
@Data
public class Student {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String email;
    private String password;
    private String name;
}
