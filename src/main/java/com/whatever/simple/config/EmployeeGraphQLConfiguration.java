package com.whatever.simple.config;

import com.coxautodev.graphql.tools.SchemaParser;
import com.whatever.simple.employee.EmployeeMutationResolver;
import com.whatever.simple.employee.EmployeeQueryResolver;
import graphql.schema.GraphQLSchema;
import graphql.servlet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class EmployeeGraphQLConfiguration {
  private final Logger logger = LoggerFactory.getLogger(getClass());

  private final EmployeeQueryResolver employeeQueryResolver;
  private final EmployeeMutationResolver employeeMutationResolver;

  @Autowired
  public EmployeeGraphQLConfiguration(EmployeeQueryResolver employeeQueryResolver, EmployeeMutationResolver employeeMutationResolver) {
    this.employeeQueryResolver = employeeQueryResolver;
    this.employeeMutationResolver = employeeMutationResolver;
  }

  @Bean
  public GraphQLSchema graphQLSchema() {
    final GraphQLSchema graphQLSchema = SchemaParser.newParser()
        .files("graphql/employee.graphqls")
        .resolvers(this.employeeQueryResolver, this.employeeMutationResolver)
        .build().makeExecutableSchema();

    return graphQLSchema;
  }

  @Bean
  ServletRegistrationBean<GraphQLHttpServlet> graphQLServletRegistrationBean(GraphQLSchema schema) {
    return new ServletRegistrationBean<>(GraphQLHttpServlet.with(schema), "/graphql");
  }

}
