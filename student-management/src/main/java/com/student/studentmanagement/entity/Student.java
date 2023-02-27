package com.student.studentmanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student",uniqueConstraints = {@UniqueConstraint(columnNames = {"email"}),
        @UniqueConstraint(columnNames = {"roll"}),@UniqueConstraint(columnNames = {"mobile"})})
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private int roll;
    @Column(nullable = false)
    private long mobile;
    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL,orphanRemoval = true)
    private Course course;
}
