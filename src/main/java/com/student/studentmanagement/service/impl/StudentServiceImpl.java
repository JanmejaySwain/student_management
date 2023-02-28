package com.student.studentmanagement.service.impl;

import com.student.studentmanagement.entity.Course;
import com.student.studentmanagement.entity.Student;
import com.student.studentmanagement.exception.ResourceNotFoundException;
import com.student.studentmanagement.payload.StudentDto;
import com.student.studentmanagement.payload.StudentResponse;
import com.student.studentmanagement.repository.CourseRepository;
import com.student.studentmanagement.repository.StudentRepository;
import com.student.studentmanagement.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    private CourseRepository courseRepository;
    private ModelMapper mapper;

    public StudentServiceImpl(StudentRepository studentRepository, ModelMapper mapper,CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.mapper = mapper;
        this.courseRepository=courseRepository;
    }

    @Override
    public StudentDto registerStudent(StudentDto studentDto) {
        Student student = mapToEntity(studentDto);
        Student newStudent = studentRepository.save(student);
        return mapToDto(newStudent);
    }

    @Override
    public StudentResponse retrieveAllStudents(int pageNo,int pageSize,String sortDir,String sortBy) {
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(pageNo,pageSize,sort);
        Page<Student> students = studentRepository.findAll(pageable);
        List<Student> listOfStudents = students.getContent();
        List<StudentDto> content = listOfStudents.stream().map(student -> mapToDto(student)).collect(Collectors.toList());
        StudentResponse studentResponse=new StudentResponse();
        studentResponse.setContent(content);
        studentResponse.setPageNo(students.getNumber());
        studentResponse.setPageSize(students.getSize());
        studentResponse.setTotalPages(students.getTotalPages());
        studentResponse.setTotalElements(students.getTotalElements());
        studentResponse.setLast(students.isLast());
        return studentResponse;
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

    @Override
    public void deleteOneStudent(long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("student", "id", id));
        Course course = student.getCourse();
        studentRepository.delete(student);
        courseRepository.delete(course);
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
