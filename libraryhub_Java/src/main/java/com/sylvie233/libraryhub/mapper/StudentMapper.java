package com.sylvie233.libraryhub.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sylvie233.libraryhub.entity.Student;

@Repository
public interface StudentMapper {
    List<Student> getAll();
}
