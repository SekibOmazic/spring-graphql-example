package com.whatever.simple.employee;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.whatever.simple.graphql.Pagination;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class EmployeeQueryResolver implements GraphQLQueryResolver {
    private EmployeeRepository employeeRepository;

    public List<Employee> getEmployees(Pagination pagination) {
        return employeeRepository.findAll(PageRequest.of(pagination.getPage() - 1, pagination.getSize())).getContent();
    }

    public long getEmployeeCount() {
        return employeeRepository.count();
    }

    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public Employee me() {
        Employee e = employeeRepository.findById(1L).orElseThrow(() -> new EmployeeNotFoundException(1L));

        return e;
    }
}
