package com.student.studentmanagement.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data @NoArgsConstructor @AllArgsConstructor
public class StudentResponse {
    private List<StudentDto> content;
    private int pageNo;
    private int pageSize;
    private boolean last;
    private int totalPages;
    private long totalElements;
}
