package com.student.studentmanagement.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StudentDto {
    private long id;
    @NotEmpty @Size(min =2,message = "name should  have at least 2 character")
    private String name;
    @NotEmpty(message = "email is mandatory field") @Email(message = "invalid email address")
    private String email;
    private int roll;
    private long mobile;
}
