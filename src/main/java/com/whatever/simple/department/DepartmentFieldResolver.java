package com.whatever.simple.department;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.whatever.simple.employee.Employee;
import com.whatever.simple.employee.EmployeeRepository;
import com.whatever.simple.organization.Organization;
import com.whatever.simple.organization.OrganizationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DepartmentFieldResolver implements GraphQLResolver<Department> {
    private EmployeeRepository employeeRepository;
    private OrganizationRepository organizationRepository;
    private DepartmentRepository departmentRepository;

    public List<Employee> getEmployees(Department department) {
        return employeeRepository.getAllByDepartment_Id(department.getId());
    }

    public Organization getOrganization(Department department) {
        return department.getOrganization();
    }
}
