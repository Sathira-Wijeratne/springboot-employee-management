package com.nqe.employee_management_application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND) // what does this line mean and do? - Sets the HTTP response status to 404 NOT FOUND when this exception is thrown
public class ResourceNotFoundException extends Exception {
    private static final long serialVersionUID = 1L; // what is this for? - This is a unique identifier for the Serializable class, used during serialization and deserialization

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
