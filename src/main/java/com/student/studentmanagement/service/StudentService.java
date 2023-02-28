package com.student.studentmanagement.service;

import com.student.studentmanagement.payload.StudentDto;
import com.student.studentmanagement.payload.StudentResponse;

import java.util.List;

public interface StudentService {
    StudentDto registerStudent(StudentDto studentDto);

    StudentResponse retrieveAllStudents(int PageNo,int pageSize,String sortDir,String sortBy);

    StudentDto getOneStudentById(long id);

    StudentDto updateOneStudent(StudentDto studentDto, long id);

    void deleteOneStudent(long id);
}
