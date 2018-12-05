package com.whatever.simple.employee;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.whatever.simple.department.Department;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class EmployeeFieldResolver implements GraphQLResolver<Employee> {

    public Department getDepartment(Employee employee) {
        return employee.getDepartment();
    }
}
