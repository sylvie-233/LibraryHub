package com.sylvie233.libraryhub.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.sylvie233.libraryhub.entity.Student;
import com.sylvie233.libraryhub.mapper.StudentMapper;


@Controller
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);
    private StudentMapper studentMapper;

    public TestController(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @GetMapping("/test")
    public String testIndex() {
        List<Student> students = studentMapper.getAll();
        for (Student student : students) {
            logger.debug(student.toString());
        }
        return "test";
    }

}
