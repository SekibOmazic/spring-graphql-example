package com.whatever.simple.organization;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.whatever.simple.department.Department;
import com.whatever.simple.department.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class OrganizationFieldResolver implements GraphQLResolver<Organization> {
    private DepartmentRepository departmentRepository;

    public List<Department> getDepartments(Organization organization) {
        return departmentRepository.getAllByOrganizationId(organization.getId());
    }
}
