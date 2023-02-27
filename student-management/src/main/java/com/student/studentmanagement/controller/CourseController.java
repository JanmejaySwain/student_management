package com.student.studentmanagement.controller;

import com.student.studentmanagement.payload.CourseDto;
import com.student.studentmanagement.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class CourseController {
    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/{id}/course")
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto courseDto,@PathVariable long id)
    {
        return new ResponseEntity<>(courseService.createNewCourse(courseDto,id), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/course")
    public ResponseEntity<CourseDto> getCourseDetails(@PathVariable long id)
    {

        return  new ResponseEntity<>(courseService.getCourse(id),HttpStatus.OK);
    }
    @PutMapping("{id}/course")
    public ResponseEntity<CourseDto> updateCourseDetails(@PathVariable long id,@RequestBody CourseDto dto)
    {
        return  new ResponseEntity<>(courseService.updateCourse(id,dto),HttpStatus.OK);
    }
}
