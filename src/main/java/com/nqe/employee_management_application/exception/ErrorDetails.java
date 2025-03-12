package com.nqe.employee_management_application.exception;

import java.util.Date;

// This class is used to structure the error details sent in the response when an exception is thrown, providing a consistent format for error messages

public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;

    public ErrorDetails(Date timestamp, String message, String details) {
        super(); // why is super() called here? - It can be omitted, normally it is done to initialize something in parent class, but in that case a paramater would be passed in super()
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
