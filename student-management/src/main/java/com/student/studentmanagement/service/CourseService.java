package com.student.studentmanagement.service;

import com.student.studentmanagement.payload.CourseDto;

public interface CourseService {
    CourseDto createNewCourse(CourseDto courseDto,long id);

    CourseDto getCourse(long id);

    CourseDto updateCourse(long id, CourseDto dto);
}
