package com.whatever.simple.employee;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.whatever.simple.department.Department;
import com.whatever.simple.department.DepartmentNotFoundException;
import com.whatever.simple.department.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmployeeMutationResolver implements GraphQLMutationResolver {
    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;

    public Employee createEmployee(EmployeeInput input) {

        Department department = departmentRepository
                .findById(input.getDepartmentId())
                .orElseThrow(() -> new DepartmentNotFoundException(input.getDepartmentId()));

        return employeeRepository.save(
                new Employee(
                        null,
                        input.getFirstName(),
                        input.getLastName(),
                        input.getTitle(),
                        input.getRole(),
                        department
                )
        );
    }
}
