package com.student.studentmanagement.controller;

import com.student.studentmanagement.payload.StudentDto;
import com.student.studentmanagement.payload.StudentResponse;
import com.student.studentmanagement.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
//http://localhost:8080/api/student
    @PostMapping("/student")
    public ResponseEntity<StudentDto> registerNewStudent(@Valid @RequestBody StudentDto studentDto)
    {
        return new ResponseEntity<>(studentService.registerStudent(studentDto), HttpStatus.CREATED);
    }
//http://localhost:8080/api/student
    @GetMapping("/student")
    public ResponseEntity<StudentResponse> getAllStudents(
            @RequestParam(value = "pageNo",defaultValue ="0",required = false)int pageNo,
            @RequestParam(value = "PageSize",defaultValue = "10",required = false)int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "id",required = false)String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false)String sortDir
    )
    {
        return new ResponseEntity<>(studentService.retrieveAllStudents(pageNo,pageSize,sortDir,sortBy), HttpStatus.OK);
    }
    //http://localhost:8080/api/student/{id}
    @GetMapping("/student/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable long id)
    {
        return new ResponseEntity<>(studentService.getOneStudentById(id), HttpStatus.OK);
    }
    //http://localhost:8080/api/student/{id}
    @PutMapping("/student/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable long id,@RequestBody StudentDto studentDto)
    {
        return new ResponseEntity<>(studentService.updateOneStudent(studentDto,id), HttpStatus.OK);
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable long id)
    {
        studentService.deleteOneStudent(id);
        return new ResponseEntity<>("student's record deleted successfully",HttpStatus.OK);
    }

}
