package com.whatever.simple.employee;

import lombok.Data;

@Data
public class EmployeeInput {
    private String firstName;
    private String lastName;
    private String title;
    private String email;
}
