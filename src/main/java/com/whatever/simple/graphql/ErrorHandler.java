package com.whatever.simple.graphql;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ErrorHandler implements GraphQLErrorHandler {
    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> errors) {
        return errors.stream().map(this::getUnwrapped).collect(Collectors.toList());
    }

    private GraphQLError getUnwrapped(GraphQLError error) {
        if (error instanceof ExceptionWhileDataFetching) {
            ExceptionWhileDataFetching fetchingError = (ExceptionWhileDataFetching) error;
            if (fetchingError.getException() instanceof GraphQLError) {
                return (GraphQLError) fetchingError.getException();
            }
        }
        return error;
    }
}
