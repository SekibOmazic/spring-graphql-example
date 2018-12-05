package com.whatever.simple.config;

import com.coxautodev.graphql.tools.SchemaParser;
import com.whatever.simple.department.DepartmentFieldResolver;
import com.whatever.simple.department.DepartmentMutationResolver;
import com.whatever.simple.department.DepartmentQueryResolver;
import com.whatever.simple.employee.EmployeeFieldResolver;
import com.whatever.simple.employee.EmployeeMutationResolver;
import com.whatever.simple.employee.EmployeeQueryResolver;
import com.whatever.simple.organization.OrganizationFieldResolver;
import com.whatever.simple.organization.OrganizationQueryResolver;
import graphql.schema.GraphQLSchema;
import graphql.servlet.GraphQLHttpServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;


@Configuration
public class EmployeeGraphQLConfiguration {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final EmployeeQueryResolver employeeQueryResolver;
    private final EmployeeMutationResolver employeeMutationResolver;
    private final EmployeeFieldResolver employeeFieldResolver;
    private final OrganizationQueryResolver organizationQueryResolver;
    private final OrganizationFieldResolver organizationFieldResolver;
    private final DepartmentQueryResolver departmentQueryResolver;
    private final DepartmentMutationResolver departmentMutationResolver;
    private final DepartmentFieldResolver departmentFieldResolver;

    @Autowired
    public EmployeeGraphQLConfiguration(EmployeeQueryResolver employeeQueryResolver,
                                        EmployeeMutationResolver employeeMutationResolver,
                                        EmployeeFieldResolver employeeFieldResolver,
                                        OrganizationQueryResolver organizationQueryResolver,
                                        OrganizationFieldResolver organizationFieldResolver,
                                        DepartmentQueryResolver departmentQueryResolver,
                                        DepartmentMutationResolver departmentMutationResolver,
                                        DepartmentFieldResolver departmentFieldResolver) {
        this.employeeQueryResolver = employeeQueryResolver;
        this.employeeMutationResolver = employeeMutationResolver;
        this.employeeFieldResolver = employeeFieldResolver;
        this.organizationQueryResolver = organizationQueryResolver;
        this.organizationFieldResolver = organizationFieldResolver;
        this.departmentQueryResolver = departmentQueryResolver;
        this.departmentMutationResolver = departmentMutationResolver;
        this.departmentFieldResolver = departmentFieldResolver;
    }

    /**
     * Keeps the session open until the end of a request. Allows us to use
     * lazy-loading with Hibernate.
     */
    @Bean
    public OpenEntityManagerInViewFilter openEntityManagerInViewFilter() {
        return new OpenEntityManagerInViewFilter();
    }

    @Bean
    public GraphQLSchema graphQLSchema() {
        final GraphQLSchema graphQLSchema = SchemaParser.newParser()
                .files("graphql/organization.graphqls", "graphql/department.graphqls", "graphql/employee.graphqls")
                .resolvers(
                        this.employeeQueryResolver,
                        this.employeeMutationResolver,
                        this.employeeFieldResolver,
                        this.organizationQueryResolver,
                        this.organizationFieldResolver,
                        this.departmentQueryResolver,
                        this.departmentMutationResolver,
                        this.departmentFieldResolver
                )
                .build().makeExecutableSchema();

        return graphQLSchema;
    }

    @Bean
    ServletRegistrationBean<GraphQLHttpServlet> graphQLServletRegistrationBean(GraphQLSchema schema) {
        return new ServletRegistrationBean<>(GraphQLHttpServlet.with(schema), "/graphql");
    }

}
