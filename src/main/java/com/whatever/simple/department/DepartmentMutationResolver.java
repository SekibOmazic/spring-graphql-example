package com.whatever.simple.department;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.whatever.simple.organization.Organization;
import com.whatever.simple.organization.OrganizationNotFoundException;
import com.whatever.simple.organization.OrganizationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DepartmentMutationResolver implements GraphQLMutationResolver {
    private OrganizationRepository organizationRepository;
    private DepartmentRepository departmentRepository;

    public Department createDepartment(Long organizationId, String name) {

        Organization organization = organizationRepository
                .findById(organizationId)
                .orElseThrow(() -> new OrganizationNotFoundException(organizationId));

        return departmentRepository
                .save(new Department(null, name, null, organization));
    }

    public Boolean deleteDepartment(Long id) {
        departmentRepository.deleteById(id);

        return !departmentRepository.existsById(id);
    }

    public Department updateDepartment(Long id, String name) {
        Department old = departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException(id));
        old.setName(name);

        return departmentRepository.save(old);
    }
}
