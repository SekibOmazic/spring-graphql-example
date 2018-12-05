package com.whatever.simple.department;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    List<Department> getAllByOrganizationId(Long organizationId);
}
