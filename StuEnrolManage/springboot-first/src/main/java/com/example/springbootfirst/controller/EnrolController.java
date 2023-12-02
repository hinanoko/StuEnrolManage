package com.example.springbootfirst.controller;


import com.example.springbootfirst.common.Result;
import com.example.springbootfirst.entity.CourseInfo;
import com.example.springbootfirst.entity.Student;
import com.example.springbootfirst.entity.StudentCourse;
import com.example.springbootfirst.service.EnrolService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/enrol")
public class EnrolController {

    @Resource
    private EnrolService enrolService;


    @GetMapping("/{studentId}")
    public Result<?> findAll(@PathVariable String studentId) {
        List<CourseInfo> userList = enrolService.enrol(); // Passing in null means no query conditions are added
         List<StudentCourse> courseList = enrolService.getStudentCourses(studentId);



        List<String> selectedCourseIds = courseList.stream()
                .map(StudentCourse::getCourseid)
                .collect(Collectors.toList());

        List<CourseInfo> filterUnselectedCourses = userList.stream()
                .filter(course -> !selectedCourseIds.contains(course.getCourseid()))
                .collect(Collectors.toList());

        System.out.println(filterUnselectedCourses);

        return Result.success(filterUnselectedCourses);
    }


    @DeleteMapping("/{id}")
    public Result<?> deleteAllCourses(@PathVariable Integer id){
        enrolService.deleteAllCourses(id);
        return Result.success();

    }

}
