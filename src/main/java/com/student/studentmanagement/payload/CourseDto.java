package com.student.studentmanagement.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CourseDto {
    private long id;
    @NotBlank
    private String courseName;
    @NotBlank @Size(min = 2,message = "name should have at least two characters")
    private  String instructorName;
    @NotBlank(message = "duration is mandatory field")
    private int duration;
}
