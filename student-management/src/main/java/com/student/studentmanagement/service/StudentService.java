package com.student.studentmanagement.service;

import com.student.studentmanagement.payload.StudentDto;

import java.util.List;

public interface StudentService {
    StudentDto registerStudent(StudentDto studentDto);

    List<StudentDto> retrieveAllStudents();

    StudentDto getOneStudentById(long id);

    StudentDto updateOneStudent(StudentDto studentDto, long id);
}
