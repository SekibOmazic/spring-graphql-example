package com.whatever.simple.department;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DepartmentQueryResolver implements GraphQLQueryResolver {
    private DepartmentRepository departmentRepository;

    public List<Department> getDepartments(Long organizationId) {
        return departmentRepository.getAllByOrganizationId(organizationId);
    }

    public Department getDepartment(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException(id));
    }
}
