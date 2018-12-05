package com.whatever.simple.department;

import com.whatever.simple.employee.Employee;
import com.whatever.simple.organization.Organization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @NotNull
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @NotNull
    private LocalDateTime updatedAt;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    private List<Employee> employees = new LinkedList<>();

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Organization organization;


    public Department(Long id, String name, List<Employee> employees, Organization organization) {
        this.id = id;
        this.name = name;
        this.employees = employees;
        this.organization = organization;
    }

    @PrePersist
    public void onPrePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onPreUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
