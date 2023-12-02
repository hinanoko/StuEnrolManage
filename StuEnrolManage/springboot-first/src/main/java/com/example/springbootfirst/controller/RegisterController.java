package com.example.springbootfirst.controller;

import com.example.springbootfirst.common.Result;
import com.example.springbootfirst.entity.CourseInfo;
import com.example.springbootfirst.entity.Student;
import com.example.springbootfirst.entity.StudentCourse;
import com.example.springbootfirst.mapper.RegisterMapper;
import com.example.springbootfirst.mapper.UserMapper;
import com.example.springbootfirst.service.RegisterService;
import com.example.springbootfirst.service.StudentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;


@RestController
@RequestMapping("/register")
public class RegisterController {


    @Resource
    private RegisterService registerService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private RegisterMapper registerMapper;

    @GetMapping
    public Result<?> getAllCourse(@RequestParam Integer coursestuid){
        List<StudentCourse> studentCourses = registerService.findCourseById(coursestuid);
        return Result.success(studentCourses);
    }

    @DeleteMapping("/{idcoursetable}")
    public Result<?> deleteStudentCourse(@PathVariable Integer idcoursetable){
        registerService.deleteStudent(idcoursetable);
        return Result.success();
    }



    @PostMapping
    public Result<?> Register(@RequestBody StudentCourse studentCourse){
        Random r = new Random();
        int mark = r.nextInt(75) + 25;
        studentCourse.setCoursemark(mark);
        registerService.save(studentCourse);
        return Result.success("Registration successful");
    }

    @GetMapping("/{studentId}")
    public Result<List<StudentCourse>> getStudentCourses(@PathVariable String studentId) {
        Student student = userMapper.selectById(studentId);
        if (student == null) {
            return Result.error("Student not found");
        }

        // Obtain course information from students
        List<StudentCourse> courses = registerMapper.selectCourseByid(studentId);

        return Result.success(courses);
    }

}
