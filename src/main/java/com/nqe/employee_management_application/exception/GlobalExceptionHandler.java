package com.nqe.employee_management_application.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/*
To use ErrorDetails to return the error response, let’s create a GlobalExceptionHandler class annotated with
@ControllerAdvice annotation. This class handles exception-specific and global exceptions in a single place.
 */
// what does this class do? - This class handles global exceptions and customizes the error response for the application

@ControllerAdvice // what does this do? - Allows centralized handling of exceptions across all controllers
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class) // what does this do? - Handles exceptions of type ResourceNotFoundException.
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request){ // what does <?> mean? - <?> is a wildcard representing any type
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false)); // why false is passed as parameter? - 'false' indicates to exclude the request's URI in the description

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class) // what does this do? - Handles exceptions of type Exception (i.e., all other exceptions).
    public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

/*

*The @ControllerAdvice annotation marks the class as a global exception handler for all controllers in the application.

*The class has two methods annotated with @ExceptionHandler. These methods handle exceptions of type
ResourceNotFoundException and Exception, respectively.

*When a ResourceNotFoundException is thrown, the resourceNotFoundException() method is called, which creates an instance
of ErrorDetails containing the date, message, and description of the exception, and returns a ResponseEntity with the
HttpStatus NOT_FOUND and the ErrorDetails.

*When an exception of type Exception is thrown, the globleExcpetionHandler() method is called, which creates an instance
of ErrorDetails containing the date, message, and description of the exception, and returns a ResponseEntity with the
HttpStatus INTERNAL_SERVER_ERROR and the ErrorDetails.

*/