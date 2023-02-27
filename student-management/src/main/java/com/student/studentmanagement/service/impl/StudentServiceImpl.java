package com.student.studentmanagement.service.impl;

import com.student.studentmanagement.entity.Student;
import com.student.studentmanagement.exception.ResourceNotFoundException;
import com.student.studentmanagement.payload.StudentDto;
import com.student.studentmanagement.repository.StudentRepository;
import com.student.studentmanagement.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    private ModelMapper mapper;

    public StudentServiceImpl(StudentRepository studentRepository, ModelMapper mapper) {
        this.studentRepository = studentRepository;
        this.mapper = mapper;
    }

    @Override
    public StudentDto registerStudent(StudentDto studentDto) {
        Student student = mapToEntity(studentDto);
        Student newStudent = studentRepository.save(student);
        return mapToDto(newStudent);
    }

    @Override
    public List<StudentDto> retrieveAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> dtos = students.stream().map(student -> mapToDto(student)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public StudentDto getOneStudentById(long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("student", "id", id));
        return mapToDto(student);
    }

    @Override
    public StudentDto updateOneStudent(StudentDto studentDto, long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("student", "id", id));
        student.setName(studentDto.getName());
        student.setEmail(studentDto.getEmail());
        student.setRoll(studentDto.getRoll());
        student.setMobile(studentDto.getMobile());
        Student updatedStudent = studentRepository.save(student);
        return mapToDto(updatedStudent);
    }

    private Student mapToEntity(StudentDto studentDto)
    {
       return mapper.map(studentDto,Student.class);
    }
    private StudentDto mapToDto(Student student)
    {
        return mapper.map(student,StudentDto.class);
    }
}
