package com.nqe.employee_management_application.model;

import jakarta.validation.constraints.NotBlank; //jakarta is a set of specifications for building enterprise level java applications
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Employee")
public class Employee {
    @Transient // means the field should not be saved/persisted in the database
    public static final String SEQUENCE_NAME = "users_sequence"; //a constant the represent name of sequence used for generating unique IDs // it will be used to access the most recent employee id from the db, increment it and assign it to the next employee created

    @Id
    private long id;

    @NotBlank
    @Size(max = 100)
    @Indexed(unique = true) //creates an index on the field, ensuring that the field values are unique // annotations apply only to the variable they are directly above // some of these annotations part of Jakarta Bean Validation, some are part of Spring Data MongoDB
    private String firstName;
    private String lastName;

    @NotBlank
    @Size(max = 100)
    @Indexed(unique = true)
    private String emailId;

    // constructors
    public Employee() {
    }

    public Employee(String firstName, String lastName, String emailId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
    }

    //getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public String toString() { // is a string representation of the Employee object // useful for debugging and logging
        return "Employee[" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailId='" + emailId + '\'' +
                ']';
    }
}
