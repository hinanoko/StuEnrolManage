package com.example.springbootfirst.controller;



import com.example.springbootfirst.common.Result;
import com.example.springbootfirst.entity.Student;
import com.example.springbootfirst.entity.StudentWithCoursesDTO;
import com.example.springbootfirst.mapper.RegisterMapper;
import com.example.springbootfirst.mapper.UserMapper;
import com.example.springbootfirst.service.AdminService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    UserMapper userMapper;

    @Resource
    private AdminService adminService;

    @PostMapping
    public Result<?> save(@RequestBody Student student){

        userMapper.insert(student);
        return Result.success();
    }

    @GetMapping
    public Result<StudentWithCoursesDTO> getAllStudentsWithCourses(@RequestParam String email) {
             StudentWithCoursesDTO studentWithCoursesDTO = adminService.getStudentWithCoursesByEmail(email);

             return Result.success(studentWithCoursesDTO);
    }

    @DeleteMapping("/{id}")
    public Result<?> deleteStudent(@PathVariable Integer id){
             adminService.deleteStudentById(id);
             return Result.success();
    }


    @PutMapping
    public Result<?> changePassword(@RequestBody Map<String, String> requestParams){
        String studentId = requestParams.get("studentId");
        String password = requestParams.get("password");

        System.out.println(studentId);
        System.out.println(password);

        Student student = userMapper.selectById(studentId);
        if (student == null) {
            return Result.error("Student not found");
        }
        // update password
        student.setPassword(password);
        userMapper.updateById(student);

        return Result.success("Password changed successfully");

    }


}
