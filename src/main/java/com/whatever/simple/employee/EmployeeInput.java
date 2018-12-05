package com.whatever.simple.employee;

import com.whatever.simple.department.Department;
import lombok.Data;

@Data
public class EmployeeInput {
    private String firstName;
    private String lastName;
    private String title;
    private String role;
    private Long departmentId;
}
