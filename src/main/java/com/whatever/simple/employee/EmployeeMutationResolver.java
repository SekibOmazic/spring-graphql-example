package com.whatever.simple.employee;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmployeeMutationResolver implements GraphQLMutationResolver {
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(EmployeeInput input) {
        return employeeRepository.save(
                new Employee(
                        null,
                        input.getFirstName(),
                        input.getLastName(),
                        input.getTitle(), input.getEmail())
        );
    }
}
