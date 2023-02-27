package com.student.studentmanagement.service.impl;


import com.student.studentmanagement.entity.Course;
import com.student.studentmanagement.entity.Student;
import com.student.studentmanagement.exception.ResourceNotFoundException;
import com.student.studentmanagement.payload.CourseDto;
import com.student.studentmanagement.repository.CourseRepository;
import com.student.studentmanagement.repository.StudentRepository;
import com.student.studentmanagement.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {
    private CourseRepository courseRepository;
    private StudentRepository studentRepository;
    private ModelMapper mapper;

    public CourseServiceImpl(CourseRepository courseRepository, ModelMapper mapper,StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.mapper = mapper;
        this.studentRepository=studentRepository;
    }

    @Override
    public CourseDto createNewCourse(CourseDto courseDto,long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));
        Course course = mapToEntity(courseDto);
        course.setStudent(student);
        Course newCourse = courseRepository.save(course);
        return mapToDto(newCourse);
    }

    @Override
    public CourseDto getCourse(long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("student", "id", id));
        Course course = student.getCourse();
        return mapToDto(course);
    }

    @Override
    public CourseDto updateCourse(long id, CourseDto dto) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("course", "id", id));
        Course course = student.getCourse();
        course.setCourseName(dto.getCourseName());
        course.setInstructorName(dto.getInstructorName());
        course.setDuration(dto.getDuration());
        Course updatedCourse = courseRepository.save(course);
        return mapToDto(updatedCourse);
    }

    private Course mapToEntity(CourseDto courseDto)
    {
        return mapper.map(courseDto,Course.class);
    }
    private CourseDto mapToDto(Course course)
    {
        return mapper.map(course,CourseDto.class);
    }
}
